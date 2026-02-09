package com.vinicius.agendador_tarefas_api.infrastructure.repository;

import com.vinicius.agendador_tarefas_api.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {

    List<TarefasEntity> findByDataEventoBetween(LocalDateTime dataInicial, LocalDateTime dataFinal);
    List<TarefasEntity> findByEmailUsuario(String email);

}
