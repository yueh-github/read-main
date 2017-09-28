package com.readmain.task.test;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * Created by yuehao on 2017/9/18.
 */
public class TestBase64 {


    public static void main(String[] args) {
        String string = "我是岳浩";

        final String encoded = Base64.getEncoder().encodeToString(string.getBytes(StandardCharsets.UTF_8));
        System.out.println(encoded);

        final String decoded = new String(Base64.getDecoder().decode(encoded), StandardCharsets.UTF_8);
        System.out.println(decoded);
    }
}
