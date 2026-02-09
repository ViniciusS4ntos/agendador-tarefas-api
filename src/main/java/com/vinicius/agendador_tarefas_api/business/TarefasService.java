package com.vinicius.agendador_tarefas_api.business;

import com.vinicius.agendador_tarefas_api.business.dto.TarefasDTO;
import com.vinicius.agendador_tarefas_api.business.mapper.TarefasConverter;
import com.vinicius.agendador_tarefas_api.infrastructure.entity.TarefasEntity;
import com.vinicius.agendador_tarefas_api.infrastructure.enums.StatusNotificacaoEnum;
import com.vinicius.agendador_tarefas_api.infrastructure.repository.TarefasRepository;
import com.vinicius.agendador_tarefas_api.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas(String token,TarefasDTO dto){
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefaEntity(dto);

        return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }


}
