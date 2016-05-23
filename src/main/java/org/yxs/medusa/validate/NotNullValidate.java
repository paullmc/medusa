package org.yxs.medusa.validate;

/**
 * Created by 一线生 on 2016/5/22.
 *
 */
public class NotNullValidate extends AbstractValidate {

    public boolean isValidate(Object object) {
        return null != object;
    }
}
