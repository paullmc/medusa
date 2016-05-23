package org.yxs.medusa.validate;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.NotNull;

/**
 * Created by 一线生 on 2016/5/22.
 *
 */
public class NotNullValidate extends AbstractValidate<NotNull> {

    public boolean isValidate(Object object) {
        return null != object;
    }

    public Medusa result(Object object, Object msg) {
        boolean flag = isValidate(object);
        return new Medusa(flag, flag ? "" : String.valueOf(msg));
    }
}
