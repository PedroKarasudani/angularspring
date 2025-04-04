package com.angularspring.helpdesk.services;

import com.angularspring.helpdesk.domain.Chamado;
import com.angularspring.helpdesk.domain.Cliente;
import com.angularspring.helpdesk.domain.Tecnico;
import com.angularspring.helpdesk.domain.dtos.ChamadoDTO;
import com.angularspring.helpdesk.domain.enums.Prioridade;
import com.angularspring.helpdesk.domain.enums.Status;
import com.angularspring.helpdesk.domain.exceptions.ObjectNotFoundException;
import com.angularspring.helpdesk.repositories.ChamadoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ChamadoService {

    @Autowired
    private ChamadoRepository repository;

    @Autowired
    private TecnicoService tecnicoService;

    @Autowired
    private ClienteService clienteService;

    public Chamado findById(Integer id) {
        Optional<Chamado> obj = this.repository.findById(id);
        return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado, ID: " + id));
    }

    public List<Chamado> findAll() {
        return this.repository.findAll();
    }

    public Chamado create(@Valid ChamadoDTO objDTO) {
        return this.repository.save(newChamado(objDTO));
    }

    private Chamado newChamado(ChamadoDTO objDTO) {
        Tecnico tecnico = this.tecnicoService.findById(objDTO.getTecnico());
        Cliente cliente = this.clienteService.findById(objDTO.getCliente());
        Chamado chamado = new Chamado();
        if(objDTO.getId() != null) {
            chamado.setId(objDTO.getId());
        }
        chamado.setTecnico(tecnico);
        chamado.setCliente(cliente);
        chamado.setPrioridade(Prioridade.toEnum(objDTO.getPrioridade()));
        chamado.setStatus(Status.toEnum(objDTO.getStatus()));
        chamado.setTitulo(objDTO.getTitulo());
        chamado.setObservacoes(objDTO.getObservacoes());
        return chamado;
    }
}
