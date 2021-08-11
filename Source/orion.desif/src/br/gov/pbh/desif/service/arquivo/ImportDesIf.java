
package br.gov.pbh.desif.service.arquivo;

import br.gov.pbh.desif.dao.*;
import br.gov.pbh.desif.dao.impl.IssqnMensalDaoImpl;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.model.pojo.OrigemCreditoCompensar;
import br.gov.pbh.desif.model.registros.*;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.util.ArrayList;
import java.util.List;

// Referenced classes of package br.gov.pbh.desif.service.arquivo:
//            CarregarArquivo, ExceptionLeituraArquivo, ExceptionArquivoInvalido

public class ImportDesIf
{

    private PanGerarDeclaracao panGerDec;
    private CarregarArquivo carregaArq;
    private RegUtil regUtil;
    private IdentDeclaracaoDao declDao;
    private IdentDependenciaDao depDao;
    private ApuracaoReceitaDao apMensIssqnDao;
    private BalanceteAnaliticoMensalDao balanceteAnaliticoMensalDao;
    private DemonstrativoRateioMensalDao rateioMensalDao;
    private IssqnMensalDao demMensIssqnDao;
    private PlanoGeralContaComentadoDao pgccDao;
    private TarifaServicoDao tarServDao;
    private IdentServicosRemunVariavelDao idServRemVarDao;

    public ImportDesIf()
    {
        regUtil = new RegUtil();
    }

    public void importarArquivo(String caminho)
        throws ExceptionLeituraArquivo, ExceptionArquivoInvalido
    {
        regUtil.getArquivoName(caminho);
        carregaArq = new CarregarArquivo();
        String arq = carregaArq.lerArquivo(caminho);
        validar(arq);
    }

    public boolean validar(String arq)
        throws ExceptionLeituraArquivo, ExceptionArquivoInvalido
    {
        String moduDecl = "";
        if(arq == null)
            throw new ExceptionLeituraArquivo();
        String line[] = arq.split("\\\n", -1);
        panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
        if(line.length == 1)
            SwingUtils.msgErro(null, "Arquivo vazio!");
        for(int i = 0; i < line.length - 1; i++)
        {
            panGerDec.atualizarProgressoImportacao(i + 1, line.length - 1);
            String token[] = line[i].split("\\|", -1);
            if(token.length < 3)
            {
                if(i == 0)
                {
                    RegUtil.exErro = true;
                    throw new ExceptionArquivoInvalido();
                }
                continue;
            }
            String registro = token[1].trim();
            regUtil.contCaracterRegistro(i + 1, registro, 4, 2, registro);
            if(i == 0)
            {
                moduDecl = token[8].trim();
                RegUtil.moduloDeclaracao = moduDecl;
                if(registro.equals("0000"))
                {
                    try
                    {
                        processaRegistro0000(token, i + 1);
                        continue;
                    }
                    catch(Exception ex)
                    {
                        RegUtil.exErro = true;
                        ex.printStackTrace();
                    }
                    continue;
                }
                if(registro.equals(""))
                {
                    regUtil.setErro(i + 1, "ED035", 2, (short)1, "0000");
                    break;
                }
                if(registro.trim().equals("0000"))
                    continue;
                regUtil.setErro(i + 1, "ED035", 2, (short)1, "0000");
                break;
            }
            int reg = -1;
            try
            {
                if(moduDecl.equals("1"))
                {
                    if(regUtil.isInteiro(registro))
                        reg = Integer.parseInt(registro);
                    switch(reg)
                    {
                    case 0: // '\0'
                        regUtil.setErro(i + 1, "ED037", 2, (short)1, "", registro);
                        break;

                    case 400: 
                        processaRegistro0400(token, i + 1);
                        break;

                    case 410: 
                        processaRegistro0410(token, i + 1);
                        break;

                    case 420: 
                        processaRegistro0420(token, i + 1);
                        break;

                    default:
                        String txtSolucao = (new StringBuilder()).append("Tipo de registro informado: ").append(registro).toString();
                        regUtil.setErro(i + 1, "EG012", 2, (short)1, "", txtSolucao);
                        break;
                    }
                    continue;
                }
                if(moduDecl.equals("2"))
                {
                    if(regUtil.isInteiro(registro))
                        reg = Integer.parseInt(registro);
                    switch(reg)
                    {
                    case 0: // '\0'
                        regUtil.setErro(i + 1, "ED037", 2, (short)1, "", registro);
                        break;

                    case 400: 
                        processaRegistro0400(token, i + 1);
                        break;

                    case 430: 
                        processaRegistro0430(token, i + 1);
                        break;

                    case 440: 
                        processaRegistro0440(token, i + 1);
                        break;

                    default:
                        String txtSolucao = (new StringBuilder()).append("Tipo de registro informado: ").append(registro).toString();
                        regUtil.setErro(i + 1, "EG012", 2, (short)1, "", txtSolucao);
                        break;
                    }
                    continue;
                }
                if(!moduDecl.equals("3"))
                    continue;
                if(regUtil.isInteiro(registro))
                    reg = Integer.parseInt(registro);
                switch(reg)
                {
                case 0: // '\0'
                    regUtil.setErro(i + 1, "ED037", 2, (short)1, "", registro);
                    break;

                case 100: // 'd'
                    processaRegistro0100(token, i + 1);
                    break;

                case 200: 
                    processaRegistro0200(token, i + 1);
                    break;

                case 300: 
                    processaRegistro0300(token, i + 1);
                    break;

                default:
                    String txtSolucao = (new StringBuilder()).append("Tipo de registro informado: ").append(registro).toString();
                    regUtil.setErro(i + 1, "EG012", 2, (short)1, "", txtSolucao);
                    break;
                }
                continue;
            }
            catch(Exception e)
            {
                e.printStackTrace();
            }
            RegUtil.exErro = true;
            if(registro.equals(""))
            {
                String txtSolucao = "Registro vazio: ";
                regUtil.setErro(i + 1, "EG012", 2, (short)1, "EG012", txtSolucao);
            } else
            {
                String txtSolucao = (new StringBuilder()).append("Tipo de registro informado: ").append(registro).toString();
                regUtil.setErro(i + 1, "EG012", 2, (short)1, "EG012", txtSolucao);
            }
        }

        return true;
    }

    public void processaRegistro0000(String token[], int linha)
        throws Exception
    {
        if(token.length == 15)
        {
            Registro0000New reg0000 = new Registro0000New(token, linha);
            if(!RegUtil.exErro)
            {
                br.gov.pbh.desif.model.pojo.NewIdentificacaoDeclaracao dec = reg0000.getNewDeclaracaoPojo();
                declDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
                declDao.save(dec);
            }
        } else
        {
            String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 15 Tamanho no arquivo: ").append(token.length).toString();
            regUtil.setErro(linha, "EG014", 2, (short)1, "0000", txtSolucao);
        }
    }

    public void processaRegistro0100(String token[], int linha)
    {
        try
        {
            if(token.length == 8)
            {
                Registro0100 reg0100 = new Registro0100(token, linha);
                if(!RegUtil.exErro)
                {
                    br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado pgcc = reg0100.getPGCCPojo();
                    pgccDao = (PlanoGeralContaComentadoDao)Contexto.getObject("pgccDao");
                    pgccDao.save(pgcc);
                }
            } else
            {
                String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 8 Tamanho no arquivo: ").append(token.length).toString();
                regUtil.setErro(linha, "EG014", 2, (short)1, "0100", txtSolucao);
            }
        }
        catch(Exception e)
        {
            RegUtil.exErro = true;
            e.printStackTrace();
        }
    }

    public void processaRegistro0200(String token[], int linha)
    {
        try
        {
            if(token.length == 5)
            {
                Registro0200 reg0200 = new Registro0200(token, linha);
                if(!RegUtil.exErro)
                {
                    br.gov.pbh.desif.model.pojo.TarifaServico tarServ = reg0200.getTarifaServico();
                    tarServDao = (TarifaServicoDao)Contexto.getObject("tarServDao");
                    tarServDao.save(tarServ);
                }
            } else
            {
                String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 5 Tamanho no arquivo: ").append(token.length).toString();
                regUtil.setErro(linha, "EG014", 2, (short)1, "0200", txtSolucao);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void processaRegistro0300(String token[], int linha)
    {
        try
        {
            if(token.length == 5)
            {
                Registro0300 reg0300 = new Registro0300(token, linha);
                if(!RegUtil.exErro)
                {
                    br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel idServRemVar = reg0300.getServicosRemVariavel();
                    idServRemVarDao = (IdentServicosRemunVariavelDao)Contexto.getObject("identServRemVarDao");
                    idServRemVarDao.save(idServRemVar);
                }
            } else
            {
                String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 5 Tamanho no arquivo: ").append(token.length).toString();
                regUtil.setErro(linha, "EG014", 2, (short)1, "0300", txtSolucao);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void processaRegistro0400(String token[], int linha)
    {
        try
        {
            if(token.length == 12)
            {
                Registro0400 reg0400 = new Registro0400(token, linha);
                if(!RegUtil.exErro)
                {
                    br.gov.pbh.desif.model.pojo.IdentificacaoDependencia dep = reg0400.getDependenciaPojo();
                    depDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
                    depDao.save(dep);
                }
            } else
            {
                String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 12 Tamanho no arquivo: ").append(token.length).toString();
                regUtil.setErro(linha, "EG014", 2, (short)1, "0400", txtSolucao);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void processaRegistro0410(String token[], int linha)
    {
        try
        {
            if(token.length == 9)
            {
                Registro0410 reg0410 = new Registro0410(token, linha);
                if(!RegUtil.exErro)
                {
                    br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal balanceteAnaliticoMensal = reg0410.getBalanceteAnaliticoMensal();
                    balanceteAnaliticoMensalDao = (BalanceteAnaliticoMensalDao)Contexto.getObject("balancAnalitMensalDao");
                    balanceteAnaliticoMensalDao.save(balanceteAnaliticoMensal);
                }
            } else
            {
                String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 9 Tamanho no arquivo: ").append(token.length).toString();
                regUtil.setErro(linha, "EG014", 2, (short)1, "0410", txtSolucao);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void processaRegistro0420(String token[], int linha)
    {
        try
        {
            if(token.length == 8)
            {
                Registro0420 reg0420 = new Registro0420(token, linha);
                if(!RegUtil.exErro)
                {
                    br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal demonstRateioMensal = reg0420.getDemonstrativoRateioMensal();
                    rateioMensalDao = (DemonstrativoRateioMensalDao)Contexto.getObject("rateioMensalDao");
                    rateioMensalDao.save(demonstRateioMensal);
                }
            } else
            {
                String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 8 Tamanho no arquivo: ").append(token.length).toString();
                regUtil.setErro(linha, "EG014", 2, (short)1, "0420", txtSolucao);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void processaRegistro0430(String token[], int linha)
    {
        try
        {
            if(token.length == 16)
            {
                Registro0430 reg0430 = new Registro0430(token, linha);
                if(!RegUtil.exErro)
                {
                    br.gov.pbh.desif.model.pojo.ApuracaoReceita apmi = reg0430.getApuracaoMensalIssqnPojo();
                    apMensIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
                    apMensIssqnDao.save(apmi);
                }
            } else
            {
                String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 16Tamanho no arquivo: ").append(token.length).toString();
                regUtil.setErro(linha, "EG014", 2, (short)1, "0430", txtSolucao);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void processaRegistro0440(String token[], int linha)
    {
        if(token.length == 21)
        {
            Registro0440 reg0440 = new Registro0440(token, linha);
            if(!RegUtil.exErro)
            {
                IssqnMensal demMensIssqn = reg0440.getDemostrativoMensalIssqn();
                List aux = new ArrayList(demMensIssqn.getOrigemCredCompensars());
                for(int i = 0; i < aux.size(); i++)
                    ((OrigemCreditoCompensar)aux.get(i)).setIssqnMensal(demMensIssqn);

                demMensIssqnDao = (IssqnMensalDaoImpl)Contexto.getObject("demMensalIssqnDao");
                demMensIssqnDao.save(demMensIssqn);
            }
        } else
        {
            String txtSolucao = (new StringBuilder()).append("Tamanho definido no layout: 21Tamanho no arquivo: ").append(token.length).toString();
            regUtil.setErro(linha, "EG014", 2, (short)1, "0440", txtSolucao);
        }
    }

    public String[] verificaUltimoElementoToken(String entrada[])
    {
        String reduzido[];
        if(entrada[entrada.length - 1].length() == 0)
        {
            int tamanho = entrada.length - 1;
            reduzido = new String[tamanho];
            System.arraycopy(entrada, 0, reduzido, 0, tamanho);
        } else
        {
            reduzido = entrada;
        }
        return reduzido;
    }
}
