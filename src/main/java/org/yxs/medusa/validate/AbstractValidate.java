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

    public abstract boolean isValidate(Object object);

    public Medusa result(Entity entity, String msg) {
        boolean flag = isValidate(entity.getValue());
        return new Medusa(flag, entity.getField(), flag ? "" : msg);
    }
}
