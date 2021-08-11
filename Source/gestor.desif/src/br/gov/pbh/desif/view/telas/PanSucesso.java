
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.control.Controle;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.awt.Component;
import java.awt.Container;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import org.jdesktop.layout.GroupLayout;

public class PanSucesso
extends JPanel {
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
        this.initComponents();
        this.setTela();
        this.verificaAlertas();
        this.jButton1.setVisible(false);
        this.setLabelModulo();
    }

    public void setTela() {
        DesLookandFeel.getInstance().formatarJButtons(new JButton[]{this.btnAlerta, this.btnAssinar, this.btnEnviar, this.btnVerificar});
        this.btnAlerta.setVisible(false);
    }

    public void verificaAlertas() {
        System.out.println("esta entrando no verificaAlertas??????");
        this.controle = (Controle)Contexto.getObject("controle");
        List l = this.controle.buscaSistemaAlerta(0.0);
        if (l.size() > 0) {
            this.btnAlerta.setVisible(true);
        }
    }

    public void setLabelModulo() {
        switch (RegUtil.moduloDeclaracao) {
            case "1":
                this.jlbModulo.setText("M\u00f3dulo Cont\u00e1bil");
                break;
            case "2":
                this.jlbModulo.setText("M\u00f3dulo Apura\u00e7\u00e3o Mensal do ISSQN");
                break;
            case "3":
                this.jlbModulo.setText("M\u00f3dulo de Informa\u00e7\u00f5es Comuns aos Mun\u00edcipios");
                break;
        }
    }

    public void assinarDeclaracao() {
        this.controle = (Controle)Contexto.getObject("controle");
        this.controle.assinarDocumento();
    }

    public void verificarAssinaturaDeclaracao() {
        this.controle = (Controle)Contexto.getObject("controle");
        this.controle.verificarDocumento(RegUtil.caminhoArquivo);
    }

    public void enviarDeclaracao() {
        this.controle = (Controle)Contexto.getObject("controle");
        this.controle.enviarDeclaracao();
    }

    public void setarBotoesAssinatura() {
        this.btnEnviar.setEnabled(false);
        this.btnVerificar.setEnabled(false);
        this.btnAssinar.setEnabled(false);
    }

    private void initComponents() {
        this.btnAssinar = new JButton();
        this.btnAlerta = new JButton();
        this.btnVerificar = new JButton();
        this.btnEnviar = new JButton();
        this.jLabel1 = new JLabel();
        this.jButton1 = new JButton();
        this.jlbModulo = new JLabel();
        this.btnAssinar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/assinar.gif")));
        this.btnAssinar.setToolTipText("");
        this.btnAssinar.setContentAreaFilled(false);
        this.btnAssinar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanSucesso.this.btnAssinarActionPerformed(evt);
            }
        });
        this.btnAlerta.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_visualizar_alertas.PNG")));
        this.btnAlerta.setContentAreaFilled(false);
        this.btnAlerta.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanSucesso.this.btnAlertaActionPerformed(evt);
            }
        });
        this.btnVerificar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/verificar-assinatura.gif")));
        this.btnVerificar.setToolTipText("");
        this.btnVerificar.setContentAreaFilled(false);
        this.btnVerificar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanSucesso.this.btnVerificarActionPerformed(evt);
            }
        });
        this.btnEnviar.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/bt_transmitir_declaracao.png")));
        this.btnEnviar.setToolTipText("");
        this.btnEnviar.setContentAreaFilled(false);
        this.btnEnviar.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanSucesso.this.btnEnviarActionPerformed(evt);
            }
        });
        this.jLabel1.setIcon(new ImageIcon(this.getClass().getResource("/br/gov/pbh/desif/view/icons/sucesso.PNG")));
        this.jButton1.setText("jButton1");
        this.jButton1.addActionListener(new ActionListener(){

            @Override
            public void actionPerformed(ActionEvent evt) {
                PanSucesso.this.jButton1ActionPerformed(evt);
            }
        });
        this.jlbModulo.setFont(new Font("Tahoma", 1, 14));
        this.jlbModulo.setHorizontalAlignment(0);
        this.jlbModulo.setText("modulo");
        GroupLayout layout = new GroupLayout((Container)this);
        this.setLayout((LayoutManager)layout);
        layout.setHorizontalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().addContainerGap().add((GroupLayout.Group)layout.createParallelGroup(2, false).add((Component)this.jlbModulo, -1, -1, 32767).add(1, (GroupLayout.Group)layout.createSequentialGroup().add((Component)this.btnAssinar, -2, 77, -2).addPreferredGap(0).add((Component)this.btnVerificar).addPreferredGap(0).add((Component)this.btnEnviar, -2, 190, -2)).add(1, (GroupLayout.Group)layout.createSequentialGroup().add((Component)this.btnAlerta, -2, 187, -2).add(38, 38, 38).add((Component)this.jButton1)).add(1, (Component)this.jLabel1, -1, -1, 32767)).addContainerGap(90, 32767)));
        layout.setVerticalGroup((GroupLayout.Group)layout.createParallelGroup(1).add((GroupLayout.Group)layout.createSequentialGroup().add(5, 5, 5).add((Component)this.jlbModulo).addPreferredGap(0).add((Component)this.jLabel1).add(18, 18, 18).add((GroupLayout.Group)layout.createParallelGroup(1, false).add((Component)this.btnVerificar, -1, -1, 32767).add((Component)this.btnEnviar, -1, -1, 32767).add((Component)this.btnAssinar, 0, 0, 32767)).add(12, 12, 12).add((GroupLayout.Group)layout.createParallelGroup(2).add((Component)this.btnAlerta).add((Component)this.jButton1)).addContainerGap(67, 32767)));
    }

    private void btnAlertaActionPerformed(ActionEvent evt) {
        ((DialAlerta)Contexto.getObject("dialAlerta")).setVisible(true);
    }

    private void btnAssinarActionPerformed(ActionEvent evt) {
        this.assinarDeclaracao();
    }

    private void btnVerificarActionPerformed(ActionEvent evt) {
        this.verificarAssinaturaDeclaracao();
    }

    private void btnEnviarActionPerformed(ActionEvent evt) {
        this.setarBotoesAssinatura();
        this.enviarDeclaracao();
    }

    private void jButton1ActionPerformed(ActionEvent evt) {
    }

}

