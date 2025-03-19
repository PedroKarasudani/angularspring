package com.angularspring.helpdesk;

import com.angularspring.helpdesk.domain.Chamado;
import com.angularspring.helpdesk.domain.Cliente;
import com.angularspring.helpdesk.domain.Tecnico;
import com.angularspring.helpdesk.domain.enums.Perfil;
import com.angularspring.helpdesk.domain.enums.Prioridade;
import com.angularspring.helpdesk.domain.enums.Status;
import com.angularspring.helpdesk.repositories.ChamadoRepository;
import com.angularspring.helpdesk.repositories.ClienteRepository;
import com.angularspring.helpdesk.repositories.PessoaRepository;
import com.angularspring.helpdesk.repositories.TecnicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Arrays;
import java.util.TreeMap;

@SpringBootApplication
public class HelpdeskApplication {

	public static void main(String[] args) {
		SpringApplication.run(HelpdeskApplication.class, args);
	}

}
