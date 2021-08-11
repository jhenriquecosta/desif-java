
package br.gov.pbh.desif.view.util;

import java.io.File;
import javax.swing.filechooser.FileFilter;

// Referenced classes of package br.gov.pbh.desif.view.util:
//            SwingUtils

public class TxtFilter extends FileFilter
{

    public TxtFilter()
    {
    }

    public boolean accept(File f)
    {
        if(f.isDirectory())
            return true;
        String extension = SwingUtils.getExtension(f);
        if(extension != null)
            return extension.equals(SwingUtils.txt);
        else
            return false;
    }

    public String getDescription()
    {
        return "*.txt";
    }
}
