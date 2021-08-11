
package br.gov.pbh.desif.view.telas;

import br.gov.pbh.des.componentes.utils.DesLookandFeel;
import br.gov.pbh.desif.model.registros.RegUtil;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import org.jdesktop.layout.GroupLayout;

// Referenced classes of package br.gov.pbh.desif.view.telas:
//            DialErroImp

public class PanErro extends JPanel
{

    private JButton btnCabErro;
    private JButton btnVisErro;
    private JPanel jPanel1;
    private JSeparator jSeparator1;
    private JLabel labInfErro;

    public PanErro()
    {
        initComponents();
        btnVisErro.setCursor(Cursor.getPredefinedCursor(12));
        DesLookandFeel.getInstance().formatarJButtons(new JButton[] {
            btnCabErro, btnVisErro
        });
        setLabel();
    }

    public void setaTela()
    {
    }

    private void setLabel()
    {
       labInfErro.setText((new StringBuilder()).append("Ocorreram ").append(RegUtil.countErro).append(" erros na importa\347\343o da declara\347\343o. ").toString());
  //        labInfErro.setText("erros...");
    }

    private void initComponents()
    {
        jPanel1 = new JPanel();
        jSeparator1 = new JSeparator();
        labInfErro = new JLabel();
        btnVisErro = new JButton();
        btnCabErro = new JButton();
        jPanel1.setBackground(new Color(255, 232, 232));
        jSeparator1.setBackground(new Color(255, 78, 78));
        jSeparator1.setForeground(new Color(255, 78, 78));
        btnVisErro.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/visualizar_erros.gif")));
        btnVisErro.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent evt)
            {
                btnVisErroActionPerformed(evt);
            }

        }
);
        GroupLayout jPanel1Layout = new GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(1).add(2, jSeparator1, -1, 490, 32767).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(labInfErro).addContainerGap(480, 32767)).add(jPanel1Layout.createSequentialGroup().addContainerGap().add(btnVisErro).addContainerGap(289, 32767)));
        jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(1).add(jPanel1Layout.createSequentialGroup().add(jSeparator1, -2, -1, -2).add(30, 30, 30).add(labInfErro).addPreferredGap(0, 40, 32767).add(btnVisErro).addContainerGap()));
        btnCabErro.setIcon(new ImageIcon(getClass().getResource("/br/gov/pbh/desif/view/icons/cab_erro.GIF")));
        GroupLayout layout = new GroupLayout(this);
        setLayout(layout);
        layout.setHorizontalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().addContainerGap().add(layout.createParallelGroup(1).add(jPanel1, -1, -1, 32767).add(btnCabErro, -2, 96, -2)).addContainerGap()));
        layout.setVerticalGroup(layout.createParallelGroup(1).add(layout.createSequentialGroup().add(15, 15, 15).add(btnCabErro, -2, 41, -2).addPreferredGap(1).add(jPanel1, -2, -1, -2).addContainerGap(131, 32767)));
    }

    private void btnVisErroActionPerformed(ActionEvent evt)
    {
        ((DialErroImp)Contexto.getObject("dialErroImp")).setVisible(true);
    }

}
