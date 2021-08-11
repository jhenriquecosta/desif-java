
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.guias.PnlDetalhamentoIss;
import br.gov.pbh.des.componentes.utils.SwingUtils;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.dao.IndiceMonetarioDao;
import br.gov.pbh.desif.model.pojo.GuiaEstaticaVO;
import br.gov.pbh.desif.model.pojo.IssqnMensal;
import br.gov.pbh.desif.service.contexto.Contexto;
import br.gov.pbh.desif.service.relatorios.DetalhamentoIssImpl;
import java.awt.Container;
import java.awt.Frame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import java.util.GregorianCalendar;
import javax.swing.*;

public class DialGuia extends JDialog
{

    private IssqnMensal issqn;
    private GuiaEstaticaVO dadosDescritGuia;
    private Double txExpediente;
    private Controle controle;
    private PnlDetalhamentoIss pnlDetalhamentoIss;

    public DialGuia(Frame parent, boolean modal, Object dadosGuia[])
    {
        super(parent, modal);
        controle = null;
        initComponents();
        pnlDetalhamentoIss.setAddCompensacaoVisible(false);
        pnlDetalhamentoIss.setAddIcentivoVisible(false);
        pnlDetalhamentoIss.setAddDeducaoVisible(false);
        SwingUtils.getInstance().centralizar(this);
        issqn = (IssqnMensal)dadosGuia[0];
        dadosDescritGuia = (GuiaEstaticaVO)dadosGuia[1];
        txExpediente = (Double)dadosGuia[2];
        configuraValorGuia();
        pnlDetalhamentoIss.getBtnEmitirGuia().addActionListener(new ActionListener() {

        public void actionPerformed(ActionEvent e)
            {
                pnlDetalhamentoIss.imprimirGuia();
            }

        }
);
    }

    private int obterUltimoIndiceMonetario()
    {
        IndiceMonetarioDao indMonetDAO = (IndiceMonetarioDao)Contexto.getObject("indiceMonetarioDao");
        java.util.Date ultimoIndice = indMonetDAO.anoUltimoIndiceAtualizado();
        GregorianCalendar ultIndMonet = new GregorianCalendar();
        ultIndMonet.setTime(ultimoIndice);
        int anoUltIndMonet = ultIndMonet.get(1);
        return anoUltIndMonet;
    }

    public void configuraValorGuia()
    {
        DetalhamentoIssImpl det = new DetalhamentoIssImpl();
        det.setIssqnDevido(issqn.getValorIssqnDevido().doubleValue());
        det.setIssqnRetido(issqn.getValorIssqnRetido().doubleValue());
        Double valorImposto = Double.valueOf(issqn.getValorIssqnDevido().doubleValue() - issqn.getValorIssqnRetido().doubleValue());
        det.setIss(valorImposto.doubleValue());
        Double somaIncentivo = Double.valueOf(issqn.getValorIncentivoFiscal().doubleValue() + issqn.getValorIncentivoFiscalSubtitulo().doubleValue());
        det.setValorIncentivo(somaIncentivo.doubleValue());
        Double somaCompensacao = Double.valueOf(issqn.getValorCredito().doubleValue() + issqn.getValorIssqnRecolhido().doubleValue());
        det.setValorCompensacao(somaCompensacao.doubleValue());
        det.setReferencia(dadosDescritGuia.getDataInicioCompetencia());
        det.setTaxaExpediente(txExpediente.doubleValue());
        det.setStrTipoGuia("ISSQN DESIF");
        det.setTipoGuia(70);
        det.setRazaoSocial(dadosDescritGuia.getNomeInstituicao());
        det.setCnpj((new StringBuilder()).append(dadosDescritGuia.getCnpjInstituicao()).append(issqn.getCnpj()).toString());
        controle = new Controle();
        Integer nGuia = controle.numeroGuia("SEQ_GUIA");
        System.out.println((new StringBuilder()).append("Numero da guia => ").append(nGuia).toString());
        det.setNumeroGuia(nGuia.toString());
        det.setInscricaoMunicipal(dadosDescritGuia.getCodigoDependencia());
        det.setAnoUltimoIndiceAtualizado(obterUltimoIndiceMonetario());
        pnlDetalhamentoIss.setAbstractDetalhamentoISS(det);
    }

    private void initComponents()
    {
        pnlDetalhamentoIss = new PnlDetalhamentoIss();
        setDefaultCloseOperation(2);
        setTitle("Calculo Guia");
        pnlDetalhamentoIss.setOpaque(false);
        GroupLayout layout = new GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(-1, 32767).addComponent(pnlDetalhamentoIss, -2, -1, -2).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup().addContainerGap(25, 32767).addComponent(pnlDetalhamentoIss, -2, -1, -2).addContainerGap()));
        pack();
    }

}
