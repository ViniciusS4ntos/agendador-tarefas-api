package com.vinicius.agendador_tarefas_api.business.mapper;

import com.vinicius.agendador_tarefas_api.business.dto.TarefasDTORecord;
import com.vinicius.agendador_tarefas_api.infrastructure.entity.TarefasEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface TarefaUpdateConverter {
    // se retorna void, pode usar a entity que vc passou na service para fazer o return pq ele salva nela
    TarefasEntity updateTarefas(TarefasDTORecord dto, @MappingTarget TarefasEntity entity);

}
