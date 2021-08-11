

package br.gov.pbh.desif.esec.config.tab;

import javax.swing.JPanel;

public abstract class AbstractConfigurationTab
{

    protected String name;
    protected String title;

    public AbstractConfigurationTab()
    {
    }

    public String getName()
    {
        return name;
    }

    public String getTitle()
    {
        return title;
    }

    public abstract JPanel getPanel();
}