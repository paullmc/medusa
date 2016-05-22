package org.yxs.medusa.validate;

import org.yxs.medusa.Medusa;

import java.util.List;

/**
 * Created by 一线生 on 2016/5/21.
 */
public abstract class AbstractValidate <T> {

    public T t;

    public AbstractValidate(T t) {
        setType(t);
    }

    private void setType(T t) {
        this.t = t;
    }

    public abstract boolean isValidate(Object object);

    public abstract List<Medusa> result(Object object);
}
