package com.bff.reservation.web.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/web-api")
public class ControllerTest2 {
    @GetMapping("/test")
    @ResponseBody
    public Object getKampus() {
        Map<String,Object> obj = new HashMap<>();
        obj.put("fakultas", "aaa");
        obj.put("universitas", "aaaa");
        return obj;
    }
}
