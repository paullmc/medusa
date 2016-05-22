package org.yxs;

import org.yxs.medusa.Entity;
import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.NotNull;
import org.yxs.medusa.reflect.ReflectUtils;
import org.yxs.medusa.validate.AbstractValidate;
import org.yxs.medusa.validate.NotNullValidate;

import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class App 
{
    public static class User {

        @NotNull
        private String uname;
        private String passwd;

        public String getUname() {
            return uname;
        }

        public void setUname(String uname) {
            this.uname = uname;
        }

        public String getPasswd() {
            return passwd;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }
    }
    public static void main( String[] args ) throws IllegalAccessException {
        User user = new User();
        Set<Entity> set = ReflectUtils.getSet(user);
        for (Entity entity : set) {
            AbstractValidate abstractValidate = new NotNullValidate((NotNull) entity.getAnnotation());
            List<Medusa> medusas = abstractValidate.result(entity.getValue());
            System.out.println(medusas.get(0).toString());
        }
    }
}
