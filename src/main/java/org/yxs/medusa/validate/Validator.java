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

    private Validator() {}

    private Validator(Object object) {
        this.object = object;
    }

    /**
     * author: 一线生
     * explain: 校验方法，该方法校验整个对象的所有注解，然后返回true/false
     * @param object 待校验的对象
     * date 2016/5/25 - 11:52
     **/
    public boolean validate(Object object) {
        Set<Medusa> medusaSet = result(object);
        for (Medusa medusa : medusaSet) {
            if (!medusa.isFlag()) return false;
        }
        return true;
    }

    /**
     * author: 一线生
     * explain: 校验方法，该方法校验整个对象的所有注解，然后返回true/false
     *      该方法无参，使用{@link Validator#newInstance(Object)} 方法，可以直接调用此方法得到校验结果
     * date 2016/5/25 - 11:52
     **/
    public boolean validate() {
        return validate(this.object);
    }

    /**
     * author: 一线生
     * explain: 弹出第一个校验结果
     *     该方法无参，使用{@link Validator#newInstance(Object)} 方法，可以直接调用此方法得到校验结果
     * date 2016/5/25 - 11:53
     **/
    public Medusa pop() {
        return pop(this.object);
    }

    /**
     * author: 一线生
     * explain: 弹出第一个校验结果
     * @param object 待校验的对象
     * date 2016/5/25 - 11:53
     **/
    public Medusa pop(Object object) {
        Set<Medusa> medusaSet = result(object);
        return medusaSet.iterator().next();
    }

    /**
     * author: 一线生
     * explain: 弹出第一个错误的校验结果
     * @param object 待校验的对象
     * date 2016/5/25 - 11:53
     **/
    public Medusa popDeny(Object object) {
        Set<Medusa> medusaSet = result(object);
        for (Medusa medusa : medusaSet) {
            if (!medusa.isFlag()) return medusa;
        }
        return null;
    }

    /**
     * author: 一线生
     * explain: 获取校验返回值 该方法校验所有的注解，并返回校验结果Set<Medusa>
     *     该方法无参，使用{@link Validator#newInstance(Object)} 方法，可以直接调用此方法得到校验结果
     * date 2016/5/25 - 11:54
     **/
    public Set<Medusa> result() {
        return result(this.object);
    }

    /**
     * author: 一线生
     * explain: 获取校验返回值 该方法校验所有的注解，并返回校验结果Set<Medusa>
     *     该方法需要校验对象作为参数，使用{@link Validator#newInstance()} 方法，可以直接调用此方法传入校验对象得到校验结果
     * @param object 待校验的对象
     * date 2016/5/25 - 11:54
     **/
    public Set<Medusa> result(Object object) {
        try {
            //获取待校验对象的注解和Field，Set集合
            Set<Entity> entitySet = ReflectUtils.getSet(object);
            //构建一个空的HashSet，用于存放校验返回结果
            Set<Medusa> medusaSet = new HashSet<Medusa>();
            //循环校验所有field
            for (Entity entity : entitySet) {
                //获取注解
                Annotation annotation = entity.getAnnotation();
                //获取注解的class
                Class<? extends Annotation> clazz = AnnotationHelper.choice(annotation);
                //执行注解clazz方法指向的class.result方法
                Object[] params = {entity};
                Object result = ReflectUtils.invokeMethod((Class<?>) clazz.getMethod("clazz").getDefaultValue(), "result", params);
                //将校验结果放入set
                medusaSet.add((Medusa) result);
            }
            return medusaSet;

        } catch (Exception e) {
            throw new MedusaException(e);
        }
    }
}
