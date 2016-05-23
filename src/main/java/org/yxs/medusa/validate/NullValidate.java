package org.yxs.medusa.validate;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class NullValidate extends AbstractValidate {

    public boolean isValidate(Object object) {
        return null != object;
    }
}
