package conlon.cloud.common.filter;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import java.time.LocalDateTime;
import java.util.Objects;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.SystemMetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

/**
 * @program: sdongpos DefaultColumnHandler.java
 * @description: 默认字段填充器
 * @author: Mr zonelen
 * @create: 2019-08-10 11:31
 **/
@Component
@Slf4j
public class DefaultColumnFilter implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
//        SecurityUtils.getSubject().getPrincipal();
        log.debug("start insert fill ....");
        //避免使用metaObject.setValue()
        this.setFieldValByName("createdTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("createdUserId", 0L, metaObject);
        this.setFieldValByName("createdUserName", "admin", metaObject);
        this.setFieldValByName("modifiedTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("modifiedUserId", 0L, metaObject);
        this.setFieldValByName("modifiedUserName", "admin", metaObject);
        this.setFieldValByName("isDeleted", 0, metaObject);
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.debug("start update fill ....");
        this.setFieldValByName("modifiedTime", LocalDateTime.now(), metaObject);
        this.setFieldValByName("modifiedUserId", 0L, metaObject);
        this.setFieldValByName("modifiedUserName", "admin", metaObject);
    }

    @Override
    public MetaObjectHandler setFieldValByName(String fieldName, Object fieldVal, MetaObject metaObject) {
        if (Objects.nonNull(fieldVal)) {
            if (metaObject.hasSetter(fieldName) && metaObject.hasGetter(fieldName)) {
                Object obj = metaObject.getValue(fieldName);
                log.debug("start setFieldValByName fill ....fieldName:" + fieldName + "  设置值fieldVal:" + fieldVal
                        + "已设置的对象value:" + obj);
                if (null == obj) {
                    metaObject.setValue(fieldName, fieldVal);
                } else {
                    metaObject.setValue(fieldName, obj);
                }
            } else if (metaObject.hasGetter("et")) {
                Object et = metaObject.getValue("et");
                if (et != null) {
                    MetaObject etMeta = SystemMetaObject.forObject(et);
                    if (etMeta.hasSetter(fieldName)) {
                        etMeta.setValue(fieldName, fieldVal);
                    }
                }
            }
        }

        return this;
    }
}
