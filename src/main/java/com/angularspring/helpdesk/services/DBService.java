package com.angularspring.helpdesk.services;

import com.angularspring.helpdesk.domain.Chamado;
import com.angularspring.helpdesk.domain.Cliente;
import com.angularspring.helpdesk.domain.Tecnico;
import com.angularspring.helpdesk.domain.enums.Perfil;
import com.angularspring.helpdesk.domain.enums.Prioridade;
import com.angularspring.helpdesk.domain.enums.Status;
import com.angularspring.helpdesk.repositories.ChamadoRepository;
import com.angularspring.helpdesk.repositories.ClienteRepository;
import com.angularspring.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class DBService {

    @Autowired
    private TecnicoRepository tecnicoRepository;

    @Autowired
    private ClienteRepository clienteRepository;

    @Autowired
    private ChamadoRepository chamadoRepository;

    public void instanciaDB() {
        Tecnico tec1 = new Tecnico(null, "Valdir Cesar", "000.000.000-00", "valdir@gmail.com", "123");
        tec1.addPerfil(Perfil.ADMIN);
        Cliente cli1 = new Cliente(null, "Linus Torvals","000.000.000-01", "linus@gmail.com", "123");
        Chamado c1 = new Chamado(null, Prioridade.MEDIA, Status.ANDAMENTO, "Chamado 01", "Primeiro Chamado", tec1, cli1);
        tecnicoRepository.saveAll(Arrays.asList(tec1));
        clienteRepository.saveAll(Arrays.asList(cli1));
        chamadoRepository.saveAll(Arrays.asList(c1));
    }
}
