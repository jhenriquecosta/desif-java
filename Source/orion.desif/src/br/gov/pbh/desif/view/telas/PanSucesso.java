
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.esec.assinatura.EnvelopeInfo;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.PrintStream;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.layout.GroupLayout;

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            DialAlerta

public class PanSucesso extends JPanel
{

    private Controle controle;
    private JButton btnAlerta;
    private JButton btnAssinar;
    private JButton btnEnviar;
    private JButton btnVerificar;
    private JButton jButton1;
    private JLabel jLabel1;
    private JLabel jlbModulo;

    public PanSucesso()
    {
        initComponents();
        setTela();
        verificaAlertas();
        jButton1.setVisible(false);
        setLabelModulo();
    }

    public void setTela()
    {
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnAlerta, btnAssinar, btnEnviar, btnVerificar
        });
        btnAlerta.setVisible(false);
    }

    public void verificaAlertas()
    {
        System.out.println("esta entrando no verificaAlertas??????");
        controle = (Controle)Contexto.getObject("controle");
        java.util.List l = controle.buscaSistemaAlerta(0.0D);
        if(l.size() > 0)
            btnAlerta.setVisible(true);
    }

    public void setLabelModulo()
    {
        if(RegUtil.moduloDeclaracao.equals("1"))
            jlbModulo.setText("M\363dulo Cont\341bil");
        else
        if(RegUtil.moduloDeclaracao.equals("2"))
            jlbModulo.setText("M\363dulo Apura\347\343o Mensal do ISSQN");
        else
        if(RegUtil.moduloDeclaracao.equals("3"))
            jlbModulo.setText("M\363dulo de Informa\347\365es Comuns aos Mun\355cipios");
    }

    public void assinarDeclaracao()
    {
        controle = (Controle)Contexto.getObject("controle");
        controle.assinarDocumento();
    }

    public void verificarAssinaturaDeclaracao()
    {
        controle = (Controle)Contexto.getObject("controle");
        controle.verificarDocumento(RegUtil.caminhoArquivo);
    }

    private void imprimirEnvelope(EnvelopeInfo envelopeinfo)
    {
    }

    public void enviarDeclaracao()
    {
        controle = (Controle)Contexto.getObject("controle");
        controle.enviarDeclaracao();
    }

    public void setarBotoesAssinatura()
    {
        btnEnviar.setEnabled(false);
        btnVerificar.setEnabled(false);
        btnAssinar.setEnabled(false);
    }

    private void initComponents()
    {
        btnAssinar = new JButton();
        btnAlerta = new JButton();
        btnVerificar = new JButton();
        btnEnviar = new JButton();
        jLabel1 = new JLabel();
        jButton1 = new JButton();
        jlbModulo = new JLabel();
        btnAssinar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/assinar.gif")));
        btnAssinar.setToolTipText("");
        btnAssinar.setContentAreaFilled(false);
        btnAssinar.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt)
            {
                btnAssinarActionPerformed(evt);
            }

        }
);
        btnAlerta.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_visualizar_alertas.PNG")));
        btnAlerta.setContentAreaFilled(false);
        btnAlerta.addActionListener(new ActionListener() {
        public void actionPerformed(ActionEvent evt)
            {
                btnAlertaActionPerformed(evt);
            }

        }
);
        btnVerificar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/verificar-assinatura.gif")));
        btnVerificar.setToolTipText("");
        btnVerificar.setContentAreaFilled(false);
        btnVerificar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnVerificarActionPerformed(evt);
            }
        }
);
        btnEnviar.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/bt_transmitir_declaracao.png")));
        btnEnviar.setToolTipText("");
        btnEnviar.setContentAreaFilled(false);
        btnEnviar.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnEnviarActionPerformed(evt);
            }
        }
);
        jLabel1.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/sucesso.PNG")));
        jButton1.setText("jButton1");
        jButton1.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                jButton1ActionPerformed(evt);
            }
        }
);
        jlbModulo.setFont(new Font("Tahoma", 1, 14));
        jlbModulo.setHorizontalAlignment(0);
        jlbModulo.setText("modulo");
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(2, false).add(jlbModulo, -1, -1, 32767).add(1, layout.createSequentialGroup().add(btnAssinar, -2, 77, -2).addPreferredGap(0).add(btnVerificar).addPreferredGap(0).add(btnEnviar, -2, 190, -2)).add(1, layout.createSequentialGroup().add(btnAlerta, -2, 187, -2).add(38, 38, 38).add(jButton1)).add(1, jLabel1, -1, -1, 32767)).addContainerGap(90, 32767)));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(5, 5, 5).add(jlbModulo).addPreferredGap(0).add(jLabel1).add(18, 18, 18).add(layout.createParallelGroup(1, false).add(btnVerificar, -1, -1, 32767).add(btnEnviar, -1, -1, 32767).add(btnAssinar, 0, 0, 32767)).add(12, 12, 12).add(layout.createParallelGroup(2).add(btnAlerta).add(jButton1)).addContainerGap(67, 32767)));
    }

    private void btnAlertaActionPerformed(ActionEvent evt)
    {
        ((DialAlerta)Contexto.getObject("dialAlerta")).setVisible(true);
    }

    private void btnAssinarActionPerformed(ActionEvent evt)
    {
        assinarDeclaracao();
    }

    private void btnVerificarActionPerformed(ActionEvent evt)
    {
        verificarAssinaturaDeclaracao();
    }

    private void btnEnviarActionPerformed(ActionEvent evt)
    {
        setarBotoesAssinatura();
        enviarDeclaracao();
    }

    private void jButton1ActionPerformed(ActionEvent actionevent)
    {
    }





}
