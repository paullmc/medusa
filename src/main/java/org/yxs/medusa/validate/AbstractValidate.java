package org.yxs.medusa.validate;

import org.yxs.medusa.Entity;
import org.yxs.medusa.Medusa;

import java.lang.annotation.Annotation;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public abstract class AbstractValidate {

    public Annotation annotation;
    public String msg;

    public abstract boolean validate(Object object);

    public abstract void init();

    public Medusa result(Entity entity) {
        annotation = entity.getAnnotation();
        init();
        boolean flag = validate(entity.getValue());
        return new Medusa(flag, entity.getField(), flag ? "" : this.msg);
    }
}
