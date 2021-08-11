/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import java.awt.Component;
import java.awt.Container;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class TesteJlist {
    public static void main(String[] args) {
        String[] labels = new String[]{"1", "  1.1", "    1.2.1", "2", "  2.1", "  2.2", "  2.3", "4", "5", "6"};
        String title = "JList Exemplo";
        JFrame f = new JFrame(title);
        f.setDefaultCloseOperation(3);
        JList<String> list = new JList<String>(labels);
        JScrollPane scrollPane = new JScrollPane(list);
        Container contentPane = f.getContentPane();
        contentPane.add((Component)scrollPane, "Center");
        f.setSize(200, 200);
        f.setVisible(true);
    }
}

