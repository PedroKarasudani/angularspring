package com.angularspring.helpdesk.resources;

import com.angularspring.helpdesk.domain.Tecnico;
import com.angularspring.helpdesk.domain.dtos.TecnicoDTO;
import com.angularspring.helpdesk.services.TecnicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tecnico")
public class TecnicoResource {

    @Autowired
    private TecnicoService service;

    @GetMapping("/{id}")
    public ResponseEntity<TecnicoDTO> findById (@PathVariable Integer id) {
        return ResponseEntity.ok().body(new TecnicoDTO(service.findById(id)));
    }
}
