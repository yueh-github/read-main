package com.readmain.admin.utils;

import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.Map;

public class CommonUtils {

    public static Map<String, Object> mapReturn(Object object) {
        Map<String, Object> result = new HashMap<>();
        result.put("result", object);
        return result;
    }

    public static String jumpForSuccess(Model model, String jumpUrl, String successMsg) {
        model.addAttribute("message", successMsg);
        model.addAttribute("jumpUrl", jumpUrl);
        return "success";
    }

    public static String jumpForFail(Model model, String jumpUrl, String failMsg) {
        model.addAttribute("message", failMsg);
        model.addAttribute("jumpUrl", jumpUrl);
        return "error";
    }

}
