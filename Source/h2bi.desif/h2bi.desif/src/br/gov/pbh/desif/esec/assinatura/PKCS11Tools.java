
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkcs.pkcs11.sc.SCCryptoki;
import br.com.esec.pkcs.pkcs11.sc.SCTerminalInfo;
import java.util.*;

public class PKCS11Tools
{

    private static Map libraryNamesMap;
    private static boolean terminalsLoaded = false;

    public PKCS11Tools()
    {
    }

    public static String[] getSmartCardModels()
    {
        if(!loadTerminals())
            return null;
        else
            return (String[])(String[])libraryNamesMap.keySet().toArray(new String[0]);
    }

    private static boolean loadTerminals()
    {
        if(!terminalsLoaded)
        {
            libraryNamesMap = new Hashtable();
            SCTerminalInfo terminals[] = SCCryptoki.getTerminals();
            for(int i = 0; i < terminals.length; i++)
                libraryNamesMap.put(terminals[i].getTerminalName(), terminals[i]);

            terminalsLoaded = true;
        }
        return terminalsLoaded;
    }

}