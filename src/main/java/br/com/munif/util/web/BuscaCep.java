package br.com.munif.util.web;

import br.com.munif.webbase.entidades.Webservicecep;
import java.net.URL;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

public class BuscaCep {

    public static Webservicecep getEndereco(String cep) {
        try {
            JAXBContext jc = JAXBContext.newInstance(Webservicecep.class);
            Unmarshaller u = jc.createUnmarshaller();
            URL url = new URL("http://cep.republicavirtual.com.br/web_cep.php?cep=" + cep + "&formato=xml");
            Webservicecep wCep = (Webservicecep) u.unmarshal(url);
            return wCep;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return new Webservicecep();
    }
}
