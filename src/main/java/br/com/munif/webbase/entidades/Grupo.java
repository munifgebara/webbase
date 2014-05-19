/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.webbase.entidades;

/**
 *
 * @author Peixe
 */
public enum Grupo {

    ADMIN("Administrador"),
    GERENTE("Gerente");
    private String descricao;

    private Grupo(String descricao) {
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
