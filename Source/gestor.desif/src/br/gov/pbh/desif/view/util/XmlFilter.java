/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.view.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class XmlFilter
extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isDirectory()) {
            return true;
        }
        String extension = SwingUtils.getExtension(f);
        if (extension != null) {
            if (extension.equals(SwingUtils.xml)) {
                return true;
            }
            return false;
        }
        return false;
    }

    @Override
    public String getDescription() {
        return "*.xml";
    }
}

