/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.certificacao;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TxtFileFilter
extends FileFilter {
    @Override
    public boolean accept(File f) {
        if (f.isFile()) {
            if (f.getName().toLowerCase().endsWith("txt")) {
                return true;
            }
            return false;
        }
        return true;
    }

    @Override
    public String getDescription() {
        return "Arquivo TXT";
    }
}

