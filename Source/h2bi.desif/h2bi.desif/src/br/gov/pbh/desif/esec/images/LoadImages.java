
package br.gov.pbh.desif.esec.images;

import java.awt.Toolkit;
import javax.swing.ImageIcon;

public class LoadImages
{

    public LoadImages()
    {
    }

    public static ImageIcon getImage(String icon)
    {
        return new ImageIcon(Toolkit.getDefaultToolkit().getImage(br.gov.pbh.desif.esec.images.LoadImages.class.getResource(icon)), icon);
    }
}