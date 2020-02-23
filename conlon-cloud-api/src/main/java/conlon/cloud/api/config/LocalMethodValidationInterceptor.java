package conlon.cloud.api.config;

import com.alibaba.fastjson.JSON;
import conlon.cloud.api.constant.Canstant;
import conlon.cloud.api.exception.InternalApiException;
import conlon.cloud.common.enums.ResponseCode;
import java.lang.reflect.Method;
import java.util.Set;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.BridgeMethodResolver;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ClassUtils;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;

@Component
@Aspect
@Slf4j
public class LocalMethodValidationInterceptor {


    @Pointcut("execution(public * conlon.cloud.*.controller..*.*(..))")
    public void Pointcut() {
    }

    @Autowired
    private Validator validator;

    @Before("Pointcut()")
    public void invoke(JoinPoint joinPoint) {
        String class_name = joinPoint.getTarget().getClass().getName();
        if (!StringUtils.isEmpty(class_name)&& class_name.contains(Canstant.INTERNAL_API_EXCEPTION)){

            //切点对象
            Object target = joinPoint.getTarget();
            // 切点类
            Class<?> aClass = target.getClass();
            // 方法对象
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Method method = signature.getMethod();
            // 获取所有参数
            Object[] args = joinPoint.getArgs(); // 参数值
            Class<?>[] groups = this.determineValidationGroups(method, aClass);
            ExecutableValidator execVal = this.validator.forExecutables();
            Method methodToValidate = method;

            Set result;
            try {
                result = execVal
                        .validateParameters(target, methodToValidate, args, groups);
            } catch (IllegalArgumentException var7) {
                methodToValidate = BridgeMethodResolver.findBridgedMethod(
                        ClassUtils.getMostSpecificMethod(method, aClass));
                result = execVal
                        .validateParameters(target, methodToValidate, args, groups);
            }

            if (!result.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                for (Object o : result) {
                    ConstraintViolationImpl impl = (ConstraintViolationImpl)o;
                    String messageTemplate = impl.getMessageTemplate();
                    stringBuilder.append(messageTemplate);
                }
                throw new InternalApiException(ResponseCode.PARAMETER_FAIL.getResultCode(),
                        stringBuilder.toString());
            }

        }

    }

    private boolean isFactoryBeanMetadataMethod(Method method) {
        Class<?> clazz = method.getDeclaringClass();
        if (clazz.isInterface()) {
            return (clazz == FactoryBean.class || clazz == SmartFactoryBean.class) && !method.getName()
                    .equals("getObject");
        } else {
            Class<?> factoryBeanType = null;
            if (SmartFactoryBean.class.isAssignableFrom(clazz)) {
                factoryBeanType = SmartFactoryBean.class;
            } else if (FactoryBean.class.isAssignableFrom(clazz)) {
                factoryBeanType = FactoryBean.class;
            }

            return factoryBeanType != null && !method.getName().equals("getObject") && ClassUtils
                    .hasMethod(factoryBeanType, method.getName(), method.getParameterTypes());
        }
    }

    protected Class<?>[] determineValidationGroups(Method method, Class<?> aClass) {
        Validated validatedAnn = (Validated) AnnotationUtils.findAnnotation(method, Validated.class);
        if (validatedAnn == null) {
            validatedAnn = (Validated) AnnotationUtils.findAnnotation(aClass, Validated.class);
        }

        return validatedAnn != null ? validatedAnn.value() : new Class[0];
    }

}