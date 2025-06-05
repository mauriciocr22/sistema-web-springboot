package com.sistemaweb.sistemaweb.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sistemaweb.sistemaweb.dto.ClimaDTO;
import com.sistemaweb.sistemaweb.model.BuscaClima;
import com.sistemaweb.sistemaweb.service.ClimaService;

@Controller
public class ClimaController {

    @Autowired
    private ClimaService climaService;
    
    @GetMapping("/")
    public String home(Model model) {
        List<BuscaClima> buscas = climaService.listarTodas();
        model.addAttribute("buscas", buscas);
        return "home";
    }
    
    @GetMapping("/login")
    public String login() {
        return "login";
    }

@PostMapping("/buscar")
public String buscarClima(@RequestParam String cidade, Model model) {
    ClimaDTO clima = climaService.buscarClimaPorCidade(cidade);

    BuscaClima busca = new BuscaClima();
    busca.setCidade(clima.getCidade());
    busca.setTemperatura(clima.getTemperatura());
    busca.setDescricao(clima.getDescricao());
    busca.setDataConsulta(LocalDateTime.now());
    // climaService.salvar(busca);

    model.addAttribute("clima", clima);
    return "resultado";
}

    @GetMapping("/historico")
    public String historico(Model model) {
        List<BuscaClima> buscas = climaService.listarTodas();
        model.addAttribute("buscas", buscas);
        return "historico";
    }
}
