package com.vinicius.agendador_tarefas_api.business.mapper;

import com.vinicius.agendador_tarefas_api.business.dto.TarefasDTORecord;
import com.vinicius.agendador_tarefas_api.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

// converter rapido, em vez de usar builder

@Mapper(componentModel = "spring")
public interface TarefasConverter {
    @Mapping(source = "id", target = "id")
    @Mapping(source = "dataEvento", target = "dataEvento")
    @Mapping(source = "dataCriacao", target = "dataCriacao")
    TarefasEntity paraTarefaEntity(TarefasDTORecord dto);

    TarefasDTORecord paraTarefaDTO(TarefasEntity entity);

    List<TarefasEntity> paraListaTarefasEntity(List<TarefasDTORecord> dtos);

    List<TarefasDTORecord> paraListaTarefasDTORecord(List<TarefasEntity> entities);

}
