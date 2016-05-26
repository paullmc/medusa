package org.yxs;

import org.yxs.medusa.Medusa;
import org.yxs.medusa.annotation.*;
import org.yxs.medusa.annotation.Number;
import org.yxs.medusa.validate.Validator;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Hello world!
 *
 */
public class Example
{
    public static class User {

        @NotNull
        private String uname;

        @Null
        private String passwd;

        @Email
        private String email;

        @NotEmpty
        private String empty;

        @NotBlank
        private String blank;

        @Number
        private String number;

        @Pattern(pattern = "^1")
        private String pattern;

        @Length(max = 10, min = 2)
        private String length;

        @Size(max = 10, min = 2)
        private List<String> list;

        public void setUname(String uname) {
            this.uname = uname;
        }

        public void setPasswd(String passwd) {
            this.passwd = passwd;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public void setEmpty(String empty) {
            this.empty = empty;
        }

        public void setBlank(String blank) {
            this.blank = blank;
        }

        public void setNumber(String number) {
            this.number = number;
        }

        public void setPattern(String pattern) {
            this.pattern = pattern;
        }

        public void setLength(String length) {
            this.length = length;
        }

        public void setList(List<String> list) {
            this.list = list;
        }
    }
    public static void main( String[] args ) {
        User user = new User();
        user.setPasswd("1ffaa");
        user.setUname("gfaa");
        user.setEmail("97545465@163.com");
        user.setEmpty("");
        user.setBlank("");
        user.setNumber("1");
        user.setPattern("12");
        user.setLength("a");

        Validator validator = Validator.newInstance(user);

        long start = new Date().getTime();
        Set<Medusa> set = validator.result();
        for (Medusa medusa : set) {
            System.out.println(medusa.toString());
        }
        System.out.println("消耗 " + (new Date().getTime() - start) + " MS");
    }
}
