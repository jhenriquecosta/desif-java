
package br.gov.pbh.desif.esec.config.tab;

import java.util.LinkedList;
import java.util.List;

// Referenced classes of package br.gov.pbh.desif.esec.config.tab:
//            GeneralConfigurationTab, AboutTab

public class DefaultTabFactory
{

    private List tabList;

    public DefaultTabFactory()
    {
        tabList = new LinkedList();
        tabList.add(GeneralConfigurationTab.getInstance());
        tabList.add(AboutTab.getInstance());
    }

    public List getTabs()
    {
        return tabList;
    }
}