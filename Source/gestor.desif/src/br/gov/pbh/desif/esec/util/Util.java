/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.util;

import java.awt.Dimension;
import java.awt.Frame;
import java.awt.KeyboardFocusManager;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.Window;

public class Util {
    public static Frame generalOwner = new Frame();

    public static void centerOnScreen(Window w) {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        Dimension frameSize = w.getSize();
        if (frameSize.height > screenSize.height) {
            frameSize.height = screenSize.height;
        }
        if (frameSize.width > screenSize.width) {
            frameSize.width = screenSize.width;
        }
        w.setLocation((screenSize.width - frameSize.width) / 2, (screenSize.height - frameSize.height) / 2);
    }

    public static void centerOnScreen(Window parent, Window child) {
        if (parent == null) {
            Util.centerOnScreen(child);
            return;
        }
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

    public static Window getActiveWindow() {
        return KeyboardFocusManager.getCurrentKeyboardFocusManager().getActiveWindow();
    }
}

