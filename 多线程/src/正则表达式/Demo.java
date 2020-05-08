package 正则表达式;

import org.junit.Test;

public class Demo {

    @Test
    public void test01() {
        // 匹配十一位数字
        String regex = "\\d{11}";
        String str = "12345678901";
        System.out.println(str.matches(regex));
    }

    @Test
    public void test02() {
        // 匹配以'hello'开头,结尾
        String regex = "hello";
        String str = "aaahelloooo";
        System.out.println(str.matches(regex));
    }

    @Test
    public void test03() {
        // 匹配[abcdefgh]中任意一个字符,后缀为ello
        String regex = "[abcdefgh]ello";
        String str = "aello";
        System.out.println(str.matches(regex));
    }

    @Test
    public void test04() {
        // 匹配[a-zA-Z0-9]中任意一个字符,后缀为ello
        String regex = "[a-zA-Z0-9]ello";
        String str = "2ello";
        String str2 = "aello";
        String str3 = "Bello";
        System.out.println(str3.matches(regex));
    }

    @Test
    public void test05() {
        // 匹配多个[a-zA-Z0-9]+
        String regex = "[a-zA-Z0-9]+";
        String str = "2ello";
        String str2 = "aello";
        String str3 = "Bello";
        System.out.println(str3.matches(regex));
    }

    @Test
    public void test06() {
        // 匹配0到多个[a-zA-Z0-9]*
        String regex = "[a-zA-Z0-9]*";
        String str = "";
        System.out.println(str.matches(regex));     // true
        str = "aelo";
        System.out.println(str.matches(regex));     // true
        str = "Bello";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test07() {
        // 匹配0到1个[a-zA-Z0-9]?
        String regex = "[a-zA-Z0-9]?";
        String str = "";
        System.out.println(str.matches(regex));     // true
        str = "aelo";
        System.out.println(str.matches(regex));     // false
        str = "Bello";
        System.out.println(str.matches(regex));     // false
    }

    @Test
    public void test08() {
        // 匹配3个[a-zA-Z0-9]{3}
        String regex = "[a-zA-Z0-9]{3}";
        String str = "hello";
        System.out.println(str.matches(regex));     // false
        str = "ael@#$";
        System.out.println(str.matches(regex));     // false
        str = "asd";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test09() {
        // 匹配3个以上[a-zA-Z0-9]{3}
        String regex = "[a-zA-Z0-9]{3,}";
        String str = "hello";
        System.out.println(str.matches(regex));     // true
        str = "ael@#$";
        System.out.println(str.matches(regex));     // false
        str = "asd";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test10() {
        // 匹配3到5个[a-zA-Z0-9]{3,5}
        String regex = "[a-zA-Z0-9]{3,5}";
        String str = "hello";
        System.out.println(str.matches(regex));     // true
        str = "aelertt";
        System.out.println(str.matches(regex));     // false
        str = "asd";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test11() {
        // 除0-9外的字符匹配3个[^0-9]{3}
        String regex = "[^0-9]{3}";
        String str = "hel";
        System.out.println(str.matches(regex));     // true
        str = "ael152";
        System.out.println(str.matches(regex));     // false
        str = "123";
        System.out.println(str.matches(regex));     // false
        str = "@#$";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test12() {
        // \d 匹配0-9的字符3个\d{3}
        // \D 不匹配数字
        String regex = "\\d{3}";
        String str = "hel";
        System.out.println(str.matches(regex));     // false
        str = "152";
        System.out.println(str.matches(regex));     // true

        regex = "\\D{3}";
        str = "123";
        System.out.println(str.matches(regex));     // false
        str = "@#$";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test13() {
        // \w 匹配所有字符[a-zA-Z0-9]
        // \W 不匹配[a-zA-Z0-9]
        String regex = "\\w{3}";
        String str = "hel";
        System.out.println(str.matches(regex));     // true
        str = "15B";
        System.out.println(str.matches(regex));     // true

        regex = "\\W{3}";
        str = "123";
        System.out.println(str.matches(regex));     // false
        str = "@#$";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test14() {
        // \s 匹配所有空白字符,包括空格,制表等
        // \S 不匹配上述
        String regex = "\\s{3}";
        String str = "   ";
        System.out.println(str.matches(regex));     // true
        str = "         ";
        System.out.println(str.matches(regex));     // false

        regex = "\\S{3}";
        str = "123";
        System.out.println(str.matches(regex));     // true
        str = "@#$";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test15() {
        // 匹配任意字符.{3}
        String regex = ".{0,3}";
        String str = " #$";
        System.out.println(str.matches(regex));     // true
        str = "hello";
        System.out.println(str.matches(regex));     // false

    }

    @Test
    public void test16() {
        // 匹配是否包含hello .{0,}hello.{0,}
        String regex = ".*hello.*";
        String str = "@#$hello*()";
        System.out.println(str.matches(regex));     // true
        str = "hello ";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test17() {
        // 转义符
        // 匹配"\\" -> "\\\\"
        String regex = "\\\\";
        String str = "\\";
        System.out.println(str.matches(regex));     // true
        regex = "/";
        str = "/";
        System.out.println(str.matches(regex));     // true
        regex = "\\[\\]";
        str = "[]";
        System.out.println(str.matches(regex));     // true
        regex = "\\.";
        str = ".";
        System.out.println(str.matches(regex));     // true
        // ......
    }

    @Test
    public void test18() {
        // 匹配电话号码格式,分组
        // 第一组 (\(0\d{2}\)|0\d{2}) 前缀(0xx)或者0xx
        // 第二组 ( |\-) 连接符为空格或者 - 或者 *
        // 后缀匹配7到8位数字
        String regex = "(\\(0\\d{2}\\)|0\\d{2})( |\\-|\\*)\\d{7,8}";
        String str = "029 1234567";
        System.out.println(str.matches(regex));     // true
        str = "029-1234567";
        System.out.println(str.matches(regex));     // true
        str = "029*1234567";
        System.out.println(str.matches(regex));     // true
        str = "(029) 1234567";
        System.out.println(str.matches(regex));     // true
        str = "(029)-1234567";
        System.out.println(str.matches(regex));     // true
        str = "(029)*12345678";
        System.out.println(str.matches(regex));     // true
    }

    @Test
    public void test19() {
        // 匹配邮件格式: [a-zA-Z0-9]@[a-z0-9].com
        String regex = "\\w*@[a-z0-9]*\\.com";
        String str = "min356haha@fox163qqmail.com";
        System.out.println(str.matches(regex));     // true
    }
}
