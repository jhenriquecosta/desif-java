/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.config.tab;

import br.gov.pbh.desif.esec.config.tab.AboutTab;
import br.gov.pbh.desif.esec.config.tab.GeneralConfigurationTab;
import java.util.LinkedList;
import java.util.List;

public class DefaultTabFactory {
    private List tabList = new LinkedList();

    public DefaultTabFactory() {
        this.tabList.add(GeneralConfigurationTab.getInstance());
        this.tabList.add(AboutTab.getInstance());
    }

    public List getTabs() {
        return this.tabList;
    }
}

