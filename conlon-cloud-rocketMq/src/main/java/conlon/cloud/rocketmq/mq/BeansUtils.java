package conlon.cloud.rocketmq.mq;

import java.util.Map;
import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class BeansUtils implements ApplicationContextAware {

    private static ApplicationContext context;

    public static Map getBeansOfType(Class<T> tClass) {
        return context.getBeansOfType(tClass);
    }

    public static <T> T getBean(Class<T> bean) {
        return context.getBean(bean);
    }
    public static <T> T getBean(String var1, @Nullable Class<T> var2) {
        return context.getBean(var1, var2);
    }

    public static ApplicationContext getContext() {
        return context;
    }

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        BeansUtils.context = context;
    }
}