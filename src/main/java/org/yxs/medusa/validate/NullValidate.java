package org.yxs.medusa.validate;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.Null;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class NullValidate extends AbstractValidate<Null> {

    public NullValidate(Null aNull) {
        super(aNull);
    }

    public boolean isValidate(Object object) {
        return null != object;
    }

    public List<Medusa> result(Object object) {
        List<Medusa> medusas = new ArrayList<Medusa>();
        boolean flag = isValidate(object);
        medusas.add(new Medusa(flag, flag ? "" : this.t.value()));
        return medusas;
    }
}
