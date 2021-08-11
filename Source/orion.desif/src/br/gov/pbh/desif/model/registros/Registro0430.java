

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.ApuracaoReceita;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            RegUtil

public class Registro0430
{

    ApuracaoReceita apuracaoMensalIssqn;
    private RegUtil regUtil;
    private String numLinha;
    private String codDependencia;
    private String subTitulo;
    private String codTributacaoDesif;
    private String valorCreditoMensal;
    private String valorDebitoMensal;
    private String receitaDeclarada;
    private String deducaoReceitaDeclarada;
    private String descDeducao;
    private String baseCalculo;
    private String aliquotaIssqn;
    private String incentivoFiscal;
    private String descIncentivoFiscal;
    private String motivoNaoExigibilidade;
    private String processoMotivoNaoExigibilidade;
    private String token[];
    private int linha;
    private String registro;
    private String txtErroCasasDecimais;

    public Registro0430(String token[], int linha)
    {
        registro = token[1];
        regUtil = new RegUtil();
        this.token = token;
        this.linha = linha;
        txtErroCasasDecimais = "Quantidade de casas decimais diferente de duas: ";
        verNumLinha();
        verCodDependencia();
        verSubTitulo();
        verCodTributacaoDesif();
        verValorCreditoMensal();
        verValorDebitoMensal();
        verReceitaDeclarada();
        verDeducaoReceitaDeclarada();
        verDescDeducao();
        verBaseCalculo();
        verAliquotaIssqn();
        verIncentivoFiscal();
        verDescIncentivoFiscal();
        verMotivoNaoExigibilidade();
        verProcessoMotivoNaoExigibilidade();
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

    private void verSubTitulo()
    {
        int coluna = 4;
        subTitulo = regUtil.contCaracterRegistro(linha, token[3].trim(), 30, coluna, registro);
        if(subTitulo.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append("<BR>aliquota: ").append(aliquotaIssqn).toString();
            regUtil.setErro(linha, "EM071", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCodTributacaoDesif()
    {
        int coluna = 5;
        codTributacaoDesif = regUtil.contCaracterRegistro(linha, token[4].trim(), 20, coluna, registro);
        if(codTributacaoDesif.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo da depend\352ncia: ").append(codDependencia).append("<BR>subt\355tulo: ").append(subTitulo).toString();
            regUtil.setErro(linha, "EM004", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verValorCreditoMensal()
    {
        int coluna = 6;
        valorCreditoMensal = regUtil.contCaracterRegistro(linha, token[5].trim(), 19, coluna, registro);
        if(valorCreditoMensal.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(codDependencia).append("<BR>subt\355tulo: ").append(subTitulo).toString();
            regUtil.setErro(linha, "EM061", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(valorCreditoMensal))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(valorCreditoMensal).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(valorCreditoMensal))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(valorCreditoMensal).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verValorDebitoMensal()
    {
        int coluna = 7;
        valorDebitoMensal = regUtil.contCaracterRegistro(linha, token[6].trim(), 19, coluna, registro);
        if(valorDebitoMensal.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(codDependencia).append("<BR>subt\355tulo: ").append(subTitulo).toString();
            regUtil.setErro(linha, "EM062", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(valorDebitoMensal))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(valorDebitoMensal).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(valorDebitoMensal))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(valorDebitoMensal).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verReceitaDeclarada()
    {
        int coluna = 8;
        receitaDeclarada = regUtil.contCaracterRegistro(linha, token[7].trim(), 19, coluna, registro);
        if(receitaDeclarada.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(codDependencia).append("<BR>subt\355tulo: ").append(subTitulo).toString();
            regUtil.setErro(linha, "EM063", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(receitaDeclarada))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(receitaDeclarada).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(receitaDeclarada))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(receitaDeclarada).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verDeducaoReceitaDeclarada()
    {
        int coluna = 9;
        deducaoReceitaDeclarada = regUtil.contCaracterRegistro(linha, token[8].trim(), 19, coluna, registro);
        if(!deducaoReceitaDeclarada.equals(""))
            deducaoReceitaDeclarada = regUtil.parseZero(deducaoReceitaDeclarada);
        if(!deducaoReceitaDeclarada.equals(""))
            if(!regUtil.isNumeric(deducaoReceitaDeclarada))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(deducaoReceitaDeclarada).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(deducaoReceitaDeclarada))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(deducaoReceitaDeclarada).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verDescDeducao()
    {
        int coluna = 10;
        descDeducao = regUtil.contCaracterRegistro(linha, token[9].trim(), 255, coluna, registro);
        if(!deducaoReceitaDeclarada.equals("") && (descDeducao.equals("") || descDeducao == null))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(codDependencia).append("<BR>subt\355tulo: ").append(subTitulo).append("<BR>dedu\347\343o da receita declarada: ").append(deducaoReceitaDeclarada).toString();
            regUtil.setErro(linha, "EM029", coluna, (short)1, registro, txtSolucao);
        }
        if(deducaoReceitaDeclarada.equals("") && !descDeducao.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(codDependencia).append("<BR>descri\347\343o da dedu\347\343o da receita declarada: ").append(descDeducao).toString();
            regUtil.setErro(linha, "EM072", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verBaseCalculo()
    {
        int coluna = 11;
        baseCalculo = regUtil.contCaracterRegistro(linha, token[10].trim(), 19, coluna, registro);
        if(baseCalculo.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("c\363digo de depend\352ncia: ").append(codDependencia).append("<BR>subt\355tulo: ").append(subTitulo).toString();
            regUtil.setErro(linha, "EM030", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(baseCalculo))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(baseCalculo).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(baseCalculo))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(baseCalculo).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verAliquotaIssqn()
    {
        int coluna = 12;
        aliquotaIssqn = regUtil.contCaracterRegistro(linha, token[11].trim(), 7, coluna, registro);
        if(aliquotaIssqn.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(codDependencia).toString();
            regUtil.setErro(linha, "EM038", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(aliquotaIssqn))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(aliquotaIssqn).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(aliquotaIssqn))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(aliquotaIssqn).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verIncentivoFiscal()
    {
        int coluna = 13;
        incentivoFiscal = regUtil.contCaracterRegistro(linha, token[12].trim(), 19, coluna, registro);
        if(!incentivoFiscal.equals(""))
            incentivoFiscal = regUtil.parseZero(incentivoFiscal);
        if(!incentivoFiscal.equals(""))
            if(!regUtil.isNumeric(incentivoFiscal))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(incentivoFiscal).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(incentivoFiscal))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(incentivoFiscal).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verDescIncentivoFiscal()
    {
        int coluna = 14;
        descIncentivoFiscal = regUtil.contCaracterRegistro(linha, token[13].trim(), 255, coluna, registro);
        if(!incentivoFiscal.equals("") && descIncentivoFiscal.equals(""))
        {
            String aux = (new StringBuilder()).append(codDependencia).append("  ").append(subTitulo).append("  ").append(descIncentivoFiscal).append("  ").append(incentivoFiscal).toString();
            regUtil.setErro(linha, "EM042", coluna, (short)1, registro, aux);
        } else
        if(incentivoFiscal.equals("") && !descIncentivoFiscal.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo de depend\352ncia: ").append(codDependencia).append("<BR>subt\355tulo: ").append(subTitulo).append("descri\347\343o  do incentivo fiscal: ").append(descIncentivoFiscal).toString();
            regUtil.setErro(linha, "EM035", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verMotivoNaoExigibilidade()
    {
        int coluna = 15;
        motivoNaoExigibilidade = regUtil.contCaracterRegistro(linha, token[14].trim(), 1, coluna, registro);
        if(!motivoNaoExigibilidade.equals(""))
        {
            if(!regUtil.isNumeric(motivoNaoExigibilidade))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(motivoNaoExigibilidade).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            }
            if(!motivoNaoExigibilidade.equals("1") && !motivoNaoExigibilidade.equals("2"))
            {
                String txtSolucao = (new StringBuilder()).append("Motivo de n\343o exigibilidade: ").append(motivoNaoExigibilidade).toString();
                regUtil.setErro(linha, "EM016", coluna, (short)1, registro, txtSolucao);
            }
        }
    }

    private void verProcessoMotivoNaoExigibilidade()
    {
        int coluna = 16;
        processoMotivoNaoExigibilidade = regUtil.contCaracterRegistro(linha, token[15].trim(), 20, coluna, registro);
        if(motivoNaoExigibilidade.equals("1") | motivoNaoExigibilidade.equals("2") && processoMotivoNaoExigibilidade.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("C\363digo depend\352ncia: ").append(codDependencia).append("<BR>motivo de n\343o exigibilidade: ").append(motivoNaoExigibilidade).toString();
            regUtil.setErro(linha, "EM044", coluna, (short)1, registro, txtSolucao);
        }
    }

    public ApuracaoReceita getApuracaoMensalIssqnPojo()
    {
        valorCreditoMensal = regUtil.trocaVirgulaPonto(valorCreditoMensal);
        valorDebitoMensal = regUtil.trocaVirgulaPonto(valorDebitoMensal);
        receitaDeclarada = regUtil.trocaVirgulaPonto(receitaDeclarada);
        deducaoReceitaDeclarada = regUtil.trocaVirgulaPonto(deducaoReceitaDeclarada);
        baseCalculo = regUtil.trocaVirgulaPonto(baseCalculo);
        aliquotaIssqn = regUtil.trocaVirgulaPonto(aliquotaIssqn);
        incentivoFiscal = regUtil.trocaVirgulaPonto(incentivoFiscal);
        if(motivoNaoExigibilidade.equals(""))
            motivoNaoExigibilidade = null;
        if(motivoNaoExigibilidade != null)
            apuracaoMensalIssqn = new ApuracaoReceita(new Long(Long.parseLong(numLinha)), codTributacaoDesif, new Long(Long.parseLong(numLinha)), codDependencia, subTitulo, new Double(Double.parseDouble(valorCreditoMensal)), new Double(Double.parseDouble(valorDebitoMensal)), new Double(Double.parseDouble(receitaDeclarada)), new Double(Double.parseDouble(baseCalculo)), new Double(Double.parseDouble(aliquotaIssqn)), new Double(Double.parseDouble(deducaoReceitaDeclarada)), descDeducao, new Double(Double.parseDouble(incentivoFiscal)), descIncentivoFiscal, new Short(Short.parseShort(motivoNaoExigibilidade)), processoMotivoNaoExigibilidade, null);
        else
            apuracaoMensalIssqn = new ApuracaoReceita(new Long(Long.parseLong(numLinha)), codTributacaoDesif, new Long(Long.parseLong(numLinha)), codDependencia, subTitulo, new Double(Double.parseDouble(valorCreditoMensal)), new Double(Double.parseDouble(valorDebitoMensal)), new Double(Double.parseDouble(receitaDeclarada)), new Double(Double.parseDouble(baseCalculo)), new Double(Double.parseDouble(aliquotaIssqn)), new Double(Double.parseDouble(deducaoReceitaDeclarada)), descDeducao, new Double(Double.parseDouble(incentivoFiscal)), descIncentivoFiscal, null, processoMotivoNaoExigibilidade, null);
        return apuracaoMensalIssqn;
    }
}