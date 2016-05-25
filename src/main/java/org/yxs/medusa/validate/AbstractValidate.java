package org.yxs.medusa.validate;

import org.yxs.medusa.Entity;
import org.yxs.medusa.Medusa;

/**
 * Created by 一线生 on 2016/5/21.
 * 注解校验方法抽象类，所有的注解方法都需要继承该抽象类，编写init方法和validate逻辑
 */
public abstract class AbstractValidate<T> {
    //注解
    public T annotation;
    //错误提示
    public String msg;

    /**
     * author: 一线生
     * explain: 校验逻辑方法
     * @param object 待校验的参数属性
     * date 2016/5/25 - 11:49
     **/
    public abstract boolean validate(Object object);

    /**
     * author: 一线生
     * explain: 初始化方法，所有的校验子类在继承该抽象类后需要编写init方法设置错误提示消息或者其他自定义操作
     * date 2016/5/25 - 11:49
     **/
    public abstract void init();

    /**
     * author: 一线生
     * explain: 获取校验返回值 {@link Medusa}
     * @param entity {@link Entity} 该参数包含属性的注解，属性Field对象，以及属性的值
     * date 2016/5/25 - 11:50
     **/
    public Medusa result(Entity entity) {
        //设置annotation对象为属性的校验注解
        annotation = (T) entity.getAnnotation();
        //执行初始化方法
        init();
        //执行校验逻辑
        boolean flag = validate(entity.getValue());
        //返回校验结果
        return new Medusa(flag, entity.getField(), flag ? "" : this.msg);
    }
}
