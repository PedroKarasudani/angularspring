package com.angularspring.helpdesk.services;

import com.angularspring.helpdesk.domain.Pessoa;
import com.angularspring.helpdesk.domain.Cliente;
import com.angularspring.helpdesk.domain.dtos.ClienteDTO;
import com.angularspring.helpdesk.domain.exceptions.DataIntegrityViolationException;
import com.angularspring.helpdesk.domain.exceptions.ObjectNotFoundException;
import com.angularspring.helpdesk.repositories.PessoaRepository;
import com.angularspring.helpdesk.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private BCryptPasswordEncoder encoder;

    public Cliente findById(Integer id) {
        return this.repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! Id: " + id));
    }

    public List<Cliente> findAll() {
        return this.repository.findAll();
    }

    public Cliente create(ClienteDTO objDTO) {
        objDTO.setId(null);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        validaPorCpfEEmail(objDTO);
        return this.repository.save(new Cliente(objDTO));
    }

    public Cliente update(Integer id, @Valid ClienteDTO objDTO) {
        objDTO.setId(id);
        objDTO.setSenha(encoder.encode(objDTO.getSenha()));
        Cliente oldObj = this.findById(id);
        validaPorCpfEEmail(objDTO);
        oldObj = new Cliente(objDTO);
        return repository.save(oldObj);
    }

    private void validaPorCpfEEmail(ClienteDTO objDTO) {
        Optional<Pessoa>obj = pessoaRepository.findByCpf(objDTO.getCpf());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("CPF já cadastrado no sistema!");
        }

        obj = pessoaRepository.findByEmail(objDTO.getEmail());
        if(obj.isPresent() && obj.get().getId() != objDTO.getId()){
            throw new DataIntegrityViolationException("Email já cadastrado no sistema!");
        }
    }

    public void deleteById(Integer id) {
        Cliente obj = this.findById(id);
        if(obj.getChamados().size() > 0) {
            throw new DataIntegrityViolationException("Cliente possui ordem de servico, nao pode ser deletado!");
        }
        repository.deleteById(id);
    }
}
