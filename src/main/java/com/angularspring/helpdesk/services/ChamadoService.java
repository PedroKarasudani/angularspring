package com.angularspring.helpdesk.services;

import com.angularspring.helpdesk.domain.Chamado;
import com.angularspring.helpdesk.domain.exceptions.ObjectNotFoundException;
import com.angularspring.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = this.repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id));
    }

    public List<Chamado> findAll() {
        return this.repository.findAll();
    }
}
