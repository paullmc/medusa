package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.NotEmpty;

/**
 * Created by medusa on 2016/5/24.
 *
 */
public class NotEmptyValidate extends AbstractValidate<NotEmpty> {

    public boolean validate(Object object) {
        return null != object && !"".equals(object);
    }

    public void init() {
        this.msg = annotation.value();
    }
}
