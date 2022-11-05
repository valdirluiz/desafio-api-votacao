package com.valdirluiz.app.votacao.datasources.database;

import com.valdirluiz.app.votacao.entities.Sessao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SessaoJpaRepository extends JpaRepository<Sessao, Long> {

}
