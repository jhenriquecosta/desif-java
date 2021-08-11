/*
 * Decompiled with CFR 0_125.
 */
import java.io.PrintStream;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class NewClass {
    public String verificaDataLiminar(Date dataCompetenciaDeclaracaop) {
        try {
            String mensagem = "Aten\u00e7\u00e3o Senhor Contribuinte: caso o senhor tenha sido beneficiado com a redu\u00e7\u00e3o de al\u00edquota de ISS em raz\u00e3o de liminar expedida pelo TJMG no processo judicial n\u00ba 0279811-03.2014.8.13.0000, de 11/09/2014, alertamos que poder\u00e1 ser cobrada a diferan\u00e7a de al\u00edquota, em caso de revers\u00e3o da decis\u00e3o judicial.";
            String resposta = new String(mensagem.getBytes("UTF-8"), "UTF-8");
            SimpleDateFormat formataData = new SimpleDateFormat("MM/yyyy");
            Date dataLiminar = null;
            dataLiminar = formataData.parse("05/2014");
            Date dataCompetenciaDeclaracao = dataCompetenciaDeclaracaop;
            if (dataLiminar.equals(dataCompetenciaDeclaracao)) {
                System.out.println("Escrever por que \u00e9 igual");
            } else if (dataCompetenciaDeclaracao.after(dataLiminar)) {
                System.out.println("Escrever por que \u00e9 depois");
            } else {
                resposta = null;
            }
            return resposta;
        }
        catch (ParseException ex) {
            ex.printStackTrace();
            Object resposta = null;
        }
        catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            Object resposta = null;
        }
        return null;
    }

    public static void main(String[] args) {
        Date dataCompetenciaDeclaracao = null;
        NewClass nc = new NewClass();
        float teste = 2.2f;
        try {
            SimpleDateFormat formataData = new SimpleDateFormat("MM/yyyy");
            dataCompetenciaDeclaracao = formataData.parse("04/2014");
            String resp = nc.verificaDataLiminar(dataCompetenciaDeclaracao);
            System.out.println(resp);
        }
        catch (ParseException ex) {
            Logger.getLogger(NewClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}

