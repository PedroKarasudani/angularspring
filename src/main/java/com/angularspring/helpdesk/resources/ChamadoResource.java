package com.angularspring.helpdesk.resources;

import com.angularspring.helpdesk.domain.Chamado;
import com.angularspring.helpdesk.domain.dtos.ChamadoDTO;
import com.angularspring.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/chamados")
public class ChamadoResource {

    @Autowired
    private ChamadoService service;

    @GetMapping("/{id}")
    ResponseEntity<ChamadoDTO> findById(@PathVariable Integer id) {
        return ResponseEntity.ok().body(new ChamadoDTO(this.service.findById(id)));
    }

    ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = this.service.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }
}
