package com.vinicius.agendador_tarefas_api.business;

import com.vinicius.agendador_tarefas_api.business.dto.TarefasDTORecord;
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

    public TarefasDTORecord gravarTarefas(String token, TarefasDTORecord dto) {
        String email = jwtUtil.extractUsername(token.substring(7));

        TarefasDTORecord dtoFinal = new TarefasDTORecord(null, dto.nomeTarefa(), dto.descricao(), LocalDateTime.now(),
                dto.dataEvento(), email,  null, StatusNotificacaoEnum.PENDENTE);

        TarefasEntity entity = tarefasConverter.paraTarefaEntity(dtoFinal);

        return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));
    }

    public List<TarefasDTORecord> buscarTarefasAgendadasPorPeriodo(LocalDateTime dataInicial, LocalDateTime dataFinal) {
        return tarefasConverter.paraListaTarefasDTORecord(
                tarefasRepository.findByDataEventoBetweenAndStatusNotificacaoEnum(dataInicial, dataFinal, StatusNotificacaoEnum.PENDENTE)
        );
    }

    public List<TarefasDTORecord> buscarTarefasPorEmail(String token) {
        String email = jwtUtil.extractUsername(token.substring(7));

        return tarefasConverter.paraListaTarefasDTORecord(tarefasRepository.findByEmailUsuario(email));
    }

    public void deletarTarefaPorId(String id) {
        try {
            tarefasRepository.deleteById(id);
        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error : Id não encontrado: " + id, e.getCause());
        }
    }

    public TarefasDTORecord alterarStatus(StatusNotificacaoEnum status, String id) {
        try {

            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Error : Id não encontrado: " + id));
            entity.setStatusNotificacaoEnum(status);
            return tarefasConverter.paraTarefaDTO(tarefasRepository.save(entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error ao alterar status da  tarefa: " + id, e.getCause());
        }
    }

    public TarefasDTORecord updateTarefas(TarefasDTORecord dto, String id) {
        try {

            TarefasEntity entity = tarefasRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("Error : Id não encontrado: " + id));

            TarefasDTORecord dtoFinal = new TarefasDTORecord(null, dto.nomeTarefa(),dto.descricao(),dto.dataCriacao(),
                    dto.dataEvento(),dto.emailUsuario(),LocalDateTime.now(), dto.statusNotificacaoEnum());

            return tarefasConverter.paraTarefaDTO(tarefaUpdateConverter.updateTarefas(dtoFinal, entity));

        } catch (ResourceNotFoundException e) {
            throw new ResourceNotFoundException("Error ao alterar status da  tarefa: " + id, e.getCause());
        }
    }


}
