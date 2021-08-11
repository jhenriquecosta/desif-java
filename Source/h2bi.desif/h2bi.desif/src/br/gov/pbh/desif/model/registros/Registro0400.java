

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            RegUtil, Data

public class Registro0400
{

    private IdentificacaoDependencia dependencia;
    private RegUtil regUtil;
    private Data dt;
    private String numLinha;
    private String codDependencia;
    private String indInscricaoMunicipal;
    private String cnpjProprio;
    private String tipoDependencia;
    private String endDependencia;
    private String cnpjUnificado;
    private String codMunicipio;
    private String contabilidadePropria;
    private String datInicParalizacao;
    private String datFimParalizacao;
    private String token[];
    private int linha;
    String registro;

    public Registro0400(String token[], int linha)
    {
        registro = token[1];
        regUtil = new RegUtil();
        dt = new Data();
        this.token = token;
        this.linha = linha;
        verNumLinha();
        verCodDependencia();
        verIndInscricaoMunicipal();
        verCnpjProprio();
        verTipoDependencia();
        verEndDependencia();
        verCnpjUnificado();
        verCodMunicipio();
        verDatInicParalizacao();
        verDatFimParalizacao();
        verContabilidadePropria();
    }

    private void verNumLinha()
    {
        int coluna = 1;
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
        }
    }

    private void verCodDependencia()
    {
        int coluna = 3;
        codDependencia = regUtil.contCaracterRegistro(linha, token[2].trim(), 15, coluna, registro);
        if(codDependencia.equals(""))
            regUtil.setErro(linha, "EG002", coluna, (short)1, registro);
    }

    private void verIndInscricaoMunicipal()
    {
        int coluna = 4;
        indInscricaoMunicipal = regUtil.contCaracterRegistro(linha, token[3].trim(), 1, coluna, registro);
        if(indInscricaoMunicipal.equals("") || (!indInscricaoMunicipal.equals("1")) & (!indInscricaoMunicipal.equals("2")))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da Depend\352ncia: ").append(codDependencia).append("<BR>").append("Indicador de Inscri\347\343o municipal: ").append(indInscricaoMunicipal).toString();
            regUtil.setErro(linha, "ED007", coluna, (short)1, registro, txtSolucao);
        }
        if(!regUtil.isInteiro(indInscricaoMunicipal))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(indInscricaoMunicipal).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCnpjProprio()
    {
        int coluna = 5;
        cnpjProprio = regUtil.contCaracterRegistro(linha, token[4].trim(), 6, coluna, registro);
    }

    private void verTipoDependencia()
    {
        int coluna = 6;
        tipoDependencia = regUtil.contCaracterRegistro(linha, token[5].trim(), 2, coluna, registro);
        if(tipoDependencia.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append("<BR>CNPJ pr\363prio: ").append(cnpjProprio).toString();
            regUtil.setErro(linha, "ED017", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isInteiro(tipoDependencia))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(tipoDependencia).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verEndDependencia()
    {
        int coluna = 7;
        endDependencia = regUtil.contCaracterRegistro(linha, token[6].trim(), 100, coluna, registro);
        if(indInscricaoMunicipal.equals("2") && endDependencia.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append("<BR>indicador de inscri\347\343o municipal: ").append(indInscricaoMunicipal).toString();
            regUtil.setErro(linha, "ED009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCnpjUnificado()
    {
        int coluna = 8;
        cnpjUnificado = regUtil.contCaracterRegistro(linha, token[7].trim(), 6, coluna, registro);
        if(cnpjUnificado.equals(""))
            regUtil.setErro(linha, "EG004", coluna, (short)1, registro);
    }

    private void verCodMunicipio()
    {
        int coluna = 9;
        codMunicipio = regUtil.contCaracterRegistro(linha, token[8].trim(), 7, coluna, registro);
        if(codMunicipio.equals(""))
            regUtil.setErro(linha, "EG010", coluna, (short)1, registro);
        else
        if(!regUtil.isInteiro(codMunicipio))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(codMunicipio).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verContabilidadePropria()
    {
        int coluna = 10;
        contabilidadePropria = regUtil.contCaracterRegistro(linha, token[9].trim(), 1, coluna, registro);
        if(contabilidadePropria.equals("") || (!contabilidadePropria.equals("1")) & (!contabilidadePropria.equals("2")))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append("<BR>identifica\347\343o de contabilidade pr\363pria: ").append(contabilidadePropria).toString();
            regUtil.setErro(linha, "EM010", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verDatInicParalizacao()
    {
        int coluna = 11;
        datInicParalizacao = regUtil.contCaracterRegistro(linha, token[10].trim(), 8, coluna, registro);
        if(!datInicParalizacao.trim().equals("") && datInicParalizacao != null)
            if(!dt.validaData(datInicParalizacao, "yyyyMMdd"))
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append(" <BR>data de in\355cio da paralisa\347\343o: ").append(datInicParalizacao).toString();
                regUtil.setErro(linha, "EG005", coluna, (short)1, registro, txtSolucao);
            } else
            if(!dt.validaDiferencaParaAnoCorrente(datInicParalizacao, "yyyyMMdd", ">", -10))
                regUtil.setAlerta(linha, "A006", coluna, (short)1, registro);
    }

    private void verDatFimParalizacao()
    {
        int coluna = 12;
        datFimParalizacao = regUtil.contCaracterRegistro(linha, token[11].trim(), 8, coluna, registro);
        if(!datFimParalizacao.trim().equals("") && datFimParalizacao != null)
        {
            if(!dt.validaData(datFimParalizacao, "yyyyMMdd"))
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append(" <BR>data de fim da paralisa\347\343o: ").append(datFimParalizacao).toString();
                regUtil.setErro(linha, "EG005", coluna, (short)1, registro, txtSolucao);
            } else
            if(datInicParalizacao.equals("") & (!datFimParalizacao.equals("")))
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append("<BR>data in\355cio da paralisa\347\343o: ").append(datInicParalizacao).append("<BR>data fim da paralisa\347\343o: ").append(datFimParalizacao).toString();
                regUtil.setErro(linha, "ED010", coluna, (short)1, registro, txtSolucao);
            } else
            if(dt.validaData(datFimParalizacao, "yyyyMMdd") && dt.validaData(datInicParalizacao, "yyyyMMdd") && (!dt.comparaDataMaior(datFimParalizacao, datInicParalizacao, "yyyyMMdd") || datFimParalizacao.equals(datInicParalizacao)))
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append("<BR>data in\355cio da paralisa\347\343o: ").append(datInicParalizacao).append("<BR>data fim da paralisa\347\343o: ").append(datFimParalizacao).toString();
                regUtil.setErro(linha, "ED011", coluna, (short)1, registro, txtSolucao);
            }
            if(dt.compararNumDiasEntreDatas(datInicParalizacao, datFimParalizacao, "yyyyMMdd") > 180L)
            {
                String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append("<BR>inicio paralisa\347\343o:  ").append(datInicParalizacao).append("<BR>fim da paralisa\347\343o: ").append(datFimParalizacao).toString();
                regUtil.setAlerta(linha, "A003", coluna, (short)1, registro);
            }
        }
    }

    public IdentificacaoDependencia getDependenciaPojo()
    {
        try
        {
            java.util.Date anoMIP = regUtil.parseData(datInicParalizacao, "yyyyMMdd");
            java.util.Date anoMFP = regUtil.parseData(datFimParalizacao, "yyyyMMdd");
            dependencia = new IdentificacaoDependencia(new Long(Long.parseLong(numLinha)), new Integer(Integer.parseInt(tipoDependencia)), new Long(Long.parseLong(codMunicipio)), new Long(Long.parseLong(numLinha)), codDependencia, new Short(Short.parseShort(indInscricaoMunicipal)), new Short(Short.parseShort(contabilidadePropria)), cnpjUnificado, cnpjProprio, endDependencia, anoMIP, anoMFP);
            return dependencia;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}