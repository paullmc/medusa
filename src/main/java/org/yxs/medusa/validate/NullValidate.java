package org.yxs.medusa.validate;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.Null;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class NullValidate extends AbstractValidate<Null> {

    public boolean isValidate(Object object) {
        return null != object;
    }

    public Medusa result(Object object, Object msg) {
        boolean flag = isValidate(object);
        return new Medusa(flag, flag ? "" : String.valueOf(msg));
    }
}
