/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package br.com.munif.munif.negocios;

import br.com.munif.munif.entidades.Artigo;
import br.com.munif.munif.entidades.Assunto;
import br.com.munif.webbase.filtros.TransacoesFiltro;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author munifgebarajunior
 */
public class PaginaPrincipal {
    
    public static List<Artigo> ultimos4Artigos(){
        EntityManager em=TransacoesFiltro.tlem.get();
        Query q=em.createQuery("from Artigo artigo order by artigo.quando desc");
        q.setMaxResults(4);
        return q.getResultList();
    }

    public static List<Assunto> assuntos() {
        EntityManager em=TransacoesFiltro.tlem.get();
        Query q=em.createQuery("from Assunto assunto order by assunto.nome");
        return q.getResultList();
    }
    
}
