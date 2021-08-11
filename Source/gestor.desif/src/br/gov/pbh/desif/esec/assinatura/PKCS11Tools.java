/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  br.com.esec.pkcs.pkcs11.sc.SCCryptoki
 *  br.com.esec.pkcs.pkcs11.sc.SCTerminalInfo
 */
package br.gov.pbh.desif.esec.assinatura;

import br.com.esec.pkcs.pkcs11.sc.SCCryptoki;
import br.com.esec.pkcs.pkcs11.sc.SCTerminalInfo;
import java.util.Hashtable;
import java.util.Map;
import java.util.Set;

public class PKCS11Tools {
    private static Map libraryNamesMap;
    private static boolean terminalsLoaded;

    public static String[] getSmartCardModels()
    {
        if (!PKCS11Tools.loadTerminals()) {
            return null;
        }
       // return libraryNamesMap.keySet().toArray(new String[0]);
        return (String[])(String[])libraryNamesMap.keySet().toArray(new String[0]);
        
    }

    private static boolean loadTerminals() {
        if (!terminalsLoaded) {
            libraryNamesMap = new Hashtable();
            SCTerminalInfo[] terminals = SCCryptoki.getTerminals();
            for (int i = 0; i < terminals.length; ++i) {
                libraryNamesMap.put(terminals[i].getTerminalName(), terminals[i]);
            }
            terminalsLoaded = true;
        }
        return terminalsLoaded;
    }

    static {
        terminalsLoaded = false;
    }
}

