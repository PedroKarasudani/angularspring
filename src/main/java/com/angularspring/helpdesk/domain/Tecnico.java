package com.angularspring.helpdesk.domain;

import com.angularspring.helpdesk.domain.Pessoa;
import com.angularspring.helpdesk.domain.enums.Perfil;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

public class Tecnico extends Pessoa {

    @OneToMany(mappedBy = "tecnico")
    private List<Chamado> chamados = new ArrayList<>();

    public Tecnico() {
        super();
        addPerfil(Perfil.TECNICO);
    }

    public Tecnico(Integer id, String nome, String cpf, String email, String senha) {
        super(id, nome, cpf, email, senha);
        addPerfil(Perfil.TECNICO);
    }

    public List<Chamado> getChamados() {
        return chamados;
    }

    public void setChamados(List<Chamado> chamados) {
        this.chamados = chamados;
    }
}
