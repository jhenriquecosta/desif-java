/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.gov.pbh.des.componentes.guias.AbstractDetalhamentoISS
 *  br.gov.pbh.des.componentes.guias.PnlDetalhamentoIss
 *  br.gov.pbh.des.componentes.utils.SwingUtils
 */
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.guias.AbstractDetalhamentoISS;
import br.gov.pbh.des.componentes.guias.PnlDetalhamentoIss;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.dao.IndiceMonetarioDao;
import br.gov.pbh.desif.model.pojo.GuiaEstaticaVO;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.relatorios.DetalhamentoIssImpl;
import java.awt.Component;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JDialog;

public class DialGuia
extends JDialog {
    private IssqnMensal issqn;
    private GuiaEstaticaVO dadosDescritGuia;
    private Double txExpediente;
    private Controle controle = null;
    private PnlDetalhamentoIss pnlDetalhamentoIss;

    public DialGuia(Frame parent, boolean modal, Object[] dadosGuia) {
        super(parent, modal);
        this.initComponents();
        this.pnlDetalhamentoIss.setAddCompensacaoVisible(false);
        this.pnlDetalhamentoIss.setAddIcentivoVisible(false);
        this.pnlDetalhamentoIss.setAddDeducaoVisible(false);
        SwingUtils.getInstance().centralizar((JDialog)this);
        this.issqn = (IssqnMensal)dadosGuia[0];
        this.dadosDescritGuia = (GuiaEstaticaVO)dadosGuia[1];
        this.txExpediente = (Double)dadosGuia[2];
        this.configuraValorGuia();
        this.pnlDetalhamentoIss.getBtnEmitirGuia().addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent e) {
                DialGuia.this.pnlDetalhamentoIss.imprimirGuia();
            }
        });
    }

    private int obterUltimoIndiceMonetario() {
        IndiceMonetarioDao indMonetDAO = (IndiceMonetarioDao)Contexto.getObject("indiceMonetarioDao");
        Date ultimoIndice = indMonetDAO.anoUltimoIndiceAtualizado();
        GregorianCalendar ultIndMonet = new GregorianCalendar();
        ultIndMonet.setTime(ultimoIndice);
        int anoUltIndMonet = ultIndMonet.get(1);
        System.out.println("/*-+.\u00daltimo \u00cdndice Monet\u00e1rio " + anoUltIndMonet);
        return anoUltIndMonet;
    }

    public void configuraValorGuia() {
        DetalhamentoIssImpl det = new DetalhamentoIssImpl();
        det.setIssqnDevido(this.issqn.getValorIssqnDevido());
        det.setIssqnRetido(this.issqn.getValorIssqnRetido());
        Double valorImposto = this.issqn.getValorIssqnDevido() - this.issqn.getValorIssqnRetido();
        det.setIss(valorImposto);
        Double somaIncentivo = this.issqn.getValorIncentivoFiscal() + this.issqn.getValorIncentivoFiscalSubtitulo();
        det.setValorIncentivo(somaIncentivo);
        Double somaCompensacao = this.issqn.getValorCredito() + this.issqn.getValorIssqnRecolhido();
        det.setValorCompensacao(somaCompensacao);
        det.setReferencia(this.dadosDescritGuia.getDataInicioCompetencia());
        det.setTaxaExpediente(this.txExpediente);
        det.setStrTipoGuia("ISSQN DESIF");
        det.setTipoGuia(50);
        det.setRazaoSocial(this.dadosDescritGuia.getNomeInstituicao());
        det.setCnpj(this.dadosDescritGuia.getCnpjInstituicao() + this.issqn.getCnpj());
        this.controle = new Controle();
        Integer nGuia = this.controle.numeroGuia("SEQ_GUIA");
        System.out.println("Numero da guia => " + nGuia);
        det.setNumeroGuia(nGuia.toString());
        det.setInscricaoMunicipal(this.dadosDescritGuia.getCodigoDependencia());
        det.setAnoUltimoIndiceAtualizado(this.obterUltimoIndiceMonetario());
        this.pnlDetalhamentoIss.setAbstractDetalhamentoISS((AbstractDetalhamentoISS)det);
    }

    private void initComponents() {
        this.pnlDetalhamentoIss = new PnlDetalhamentoIss();
        this.setDefaultCloseOperation(2);
        this.setTitle("Calculo Guia");
        this.pnlDetalhamentoIss.setOpaque(false);
        GroupLayout layout = new GroupLayout(this.getContentPane());
        this.getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent((Component)this.pnlDetalhamentoIss, -2, -1, -2).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGroup(GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(25, 32767).addComponent((Component)this.pnlDetalhamentoIss, -2, -1, -2).addContainerGap()));
        this.pack();
    }

}

