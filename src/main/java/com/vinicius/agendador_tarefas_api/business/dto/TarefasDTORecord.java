package com.vinicius.agendador_tarefas_api.business.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.vinicius.agendador_tarefas_api.infrastructure.enums.StatusNotificacaoEnum;
import lombok.AllArgsConstructor;

import java.time.LocalDateTime;


public record TarefasDTORecord(
        String id,
        String nomeTarefa,
        String descricao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime dataCriacao,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime dataEvento,
        String emailUsuario,
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
        LocalDateTime dataAlteracao,
        StatusNotificacaoEnum statusNotificacaoEnum
) {
}
