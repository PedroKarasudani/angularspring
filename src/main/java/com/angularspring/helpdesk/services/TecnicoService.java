package com.angularspring.helpdesk.services;

import com.angularspring.helpdesk.domain.Pessoa;
import com.angularspring.helpdesk.domain.Tecnico;
import com.angularspring.helpdesk.domain.dtos.TecnicoDTO;
import com.angularspring.helpdesk.domain.exceptions.DataIntegrityViolationException;
import com.angularspring.helpdesk.domain.exceptions.ObjectNotFoundException;
import com.angularspring.helpdesk.repositories.PessoaRepository;
import com.angularspring.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class TecnicoService {

    @Autowired
    private TecnicoRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    public Tecnico findById(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Tecnico> findAll() {
        return this.repository.findAll();
    }

    public Tecnico create(TecnicoDTO objDTO) {
        objDTO.setId(null);
        validaPorCpfEEmail(objDTO);
        return this.repository.save(new Tecnico(objDTO));
    }

    public Tecnico update(Integer id, @Valid TecnicoDTO objDTO) {
        objDTO.setId(id);
        Tecnico oldObj = this.findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Tecnico(objDTO);
        return repository.save(oldObj);
    }

    private void validaPorCpfEEmail(TecnicoDTO objDTO) {
        Optional<Pessoa>obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

}
