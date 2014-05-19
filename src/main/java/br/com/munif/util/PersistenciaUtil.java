/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.util;

import br.com.munif.webbase.entidades.Grupo;
import br.com.munif.webbase.entidades.GrupoUsuario;
import br.com.munif.webbase.entidades.Usuario;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author munifgebarajunior
 */
public class PersistenciaUtil {

    private static final Logger logger = Logger.getLogger("br.com.munif.util.Persistencia");

    /**
     * Obtem o valor da chave primária de uma entidade. O método procura o
     * atributo com a anotação ID na classe e nas superclasses
     *
     * @param entidade entidade que deseja-se a chave
     * @return valor da chave
     */
    public static Object getId(Object entidade) {
        try {
            Field f = getFieldId(entidade.getClass());
            f.setAccessible(true);
            return f.get(entidade);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Não foi possível encontrar a chave primária de " + entidade, ex);
        }
        return null;
    }

    /**
     * Obtém o valor do atributo a partir de seu nome e do objeto que o contém
     *
     * @param entidade
     * @param attrName
     * @return
     */
    public static Object getAttributeValue(Object entidade, String attrName) {
        try {
            Field f = entidade.getClass().getDeclaredField(attrName);
            f.setAccessible(true);
            return f.get(entidade);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Não foi possível encontrar o atributo " + attrName + " na classe " + entidade.getClass().getName(), ex);
        }
        return null;
    }

    /**
     * Altera o valor da chave primária de um objeto, procura pelo
     *
     * @Id
     *
     * @param entidade a entidade a alterar o ID
     * @param valor novo valor
     */
    public static void setId(Object entidade, Object valor) {
        try {
            Field f = getFieldId(entidade.getClass());
            f.setAccessible(true);
            f.set(entidade, valor);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Não foi possível encontrar a chave primária de " + entidade, ex);
        }
    }

    /**
     * Obtem o atributo que é chave primária de uma entidade. O método procura o
     * atributo com a anotação ID na classe e nas superclasses
     *
     * @return Field da chave
     */
    public static Field getFieldId(Class classe) {
        try {
            for (Field f : getAtributos(classe)) {
                if (f.isAnnotationPresent(Id.class)) {
                    return f;
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Não foi possível encontrar a chave primária de " + classe, ex);
        }
        return null;
    }

    /**
     * Método recursivo para descobrir todos os atributos da entidade, incluindo
     * os das superclasses, se existirem
     *
     * @param classe classe da entidade
     * @return um ArrayList com os atributos (Fields) da entidade
     */
    public static List<Field> getAtributos(Class classe) {
        List<Field> lista = new ArrayList<Field>();
        if (!classe.getSuperclass().equals(Object.class)) {
            lista.addAll(getAtributos(classe.getSuperclass()));
        }
        for (Field f : classe.getDeclaredFields()) {
            if (Modifier.isStatic(f.getModifiers())) {
                continue;
            }
            lista.add(f);
        }
        return lista;
    }

    /**
     * Método que retorna o primeiro atributo da classe de entidade. Não retorna
     * atributos estáticos. Evita retorna atributos com a anotação
     * GeneratedValue, isto é, caso exista algum atributo não estático sem esta
     * anotação, este será retornado. Este método é destinado a construção de
     * consultas genéricas.
     *
     * @param classe Classe da entidade.
     * @return
     */
    public static Field primeiroAtributo(Class classe) {
        Field f = null;
        for (Field atributo : getAtributos(classe)) {
            if (Modifier.isStatic(atributo.getModifiers())) {
                continue;
            }
            if (f == null) {
                f = atributo;
            }
            if (!atributo.isAnnotationPresent(GeneratedValue.class)) {
                return atributo;
            } else if (f == null) {
                f = atributo;
            }
        }
        return f;
    }


    public static void duplicaColecoes(Object destino, Object origem) {
        try {
            for (Field f : getAtributos(origem.getClass())) {
                if (f.isAnnotationPresent(OneToMany.class) || (f.isAnnotationPresent(ManyToMany.class))) {
                    logger.log(Level.INFO, "Colecao " + f);
                    f.setAccessible(true);
                    Collection colecaoDestino = null;
                    Collection colecaoOrigem = (Collection) f.get(origem);
                    if (f.getType().equals(Set.class)) {
                        colecaoDestino = new HashSet();
                    }
                    if (f.getType().equals(List.class)) {
                        colecaoDestino = new ArrayList();
                    }
                    for (Object obj : colecaoOrigem) {
                        colecaoDestino.add(obj);
                    }
                    f.set(destino, colecaoDestino);
                }
            }
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Problema ao duplicar colecoes", ex);
        }
    }


    public static void criaUsuarioPadrao() {
        EntityManager em = Persistencia.getInstancia().getEmf().createEntityManager();
        em.getTransaction().begin();
        boolean temAdministrador = false;
        Query q = em.createQuery("from Usuario u");
        List<Usuario> usuarios = q.getResultList();
        for (Usuario u : usuarios) {
            for (GrupoUsuario gu : u.getGrupoUsuario()) {
                if (gu.getGrupo().equals(Grupo.ADMIN)) {
                    temAdministrador = true;
                    break;
                }
            }
            if (temAdministrador) {
                break;
            }
        }
        if (!temAdministrador) {
            System.out.println("Base sem administrador, criando usuário padrão");
            Usuario u = new Usuario();
            u.setAtivo(true);
            u.setLogin("admin");
            u.setSenha("qwe123");
            em.persist(u);
            GrupoUsuario gu = new GrupoUsuario();
            gu.setCriadoEm(new Date());
            gu.setGrupo(Grupo.ADMIN);
            gu.setUsuario(u);
            em.persist(gu);
        }
        em.getTransaction().commit();
    }

}
