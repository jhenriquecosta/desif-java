

package br.gov.pbh.desif.esec.util;

import java.awt.*;

public class Util
{

    public static Frame generalOwner = new Frame();

    public Util()
    {
    }

    public static void centerOnScreen(Window w)
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = w.getSize();
        if(frameSize.height > screenSize.height)
            frameSize.height = screenSize.height;
        if(frameSize.width > screenSize.width)
            frameSize.width = screenSize.width;
        w.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public static void centerOnScreen(Window parent, Window child)
    {
        if(parent == null)
        {
            centerOnScreen(child);
            
        }
        else
        {
            Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
            Rectangle parentRect = parent.getBounds();
            Rectangle childRect = child.getBounds();
            childRect.x = parentRect.x + (parentRect.width - childRect.width) / 2;
            childRect.y = parentRect.y + (parentRect.height - childRect.height) / 2;
            childRect.x = Math.max(0, childRect.x);
            childRect.y = Math.max(0, childRect.y);
            childRect.x = Math.min(screenSize.width - childRect.width, childRect.x);
            childRect.y = Math.min(screenSize.height - childRect.height, childRect.y);
            child.setBounds(childRect);
            
        }
    }

    public static Window getActiveWindow()
    {
        return KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
    }

}