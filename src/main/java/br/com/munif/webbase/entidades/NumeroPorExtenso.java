/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.webbase.entidades;

/**
 *
 * @author Munif
 */
public class NumeroPorExtenso {

    public static void main(String args[]) {
        for (int i = 1000; i < Integer.MAX_VALUE; i *= 2) {
            System.out.printf("%8d %s\n", i, escreveInteiro(i));
        }
    }

    private static final String unidadesEdezeAlgos[] = {"zero", "um", "dois", "três", "quatro", "cinco", "seis", "sete", "oito", "nove", "dez",
        "onze", "doze", "treza", "quatorze", "quinze", "dezesseis", "dezessete", "dezoito", "dezenove"
    };

    private static final String dezenas[] = {"X", "XX", "vinte", "trinta", "quarenta", "cinquenta", "sessenta", "setenta", "oitenta", "noventa"};
    private static String centenas[] = {"XXX", "cento", "duzentos", "trezentos", "quatrocentos", "quinhentos", "seiscentos", "setecentos", "oitocentos", "novecentos"};
    private static String grandesSingular[] = {"XXXX", "mil", "milhão", "bilhão", "trilhão", "quadrilhão", "quintilhão"};
    private static String grandesPlural[] = {"XXXXX", "mil", "milhões", "bilhões", "trilhões", "quadrilhões", "quintilhões"};

    private static String escreveInteiro(int n) {
        if (n < 0) {
            return "menos " + escreveInteiro(-n);
        }
        if (n < 20) {
            return unidadesEdezeAlgos[n];
        }
        for (int i = 2; i < 10; i++) {
            if (n == i * 10) {
                return dezenas[i];
            }
            if (n < i * 10 + 10) {
                return dezenas[i] + " e " + escreveInteiro(n - i * 10);
            }
        }
        if (n == 100) {
            return "cem";
        }
        for (int i = 1; i < 10; i++) {
            if (n == i * 100) {
                return centenas[i];
            }
            if (n < i * 100 + 100) {
                return centenas[i] + " e " + escreveInteiro(n - i * 100);
            }
        }

        for (int i = 1; i < 6; i++) {
            if (n < Math.pow(1000, i + 1)) {
                int pg = (int) (n / Math.pow(1000, i));
                int pp = (int) (n - pg * Math.pow(1000, i));
                if (pp == 0) {
                    if (pg == 1) {
                        return escreveInteiro(pg) + " " + grandesSingular[i];
                    }
                    return escreveInteiro(pg) + " " + grandesPlural[i];
                }
                if (pp > 0) {
                    if (pg == 1) {
                        return escreveInteiro(pg) + " " + grandesSingular[i] + " " + escreveInteiro(pp);
                    }
                    else{
                        return escreveInteiro(pg) + " " + grandesPlural[i] + " " + escreveInteiro(pp);
                    }
                }

            }
        }

        return "não sei " + n;
    }

}
