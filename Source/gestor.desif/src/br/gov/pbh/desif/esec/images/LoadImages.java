/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.images;

import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import javax.swing.ImageIcon;

public class LoadImages {
    public static ImageIcon getImage(String icon) {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(LoadImages.class.getResource(icon)), icon);
    }
}

