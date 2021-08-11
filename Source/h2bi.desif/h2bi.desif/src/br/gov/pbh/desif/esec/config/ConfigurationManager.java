

package br.gov.pbh.desif.esec.config;

import br.com.esec.misc.Base64;
import br.com.esec.util.libs.DownloadLibs;
import br.com.esec.version.SDKVersion;
import br.gov.pbh.desif.esec.assinatura.PKCS11Tools;
import java.io.*;
import java.util.Properties;
import java.util.Set;

// Referenced classes of package br.gov.pbh.desif.esec.config:
//            Configuration, InvalidConfigurationException

public class ConfigurationManager
{

    public static final int PKCS11 = 1;
    public static final int PKCS12 = 2;
    public static final int CAPI = 3;
    public static final int HSM = 4;
    private String SDK_WEB_CONFIG_DIR;
    private String configFile;
    public static final String KEY_STORE_TYPE = "keyStoreType";
    public static final String SELF_ENCRYPTION_FLAG = "selfEncryption";
    public static final String PKCS12_FILENAME_SIGN = "pkcs12FilenameSign";
    public static final String PKCS12_FILENAME_ENCRYPT = "pkcs12FilenameEncrypt";
    public static final String PKCS11_MODEL = "pkcs11Model";
    public static final String HSM_LIB = "hsmLib";
    public static final String HSM_SLOT = "hsmSlot";
    public static final String AUTOMATIC = "Automatico";
    private static ConfigurationManager instance;
    private Properties properties;
    private Configuration selfEncryption;
    private Configuration keyStoreType;
    private Configuration configurationPKCS12FileSign;
    private Configuration configurationPKCS12FileEncrypt;
    private Configuration configurationSmartcardLibrary;
    private Configuration configurationHsmLib;
    private Configuration configurationHsmSlot;

    public static ConfigurationManager getInstance()
    {
        if(instance == null)
            instance = new ConfigurationManager();
        return instance;
    }

    private ConfigurationManager()
    {
        SDK_WEB_CONFIG_DIR = (new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").toString();
        configFile = (new StringBuilder()).append(SDK_WEB_CONFIG_DIR).append(File.separator).append("certificacao-digital.properties").toString();
        File configDir = new File(SDK_WEB_CONFIG_DIR);
        if(!configDir.exists() || !configDir.isDirectory())
            configDir.mkdirs();
        selfEncryption = new Configuration("selfEncryption", "false", new Boolean(false));
        keyStoreType = new Configuration("keyStoreType", "2", new Integer(2));
        configurationPKCS12FileSign = new Configuration("pkcs12FilenameSign", "", new File(""));
        configurationPKCS12FileEncrypt = new Configuration("pkcs12FilenameEncrypt", "", new File(""));
        configurationSmartcardLibrary = new Configuration("pkcs11Model", "", null);
        configurationHsmLib = new Configuration("hsmLib", "", null);
        configurationHsmSlot = new Configuration("hsmSlot", "", null);
        configurationPKCS12FileSign = new Configuration("pkcs12FilenameSign", "", new File(""));
    }

    public int getKeyStoreType()
    {
        Configuration conf = getConfiguration("keyStoreType");
        return Integer.parseInt(conf.getValue());
    }

    public void setKeyStoreType(int ksType)
    {
        setConfiguration(new Configuration("keyStoreType", Integer.toString(ksType), new Integer(ksType)));
    }

    public String getPKCS12FileNameSign()
    {
        Configuration conf = getInstance().getConfiguration("pkcs12FilenameSign");
        return conf.getValue();
    }

    public String getPKCS12FileNameEncrypt()
    {
        Configuration conf = getInstance().getConfiguration("pkcs12FilenameEncrypt");
        return conf.getValue();
    }

    public void setPKCS12FileNameSign(String fileName)
    {
        setConfiguration(new Configuration("pkcs12FilenameSign", fileName, new File(fileName)));
    }

    public void setPKCS12FileNameEncrypt(String fileName)
    {
        setConfiguration(new Configuration("pkcs12FilenameEncrypt", fileName, new File(fileName)));
    }

    public void setHSMLib(String libName)
    {
        setConfiguration(new Configuration("hsmLib", libName, libName));
    }

    public String getHSMLib()
    {
        Configuration conf = getInstance().getConfiguration("hsmLib");
        return conf.getValue();
    }

    public int getHSMSlot()
    {
        Configuration conf = getConfiguration("hsmSlot");
        return Integer.parseInt(conf.getValue());
    }

    public void setHSMSlot(int hsmSlot)
    {
        setConfiguration(new Configuration("hsmSlot", Integer.toString(hsmSlot), new Integer(hsmSlot)));
    }

    public String getPKCS11Model()
    {
        Configuration conf = getInstance().getConfiguration("pkcs11Model");
        return conf.getValue();
    }

    public void setPKCS11Model(String model)
    {
        if(model == null || model == "")
            model = "Autom\uFFFDtico";
        setConfiguration(new Configuration("pkcs11Model", model, model));
    }

    public String[] getPKCS11ModelList()
    {
        return PKCS11Tools.getSmartCardModels();
    }

    public boolean isSelfEncryption()
    {
        Configuration conf = getInstance().getConfiguration("selfEncryption");
        return ((Boolean)conf.getObject()).booleanValue();
    }

    public void setSelfEncryption(boolean flag)
    {
        setConfiguration(new Configuration("selfEncryption", Boolean.toString(flag), new Boolean(flag)));
    }

    public String getVersion()
    {
        return SDKVersion.getVersion();
    }

    private Configuration getConfiguration(String name)
    {
        if("keyStoreType".equals(name))
            return keyStoreType;
        if("pkcs12FilenameSign".equals(name))
            return configurationPKCS12FileSign;
        if("pkcs12FilenameEncrypt".equals(name))
            return configurationPKCS12FileEncrypt;
        if("pkcs11Model".equals(name))
            return configurationSmartcardLibrary;
        if("selfEncryption".equals(name))
            return selfEncryption;
        if("hsmLib".equals(name))
            return configurationHsmLib;
        if("hsmSlot".equals(name))
            return configurationHsmSlot;
        else
            return null;
    }

    private void setConfiguration(Configuration conf)
    {
        if("keyStoreType".equals(conf.getName()))
            keyStoreType = conf;
        else
        if("pkcs12FilenameSign".equals(conf.getName()))
            configurationPKCS12FileSign = conf;
        else
        if("pkcs12FilenameEncrypt".equals(conf.getName()))
            configurationPKCS12FileEncrypt = conf;
        else
        if("pkcs11Model".equals(conf.getName()))
            configurationSmartcardLibrary = conf;
        else
        if("selfEncryption".equals(conf.getName()))
            selfEncryption = conf;
        else
        if("hsmLib".equals(conf.getName()))
            configurationHsmLib = conf;
        else
        if("hsmSlot".equals(conf.getName()))
            configurationHsmSlot = conf;
    }

    public void loadProperties(boolean fullDevices)
        throws InvalidConfigurationException, FileNotFoundException, IOException
    {
        properties = new Properties();
        File cfgFile = new File(configFile);
        if(cfgFile.exists() && cfgFile.isFile())
            properties.load(new FileInputStream(configFile));
        Integer pkcsType = new Integer(2);
        keyStoreType = new Configuration("keyStoreType", pkcsType.toString(), pkcsType);
        selfEncryption = new Configuration("selfEncryption", (new Boolean(false)).toString(), new Boolean(false));
        String smartCardModel = null;
        if(properties.keySet().size() == 0)
        {
            if(!fullDevices)
                throw new InvalidConfigurationException();
            if(DownloadLibs.isWindows())
                pkcsType = new Integer(3);
            else
                configurationSmartcardLibrary = new Configuration("pkcs11Model", "Autom\uFFFDtico", null);
            keyStoreType = new Configuration("keyStoreType", pkcsType.toString(), pkcsType);
            saveProperties();
        }
        int ksType = Integer.parseInt(properties.getProperty("keyStoreType", "2"));
        if(!fullDevices && ksType != 2)
            throw new InvalidConfigurationException();
        Boolean selEnc = new Boolean(properties.getProperty("selfEncryption", "false"));
        selfEncryption = new Configuration("selfEncryption", selEnc.toString(), selEnc);
        switch(ksType)
        {
        case 3: // '\003'
            pkcsType = new Integer(3);
            keyStoreType = new Configuration("keyStoreType", pkcsType.toString(), pkcsType);
            break;

        case 1: // '\001'
            pkcsType = new Integer(1);
            keyStoreType = new Configuration("keyStoreType", pkcsType.toString(), pkcsType);
            smartCardModel = properties.getProperty("pkcs11Model");
            if(smartCardModel == null)
                throw new InvalidConfigurationException(new Exception("Modelo de dispositivo n\uFFFDo definido"));
            configurationSmartcardLibrary = null;
            try
            {
                configurationSmartcardLibrary = new Configuration("pkcs11Model", smartCardModel, null);
            }
            catch(UnsatisfiedLinkError e) { }
            if(configurationSmartcardLibrary == null)
                throw new InvalidConfigurationException(new Exception("Biblioteca do dispositivo n\uFFFDo carregada"));
            break;

        case 2: // '\002'
            pkcsType = new Integer(2);
            keyStoreType = new Configuration("keyStoreType", pkcsType.toString(), pkcsType);
            String filenameSign = properties.getProperty("pkcs12FilenameSign");
            String filenameEncrypt = properties.getProperty("pkcs12FilenameEncrypt");
            File file = null;
            if(filenameSign == null)
                throw new InvalidConfigurationException(new Exception("Nome do arquivo PKCS12 (A1) n\uFFFDo especificado"));
            file = new File(filenameSign);
            configurationPKCS12FileSign = new Configuration("pkcs12FilenameSign", file.getAbsolutePath(), file);
            if(filenameEncrypt == null)
                throw new InvalidConfigurationException(new Exception("Nome do arquivo PKCS12 (A1) n\uFFFDo especificado"));
            file = new File(filenameEncrypt);
            configurationPKCS12FileEncrypt = new Configuration("pkcs12FilenameEncrypt", file.getAbsolutePath(), file);
            break;

        case 4: // '\004'
            pkcsType = new Integer(4);
            keyStoreType = new Configuration("keyStoreType", pkcsType.toString(), pkcsType);
            String hsmLib = properties.getProperty("hsmLib");
            String hsmSlot = properties.getProperty("hsmSlot");
            if(hsmLib == null)
                throw new InvalidConfigurationException(new Exception("Nome do arquivo PKCS#11 do HSM n\uFFFDo especificado"));
            configurationHsmLib = new Configuration("hsmLib", hsmLib, hsmLib);
            if(hsmSlot == null)
                throw new InvalidConfigurationException(new Exception("N\uFFFDmero do Slot do HSM n\uFFFDo especificado"));
            try
            {
                Integer.parseInt(hsmSlot);
            }
            catch(NumberFormatException nfe)
            {
                throw new InvalidConfigurationException(new Exception("O valor do slot deve ser n\uFFFDmero inteiro maior que 0 (zero)"));
            }
            configurationHsmSlot = new Configuration("hsmSlot", hsmSlot, new Integer(hsmSlot));
            break;

        default:
            throw new InvalidConfigurationException(new Exception("Tipo inv\uFFFDlido de PKCS"));
        }
    }

    public void saveProperties()
        throws IOException
    {
        properties.put(keyStoreType.getName(), keyStoreType.getValue());
        properties.put(selfEncryption.getName(), selfEncryption.getValue());
        if(configurationPKCS12FileSign != null)
            properties.put(configurationPKCS12FileSign.getName(), configurationPKCS12FileSign.getValue());
        if(configurationPKCS12FileEncrypt != null)
            properties.put(configurationPKCS12FileEncrypt.getName(), configurationPKCS12FileEncrypt.getValue());
        if(configurationSmartcardLibrary != null)
            properties.put(configurationSmartcardLibrary.getName(), configurationSmartcardLibrary.getValue());
        if(configurationHsmLib != null)
            properties.put(configurationHsmLib.getName(), configurationHsmLib.getValue());
        if(configurationHsmSlot != null)
            properties.put(configurationHsmSlot.getName(), configurationHsmSlot.getValue());
        properties.store(new FileOutputStream(configFile), "DESIF - Configuracao de Certificacao Digital");
    }

    public void saveTokenOnlyProperties()
        throws FileNotFoundException, IOException
    {
        properties = new Properties();
        Integer pkcsType = new Integer(1);
        keyStoreType = new Configuration("keyStoreType", pkcsType.toString(), pkcsType);
        properties.put(keyStoreType.getName(), keyStoreType.getValue());
        selfEncryption = new Configuration("selfEncryption", (new Boolean(false)).toString(), new Boolean(false));
        properties.put(selfEncryption.getName(), selfEncryption.getValue());
        configurationSmartcardLibrary = new Configuration("pkcs11Model", "Autom\uFFFDtico", null);
        configurationPKCS12FileSign = new Configuration("pkcs12FilenameSign", "", new File(""));
        configurationPKCS12FileEncrypt = new Configuration("pkcs12FilenameEncrypt", "", new File(""));
        configurationHsmLib = new Configuration("hsmLib", "", null);
        configurationHsmSlot = new Configuration("hsmSlot", "", null);
        properties.put(configurationPKCS12FileSign.getName(), configurationPKCS12FileSign.getValue());
        properties.put(configurationPKCS12FileEncrypt.getName(), configurationPKCS12FileEncrypt.getValue());
        properties.put(configurationSmartcardLibrary.getName(), configurationSmartcardLibrary.getValue());
        properties.put(configurationHsmLib.getName(), configurationHsmLib.getValue());
        properties.put(configurationHsmSlot.getName(), configurationHsmSlot.getValue());
        properties.store(new FileOutputStream(configFile), "DESIF - Configuracao de Certificacao Digital");
    }

    public Properties getProperties()
    {
        return properties;
    }

    public String getEncoded()
    {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try
        {
            properties.store(baos, "DESIF - Configuracao de Certificacao Digital");
        }
        catch(IOException e) { }
        String xml = baos.toString();
        return Base64.base64Encode(xml);
    }

    public void saveConfiguration(byte encoded[])
        throws IOException
    {
        FileOutputStream fo = new FileOutputStream(configFile);
        fo.write(encoded);
        fo.close();
    }
}