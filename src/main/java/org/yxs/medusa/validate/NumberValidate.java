package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.Number;

/**
 * Created by medusa on 2016/5/24.
 *
 */
public class NumberValidate extends AbstractValidate<Number> {
    public boolean validate(Object object) {
        try {
            String arg = String.valueOf(object);
            Integer.parseInt(arg);
        } catch (Exception e) {
            return  false;
        }
        return true;
    }

    public void init() {
        this.msg = annotation.value();
    }
}
