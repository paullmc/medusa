package org.yxs.medusa.validate;

import org.yxs.medusa.Entity;
import org.yxs.medusa.Medusa;
import org.yxs.medusa.exception.MedusaException;
import org.yxs.medusa.helper.AnnotationHelper;
import org.yxs.medusa.reflect.ReflectUtils;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by 一线生 on 2016/5/21.
 *
 */
public class Validator {

    private Object object;

    public static Validator newInstance() {
        return new Validator();
    }

    public static Validator newInstance(Object object) {
        return new Validator(object);
    }

    public Validator() {}

    public Validator(Object object) {
        this.object = object;
    }

    public boolean validate(Object object) {
        Set<Medusa> medusaSet = result(object);
        for (Medusa medusa : medusaSet) {
            if (!medusa.isFlag()) return false;
        }
        return true;
    }

    public Medusa pop(Object object) {
        Set<Medusa> medusaSet = result(object);
        return medusaSet.iterator().next();
    }

    public Medusa popDeny(Object object) {
        Set<Medusa> medusaSet = result(object);
        for (Medusa medusa : medusaSet) {
            if (!medusa.isFlag()) return medusa;
        }
        return null;
    }

    public Set<Medusa> result() {
        return result(this.object);
    }

    public Set<Medusa> result(Object object) {
        try {
            Set<Entity> entitySet = ReflectUtils.getSet(object);
            Set<Medusa> medusaSet = new HashSet<Medusa>();
            for (Entity entity : entitySet) {
                Annotation annotation = entity.getAnnotation();
                Class<? extends Annotation> clazz = AnnotationHelper.choice(annotation);
                Object[] params = {entity};
                Object result = ReflectUtils.invokeMethod(String.valueOf(clazz.getMethod("clazz").getDefaultValue()), "result", params);
                medusaSet.add((Medusa) result);
            }
            return medusaSet;

        } catch (Exception e) {
            throw new MedusaException(e);
        }
    }
}
