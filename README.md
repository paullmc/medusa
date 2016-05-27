> 参数校验是一个健壮的程序所必要的，那么如何将参数校验做到简单，直观。
> 
> **假设有一个 User 对象，我们需要对 用户名密码 进行非null校验**


```
public class User {

        private String uname;

        private String passwd;
    }
```


----------


> 普通的校验逻辑编写

```
if (null != user.getUname() && null != user.getPasswd()) 
	return true;
return false;
```
*这段代码确实可以正常运行，并且符合我们的需求，但是这段代码读起来却不是那么美观，于是我做们做出下面的修改*

```
return (null != user.getUname() && null != user.getPasswd());
```
*修改之后的代码虽然更加简短，但同样不怎么美观*


----------

*最后我们将代码改成下面这种形式，只需要在校验的属性上加上注解，最后一个Validator就可以实现我们想要的校验逻辑，既美观，又直观*
```
public class User {

		@NotNull
        private String uname;

		@NotNull
        private String passwd;
    }
    

	public static void main( String[] args ) {
        User user = new User();
        Validator validator = Validator.newInstance(user);
		validator.validate();
    }
```
![示例1](http://img.blog.csdn.net/20160527153736404)

----------
**接下来说说如何实现这样的校验框架**
> 我的实现逻辑是这样的

 1. 提供一个抽象类，包含抽象方法validate、init，用于编写校验逻辑和初始化操作
 
 

```
public abstract class AbstractValidate<T> {
    //注解
    public T annotation;
    //错误提示
    private String msg;

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
     * explain: 设置默认校验不通过提示信息
     * @param annotation 校验注解
     * @throws  MedusaException 当注解没有 value方法时，抛出异常
     * date 2016/5/26 - 10:44
     **/
    private void enabledDefaultMsg(Annotation annotation) {
        Class<? extends Annotation> clazz = AnnotationHelper.choice(annotation);
        try {
            setMsg(String.valueOf(clazz.getMethod("value").getDefaultValue()));
        } catch (NoSuchMethodException e) {
            throw new MedusaException(e);
        }
    }

    /**
     * author: 一线生
     * explain: 设置校验不通过提示信息
     * @param msg 提示内容
     * date 2016/5/26 - 10:26
     **/
    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * author: 一线生
     * explain: 获取校验返回值 {@link Medusa}
     * @param entity {@link Entity} 该参数包含属性的注解，属性Field对象，以及属性的值
     * date 2016/5/25 - 11:50
     **/
    public Medusa result(Entity entity) {
        //设置annotation对象为属性的校验注解
        annotation = (T) entity.getAnnotation();
        //设置默认提示信息
        enabledDefaultMsg(entity.getAnnotation());
        //执行初始化方法
        init();
        //执行校验逻辑
        boolean flag = validate(entity.getValue());
        //返回校验结果
        return new Medusa(flag, entity.getField(), flag ? Message.ALLOW : this.msg);
    }
}
```

 

2 -  创建基本校验的注解 如 @NotNull、@Null、@NotEmpty 等


这些注解都包含两个默认值 
 
  `Class<?> clazz() default NotNullValidate.class;
     String value() default Message.NOT_NULL;`
    clazz 为该注解校验逻辑实现的类
    value为默认错误提示

```
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface NotNull {
    Class<?> clazz() default NotNullValidate.class;
    String value() default Message.NOT_NULL;
}
```
    
3 - Validator 校验时，通过反射找到注解clazz默认的java类，执行该类的validate方法，
	结果正确则标记为true，错误则标记为false并将默认错误提示放入校验结果集
 
提供`result(Object object)` 方法提供返回校验结果集


```
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
     * explain: 弹出第一个错误的校验结果
     *      该方法无参，使用{@link Validator#newInstance(Object)} 方法，可以直接调用此方法得到校验结果
     * date 2016/5/25 - 11:53
     **/
    public Medusa popDeny() {
        return popDeny(this.object);
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
```

4 - 所有的校验类都需要继承抽象类AbstractValidate，并编写validate逻辑，以及初始化操作

```
public class NotNullValidate extends AbstractValidate<NotNull> {

    public boolean validate(Object object) {
        return null != object;
    }

    public void init() {
        this.setMsg(annotation.value());
    }
}

```

5 - 可扩展性，虽然实现了基本的校验注解，但是正常开发中可能会需要自定义的业务注解，为了可以编写自己的业务注解， 只需要创建自己的注解，将clazz指向自己的校验类，并将校验类继承抽象类 AbstractValidate，编写校验逻辑，这里提供了一个AnnotationHelper类，该类会加载指定包下的注解，这个地方可以做成可配置的，实现了可以允许开发者自定义注解。

```
public class AnnotationHelper {
    //BASE_PACKAGE 包下的所有class集合
    private static Set<Class<?>> ANNOTATION_SET;
    //初始化要加载的注解包路径
    private final static String BASE_PACKAGE = "org.yxs.medusa.annotation";
    //注解Class 对应 Annotation的map集合
    private static Map<Annotation, Class<? extends Annotation>> ANNOTATION_CLASS_SET = new HashMap<Annotation, Class<? extends Annotation>>();

    static {
        loadAnnotation(BASE_PACKAGE);
    }

    /**
     * author: 一线生
     * explain: 初始化加载所有基础校验注解class
     * @param packageName 要加载的包路径
     * date 2016/5/25 - 11:45
     **/
    private static void loadAnnotation(String packageName) {
        ANNOTATION_SET = ClassUtil.getClassSet(packageName);
    }

    /**
     * author: 一线生
     * explain: 选择属性的校验类型（在基础包下的所有注解）注解
     * @param field 对象属性
     * date 2016/5/25 - 11:46
     **/
    public static Annotation choice(Field field) {
        for (Class<?> clazz : ANNOTATION_SET) {
            Class<? extends Annotation> castClazz = (Class<? extends Annotation>) clazz;
            if (field.isAnnotationPresent(castClazz)) {
                Annotation annotation = field.getAnnotation(castClazz);
                ANNOTATION_CLASS_SET.put(annotation, castClazz);
                return annotation;
            }
        }
        return null;
    }

    /**
     * author: 一线生
     * explain: 根据注解Annotation获取该注解的class 用于反射执行方法
     * @param annotation 注解
     * date 2016/5/25 - 11:47
     **/
    public static Class<? extends Annotation> choice(Annotation annotation) {
        return ANNOTATION_CLASS_SET.get(annotation);
    }
}
```


----------
以上只是部分代码，个人文笔太差，表达不能，源码奉上，有兴趣的可以看看。

[请用力戳我](https://github.com/smallcham/medusa.git)  https://github.com/smallcham/medusa.git

> 最后奉上目录图
> 
> ![目录](http://img.blog.csdn.net/20160527161618622)
>  
>  以及与hibernate-validtor框架的速度对比
>  
>  ![对比](http://img.blog.csdn.net/20160527162607688)
>  
>  同样的校验下要比hibernate-validator快一半

