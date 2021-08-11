
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal;
import java.util.Calendar;
import java.util.GregorianCalendar;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            RegUtil, Data

public class Registro0420
{

    DemonstrativoRateioMensal demonstrativoRateioMensal;
    private RegUtil regUtil;
    private Data dt;
    private String numLinha;
    private String registroValidacao;
    private String codDependencia;
    private String anoMesCompetencia;
    private String descricaoDetalhada;
    private String valorReceitaRateada;
    private String tipoPartida;
    private String codigoEvento;
    private String token[];
    private int linha;
    String registro;

    public Registro0420(String token[], int linha)
    {
        registro = token[1];
        regUtil = new RegUtil();
        dt = new Data();
        this.token = token;
        this.linha = linha;
        verNumLinha();
        verRegistro();
        verCodDependencia();
        verAnoMesCompetencia();
        verCodigoEvento();
        verTipoPartida();
        verValorReceitaRateada();
        verDescricaoDetalhada();
    }

    private void verNumLinha()
    {
        int coluna = 1;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de seis: ";
        numLinha = regUtil.contCaracterRegistro(linha, token[0].trim(), 6, coluna, registro);
        if(numLinha.equals(""))
            regUtil.setErro(linha, "EG013", coluna, (short)1, registro);
        else
        if(!regUtil.isInteiro(numLinha))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(numLinha).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.validaSequenciaLinha(linha, numLinha))
        {
            String txtSolucao = (new StringBuilder()).append("Numero da linha errado: ").append(numLinha).toString();
            regUtil.setErro(linha, "EG003", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(numLinha))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(numLinha).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verRegistro()
    {
        int coluna = 2;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de quatro: ";
        registroValidacao = regUtil.contCaracterRegistro(linha, token[1].trim(), 4, coluna, registro);
        if(!regUtil.verificaCasasDecimais(numLinha))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(numLinha).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCodDependencia()
    {
        int coluna = 3;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de quinze: ";
        codDependencia = regUtil.contCaracterRegistro(linha, token[2].trim(), 15, coluna, registro);
        if(codDependencia.equals(""))
            regUtil.setErro(linha, "EG002", coluna, (short)1, registro);
        else
        if(!regUtil.verificaCasasDecimais(codDependencia))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(codDependencia).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verAnoMesCompetencia()
    {
        int coluna = 4;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de seis: ";
        anoMesCompetencia = regUtil.contCaracterRegistro(linha, token[3].trim(), 6, coluna, registro);
        if(!regUtil.verificaCasasDecimais(anoMesCompetencia))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(anoMesCompetencia).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
        String dataSeparada[] = dt.separaData(anoMesCompetencia);
        int mesSeparado = Integer.parseInt(dataSeparada[1]);
        int anoSeparado = Integer.parseInt(dataSeparada[0]);
        Calendar calendario = GregorianCalendar.getInstance();
        int anoAtual = calendario.get(1);
        if(anoMesCompetencia == null || anoMesCompetencia.equals("") || !dt.validaData(anoMesCompetencia, "yyyyMM") || dt.validaDiferencaParaAnoCorrente(anoMesCompetencia, "yyyyMM", ">", 10) || dt.validaDiferencaParaAnoCorrente(anoMesCompetencia, "yyyyMM", "<", -10))
            regUtil.setErro(linha, "EG007", coluna, (short)1, registro);
    }

    private void verDescricaoDetalhada()
    {
        int coluna = 5;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de duzentos e cinquenta e cinco (255): ";
        descricaoDetalhada = regUtil.contCaracterRegistro(linha, token[4].trim(), 255, coluna, registro);
        if(descricaoDetalhada.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da dependencia: ").append(codDependencia).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).append(", valor do rateio: ").append(valorReceitaRateada).toString();
            regUtil.setErro(linha, "EC015", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verTipoPartida()
    {
        int coluna = 7;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de um: ";
        tipoPartida = regUtil.contCaracterRegistro(linha, token[6].trim(), 1, coluna, registro);
        if(tipoPartida.equals(""))
            regUtil.setErro(linha, "EG017", coluna, (short)1, registro);
        else
        if(!regUtil.isInteiro(tipoPartida))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(tipoPartida).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(tipoPartida))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(tipoPartida).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCodigoEvento()
    {
        int coluna = 8;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de tr\352s: ";
        codigoEvento = regUtil.contCaracterRegistro(linha, token[7].trim(), 3, coluna, registro);
        if(codigoEvento.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).append(", valor do rateio: ").append(valorReceitaRateada).append(", tipo de partida: ").append(tipoPartida).toString();
            regUtil.setErro(linha, "EC018", coluna, (short)1, registro, txtSolucao);
        } else
        if(codigoEvento.equals("113"))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).append(", valor do rateio: ").append(valorReceitaRateada).append(", tipo de partida: ").append(tipoPartida).append(", c\363digo do evento: ").append(codigoEvento).toString();
            regUtil.setErro(linha, "EC020", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isInteiro(codigoEvento))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(codigoEvento).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(codigoEvento))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(codigoEvento).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verValorReceitaRateada()
    {
        int coluna = 6;
        String txtErroCasasDecimais = "Quantidade de casas decimais diferente de dezoito: ";
        valorReceitaRateada = regUtil.contCaracterRegistro(linha, token[5].trim(), 18, coluna, registro);
        valorReceitaRateada = regUtil.trocaVirgulaPonto(valorReceitaRateada);
        if(valorReceitaRateada.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append(", per\355odo de compet\352ncia: ").append(anoMesCompetencia).append(", tipo de partida: ").append(tipoPartida).toString();
            regUtil.setErro(linha, "EC016", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isInteiro(codigoEvento) || Double.parseDouble(valorReceitaRateada) < 0.0D)
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(valorReceitaRateada).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(valorReceitaRateada))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(valorReceitaRateada).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    public DemonstrativoRateioMensal getDemonstrativoRateioMensal()
    {
        java.util.Date anoMesCompet = regUtil.parseData((new StringBuilder()).append(anoMesCompetencia).append("01").toString(), "yyyyMMdd");
        demonstrativoRateioMensal = new DemonstrativoRateioMensal(new Long(Long.parseLong(numLinha)), new Long(Long.parseLong(numLinha)), codDependencia, anoMesCompet, descricaoDetalhada, new Double(Double.parseDouble(regUtil.trocaVirgulaPonto(valorReceitaRateada))), new Short(Short.parseShort(tipoPartida)), new Integer(Integer.parseInt(codigoEvento)));
        return demonstrativoRateioMensal;
    }
}