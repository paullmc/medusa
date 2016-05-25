package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.Pattern;

/**
 * Created by 一线生 on 2016/5/24.
 *
 */
public class PatternValidate extends AbstractValidate<Pattern> {

    public boolean validate(Object object) {
        if (null == object) return false;
        java.util.regex.Pattern reg = java.util.regex.Pattern.compile(annotation.pattern());
        return reg.matcher(String.valueOf(object)).matches();
    }

    public void init() {
        this.msg = annotation.value();
    }
}
