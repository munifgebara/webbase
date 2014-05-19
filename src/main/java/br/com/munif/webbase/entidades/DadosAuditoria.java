/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.webbase.entidades;

/**
 *
 * @author Munif
 */
public class DadosAuditoria {

    private String ip;
    private String usuario;

    public DadosAuditoria() {
        this.ip = "obter ip";
        this.usuario = "obter usuario";
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }
}
