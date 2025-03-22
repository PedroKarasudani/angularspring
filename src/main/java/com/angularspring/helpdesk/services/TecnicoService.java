package com.angularspring.helpdesk.services;

import com.angularspring.helpdesk.domain.Tecnico;
import com.angularspring.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    public Tecnico findById(Integer id) {
        return this.repository.findById(id).orElse(null);
    }
}
