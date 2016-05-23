package org.yxs.medusa.validate;

import org.yxs.medusa.Entity;
import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.NotNull;
import org.yxs.medusa.exception.MedusaException;
import org.yxs.medusa.reflect.ReflectUtils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class Validate {

    public boolean isValidate(Object object) {
        Set<Medusa> medusas = result(object);
        for (Medusa medusa : medusas) {
            if (!medusa.isFlag()) return false;
        }
        return true;
    }

    public Medusa pop(Object object) {
        Set<Medusa> medusas = result(object);
        return medusas.iterator().next();
    }

    public Medusa popFail(Object object) {
        Set<Medusa> medusas = result(object);
        for (Medusa medusa : medusas) {
            if (!medusa.isFlag()) return medusa;
        }
        return null;
    }

    public Set<Medusa> result(Object object) {
        try {
            Set<Entity> entitySet = ReflectUtils.getSet(object);
            Set<Medusa> medusaSet = new HashSet<Medusa>();
            for (Entity entity : entitySet) {
                Class<? extends Annotation> clazz = entity.getAnnotation();
                Object className = clazz.getMethod("clazz").getDefaultValue();
                Object[] params = new Object[2];
                params[0] = entity.getValue();
                params[1] = entity.getFieldName() + ":" + clazz.getMethod("value").getDefaultValue();
                Object result = ReflectUtils.invokeMethod(String.valueOf(className), "result", params);
                medusaSet.add((Medusa) result);
            }
            return medusaSet;

        } catch (IllegalAccessException e) {
            throw new MedusaException(e);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
        return null;
    }
}
