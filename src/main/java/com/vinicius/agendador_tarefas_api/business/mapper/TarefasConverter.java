package com.vinicius.agendador_tarefas_api.business.mapper;

import com.vinicius.agendador_tarefas_api.business.dto.TarefasDTO;
import com.vinicius.agendador_tarefas_api.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;

// converter rapido, em vez de usar builder

@Mapper(componentModel = "spring")
public interface TarefasConverter {

    TarefasEntity paraTarefaEntity(TarefasDTO dto);
    TarefasDTO paraTarefaDTO(TarefasEntity entity);

}
