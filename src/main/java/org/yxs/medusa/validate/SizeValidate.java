package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.Size;

/**
 * Created by medusa on 2016/5/25.
 *
 */
public class SizeValidate extends AbstractValidate<Size> {

    public boolean validate(Object object) {
        return false;
    }

    public void init() {
        this.msg = annotation.value();
    }
}
