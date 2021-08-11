/*
 * Decompiled with CFR 0_125.
 */
import br.gov.pbh.desif.model.registros.RegUtil;
import java.io.PrintStream;

public class formataNumero {
    int posicao;

    public String iniciar(String entrada) {
        RegUtil util = new RegUtil();
        String numero = entrada;
        if (util.verificaCasasDecimais(numero)) {
            numero = this.verificaPontoDecimal(numero);
            numero = "R$ " + this.inserePontoMilhar(numero);
            System.out.println("N\u00famero formatado: " + numero);
        } else {
            System.out.println("Deu erro!!!!!!");
        }
        return numero;
    }

    public String verificaPontoDecimal(String entrada) {
        String resultado = entrada;
        for (int i = 0; i < resultado.length(); ++i) {
            if ('.' != resultado.charAt(i)) continue;
            this.posicao = i;
            resultado = this.trocaPontoPorVirgula(resultado);
        }
        return resultado;
    }

    public String trocaPontoPorVirgula(String entrada) {
        String resultadoTroca = entrada = entrada.replace('.', ',');
        return resultadoTroca;
    }

    public String inserePontoMilhar(String entrada) {
        String resultadoInserePonto = entrada;
        int cont = 1;
        for (int i = this.posicao - 1; i > 0; --i) {
            char ponto = '.';
            if (cont == 3) {
                resultadoInserePonto = resultadoInserePonto.substring(0, i) + ponto + resultadoInserePonto.substring(i, resultadoInserePonto.length());
                cont = 0;
            }
            ++cont;
        }
        return resultadoInserePonto;
    }
}

