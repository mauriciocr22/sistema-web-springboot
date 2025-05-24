package com.sistemaweb.sistemaweb.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ClimaController {
    
    @GetMapping("/")
    public String home() {
        return "home";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/buscar")
    public String resultadoClima() {
        return "resultado";
    }

    @GetMapping("/historico")
    public String historico() {
        return "historico";
    }
}
