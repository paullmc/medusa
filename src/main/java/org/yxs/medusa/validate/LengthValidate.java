package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.Length;

/**
 * Created by 一线生 on 2016/5/25.
 *
 */
public class LengthValidate extends AbstractValidate<Length> {

    public boolean validate(Object object) {
        if (null == object) return false;
        String arg = String.valueOf(object);
        return arg.length() >= annotation.min() && arg.length() <= annotation.max();
    }

    public void init() {
        this.setMsg(annotation.value());
    }
}
