/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.webbase.entidades;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.*;

/**
 *
 * @author Peixe
 */
@Entity
public class Usuario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    private String login;
    private String senha;
    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    private List<GrupoUsuario> grupoUsuario;
    private Boolean ativo;

    public Usuario() {
        grupoUsuario = new ArrayList<GrupoUsuario>();
        ativo = Boolean.TRUE;
    }

    public List<GrupoUsuario> getGrupoUsuario() {
        return grupoUsuario;
    }

    public void setGrupoUsuario(List<GrupoUsuario> grupoUsuario) {
        this.grupoUsuario = grupoUsuario;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAtivo() {
        return ativo;
    }

    public void setAtivo(Boolean ativo) {
        this.ativo = ativo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Usuario other = (Usuario) obj;
        if ((this.login == null) ? (other.login != null) : !this.login.equals(other.login)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (this.login != null ? this.login.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return login;
    }

    public boolean temEsseGrupo(Grupo grupo) {
        for (GrupoUsuario g : grupoUsuario) {
            if (g.getGrupo().equals(grupo)) {
                return true;
            }
        }
        return false;
    }
}
