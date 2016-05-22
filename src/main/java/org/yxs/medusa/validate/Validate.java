package org.yxs.medusa.validate;

import org.yxs.medusa.Medusa;

import java.util.List;

/**
 * Created by 一线生 on 2016/5/21.
 */
public class Validate<T> extends AbstractValidate<T> {

    public Validate(T t) {
        super(t);
    }

    public boolean isValidate(Object object) {
        return false;
    }

    public List<Medusa> result(Object object) {
        return null;
    }
}
