package org.yxs;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.NotNull;
import org.yxs.medusa.annotation.Null;
import org.yxs.medusa.validate.Validator;

import java.util.Date;
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

        @Null
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
    public static void main( String[] args ) {
        User user = new User();
        user.setPasswd("asdasd");
        user.setUname("asd");
        System.out.println();
        Validator validator = new Validator();
        long start = new Date().getTime();
        Set<Medusa> set = validator.result(user);
        for (Medusa medusa : set) {
            System.out.println(medusa.toString());
        }
        System.out.println(new Date().getTime() - start);
    }
}
