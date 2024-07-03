package com.bff.reservation.mobile.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/mobile-api")
public class ControllerTest {
    @GetMapping("/kampus")
    @ResponseBody
    public Object getKampus() {
        Map<String,Object> obj = new HashMap<>();
        obj.put("fakultas", "aaa");
        obj.put("universitas", "aaaa");
        return obj;
    }
}
