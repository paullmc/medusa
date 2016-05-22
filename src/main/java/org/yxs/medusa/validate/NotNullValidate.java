package org.yxs.medusa.validate;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 一线生 on 2016/5/22.
 *
 */
public class NotNullValidate extends AbstractValidate<NotNull> {

    public NotNullValidate(NotNull notNull) {
        super(notNull);
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
