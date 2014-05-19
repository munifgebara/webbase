/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.webbase.entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Temporal;

/**
 *
 * @author munif
 */
@Entity
@GrupoDiagrama(nome = "util")
public class Arquivo implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String mimeType;
    private Long tamanho;
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date momentoDoUpload;
    @OrderBy(value = "id")
    @OneToMany(mappedBy = "arquivo", cascade = CascadeType.ALL)
    private List<ArquivoParte> partes;
    @Lob
    @Column(length = 50 * 1024) //Principalmente por causa do Mysql
    private byte thumb[];
    private String caminho;

    public Arquivo() {
        momentoDoUpload = new Date();
    }

    public String getCaminho() {
        return caminho;
    }

    public void setCaminho(String caminho) {
        this.caminho = caminho;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public Long getTamanho() {
        return tamanho;
    }

    public void setTamanho(Long tamanho) {
        this.tamanho = tamanho;
    }

    public Date getMomentoDoUpload() {
        return momentoDoUpload;
    }

    public void setMomentoDoUpload(Date momentoDoUpload) {
        this.momentoDoUpload = momentoDoUpload;
    }

    public List<ArquivoParte> getPartes() {
        return partes;
    }

    public void setPartes(List<ArquivoParte> partes) {
        this.partes = partes;
    }

    public byte[] getThumb() {
        return thumb;
    }

    public void setThumb(byte[] thumb) {
        this.thumb = thumb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arquivo)) {
            return false;
        }
        Arquivo other = (Arquivo) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nome + " (" + getId() + ")";
    }
}
