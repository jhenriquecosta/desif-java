
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.model.pojo.OrigemCreditoCompensar;
import java.util.*;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            Data, RegUtil

public class Registro0440
{

    Data dt;
    private IssqnMensal demMensIssqn;
    private RegUtil regUtil;
    private String numLinha;
    private String cnpj;
    private String receitaDeclaradaConsolidada;
    private String baseCalculo;
    private String aliquotaIssqn;
    private String issqnDevido;
    private String codTributacaoDesif;
    private String deducaoReceitaDeclaradaSubTitulo;
    private String deducaoReceitaDeclaradaConsolidada;
    private String descDeducao;
    private String valorIssqnRetido;
    private String incentivoFiscalSubTitulo;
    private String incentivoFiscal;
    private String issqnRecolhido;
    private String issqnARecolher;
    private String descIncentivoFiscal;
    private String valorCredito;
    private String motivoNaoExigibilidade;
    private String processoMotivoNaoExigibilidade;
    private String origemCreditoACompensar;
    private String cmpeOrigCred;
    private String valrOrigCred;
    private String token[];
    private int linha;
    private Set origCredCompensar;
    private String registro;
    private String txtErroCasasDecimais;

    public Registro0440(String token[], int linha)
    {
        dt = new Data();
        origCredCompensar = new HashSet();
        registro = token[1];
        regUtil = new RegUtil();
        this.token = token;
        this.linha = linha;
        txtErroCasasDecimais = "Quantidade de casas decimais diferente de duas: ";
        verNumLinha();
        verCnpj();
        verReceitaDeclaradaConsolidada();
        verBaseCalculo();
        verAliquotaIssqn();
        verValorIssqnDevido();
        verCodTributacaoDesif();
        verDeducaoReceitaDeclaradaSubTitulo();
        verDeducaoReceitaDeclaradaConsolidada();
        verDescDeducao();
        verValorIssqnRetido();
        verIncentivoFiscalSubTitulo();
        verIncentivoFiscal();
        verIssqnRecolhido();
        verIssqnARecolher();
        verDescIncentivoFiscal();
        verValorCredito();
        verMotivoNaoExigibilidade();
        verProcessoMotivoNaoExigibilidade();
        verOrigemCreditoAcompensar();
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

    private void verCnpj()
    {
        int coluna = 3;
        cnpj = regUtil.contCaracterRegistro(linha, token[2].trim(), 6, coluna, registro);
        if(cnpj.equals(""))
            regUtil.setErro(linha, "EG004", coluna, (short)1, registro);
    }

    private void verCodTributacaoDesif()
    {
        int coluna = 4;
        codTributacaoDesif = regUtil.contCaracterRegistro(linha, token[3].trim(), 20, coluna, registro);
    }

    private void verReceitaDeclaradaConsolidada()
    {
        int coluna = 5;
        receitaDeclaradaConsolidada = regUtil.contCaracterRegistro(linha, token[4].trim(), 19, coluna, registro);
        if(receitaDeclaradaConsolidada.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).toString();
            regUtil.setErro(linha, "EM039", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(receitaDeclaradaConsolidada))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(receitaDeclaradaConsolidada).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(receitaDeclaradaConsolidada))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(receitaDeclaradaConsolidada).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verDeducaoReceitaDeclaradaSubTitulo()
    {
        int coluna = 6;
        deducaoReceitaDeclaradaSubTitulo = regUtil.contCaracterRegistro(linha, token[5].trim(), 19, coluna, registro);
        if(!deducaoReceitaDeclaradaSubTitulo.equals(""))
            if(!regUtil.isNumeric(deducaoReceitaDeclaradaSubTitulo))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(deducaoReceitaDeclaradaSubTitulo).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(deducaoReceitaDeclaradaSubTitulo))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(deducaoReceitaDeclaradaSubTitulo).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verDeducaoReceitaDeclaradaConsolidada()
    {
        int coluna = 7;
        deducaoReceitaDeclaradaConsolidada = regUtil.contCaracterRegistro(linha, token[6].trim(), 19, coluna, registro);
        if(!deducaoReceitaDeclaradaConsolidada.equals(""))
            deducaoReceitaDeclaradaConsolidada = regUtil.parseZero(deducaoReceitaDeclaradaConsolidada);
        if(!deducaoReceitaDeclaradaConsolidada.equals(""))
            if(!regUtil.isNumeric(deducaoReceitaDeclaradaConsolidada))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(deducaoReceitaDeclaradaConsolidada).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(deducaoReceitaDeclaradaConsolidada))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(deducaoReceitaDeclaradaConsolidada).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verDescDeducao()
    {
        int coluna = 8;
        descDeducao = regUtil.contCaracterRegistro(linha, token[7].trim(), 255, coluna, registro);
        if(!deducaoReceitaDeclaradaConsolidada.equals("") && descDeducao.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("<BR>dedu\347\343o da receita consolidada: ").append(deducaoReceitaDeclaradaConsolidada).toString();
            regUtil.setErro(linha, "EM052", coluna, (short)1, registro, txtSolucao);
        }
        if(deducaoReceitaDeclaradaConsolidada.equals("") && !descDeducao.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("<BR>descri\347\343o da dedu\347\343o da receita declarada consolidada:  ").append(descDeducao).toString();
            regUtil.setErro(linha, "EM073", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verBaseCalculo()
    {
        int coluna = 9;
        baseCalculo = regUtil.contCaracterRegistro(linha, token[8].trim(), 19, coluna, registro);
        if(baseCalculo.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).toString();
            regUtil.setErro(linha, "EM031", coluna, (short)1, registro, txtSolucao);
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
        int coluna = 10;
        aliquotaIssqn = regUtil.contCaracterRegistro(linha, token[9].trim(), 8, coluna, registro);
        if(aliquotaIssqn.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).toString();
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

    private void verValorIssqnDevido()
    {
        int coluna = 11;
        issqnDevido = regUtil.contCaracterRegistro(linha, token[10].trim(), 19, coluna, registro);
        if(issqnDevido.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).toString();
            regUtil.setErro(linha, "EM041", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(issqnDevido))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(issqnDevido).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.verificaCasasDecimais(issqnDevido))
        {
            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(issqnDevido).toString();
            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verValorIssqnRetido()
    {
        int coluna = 12;
        valorIssqnRetido = regUtil.contCaracterRegistro(linha, token[11].trim(), 19, coluna, registro);
        if(!valorIssqnRetido.equals(""))
            if(!regUtil.isNumeric(valorIssqnRetido))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(valorIssqnRetido).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(valorIssqnRetido))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(valorIssqnRetido).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verIncentivoFiscalSubTitulo()
    {
        int coluna = 13;
        incentivoFiscalSubTitulo = regUtil.contCaracterRegistro(linha, token[12].trim(), 19, coluna, registro);
        if(!incentivoFiscalSubTitulo.equals(""))
            incentivoFiscalSubTitulo = regUtil.parseZero(incentivoFiscalSubTitulo);
        if(!incentivoFiscalSubTitulo.equals(""))
            if(!regUtil.isNumeric(incentivoFiscalSubTitulo))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(incentivoFiscalSubTitulo).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(incentivoFiscalSubTitulo))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(incentivoFiscalSubTitulo).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verIncentivoFiscal()
    {
        int coluna = 14;
        incentivoFiscal = regUtil.contCaracterRegistro(linha, token[13].trim(), 19, coluna, registro);
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
        int coluna = 15;
        descIncentivoFiscal = regUtil.contCaracterRegistro(linha, token[14].trim(), 255, coluna, registro);
        if(!incentivoFiscal.equals("") && (descIncentivoFiscal.equals("") || descIncentivoFiscal == null))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append(" <BR>incentivo fiscal: ").append(incentivoFiscal).toString();
            regUtil.setErro(linha, "EM057", coluna, (short)1, registro, txtSolucao);
        }
        if(incentivoFiscal.equals("") && !descIncentivoFiscal.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("<BR>incentivo fiscal: ").append(incentivoFiscal).toString();
            regUtil.setErro(linha, "EM042", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verValorCredito()
    {
        int coluna = 16;
        valorCredito = regUtil.contCaracterRegistro(linha, token[15].trim(), 19, coluna, registro);
        if(!valorCredito.equals(""))
            if(!regUtil.isNumeric(valorCredito))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(valorCredito).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(valorCredito))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(valorCredito).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verOrigemCreditoAcompensar()
    {
        int coluna = 17;
        boolean flag = false;
        origemCreditoACompensar = token[16].trim();
        if(!valorCredito.equals(""))
            valorCredito = regUtil.parseZero(valorCredito);
        if(!valorCredito.equals("") && origemCreditoACompensar.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("<BR>al\355quota: ").append(aliquotaIssqn).append("<BR>valor do cr\351dito a ser compensado: ").append(origemCreditoACompensar).toString();
            regUtil.setErro(linha, "EM018", coluna, (short)1, registro, txtSolucao);
            flag = true;
        } else
        {
            if(valorCredito.equals("") && origemCreditoACompensar.equals(""))
                return;
            String s[] = regUtil.multiValorado(origemCreditoACompensar, "\247");
            for(int i = 0; i < s.length; i++)
            {
                String ob[] = regUtil.multiValorado(s[i], "\243");
                if(ob.length == 2)
                {
                    cmpeOrigCred = regUtil.contCaracterRegistro(linha, ob[0], 6, coluna, registro);
                    if(!ob[1].equals(""))
                        ob[1] = regUtil.parseZero(ob[1]);
                    if(!ob[0].equals("") && ob[1].equals(""))
                    {
                        String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("compet\352ncia do cr\351dito a compensar: ").append(ob[0]).toString();
                        regUtil.setErro(linha, "EM065", coluna, (short)1, registro, txtSolucao);
                        flag = true;
                    }
                    if(ob[0].equals("") || !dt.validaData(ob[0], "yyyyMM"))
                    {
                        String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("<BR>compet\352ncia do cr\351dito a compensar: ").append(ob[0]).toString();
                        regUtil.setErro(linha, "EM014", coluna, (short)1, registro, txtSolucao);
                        flag = true;
                    }
                    cmpeOrigCred = regUtil.contCaracterRegistro(linha, ob[1], 19, coluna, registro);
                    if(!ob[1].equals(""))
                        if(!regUtil.isNumeric(ob[1]))
                        {
                            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(ob[1]).toString();
                            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
                            flag = true;
                        } else
                        if(!regUtil.verificaCasasDecimais(ob[1]))
                        {
                            String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(ob[1]).toString();
                            regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
                        }
                    if(!ob[1].equals("") && ob[0].equals(""))
                    {
                        String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("<BR>valor do cr\351dito: ").append(ob[1]).toString();
                        regUtil.setErro(linha, "EM045", coluna, (short)1, registro, txtSolucao);
                        flag = true;
                    }
                } else
                {
                    String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("<BR> valor do cr\351dito a compensar: ").append(valorCredito).toString();
                    regUtil.setErro(linha, "EM017", coluna, (short)1, registro, txtSolucao);
                    flag = true;
                }
            }

            if(!flag)
                inicializarOrigemCreditoAcompensar(s);
        }
    }

    public void inicializarOrigemCreditoAcompensar(String s[])
    {
        for(int i = 0; i < s.length; i++)
        {
            String ob[] = regUtil.multiValorado(s[i], "\243");
            OrigemCreditoCompensar occ = new OrigemCreditoCompensar();
            ob[0] = (new StringBuilder()).append(ob[0]).append("01").toString();
            Date dataComp = regUtil.parseData(ob[0], "yyyyMMdd");
            if(dataComp != null)
                occ.setDataCompetenciaOrigemCredito(dataComp);
            String aux = ob[1].replace(',', '.');
            occ.setValorOrigemCredito(Double.parseDouble(aux));
            origCredCompensar.add(occ);
        }

    }

    private void verIssqnRecolhido()
    {
        int coluna = 18;
        issqnRecolhido = regUtil.contCaracterRegistro(linha, token[17].trim(), 19, coluna, registro);
        if(!issqnRecolhido.equals(""))
            if(!regUtil.isNumeric(issqnRecolhido))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(issqnRecolhido).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(issqnRecolhido))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(issqnRecolhido).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verMotivoNaoExigibilidade()
    {
        int coluna = 19;
        motivoNaoExigibilidade = regUtil.contCaracterRegistro(linha, token[18].trim(), 1, coluna, registro);
        if(!motivoNaoExigibilidade.equals(""))
            if(!regUtil.isInteiro(motivoNaoExigibilidade))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(motivoNaoExigibilidade).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if((!motivoNaoExigibilidade.equals("1")) & (!motivoNaoExigibilidade.equals("2")))
            {
                String txtSolucao = (new StringBuilder()).append("Motivo de n\343o exigibilidade: ").append(motivoNaoExigibilidade).toString();
                regUtil.setErro(linha, "EM016", coluna, (short)1, registro, txtSolucao);
            }
    }

    private void verProcessoMotivoNaoExigibilidade()
    {
        int coluna = 20;
        processoMotivoNaoExigibilidade = regUtil.contCaracterRegistro(linha, token[19].trim(), 20, coluna, registro);
        if(motivoNaoExigibilidade.equals("1") | motivoNaoExigibilidade.equals("2") && processoMotivoNaoExigibilidade.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("CNPJ: ").append(cnpj).append("<BR>motivo de n\343o exigibilidade: ").append(motivoNaoExigibilidade).toString();
            regUtil.setErro(linha, "EM044", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verIssqnARecolher()
    {
        int coluna = 21;
        issqnARecolher = regUtil.contCaracterRegistro(linha, token[20].trim(), 19, coluna, registro);
        if(!issqnARecolher.equals(""))
            if(!regUtil.isNumeric(issqnARecolher))
            {
                String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(issqnARecolher).toString();
                regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
            } else
            if(!regUtil.verificaCasasDecimais(issqnARecolher))
            {
                String txtSolucao = (new StringBuilder()).append(txtErroCasasDecimais).append(issqnARecolher).toString();
                regUtil.setErro(linha, "EG009", coluna, (short)1, registro, txtSolucao);
            }
    }

    public IssqnMensal getDemostrativoMensalIssqn()
    {
        receitaDeclaradaConsolidada = regUtil.trocaVirgulaPonto(receitaDeclaradaConsolidada);
        baseCalculo = regUtil.trocaVirgulaPonto(baseCalculo);
        aliquotaIssqn = regUtil.trocaVirgulaPonto(aliquotaIssqn);
        issqnDevido = regUtil.trocaVirgulaPonto(issqnDevido);
        deducaoReceitaDeclaradaSubTitulo = regUtil.trocaVirgulaPonto(deducaoReceitaDeclaradaSubTitulo);
        deducaoReceitaDeclaradaConsolidada = regUtil.trocaVirgulaPonto(deducaoReceitaDeclaradaConsolidada);
        valorIssqnRetido = regUtil.trocaVirgulaPonto(valorIssqnRetido);
        incentivoFiscalSubTitulo = regUtil.trocaVirgulaPonto(incentivoFiscalSubTitulo);
        incentivoFiscal = regUtil.trocaVirgulaPonto(incentivoFiscal);
        descIncentivoFiscal = regUtil.trocaVirgulaPonto(descIncentivoFiscal);
        issqnRecolhido = regUtil.trocaVirgulaPonto(issqnRecolhido);
        issqnARecolher = regUtil.trocaVirgulaPonto(issqnARecolher);
        valorCredito = regUtil.trocaVirgulaPonto(valorCredito);
        if(!motivoNaoExigibilidade.equals(""))
            demMensIssqn = new IssqnMensal(new Long(Long.parseLong(numLinha)), new Long(Long.parseLong(numLinha)), cnpj, new Double(Double.parseDouble(receitaDeclaradaConsolidada)), new Double(baseCalculo), new Double(Double.parseDouble(aliquotaIssqn)), new Double(Double.parseDouble(issqnDevido)), codTributacaoDesif, new Double(Double.parseDouble(deducaoReceitaDeclaradaSubTitulo)), new Double(Double.parseDouble(deducaoReceitaDeclaradaConsolidada)), descDeducao, new Double(Double.parseDouble(valorIssqnRetido)), new Double(Double.parseDouble(incentivoFiscalSubTitulo)), new Double(Double.parseDouble(incentivoFiscal)), new Double(Double.parseDouble(issqnRecolhido)), new Double(Double.parseDouble(issqnARecolher)), descIncentivoFiscal, new Double(Double.parseDouble(valorCredito)), new Short(Short.parseShort(motivoNaoExigibilidade)), processoMotivoNaoExigibilidade, origCredCompensar);
        else
            demMensIssqn = new IssqnMensal(new Long(Long.parseLong(numLinha)), new Long(Long.parseLong(numLinha)), cnpj, new Double(Double.parseDouble(receitaDeclaradaConsolidada)), new Double(baseCalculo), new Double(Double.parseDouble(aliquotaIssqn)), new Double(Double.parseDouble(issqnDevido)), codTributacaoDesif, new Double(Double.parseDouble(deducaoReceitaDeclaradaSubTitulo)), new Double(Double.parseDouble(deducaoReceitaDeclaradaConsolidada)), descDeducao, new Double(Double.parseDouble(valorIssqnRetido)), new Double(Double.parseDouble(incentivoFiscalSubTitulo)), new Double(Double.parseDouble(incentivoFiscal)), new Double(Double.parseDouble(issqnRecolhido)), new Double(Double.parseDouble(issqnARecolher)), descIncentivoFiscal, new Double(Double.parseDouble(valorCredito)), null, processoMotivoNaoExigibilidade, origCredCompensar);
        return demMensIssqn;
    }
}
