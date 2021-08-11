

package br.gov.pbh.desif.model.registros;

import java.awt.Container;
import javax.swing.*;

public class TesteJlist
{

    public TesteJlist()
    {
    }

    public static void main(String args[])
    {
        String labels[] = {
            "1", "  1.1", "    1.2.1", "2", "  2.1", "  2.2", "  2.3", "4", "5", "6"
        };
        String title = "JList Exemplo";
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(3);
        JList list = new JList(labels);
        JScrollPane scrollPane = new JScrollPane(list);
        Container contentPane = f.getContentPane();
        contentPane.add(scrollPane, "Center");
        f.setSize(200, 200);
        f.setVisible(true);
    }
}
