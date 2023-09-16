package com.ULPalumnos.ULPAlumnos.SPRING;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ULPController {
    @RequestMapping("/")
    public String inicio(){
        return "inicio";
    }
    @RequestMapping("/login")
    public String login(){
        return "login";
    }
    @RequestMapping("/admin")
    public String admin(){
        return "admin";
    }

    @RequestMapping("/alumno$")
    public String alumno(){
        return "alumno";
    }

}
