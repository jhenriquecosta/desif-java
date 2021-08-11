/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.view.util;

import java.awt.Component;
import java.io.File;
import javax.swing.JOptionPane;

public class SwingUtils {
    public static String txt = "txt";
    public static String xml = "xml";

    public static void msgErro(Component component, String msg) {
        JOptionPane.showMessageDialog(component, msg, "Erro", 0);
    }

    public static void msgAlerte(Component component, String msg) {
        JOptionPane.showMessageDialog(component, msg, "Alerta", 1);
    }

    public static void msgAlertaEnvio(Component component, String msg) {
        JOptionPane.showMessageDialog(component, msg, "Alerta", 2);
    }

    public static void msgGenerica(Component component, String msg, String titulo) {
        JOptionPane.showMessageDialog(component, msg, titulo, 0);
    }

    public static int msgQues(Component component, String msg) {
        Object[] opcoes = new String[]{"Sim", "N\u00e3o"};
        int retorno = JOptionPane.showOptionDialog(component, msg, "Confirme.", 0, 3, null, opcoes, opcoes[0]);
        return retorno;
    }

    public static String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf(46);
        if (i > 0 && i < s.length() - 1) {
            ext = s.substring(i + 1).toLowerCase();
        }
        return ext;
    }
}

