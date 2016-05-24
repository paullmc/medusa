package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.Null;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class NullValidate extends AbstractValidate {

    public boolean validate(Object object) {
        return null == object;
    }

    public void init() {
        Null nullAnnotation = (Null) annotation;
        this.msg = nullAnnotation.value();
    }
}