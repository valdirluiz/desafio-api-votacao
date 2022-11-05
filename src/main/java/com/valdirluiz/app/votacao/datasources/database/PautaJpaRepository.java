package com.valdirluiz.app.votacao.datasources.database;


import com.valdirluiz.app.votacao.entities.Pauta;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PautaJpaRepository extends JpaRepository<Pauta, Long> {
}
