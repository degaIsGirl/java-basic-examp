package basic.examaple.java._uitil;

import cn.hutool.core.util.StrUtil;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;

public class MyStrUtil {
    @Test
    public void testIsBlank() {
        String name = " \t ";
        boolean b = StrUtil.isBlank(name);
        System.out.println(b);

        boolean empty = StrUtil.isEmpty(name);
        System.out.println(empty);
    }

    @Test
    public void testPrefixOrSuffix() {
        String fileName = StrUtil.removeSuffix("pretty_girl.jpg", ".jpg");
        System.out.println(fileName);

        String aThis = StrUtil.removePrefix("thisis.jpg", "this");
        System.out.println(aThis);
    }

    @Test
    public void testSub() {
        String str = "abcdefgh";
        String strSub1 = StrUtil.sub(str, 2, 3); //strSub1 -> c
        String strSub2 = StrUtil.sub(str, 2, -3); //strSub2 -> cde
        String strSub3 = StrUtil.sub(str, 3, 2); //strSub2 -> c
    }

    @Test
    public void testFormat() {
        String template = "{}爱{}，就像老鼠爱大米";
        String str = StrUtil.format(template, "我", "你"); //str -> 我爱你，就像老鼠爱大米
        System.out.println(str);

        HashMap<String, String> stringStringHashMap = new HashMap<>();
        stringStringHashMap.put("name", "maweibin");
        stringStringHashMap.put("age", "10");
        String format = StrUtil.format("年纪:{age} 姓名: {name}", stringStringHashMap);
        System.out.println(format);
    }

    @Test
    public void testTrim() {
        /**
         * 除去字符串头尾部的空白，如果字符串是null，依然返回null。
         */
        String name = " maweibin ";
        System.out.println("'" + StrUtil.trim(name) + "'");


        /**
         * 除去字符串头部的空白，如果字符串是null，则返回null。
         */
        System.out.println("'" + StrUtil.trimStart(name) + "'");

        /**
         * 除去字符串尾部的空白，如果字符串是null，则返回null。
         */
        System.out.println("'" + StrUtil.trimEnd(name) + "'");
    }

    @Test
    public void testStrip() {
        String name = "num 123 num";
        String num = StrUtil.strip(name, "num");
        System.out.println("'" + num + "'");

        num = StrUtil.stripIgnoreCase(name, "Num");
        System.out.println("'" + num + "'");
    }

    @Test
    public void testStartWith() {
        /**
         * 默认是区分大小写的
         */
        String data = "this is test";
        boolean a = StrUtil.startWith(data, "thiS");
        System.out.println(a);

        /**
         * 不再区分大小写
         */
        boolean b = StrUtil.startWithIgnoreCase(data, "thiS");
        System.out.println(b);

        /**
         * 结尾-区分大小写
         */
        boolean c = StrUtil.endWith(data, "tesT");
        System.out.println(c);

        /**
         * 结尾-不区分大小写
         */
        boolean d = StrUtil.endWithIgnoreCase(data, "tesT");
        System.out.println(d);
    }

    @Test
    public void testSplit() {
        /**
         * 按照逗号进行了分割
         */
        String name = "this, is, test, ,";
        List<String> split = StrUtil.split(name, ",");
        System.out.println(split);

        /**
         * 切分字符串，去除切分后每个元素两边的空白符，去除空白项
         */
        List<String> strings = StrUtil.splitTrim(name, ",");
        System.out.println(strings);
    }
}
