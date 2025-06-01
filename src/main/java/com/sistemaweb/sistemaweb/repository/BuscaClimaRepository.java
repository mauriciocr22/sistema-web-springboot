package com.sistemaweb.sistemaweb.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.sistemaweb.sistemaweb.model.BuscaClima;

@Repository
public interface BuscaClimaRepository extends JpaRepository<BuscaClima, Long> {

    @Query("SELECT b FROM BuscaClima b WHERE LOWER(b.cidade) = LOWER(:cidade)")
    List<BuscaClima> buscarPorCidade(String cidade);
}
