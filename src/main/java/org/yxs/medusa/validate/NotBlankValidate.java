package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.NotBlank;

/**
 * Created by 一线生 on 2016/5/24.
 *
 */
public class NotBlankValidate extends AbstractValidate<NotBlank> {

    public boolean validate(Object object) {
        if (null != object && !"".equals(object)) {
            return !"".equals(String.valueOf(object).trim());
        }
        return false;
    }

    public void init() {
        this.setMsg(annotation.value());
    }
}
