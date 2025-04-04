package com.angularspring.helpdesk.resources;

import com.angularspring.helpdesk.domain.Chamado;
import com.angularspring.helpdesk.domain.dtos.ChamadoDTO;
import com.angularspring.helpdesk.services.ChamadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
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

    @GetMapping
    ResponseEntity<List<ChamadoDTO>> findAll() {
        List<Chamado> list = this.service.findAll();
        List<ChamadoDTO> listDTO = list.stream().map(ChamadoDTO::new).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDTO);
    }

    @PostMapping
    ResponseEntity<ChamadoDTO> create(@Valid @RequestBody ChamadoDTO objDTO) {
        Chamado obj = this.service.create(objDTO);
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(obj.getId())
                .toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    ResponseEntity<ChamadoDTO> update(@PathVariable Integer id, @Valid @RequestBody ChamadoDTO objDTO) {
        Chamado obj = this.service.update(id, objDTO);
        return ResponseEntity.ok().body(new ChamadoDTO(obj));
    }
}
