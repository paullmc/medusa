package org.yxs.medusa.validate;

import java.lang.annotation.Annotation;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class NullValidate extends AbstractValidate {

    private Annotation annotation;

    public boolean isValidate(Object object) {
        return null == object;
    }
}