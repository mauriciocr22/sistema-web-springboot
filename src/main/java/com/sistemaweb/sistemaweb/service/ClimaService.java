package com.sistemaweb.sistemaweb.service;


import com.sistemaweb.sistemaweb.dto.ClimaDTO;
import com.sistemaweb.sistemaweb.model.BuscaClima;
import com.sistemaweb.sistemaweb.repository.BuscaClimaRepository;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClimaService {

    private final BuscaClimaRepository buscaClimaRepository;

    public ClimaService(BuscaClimaRepository buscaClimaRepository) {
        this.buscaClimaRepository = buscaClimaRepository;
    }

    @Value("${openweather.api.key}")
    private String apiKey;

    @Value("${openweather.api.url}")
    private String apiUrl;

    public ClimaDTO buscarClimaPorCidade(String cidade) {
        RestTemplate restTemplate = new RestTemplate();
        String url = apiUrl + "?q=" + cidade + "&appid=" + apiKey + "&units=metric&lang=pt_br";

        String response = restTemplate.getForObject(url, String.class);
        JSONObject json = new JSONObject(response);

        ClimaDTO dto = new ClimaDTO();
        dto.setCidade(json.getString("name"));
        dto.setTemperatura(json.getJSONObject("main").getDouble("temp"));
        dto.setDescricao(json.getJSONArray("weather").getJSONObject(0).getString("description"));

        BuscaClima busca = new BuscaClima();
        busca.setCidade(dto.getCidade());
        busca.setTemperatura(dto.getTemperatura());
        busca.setDescricao(dto.getDescricao());
        busca.setDataConsulta(LocalDateTime.now());

        buscaClimaRepository.save(busca);

        return dto;
    }

    public List<BuscaClima> listarTodas() {
        return buscaClimaRepository.findAll();
    }

    // public void salvar(BuscaClima busca) {
    //     buscaClimaRepository.save(busca);
    // }
}