/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.util.web;

import br.com.munif.webbase.entidades.Arquivo;
import br.com.munif.webbase.entidades.ArquivoParte;
import br.com.munif.webbase.entidades.Util;
import br.com.munif.webbase.filtros.TransacoesFiltro;
import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.imageio.ImageIO;
import javax.persistence.EntityManager;

/**
 *
 * @author munif
 */
public class ArquivoFacade {

    private static final ArquivoFacade instance = new ArquivoFacade();

    public static ArquivoFacade getInstance() {
        return instance;
    }

    private ArquivoFacade() {
    }

    public void adicionaArquivo(Arquivo arquivo, InputStream inputstream) throws IOException {
        EntityManager em = TransacoesFiltro.tlem.get();
        int bytesLidos = 0;
        while (true) {
            int restante = inputstream.available();
            byte[] buffer = new byte[restante > ArquivoParte.TAMANHO_MAXIMO ? ArquivoParte.TAMANHO_MAXIMO : restante];
            bytesLidos = inputstream.read(buffer);
            if (bytesLidos <= 0) {
                break;
            }
            ArquivoParte arquivoParte = new ArquivoParte();
            arquivoParte.setDados(buffer);
            arquivoParte.setArquivo(arquivo);
            em.persist(arquivoParte);
        }
        em.persist(arquivo);
    }

    public Arquivo recupera(Long id) {
        EntityManager em = TransacoesFiltro.tlem.get();
        return em.find(Arquivo.class, id);
    }

    public static String getCaminhoArquivos() {
        if (Util.getOs().contains("Windows")) {
            return "c:\\dadosarquivos";
        }
        return "/dadosarquivos";
    }

    public InputStream recuperaArquivoDisco(String subPasta, Long id) {
        try {
            String nomePasta = getCaminhoArquivos() + "/" + subPasta;
            String nomeArquivo = nomePasta + "/" + id;
            FileInputStream fis = new FileInputStream(nomeArquivo);
            return fis;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public void armazenaArquivoDisco(String subPasta, Long id, InputStream is) throws Exception {
        String nomePasta = getCaminhoArquivos() + "/" + subPasta;
        String nomeArquivo = nomePasta + "/" + id;
        File pasta = new File(nomePasta);
        if (!pasta.exists()) {
            pasta.mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(nomeArquivo);
        int t = 0;
        while ((t = is.available()) > 0) {
            byte buffer[];
            if (t > 4096) {
                buffer = new byte[4096];
            } else {
                buffer = new byte[t];
            }
            if (buffer.length != is.read(buffer)) {
                throw new RuntimeException("Escrito com erro!");
            }
            fos.write(buffer);
        }
        fos.close();
    }
}
