/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.arquivo;

import br.gov.pbh.desif.dao.ApuracaoReceitaDao;
import br.gov.pbh.desif.dao.BalanceteAnaliticoMensalDao;
import br.gov.pbh.desif.dao.DemonstrativoRateioMensalDao;
import br.gov.pbh.desif.dao.IdentDeclaracaoDao;
import br.gov.pbh.desif.dao.IdentDependenciaDao;
import br.gov.pbh.desif.dao.IdentServicosRemunVariavelDao;
import br.gov.pbh.desif.dao.IssqnMensalDao;
import br.gov.pbh.desif.dao.PlanoGeralContaComentadoDao;
import br.gov.pbh.desif.dao.TarifaServicoDao;
import br.gov.pbh.desif.dao.impl.IssqnMensalDaoImpl;
import br.gov.pbh.desif.model.pojo.ApuracaoReceita;
import br.gov.pbh.desif.model.pojo.BalanceteAnaliticoMensal;
import br.gov.pbh.desif.model.pojo.DemonstrativoRateioMensal;
import br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel;
import br.gov.pbh.desif.model.pojo.IdentificacaoDependencia;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.model.pojo.NewIdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.OrigemCreditoCompensar;
import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;
import br.gov.pbh.desif.model.pojo.TarifaServico;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.model.registros.Registro0000New;
import br.gov.pbh.desif.model.registros.Registro0100;
import br.gov.pbh.desif.model.registros.Registro0200;
import br.gov.pbh.desif.model.registros.Registro0300;
import br.gov.pbh.desif.model.registros.Registro0400;
import br.gov.pbh.desif.model.registros.Registro0410;
import br.gov.pbh.desif.model.registros.Registro0420;
import br.gov.pbh.desif.model.registros.Registro0430;
import br.gov.pbh.desif.model.registros.Registro0440;
import br.gov.pbh.desif.service.arquivo.CarregarArquivo;
import br.gov.pbh.desif.service.arquivo.ExceptionArquivoInvalido;
import br.gov.pbh.desif.service.arquivo.ExceptionLeituraArquivo;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.view.telas.PanGerarDeclaracao;
import br.gov.pbh.desif.view.util.SwingUtils;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

public class ImportDesIf {
    private PanGerarDeclaracao panGerDec;
    private CarregarArquivo carregaArq;
    private RegUtil regUtil = new RegUtil();
    private IdentDeclaracaoDao declDao;
    private IdentDependenciaDao depDao;
    private ApuracaoReceitaDao apMensIssqnDao;
    private BalanceteAnaliticoMensalDao balanceteAnaliticoMensalDao;
    private DemonstrativoRateioMensalDao rateioMensalDao;
    private IssqnMensalDao demMensIssqnDao;
    private PlanoGeralContaComentadoDao pgccDao;
    private TarifaServicoDao tarServDao;
    private IdentServicosRemunVariavelDao idServRemVarDao;

    public void importarArquivo(String caminho) throws ExceptionLeituraArquivo, ExceptionArquivoInvalido {
        this.regUtil.getArquivoName(caminho);
        this.carregaArq = new CarregarArquivo();
        String arq = this.carregaArq.lerArquivo(caminho);
        this.validar(arq);
    }

    public boolean validar(String arq) throws ExceptionLeituraArquivo, ExceptionArquivoInvalido {
        String moduDecl = "";
        if (arq == null) {
            throw new ExceptionLeituraArquivo();
        }
        String[] line = arq.split("\\\n", -1);
        this.panGerDec = (PanGerarDeclaracao)Contexto.getObject("panGD");
        if (line.length == 1) {
            SwingUtils.msgErro(null, "Arquivo vazio!");
        }
        block22 : for (int i = 0; i < line.length - 1; ++i) {
            this.panGerDec.atualizarProgressoImportacao(i + 1, line.length - 1);
            String[] token = line[i].split("\\|", -1);
            if (token.length < 3) {
                if (i != 0) continue;
                RegUtil.exErro = true;
                throw new ExceptionArquivoInvalido();
            }
            String registro = token[1].trim();
            this.regUtil.contCaracterRegistro(i + 1, registro, 4, 2, registro);
            if (i == 0) {
                RegUtil.moduloDeclaracao = moduDecl = token[8].trim();
                if (registro.equals("0000")) {
                    try {
                        this.processaRegistro0000(token, i + 1);
                    }
                    catch (Exception ex) {
                        RegUtil.exErro = true;
                        ex.printStackTrace();
                    }
                    continue;
                }
                if (registro.equals("")) {
                    this.regUtil.setErro(i + 1, "ED035", 2, (short)1, "0000");
                    break;
                }
                if (registro.trim().equals("0000")) continue;
                this.regUtil.setErro(i + 1, "ED035", 2, (short)1, "0000");
                break;
            }
            int reg = -1;
            try {
                String txtSolucao;
                if (moduDecl.equals("1")) {
                    if (this.regUtil.isInteiro(registro)) {
                        reg = Integer.parseInt(registro);
                    }
                    switch (reg) {
                        case 0: {
                            this.regUtil.setErro(i + 1, "ED037", 2, (short)1, "", registro);
                            continue block22;
                        }
                        case 400: {
                            this.processaRegistro0400(token, i + 1);
                            continue block22;
                        }
                        case 410: {
                            this.processaRegistro0410(token, i + 1);
                            continue block22;
                        }
                        case 420: {
                            this.processaRegistro0420(token, i + 1);
                            continue block22;
                        }
                    }
                    txtSolucao = "Tipo de registro informado: " + registro;
                    this.regUtil.setErro(i + 1, "EG012", 2, (short)1, "", txtSolucao);
                    continue;
                }
                if (moduDecl.equals("2")) {
                    if (this.regUtil.isInteiro(registro)) {
                        reg = Integer.parseInt(registro);
                    }
                    switch (reg) {
                        case 0: {
                            this.regUtil.setErro(i + 1, "ED037", 2, (short)1, "", registro);
                            continue block22;
                        }
                        case 400: {
                            this.processaRegistro0400(token, i + 1);
                            continue block22;
                        }
                        case 430: {
                            this.processaRegistro0430(token, i + 1);
                            continue block22;
                        }
                        case 440: {
                            this.processaRegistro0440(token, i + 1);
                            continue block22;
                        }
                    }
                    txtSolucao = "Tipo de registro informado: " + registro;
                    this.regUtil.setErro(i + 1, "EG012", 2, (short)1, "", txtSolucao);
                    continue;
                }
                if (!moduDecl.equals("3")) continue;
                if (this.regUtil.isInteiro(registro)) {
                    reg = Integer.parseInt(registro);
                }
                switch (reg) {
                    case 0: {
                        this.regUtil.setErro(i + 1, "ED037", 2, (short)1, "", registro);
                        continue block22;
                    }
                    case 100: {
                        this.processaRegistro0100(token, i + 1);
                        continue block22;
                    }
                    case 200: {
                        this.processaRegistro0200(token, i + 1);
                        continue block22;
                    }
                    case 300: {
                        this.processaRegistro0300(token, i + 1);
                        continue block22;
                    }
                }
                txtSolucao = "Tipo de registro informado: " + registro;
                this.regUtil.setErro(i + 1, "EG012", 2, (short)1, "", txtSolucao);
                continue;
            }
            catch (Exception e) {
                String txtSolucao;
                e.printStackTrace();
                RegUtil.exErro = true;
                if (registro.equals("")) {
                    txtSolucao = "Registro vazio: ";
                    this.regUtil.setErro(i + 1, "EG012", 2, (short)1, "EG012", txtSolucao);
                    continue;
                }
                txtSolucao = "Tipo de registro informado: " + registro;
                this.regUtil.setErro(i + 1, "EG012", 2, (short)1, "EG012", txtSolucao);
            }
        }
        return true;
    }

    public void processaRegistro0000(String[] token, int linha) {
        if (token.length == 15) {
            Registro0000New reg0000 = new Registro0000New(token, linha);
            if (!RegUtil.exErro) {
                NewIdentificacaoDeclaracao dec = reg0000.getNewDeclaracaoPojo();
                this.declDao = (IdentDeclaracaoDao)Contexto.getObject("declaracaoDao");
                this.declDao.save(dec);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 15 Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0000", txtSolucao);
        }
    }

    public void processaRegistro0100(String[] token, int linha) {
        if (token.length == 8) {
            Registro0100 reg0100 = new Registro0100(token, linha);
            if (!RegUtil.exErro) {
                PlanoGeralContaComentado pgcc = reg0100.getPGCCPojo();
                this.pgccDao = (PlanoGeralContaComentadoDao)Contexto.getObject("pgccDao");
                this.pgccDao.save(pgcc);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 8 Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0100", txtSolucao);
        }
    }

    public void processaRegistro0200(String[] token, int linha) {
        if (token.length == 5) {
            Registro0200 reg0200 = new Registro0200(token, linha);
            if (!RegUtil.exErro) {
                TarifaServico tarServ = reg0200.getTarifaServico();
                this.tarServDao = (TarifaServicoDao)Contexto.getObject("tarServDao");
                this.tarServDao.save(tarServ);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 5 Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0200", txtSolucao);
        }
    }

    public void processaRegistro0300(String[] token, int linha) {
        if (token.length == 5) {
            Registro0300 reg0300 = new Registro0300(token, linha);
            if (!RegUtil.exErro) {
                IdentServicosRemunVariavel idServRemVar = reg0300.getServicosRemVariavel();
                this.idServRemVarDao = (IdentServicosRemunVariavelDao)Contexto.getObject("identServRemVarDao");
                this.idServRemVarDao.save(idServRemVar);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 5 Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0300", txtSolucao);
        }
    }

    public void processaRegistro0400(String[] token, int linha) {
        if (token.length == 12) {
            Registro0400 reg0400 = new Registro0400(token, linha);
            if (!RegUtil.exErro) {
                IdentificacaoDependencia dep = reg0400.getDependenciaPojo();
                this.depDao = (IdentDependenciaDao)Contexto.getObject("dependenciaDao");
                this.depDao.save(dep);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 12 Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0400", txtSolucao);
        }
    }

    public void processaRegistro0410(String[] token, int linha) {
        if (token.length == 9) {
            Registro0410 reg0410 = new Registro0410(token, linha);
            if (!RegUtil.exErro) {
                BalanceteAnaliticoMensal balanceteAnaliticoMensal = reg0410.getBalanceteAnaliticoMensal();
                this.balanceteAnaliticoMensalDao = (BalanceteAnaliticoMensalDao)Contexto.getObject("balancAnalitMensalDao");
                this.balanceteAnaliticoMensalDao.save(balanceteAnaliticoMensal);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 9 Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0410", txtSolucao);
        }
    }

    public void processaRegistro0420(String[] token, int linha) {
        if (token.length == 8) {
            Registro0420 reg0420 = new Registro0420(token, linha);
            if (!RegUtil.exErro) {
                DemonstrativoRateioMensal demonstRateioMensal = reg0420.getDemonstrativoRateioMensal();
                this.rateioMensalDao = (DemonstrativoRateioMensalDao)Contexto.getObject("rateioMensalDao");
                this.rateioMensalDao.save(demonstRateioMensal);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 8 Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0420", txtSolucao);
        }
    }

    public void processaRegistro0430(String[] token, int linha) {
        if (token.length == 16) {
            Registro0430 reg0430 = new Registro0430(token, linha);
            if (!RegUtil.exErro) {
                ApuracaoReceita apmi = reg0430.getApuracaoMensalIssqnPojo();
                this.apMensIssqnDao = (ApuracaoReceitaDao)Contexto.getObject("apuracaoMensalIssqnDao");
                this.apMensIssqnDao.save(apmi);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 16Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0430", txtSolucao);
        }
    }

    public void processaRegistro0440(String[] token, int linha) {
        if (token.length == 21) {
            Registro0440 reg0440 = new Registro0440(token, linha);
            if (!RegUtil.exErro) {
                IssqnMensal demMensIssqn = reg0440.getDemostrativoMensalIssqn();
                ArrayList aux = new ArrayList(demMensIssqn.getOrigemCredCompensars());
                for (int i = 0; i < aux.size(); ++i) {
                    ((OrigemCreditoCompensar)aux.get(i)).setIssqnMensal(demMensIssqn);
                }
                this.demMensIssqnDao = (IssqnMensalDaoImpl)Contexto.getObject("demMensalIssqnDao");
                this.demMensIssqnDao.save(demMensIssqn);
            }
        } else {
            String txtSolucao = "Tamanho definido no layout: 21Tamanho no arquivo: " + token.length;
            this.regUtil.setErro(linha, "EG014", 2, (short)1, "0440", txtSolucao);
        }
    }

    public String[] verificaUltimoElementoToken(String[] entrada) {
        String[] reduzido;
        if (entrada[entrada.length - 1].length() == 0) {
            int tamanho = entrada.length - 1;
            reduzido = new String[tamanho];
            System.arraycopy(entrada, 0, reduzido, 0, tamanho);
        } else {
            reduzido = entrada;
        }
        return reduzido;
    }
}

