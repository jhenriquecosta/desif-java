

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.bhiss.utilitarios.validadores.CpfCnpj;
import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.service.util.ConstantsBusiness;
import java.util.Date;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            RegUtil, Data

public class Registro0000
{

    private IdentificacaoDeclaracao declaracao;
    private RegUtil regUtil;
    private Data dt;
    private String numLinha;
    private String cnpj;
    private String nome;
    private String tipoInstituicao;
    private String codMunicipio;
    private String anoMesInicCompet;
    private String anoMesFimCompet;
    private String moduloDeclaracao;
    private String tipoDeclaracao;
    private String protcDeclaracaoAnterior;
    private String tipoConsolidacao;
    private String cnpjResponsavelRecolhimento;
    private String token[];
    private int linha;
    String registro;

    public Registro0000(String token[], int line)
    {
        try
        {
            registro = token[1];
            regUtil = new RegUtil();
            dt = new Data();
            this.token = token;
            linha = line;
            verLinha();
            verCnjp();
            verNome();
            verTipoInstituicao();
            verCodMunicipio();
            verModuloDeclaracao();
            verAnoMesInicCompet();
            verAnoMesFimCompet();
            verTipoDeclaracao();
            verTipoConsolidacao();
            verCnpjResponsavelRecolhimento();
            verprotcDeclaracaoAnterio();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    private void verLinha()
        throws Exception
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
        }
        if(!regUtil.validaSequenciaLinha(linha, numLinha))
        {
            String txtSolucao = (new StringBuilder()).append("Numero da linha errado: ").append(numLinha).toString();
            regUtil.setErro(linha, "EG003", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCnjp()
        throws Exception
    {
        int coluna = 3;
        cnpj = regUtil.contCaracterRegistro(linha, token[2].trim(), 8, coluna, registro);
        if(cnpj.equals("") || !regUtil.isNumeric(cnpj))
            regUtil.setErro(linha, "ED001", coluna, (short)1, registro);
    }

    private void verNome()
        throws Exception
    {
        int coluna = 4;
        nome = regUtil.contCaracterRegistro(linha, token[3].trim(), 100, coluna, registro);
        if(nome.equals(""))
            regUtil.setErro(linha, "ED002", coluna, (short)1, registro);
    }

    private void verTipoInstituicao()
        throws Exception
    {
        int coluna = 5;
        tipoInstituicao = regUtil.contCaracterRegistro(linha, token[4].trim(), 1, coluna, registro);
        if(tipoInstituicao.equals(""))
            regUtil.setErro(linha, "ED020", coluna, (short)1, registro);
    }

    private void verCodMunicipio()
        throws Exception
    {
        int coluna = 6;
        codMunicipio = regUtil.contCaracterRegistro(linha, token[5].trim(), 7, coluna, registro);
        if(codMunicipio.equals(""))
            regUtil.setErro(linha, "EG010", coluna, (short)1, registro);
        else
        if(!regUtil.isInteiro(codMunicipio))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(codMunicipio).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verAnoMesInicCompet()
        throws Exception
    {
        int coluna = 7;
        anoMesInicCompet = regUtil.contCaracterRegistro(linha, token[6].trim(), 6, coluna, registro);
        if(anoMesInicCompet == null || anoMesInicCompet.equals("") || !dt.validaData(anoMesInicCompet, "yyyyMM"))
            regUtil.setErro(linha, "EG007", coluna, (short)1, registro);
        else
        if(!dt.validaDiferencaParaAnoCorrente(anoMesInicCompet, "yyyyMM", ">", -10))
        {
            String txtSolucao = (new StringBuilder()).append("Ano de compet\352ncia informado: ").append(anoMesInicCompet).toString();
            regUtil.setErro(linha, "ED004", coluna, (short)1, registro, txtSolucao);
        }
        if(moduloDeclaracao.equals("1"))
        {
            if(!dt.validaMes(anoMesInicCompet, "yyyyMM", 1))
            {
                String txtAlerta = (new StringBuilder()).append("M\363dulo declara\347\343o: M\363dulo Cont\341bil, m\352s inicio de compet\352ncia informado: ").append(anoMesInicCompet).toString();
                regUtil.setAlerta(linha, "A002", coluna, (short)2, registro, txtAlerta);
            }
        } else
        if(moduloDeclaracao.equals("3") && !dt.validaMes(anoMesInicCompet, "yyyyMM", 1))
        {
            String txtAlerta = (new StringBuilder()).append("M\363dulo declara\347\343o: M\363dulo de Informa\347\365es Comuns aos Mun\355cipios, m\352s inicio de compet\352ncia informado: ").append(anoMesInicCompet).toString();
            regUtil.setAlerta(linha, "A007", coluna, (short)2, registro, txtAlerta);
        }
        if(dt.validaDiferencaParaAnoCorrente(anoMesInicCompet, "yyyyMM", ">", 0))
        {
            String txtSolucao = (new StringBuilder()).append("Ano de compet\352ncia informado: ").append(anoMesInicCompet).toString();
            regUtil.setErro(linha, "ED005", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verAnoMesFimCompet()
        throws Exception
    {
        int coluna = 8;
        anoMesFimCompet = regUtil.contCaracterRegistro(linha, token[7].trim(), 6, coluna, registro);
        if(anoMesFimCompet == null || anoMesFimCompet.equals("") || !dt.validaData(anoMesFimCompet, "yyyyMM"))
            regUtil.setErro(linha, "EG007", coluna, (short)1, registro);
        else
        if(dt.validaDiferencaParaAnoCorrente(anoMesFimCompet, "yyyyMM", ">", 0))
        {
            String txtSolucao = (new StringBuilder()).append("Ano de compet\352ncia informado: ").append(anoMesFimCompet).toString();
            regUtil.setErro(linha, "ED005", coluna, (short)1, registro, txtSolucao);
        } else
        if(!dt.validaDiferencaParaAnoCorrente(anoMesFimCompet, "yyyyMM", ">", -10))
        {
            String txtSolucao = (new StringBuilder()).append("Ano de compet\352ncia informado: ").append(anoMesFimCompet).toString();
            regUtil.setErro(linha, "ED004", coluna, (short)1, registro, txtSolucao);
        } else
        if(!dt.validaIgualdadeEntreData(anoMesInicCompet, anoMesFimCompet, "yyyyMM", 0))
        {
            String txtSolucao = (new StringBuilder()).append("Ano de compet\352ncia inicial informado: ").append(anoMesInicCompet.substring(0, 4)).append(", ano de compet\352ncia final informado: ").append(anoMesFimCompet.substring(0, 4)).toString();
            regUtil.setErro(linha, "ED052", coluna, (short)1, registro, txtSolucao);
        } else
        if(dt.comparaDataMaior(anoMesInicCompet, anoMesFimCompet, "yyyyMM"))
        {
            String txtSolucao = (new StringBuilder()).append("Ano e m\352s de in\355cio da compet\352ncia da declara\347\343o informado: ").append(anoMesInicCompet).append(", \351 maior que o ano e m\352s de fim da compet\352ncia da declara\347\343o informado: ").append(anoMesFimCompet).toString();
            regUtil.setErro(linha, "ED054", coluna, (short)1, registro, txtSolucao);
        }
        if(moduloDeclaracao.equals("2"))
        {
            String txtSolucao = (new StringBuilder()).append("Ano-m\352s in\355cio: ").append(anoMesInicCompet).append(" <BR>ano-m\352s fim da compet\352ncia: ").append(anoMesFimCompet).toString();
            if(!dt.validaIgualdadeEntreData(anoMesInicCompet, anoMesFimCompet, "yyyyMM", 3))
                regUtil.setErro(linha, "ED023", coluna, (short)1, registro, txtSolucao);
        }
        if(moduloDeclaracao.equals("1") || moduloDeclaracao.equals("3"))
        {
            String txtModulo;
            if(moduloDeclaracao.equals("1"))
                txtModulo = "M\363dulo Cont\341bil";
            else
                txtModulo = "M\363dulo de Informa\347\365es Comuns aos Mun\355cipios";
            if(!dt.validaMes(anoMesFimCompet, "yyyyMM", 12))
            {
                String txtAlerta = (new StringBuilder()).append("M\363dulo declara\347\343o: ").append(txtModulo).append(", m\352s inicio de compet\352ncia informado: ").append(anoMesFimCompet).toString();
                regUtil.setAlerta(linha, "A001", coluna, (short)2, registro, txtAlerta);
            }
        }
    }

    private void verModuloDeclaracao()
        throws Exception
    {
        int coluna = 9;
        moduloDeclaracao = regUtil.contCaracterRegistro(linha, token[8].trim(), 1, coluna, registro);
        if(moduloDeclaracao.equals(""))
            regUtil.setErro(linha, "ED014", coluna, (short)1, registro);
        else
        if(!regUtil.isInteiro(moduloDeclaracao))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(moduloDeclaracao).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if((!moduloDeclaracao.equals("1")) & (!moduloDeclaracao.equals("2")) & (!moduloDeclaracao.equals("3")))
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o informado: ").append(moduloDeclaracao).toString();
            regUtil.setErro(linha, "ED015", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verTipoDeclaracao()
        throws Exception
    {
        int coluna = 10;
        tipoDeclaracao = regUtil.contCaracterRegistro(linha, token[9].trim(), 1, coluna, registro);
        if(tipoDeclaracao.equals(""))
            regUtil.setErro(linha, "ED018", coluna, (short)1, registro);
        else
        if(!regUtil.isInteiro(tipoDeclaracao))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(tipoDeclaracao).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(moduloDeclaracao.equals("3"))
        {
            if(!tipoDeclaracao.equals("1"))
            {
                String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(moduloDeclaracao).append(", Tipo de declara\347\343o informado: ").append(tipoDeclaracao).toString();
                regUtil.setErro(linha, "ED046", coluna, (short)1, registro, txtSolucao);
            }
        } else
        if(!tipoDeclaracao.equals("1") && !tipoDeclaracao.equals("2"))
        {
            String txtSolucao = (new StringBuilder()).append("Tipo de declara\347\343o informado: ").append(tipoDeclaracao).toString();
            regUtil.setErro(linha, "ED006", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verprotcDeclaracaoAnterio()
        throws Exception
    {
        int coluna = 11;
        protcDeclaracaoAnterior = regUtil.contCaracterRegistro(linha, token[10].trim(), 30, coluna, registro);
        if((moduloDeclaracao.equals("2") || moduloDeclaracao.equals("1")) && tipoDeclaracao.equals("2") && (protcDeclaracaoAnterior == null || protcDeclaracaoAnterior.equals("")))
        {
            String txtSolucao = (new StringBuilder()).append("Tipo de declara\347\343o: ").append(tipoDeclaracao).toString();
            regUtil.setErro(linha, "ED024", coluna, (short)1, registro, txtSolucao);
        }
        if(tipoDeclaracao.equals("1") && protcDeclaracaoAnterior != null && !protcDeclaracaoAnterior.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("Tipo de declara\347\343o: ").append(tipoDeclaracao).append("<BR>protocolo: ").append(protcDeclaracaoAnterior).toString();
            regUtil.setErro(linha, "ED026", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verTipoConsolidacao()
        throws Exception
    {
        int coluna = 12;
        tipoConsolidacao = regUtil.contCaracterRegistro(linha, token[11].trim(), 1, coluna, registro);
        if(!tipoConsolidacao.equals("") && !regUtil.isInteiro(tipoConsolidacao))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(tipoConsolidacao).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        }
        if(moduloDeclaracao.equals("2"))
        {
            if(tipoConsolidacao.equals(""))
            {
                String txtSolucao = (new StringBuilder()).append("Modulo da declara\347\343o: ").append(moduloDeclaracao).toString();
                regUtil.setErro(linha, "ED012", coluna, (short)1, registro, txtSolucao);
            }
            if(!verificaTipoConsolidacaoValidaMunicipio(tipoConsolidacao))
            {
                String txtSolucao1 = (new StringBuilder()).append("Tipo de consolida\347\343o: ").append(tipoConsolidacao).toString();
                regUtil.setErro(linha, "ED022", coluna, (short)1, registro, txtSolucao1);
            }
            if(!tipoConsolidacao.equals("1") && !tipoConsolidacao.equals("2") && !tipoConsolidacao.equals("3") && !tipoConsolidacao.equals("4"))
            {
                String txtSolucao2 = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(moduloDeclaracao).append(" <BR>tipo de consolida\347\343o: ").append(tipoConsolidacao).toString();
                regUtil.setErro(linha, "ED031", coluna, (short)1, registro, txtSolucao2);
            }
        }
        if(!moduloDeclaracao.equals("2") && !tipoConsolidacao.equals("") && tipoConsolidacao != null)
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(moduloDeclaracao).append(" <BR>tipo de consolida\347\343o: ").append(tipoConsolidacao).toString();
            regUtil.setErro(linha, "ED021", coluna, (short)1, registro, txtSolucao);
        }
        if((moduloDeclaracao.equals("1") || moduloDeclaracao.equals("3")) && !tipoConsolidacao.equals("") && tipoConsolidacao != null)
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(moduloDeclaracao).append(" <BR>tipo de consolida\347\343o: ").append(tipoConsolidacao).toString();
            regUtil.setErro(linha, "ED047", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCnpjResponsavelRecolhimento()
        throws Exception
    {
        int coluna = 13;
        cnpjResponsavelRecolhimento = regUtil.contCaracterRegistro(linha, token[12].trim(), 6, coluna, registro);
        if(!cnpjResponsavelRecolhimento.equals(""))
        {
            if(!CpfCnpj.validarCpfCnpj((new StringBuilder()).append(cnpj).append(cnpjResponsavelRecolhimento).toString()))
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append(cnpjResponsavelRecolhimento).toString();
                regUtil.setErro(linha, "EG004", coluna, (short)1, registro, txtSolucao);
            }
            if(moduloDeclaracao.equals("1"))
            {
                String txtSolucao = (new StringBuilder()).append("CNPJ respons\341vel pelo recolhimento: ").append(cnpjResponsavelRecolhimento).append(" <BR>m\363dulo da declara\347\343o: ").append(moduloDeclaracao).toString();
                regUtil.setErro(linha, "ED034", coluna, (short)1, registro, txtSolucao);
            }
        } else
        if(moduloDeclaracao.equals("2") && (tipoConsolidacao.equals("1") || tipoConsolidacao.equals("2")))
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(moduloDeclaracao).append("<BR>tipo de consolida\347\343o: ").append(tipoConsolidacao).toString();
            regUtil.setErro(linha, "ED013", coluna, (short)1, registro, txtSolucao);
        }
        if((moduloDeclaracao.equals("1") || moduloDeclaracao.equals("3")) && !cnpjResponsavelRecolhimento.equals("") && cnpjResponsavelRecolhimento != null)
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(moduloDeclaracao).append(", Respons\341vel pelo recolhimento: ").append(cnpjResponsavelRecolhimento).toString();
            regUtil.setErro(linha, "ED048", coluna, (short)1, registro, txtSolucao);
        }
        if(moduloDeclaracao.equals("2") && (tipoConsolidacao.equals("3") || tipoConsolidacao.equals("4")) && !cnpjResponsavelRecolhimento.equals("") && cnpjResponsavelRecolhimento != null)
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o: ").append(moduloDeclaracao).append(", tipo de consolida\347\343o: ").append(tipoConsolidacao).append(", respons\341vel pelo recolhimento: ").append(cnpjResponsavelRecolhimento).toString();
            regUtil.setErro(linha, "ED051", coluna, (short)1, registro, txtSolucao);
        }
    }

    public boolean verificaTipoConsolidacaoValidaMunicipio(String tipoConsolidacao)
    {
        boolean flag = false;
        for(int i = 0; i < ConstantsBusiness.TIPO_CONSOLIDACAO_ACEITA.length; i++)
            if(tipoConsolidacao.equals(ConstantsBusiness.TIPO_CONSOLIDACAO_ACEITA[i]))
                flag = true;

        return flag;
    }

    public IdentificacaoDeclaracao getDeclaracaoPojo()
    {
        try
        {
            Date anoMIC = regUtil.parseData((new StringBuilder()).append(anoMesInicCompet).append("01").toString(), "yyyyMMdd");
            String dataSeparada[] = dt.separaData(anoMesFimCompet);
            int ultimoDiaMes = dt.ultimoDiaMes(Integer.parseInt(dataSeparada[1]), Integer.parseInt(dataSeparada[0]));
            Date anoMFC = regUtil.parseData((new StringBuilder()).append(anoMesFimCompet).append(ultimoDiaMes).toString(), "yyyyMMdd");
            if(tipoConsolidacao.equals(""))
                declaracao = new IdentificacaoDeclaracao(new Integer(Integer.parseInt(numLinha)), tipoInstituicao.toUpperCase(), new Long(Long.parseLong(codMunicipio)), new Integer(Integer.parseInt(numLinha)), cnpj, nome, anoMIC, anoMFC, new Short(Short.parseShort(moduloDeclaracao)), new Short(Short.parseShort(tipoDeclaracao)), cnpjResponsavelRecolhimento, protcDeclaracaoAnterior);
            else
                declaracao = new IdentificacaoDeclaracao(new Integer(Integer.parseInt(numLinha)), tipoInstituicao.toUpperCase(), new Long(Long.parseLong(codMunicipio)), new Integer(Integer.parseInt(numLinha)), cnpj, nome, anoMIC, anoMFC, new Short(Short.parseShort(moduloDeclaracao)), new Short(Short.parseShort(tipoDeclaracao)), new Short(Short.parseShort(tipoConsolidacao)), cnpjResponsavelRecolhimento, protcDeclaracaoAnterior);
            return declaracao;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}