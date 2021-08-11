
package br.gov.pbh.desif.service.certificacao;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class P7sFileFilter extends FileFilter
{

    public P7sFileFilter()
    {
    }

    public boolean accept(File f)
    {
        if(f.isFile())
            return f.getName().toLowerCase().endsWith("p7s");
        else
            return true;
    }

    public String getDescription()
    {
        return "Arquivo XML";
    }
}
