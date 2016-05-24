package org.yxs.medusa.validate;

import org.yxs.medusa.annotation.Pattern;

/**
 * Created by medusa on 2016/5/24.
 */
public class PatternValidate extends AbstractValidate {

    private Pattern pattern;

    public boolean validate(Object object) {
        if (null == object) return false;
        java.util.regex.Pattern reg = java.util.regex.Pattern.compile(pattern.pattern());
        return reg.matcher(String.valueOf(object)).matches();
    }

    public void init() {
        pattern = (Pattern) annotation;
        this.msg = pattern.value();
    }
}
