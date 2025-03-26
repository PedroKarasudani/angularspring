package com.angularspring.helpdesk.services;

import com.angularspring.helpdesk.domain.Tecnico;
import com.angularspring.helpdesk.domain.exceptions.ObjectNotFoundException;
import com.angularspring.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return this.repository.findAll();
    }
}
