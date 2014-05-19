/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.munif.webbase.entidades;

import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.*;
import static java.util.Calendar.*;

/**
 *
 * @author daniel
 */
public class DataUtil {

    private static final int DIAS_NO_MES[] = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    private static DateFormatSymbols dfs = new DateFormatSymbols(new Locale("pt", "BR"));
    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    private static SimpleDateFormat sdf2 = new SimpleDateFormat("yyyy_MM_dd");

    public static Date getDataHoraMinutoSegundoZerado(Date valorData) {
        Calendar c = Calendar.getInstance();

        c.setTime(valorData);
        c.set(Calendar.HOUR_OF_DAY, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        c.set(Calendar.MILLISECOND, 0);

        return c.getTime();
    }

    public static String format(Date data) {
        return sdf.format(data);
    }

    public static String format2(Date data) {
        return sdf2.format(data);
    }

    public static List<Calendar> mesesEntreDatas(Date inicio, Date fim) {
        List<Calendar> aRetornar = new ArrayList<Calendar>();
        Calendar data = Calendar.getInstance();
        data.setTime(inicio);
        data.set(Calendar.DAY_OF_MONTH, 1);
        while (data.getTime().getTime() <= fim.getTime()) {
            aRetornar.add((Calendar) data.clone());
            data.add(Calendar.MONTH, 1);
        }
        return aRetornar;
    }

    public static String nomeDoMes(int m) {
        String month = "invalid";
        String[] months = dfs.getMonths();
        if (m >= 0 && m <= 11) {
            month = months[m];
        }
        return month;
    }

    public static Date amanhaUltimoSegundo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }
    
    public static Date ultimoDiaDoMes() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        cal.add(Calendar.MONTH, 1);
        cal.add(Calendar.DAY_OF_MONTH, -1);
        return cal.getTime();
    }
    
    

    public static Date depoisDeAmanhaPrimeiroSegundo() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, 2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 1);
        return cal.getTime();
    }

    public static String formatddMMyyyy(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        return format.format(date);
    }

    public static void main(String[] args) {

        System.out.println(depoisDeAmanhaPrimeiroSegundo());

        System.out.println(amanhaUltimoSegundo());

        System.out.println(formatddMMyyyy(new Date()));


        Locale ptBr = new Locale("pt", "BR");
        Locale.setDefault(ptBr);

        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.YEAR, 1975);
        cal.set(Calendar.MONTH, Calendar.AUGUST);
        cal.set(Calendar.DAY_OF_MONTH, 18);
        Date dataMenor = cal.getTime();

        cal = Calendar.getInstance();
        // cal.set(Calendar.YEAR, 2012);
        //  cal.set(Calendar.MONTH, Calendar.AUGUST);
        //  cal.set(Calendar.DAY_OF_MONTH, 31);
        Date dataMaior = cal.getTime();

        System.out.println("diferencaDatas I: " + diferencaMesesInteira(dataMenor, dataMaior) + " meses");
        System.out.println("diferencaDatas F: " + diferencaMesesFracionada(dataMenor, dataMaior) + " meses");
        System.out.println("diferencaDatas D: " + diferencaDiasInteira(dataMenor, dataMaior));


        for (Calendar m : mesesEntreDatas(dataMenor, dataMaior)) {
            System.out.println(nomeDoMes(m.get(Calendar.MONTH)));
        }
    }

    public static int diferencaMesesInteira(Date dataAnterior, Date dataPosterior) {
        if (dataAnterior.after(dataPosterior)) {
            return 0;
        }
        Calendar calMenor = Calendar.getInstance();
        calMenor.setTime(dataAnterior);
        Calendar calMaior = Calendar.getInstance();
        calMaior.setTime(dataPosterior);
        int dAno = calMaior.get(YEAR) - calMenor.get(YEAR);
        int dMes = calMaior.get(MONTH) - calMenor.get(MONTH);
        int dDia = calMaior.get(DAY_OF_MONTH) - calMenor.get(DAY_OF_MONTH);

        if (dMes < 0) {
            dMes += 12;
            dAno--;
        }
        if (dDia < 0) {
            dMes--;
        }

        return dMes + dAno * 12;
    }

    public static double diferencaMesesFracionada(Date dataAnterior, Date dataPosterior) {
        if (dataAnterior.after(dataPosterior)) {
            return 0;
        }
        Calendar calMenor = Calendar.getInstance();
        calMenor.setTime(dataAnterior);
        Calendar calMaior = Calendar.getInstance();
        calMaior.setTime(dataPosterior);
        int dAno = calMaior.get(YEAR) - calMenor.get(YEAR);
        int dMes = calMaior.get(MONTH) - calMenor.get(MONTH);
        int dDia = calMaior.get(DAY_OF_MONTH) - calMenor.get(DAY_OF_MONTH);

        if (dMes < 0) {
            dMes += 12;
            dAno--;
        }
        if (dDia < 0) {
            dDia += quantidadeDiasMesAnterior(dataPosterior);
            dMes--;
        }
        return dAno * 12 + dMes + (dDia / (double) quantidadeDiasMesAnterior(dataPosterior));
    }

    public static int getDiasNoMes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return getDiasNoMes(cal.get(Calendar.MONTH) + 1, cal.get(Calendar.YEAR));
    }

    public static int getDiasNoMes(int mes, int ano) {
        if (mes == 2 && isBissexto(ano)) {
            return 29;
        }
        return DIAS_NO_MES[mes];
    }

    public static boolean isBissexto(int ano) {
        if (ano % 400 == 0) {
            return true;
        }
        if (ano % 100 == 0) {
            return false;
        }
        if (ano % 4 == 0) {
            return true;
        }
        return false;
    }

    public static int quantidadeDiasMesAnterior(Date data) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(data);
        cal.set(DAY_OF_MONTH, 1);
        cal.add(Calendar.MONTH, -1);
        return getDiasNoMes(cal.getTime());
    }

    public static int diferencaDiasInteira(Date dataAnterior, Date dataPosterior) {
        Calendar calAnterior = Calendar.getInstance();
        calAnterior.setTime(dataAnterior);
        calAnterior.set(HOUR_OF_DAY, 0);
        calAnterior.set(MINUTE, 0);
        calAnterior.set(SECOND, 0);
        calAnterior.set(MILLISECOND, 0);
        Date dataAnteriorSemHora = calAnterior.getTime();

        Calendar calPosterior = Calendar.getInstance();
        calPosterior.setTime(dataPosterior);
        calPosterior.set(HOUR_OF_DAY, 13);
        calPosterior.set(MINUTE, 0);
        calPosterior.set(SECOND, 0);
        calPosterior.set(MILLISECOND, 0);
        Date dataPosteriorSemHora = calPosterior.getTime();
        if (dataAnteriorSemHora.after(dataPosteriorSemHora)) {
            return 0;
        }

        Long diferenca = (dataPosteriorSemHora.getTime() - dataAnteriorSemHora.getTime()) / (24 * 60 * 60 * 1000);
        return diferenca.intValue();
    }
}
