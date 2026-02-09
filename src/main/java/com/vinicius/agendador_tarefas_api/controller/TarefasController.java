package com.vinicius.agendador_tarefas_api.controller;

import com.vinicius.agendador_tarefas_api.business.TarefasService;
import com.vinicius.agendador_tarefas_api.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/tarefas")
@RequiredArgsConstructor
public class TarefasController {

    private final TarefasService tarefasService;

    @PostMapping
    public ResponseEntity<TarefasDTO> gravarTarefas(@RequestBody TarefasDTO dto,
                                                    @RequestHeader("Authorization")String token){
         return ResponseEntity.ok(tarefasService.gravarTarefas(token,dto));
    }

    @GetMapping("/eventos")
    public ResponseEntity<List<TarefasDTO>> buscarListaTarefasPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataInicial,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)LocalDateTime dataFinal
            ){
        return ResponseEntity.ok(tarefasService.buscarTarefasAgendadasPorPeriodo(dataInicial,dataFinal));
    }

    @GetMapping
    public ResponseEntity<List<TarefasDTO>> buscarTarefaPorEmail(@RequestHeader("Authorization") String token){
        return ResponseEntity.ok(tarefasService.buscarTarefasPorEmail(token));
    }

}
