/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.config.tab;

import javax.swing.JPanel;

public abstract class AbstractConfigurationTab {
    protected String name;
    protected String title;

    public String getName() {
        return this.name;
    }

    public String getTitle() {
        return this.title;
    }

    public abstract JPanel getPanel();
}

