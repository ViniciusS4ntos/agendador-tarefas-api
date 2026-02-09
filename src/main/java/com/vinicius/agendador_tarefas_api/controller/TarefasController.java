package com.vinicius.agendador_tarefas_api.controller;

import com.vinicius.agendador_tarefas_api.business.TarefasService;
import com.vinicius.agendador_tarefas_api.business.dto.TarefasDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

}
