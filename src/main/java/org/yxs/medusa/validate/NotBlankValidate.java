package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.NotBlank;

/**
 * Created by medusa on 2016/5/24.
 */
public class NotBlankValidate extends AbstractValidate {

    public boolean validate(Object object) {
        if (null != object && !"".equals(object)) {
            return !"".equals(String.valueOf(object).trim());
        }
        return false;
    }

    public void init() {
        NotBlank notBlank = (NotBlank) annotation;
        this.msg = notBlank.value();
    }
}
