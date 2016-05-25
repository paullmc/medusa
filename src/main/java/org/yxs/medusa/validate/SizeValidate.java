package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.Size;

import java.util.List;

/**
 * Created by 一线生 on 2016/5/25.
 *
 */
public class SizeValidate extends AbstractValidate<Size> {

    public boolean validate(Object object) {
        if (null == object) return false;
        List list = (List) object;
        return list.size() >= annotation.min() && list.size() <= annotation.max();
    }

    public void init() {
        this.msg = annotation.value();
    }
}
