/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.registros.RegUtil;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Data {
    public RegUtil rg = new RegUtil();
    public String x01;

    public String formataData(Date dt, String formato) {
        String resp = null;
        SimpleDateFormat format = new SimpleDateFormat(formato);
        resp = format.format(dt);
        return resp;
    }

    public boolean validaData(String data, String formato) {
        boolean resp = true;
        if (data.length() != formato.length()) {
            resp = false;
        } else {
            try {
                SimpleDateFormat format = new SimpleDateFormat(formato);
                format.setLenient(false);
                Date date = format.parse(data);
            }
            catch (ParseException ex) {
                resp = false;
            }
        }
        return resp;
    }

    public boolean validaMes(String data, String formato, int mes) {
        String[] array;
        boolean resp = false;
        int mesAux = 0;
        if (this.validaData(data, formato) && (mesAux = Integer.parseInt((array = this.separaData(data))[1])) == mes) {
            resp = true;
        }
        return resp;
    }

    public boolean validaAno(String data, String formato, int ano) {
        String[] array;
        boolean resp = false;
        int anoAux = 0;
        if (this.validaData(data, formato) && (anoAux = Integer.parseInt((array = this.separaData(data))[0])) == ano) {
            resp = true;
        }
        return resp;
    }

    public long compararNumDiasEntreDatas(String data1, String data2, String formato) {
        SimpleDateFormat df = new SimpleDateFormat(formato);
        long dias = 0L;
        if (this.validaData(data1, formato) && this.validaData(data2, formato)) {
            try {
                Date d1 = df.parse(data1);
                Date d2 = df.parse(data2);
                long dt = d2.getTime() - d1.getTime() + 3600000L;
                dias = dt / 86400000L;
            }
            catch (ParseException evt) {
                dias = 0L;
            }
        }
        return dias;
    }

    public boolean comparaDataMaior(String data1, String data2, String formato) {
        boolean resp = false;
        if (this.validaData(data1, formato) & this.validaData(data2, formato)) {
            try {
                SimpleDateFormat format = new SimpleDateFormat(formato);
                Date dt1 = format.parse(data1);
                Date dt2 = format.parse(data2);
                if (dt1.after(dt2)) {
                    resp = true;
                }
            }
            catch (ParseException ex) {
                resp = false;
            }
        }
        return resp;
    }

    public boolean validaDiferencaParaAnoCorrente(String data, String formato, String op, int delta) {
        boolean resp = false;
        Date anoCorrente = new Date();
        SimpleDateFormat format = new SimpleDateFormat(formato);
        String stAnoCorrente = format.format(anoCorrente);
        if (this.validaData(data, formato)) {
            String[] arData = this.separaData(data);
            String[] arAnoCorrente = this.separaData(stAnoCorrente);
            int anoDt = Integer.parseInt(arData[0]);
            int anCor = Integer.parseInt(arAnoCorrente[0]);
            anCor += delta;
            if (op.equals(">")) {
                if (anoDt > anCor) {
                    resp = true;
                }
            } else if (op.equals("<")) {
                if (anoDt < anCor) {
                    resp = true;
                }
            } else if (op.equals("=") && anoDt == anCor) {
                resp = true;
            }
        }
        return resp;
    }

    public boolean validaIgualdadeEntreData(String data1, String data2, String formato, int tipo) {
        boolean resp = false;
        if (this.validaData(data1, formato) && this.validaData(data2, formato)) {
            String[] arData1 = this.separaData(data1);
            String[] arData2 = this.separaData(data2);
            switch (tipo) {
                case 0: {
                    if (!arData1[0].equals(arData2[0])) break;
                    resp = true;
                    break;
                }
                case 1: {
                    if (!arData1[1].equals(arData2[1])) break;
                    resp = true;
                    break;
                }
                case 2: {
                    if (arData1[2] == null || arData2[2] == null || !arData1[2].equals(arData2[2])) break;
                    resp = true;
                    break;
                }
                case 3: {
                    if (!data1.equals(data2)) break;
                    resp = true;
                }
            }
        }
        return resp;
    }

    public String[] separaData(String data) {
        String[] array = new String[3];
        switch (data.length()) {
            case 4: {
                array[0] = data.substring(0, 4);
            }
            case 6: {
                array[0] = data.substring(0, 4);
                array[1] = data.substring(4, 6);
                break;
            }
            case 8: {
                array[0] = data.substring(0, 4);
                array[1] = data.substring(4, 6);
                array[2] = data.substring(6, 8);
                break;
            }
            case 10: {
                String tk = data.substring(4, 5);
                array = data.split(tk);
            }
        }
        return array;
    }

    public int ultimoDiaMes(int mes, int ano) {
        int resp = 0;
        Calendar calendar = Calendar.getInstance();
        calendar.set(2, --mes);
        calendar.set(1, ano);
        resp = calendar.getActualMaximum(5);
        return resp;
    }

    public boolean isNumeric(String s) {
        boolean resp = true;
        try {
            Double.parseDouble(s);
        }
        catch (NumberFormatException ex) {
            resp = false;
        }
        return resp;
    }

    public static void main(String[] args) {
        Data t = new Data();
        System.out.println(t.ultimoDiaMes(1, 2007));
    }
}

