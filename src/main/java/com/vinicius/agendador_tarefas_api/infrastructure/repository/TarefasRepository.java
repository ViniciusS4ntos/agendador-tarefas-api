package com.vinicius.agendador_tarefas_api.infrastructure.repository;

import com.vinicius.agendador_tarefas_api.infrastructure.entity.TarefasEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository

public interface TarefasRepository extends MongoRepository<TarefasEntity, String> {
}
