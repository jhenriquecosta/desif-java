
package br.gov.pbh.desif.service.certificacao;

import java.io.File;
import javax.swing.filechooser.FileFilter;

public class TxtFileFilter extends FileFilter
{

    public TxtFileFilter()
    {
    }

    public boolean accept(File f)
    {
        if(f.isFile())
            return f.getName().toLowerCase().endsWith("txt");
        else
            return true;
    }

    public String getDescription()
    {
        return "Arquivo TXT";
    }
}
