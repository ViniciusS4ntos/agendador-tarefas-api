package com.vinicius.agendador_tarefas_api.business;

import com.vinicius.agendador_tarefas_api.business.dto.TarefasDTO;
import com.vinicius.agendador_tarefas_api.business.mapper.TarefaUpdateConverter;
import com.vinicius.agendador_tarefas_api.business.mapper.TarefasConverter;
import com.vinicius.agendador_tarefas_api.infrastructure.entity.TarefasEntity;
import com.vinicius.agendador_tarefas_api.infrastructure.enums.StatusNotificacaoEnum;
import com.vinicius.agendador_tarefas_api.infrastructure.exceptions.ResourceNotFoundException;
import com.vinicius.agendador_tarefas_api.infrastructure.repository.TarefasRepository;
import com.vinicius.agendador_tarefas_api.infrastructure.security.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TarefasService {

    private final TarefasRepository tarefasRepository;
    private final TarefasConverter tarefasConverter;
    private final TarefaUpdateConverter tarefaUpdateConverter;
    private final JwtUtil jwtUtil;

    public TarefasDTO gravarTarefas(String token, TarefasDTO dto) {
        String email = jwtUtil.extractUsername(token.substring(7));
        dto.setDataCriacao(LocalDateTime.now());
        dto.setStatusNotificacaoEnum(StatusNotificacaoEnum.PENDENTE);
        dto.setEmailUsuario(email);
        TarefasEntity entity = tarefasConverter.paraTarefaEntity(dto);

        return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTO> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasConverter.paraListaTarefasDTO(
                tarefasRepository.findByDataEventoBetween(dataInicial, dataFinal)
        );
    }

    public List<TarefasDTO> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefasConverter.paraListaTarefasDTO(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : Id não encontrado: " + id, e.getCause());
        }
    }

    public TarefasDTO alterarStatus(StatusNotificacaoEnum status, String id) {
        try {

            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Error : Id não encontrado: " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error ao alterar status da  tarefa: " + id, e.getCause());
        }
    }

    public TarefasDTO updateTarefas(TarefasDTO dto, String id) {
        try {

            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Error : Id não encontrado: " + id));
            dto.setDataAlteracao(LocalDateTime.now());
            return tarefasConverter.paraTarefaDTO(tarefaUpdateConverter.updateTarefas(dto, entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error ao alterar status da  tarefa: " + id, e.getCause());
        }
    }


}
