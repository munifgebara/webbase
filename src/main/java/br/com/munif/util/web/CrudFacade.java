/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.util.web;

import br.com.munif.util.PersistenciaUtil;
import br.com.munif.webbase.filtros.TransacoesFiltro;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author munif
 */
public class CrudFacade {

    public static final int MAXCONSULTA = 50;
    private static CrudFacade instance = new CrudFacade();

    public static CrudFacade getInstance() {
        return instance;
    }

    private CrudFacade() {
    }

    public Object recuperar(Class clazz, Object chave) {
        EntityManager em = TransacoesFiltro.tlem.get();
        return em.find(clazz, chave);
    }

    public void insereOuAltera(Object entidade) {
        EntityManager em = TransacoesFiltro.tlem.get();
        if (PersistenciaUtil.getId(entidade) == null) {
            em.persist(entidade);
        } else {
            em.merge(entidade);
        }
    }

    public void inserir(Object entidade) {
        EntityManager em = TransacoesFiltro.tlem.get();
        em.persist(entidade);
    }

    public void alterar(Object entidade) {
        EntityManager em = TransacoesFiltro.tlem.get();
        em.merge(entidade);
    }

    public void remover(Object entidade) {
        EntityManager em = TransacoesFiltro.tlem.get();
        if (entidade == null) {
            return;
        }
        Object id = PersistenciaUtil.getId(entidade);
        if (id == null) {
            return;
        }
        entidade = recuperar(entidade.getClass(), PersistenciaUtil.getId(entidade));
        em.remove(entidade);
    }

    public List listarTodos(Class classe) {
        EntityManager em = TransacoesFiltro.tlem.get();
        Query q = em.createQuery("from " + classe.getCanonicalName() + " order by " + PersistenciaUtil.primeiroAtributo(classe).getName());
        return q.getResultList();
    }

    public List listarTodos(Class classe, String ordenadoPor) {
        EntityManager em = TransacoesFiltro.tlem.get();
        Query q = em.createQuery("from " + classe.getCanonicalName() + " order by " + ordenadoPor);
        return q.getResultList();
    }

    public List listarTodos(Class classe, String ordenadoPor, String criterio) {
        EntityManager em = TransacoesFiltro.tlem.get();
        Query q = em.createQuery("from " + classe.getCanonicalName() + " obj where " + criterio + " order by obj." + ordenadoPor);
        return q.getResultList();
    }

    public void persistirItensDaColecao(Collection colecao) {
        for (Object obj : colecao) {
            Object id = PersistenciaUtil.getId(obj);
            if (id == null) {
                inserir(obj);
            } else {
                alterar(obj);
            }
        }
    }

    public void removeColecao(Collection colecao) {
        for (Object obj : colecao) {
            remover(obj);
        }
    }
}
