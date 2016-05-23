package org.yxs.medusa.validate;

import org.yxs.medusa.Medusa;

/**
 * Created by 一线生 on 2016/5/21.
 */
public abstract class AbstractValidate {

    public abstract boolean isValidate(Object object);

    public Medusa result(Object object, Object msg) {
        boolean flag = isValidate(object);
        return new Medusa(flag, flag ? "" : String.valueOf(msg));
    }
}
