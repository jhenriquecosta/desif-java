package br.gov.pbh.desif.esec.config;

import br.com.esec.misc.Base64;
import br.com.esec.util.libs.DownloadLibs;
import br.com.esec.version.SDKVersion;
import br.gov.pbh.desif.esec.assinatura.PKCS11Tools;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ConfigurationManager {
    public static final int PKCS11 = 1;
    public static final int PKCS12 = 2;
    public static final int CAPI = 3;
    public static final int HSM = 4;
    private String SDK_WEB_CONFIG_DIR = System.getProperty("user.home") + File.separator + ".desif";
    private String configFile = this.SDK_WEB_CONFIG_DIR + File.separator + "certificacao-digital.properties";
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

    public static ConfigurationManager getInstance() {
        if (instance == null) {
            instance = new ConfigurationManager();
        }
        return instance;
    }

    private ConfigurationManager() {
        File configDir = new File(this.SDK_WEB_CONFIG_DIR);
        if (!configDir.exists() || !configDir.isDirectory()) {
            configDir.mkdirs();
        }
        this.selfEncryption = new Configuration(SELF_ENCRYPTION_FLAG, "false", new Boolean(false));
        this.keyStoreType = new Configuration(KEY_STORE_TYPE, "2", new Integer(2));
        this.configurationPKCS12FileSign = new Configuration(PKCS12_FILENAME_SIGN, "", new File(""));
        this.configurationPKCS12FileEncrypt = new Configuration(PKCS12_FILENAME_ENCRYPT, "", new File(""));
        this.configurationSmartcardLibrary = new Configuration(PKCS11_MODEL, "", null);
        this.configurationHsmLib = new Configuration(HSM_LIB, "", null);
        this.configurationHsmSlot = new Configuration(HSM_SLOT, "", null);
        this.configurationPKCS12FileSign = new Configuration(PKCS12_FILENAME_SIGN, "", new File(""));
    }

    public int getKeyStoreType() {
        Configuration conf = this.getConfiguration(KEY_STORE_TYPE);
        return Integer.parseInt(conf.getValue());
    }

    public void setKeyStoreType(int ksType) {
        this.setConfiguration(new Configuration(KEY_STORE_TYPE, Integer.toString(ksType), new Integer(ksType)));
    }

    public String getPKCS12FileNameSign() {
        Configuration conf = ConfigurationManager.getInstance().getConfiguration(PKCS12_FILENAME_SIGN);
        return conf.getValue();
    }

    public String getPKCS12FileNameEncrypt() {
        Configuration conf = ConfigurationManager.getInstance().getConfiguration(PKCS12_FILENAME_ENCRYPT);
        return conf.getValue();
    }

    public void setPKCS12FileNameSign(String fileName) {
        this.setConfiguration(new Configuration(PKCS12_FILENAME_SIGN, fileName, new File(fileName)));
    }

    public void setPKCS12FileNameEncrypt(String fileName) {
        this.setConfiguration(new Configuration(PKCS12_FILENAME_ENCRYPT, fileName, new File(fileName)));
    }

    public void setHSMLib(String libName) {
        this.setConfiguration(new Configuration(HSM_LIB, libName, libName));
    }

    public String getHSMLib() {
        Configuration conf = ConfigurationManager.getInstance().getConfiguration(HSM_LIB);
        return conf.getValue();
    }

    public int getHSMSlot() {
        Configuration conf = this.getConfiguration(HSM_SLOT);
        return Integer.parseInt(conf.getValue());
    }

    public void setHSMSlot(int hsmSlot) {
        this.setConfiguration(new Configuration(HSM_SLOT, Integer.toString(hsmSlot), new Integer(hsmSlot)));
    }

    public String getPKCS11Model() {
        Configuration conf = ConfigurationManager.getInstance().getConfiguration(PKCS11_MODEL);
        return conf.getValue();
    }

    public void setPKCS11Model(String model) {
        if (model == null || model == "") {
            model = "Autom\ufffdtico";
        }
        this.setConfiguration(new Configuration(PKCS11_MODEL, model, model));
    }

    public String[] getPKCS11ModelList() {
        return PKCS11Tools.getSmartCardModels();
    }

    public boolean isSelfEncryption() {
        Configuration conf = ConfigurationManager.getInstance().getConfiguration(SELF_ENCRYPTION_FLAG);
        return (Boolean)conf.getObject();
    }

    public void setSelfEncryption(boolean flag) {
        this.setConfiguration(new Configuration(SELF_ENCRYPTION_FLAG, Boolean.toString(flag), new Boolean(flag)));
    }

    public String getVersion() {
        return SDKVersion.getVersion();
    }

    private Configuration getConfiguration(String name) {
        if (KEY_STORE_TYPE.equals(name)) {
            return this.keyStoreType;
        }
        if (PKCS12_FILENAME_SIGN.equals(name)) {
            return this.configurationPKCS12FileSign;
        }
        if (PKCS12_FILENAME_ENCRYPT.equals(name)) {
            return this.configurationPKCS12FileEncrypt;
        }
        if (PKCS11_MODEL.equals(name)) {
            return this.configurationSmartcardLibrary;
        }
        if (SELF_ENCRYPTION_FLAG.equals(name)) {
            return this.selfEncryption;
        }
        if (HSM_LIB.equals(name)) {
            return this.configurationHsmLib;
        }
        if (HSM_SLOT.equals(name)) {
            return this.configurationHsmSlot;
        }
        return null;
    }

    private void setConfiguration(Configuration conf) {
        if (KEY_STORE_TYPE.equals(conf.getName())) {
            this.keyStoreType = conf;
        } else if (PKCS12_FILENAME_SIGN.equals(conf.getName())) {
            this.configurationPKCS12FileSign = conf;
        } else if (PKCS12_FILENAME_ENCRYPT.equals(conf.getName())) {
            this.configurationPKCS12FileEncrypt = conf;
        } else if (PKCS11_MODEL.equals(conf.getName())) {
            this.configurationSmartcardLibrary = conf;
        } else if (SELF_ENCRYPTION_FLAG.equals(conf.getName())) {
            this.selfEncryption = conf;
        } else if (HSM_LIB.equals(conf.getName())) {
            this.configurationHsmLib = conf;
        } else if (HSM_SLOT.equals(conf.getName())) {
            this.configurationHsmSlot = conf;
        }
    }

    public void loadProperties(boolean fullDevices) throws InvalidConfigurationException, FileNotFoundException, IOException {
        this.properties = new Properties();
        File cfgFile = new File(this.configFile);
        if (cfgFile.exists() && cfgFile.isFile()) {
            this.properties.load(new FileInputStream(this.configFile));
        }
        Integer pkcsType = new Integer(2);
        this.keyStoreType = new Configuration(KEY_STORE_TYPE, pkcsType.toString(), pkcsType);
        this.selfEncryption = new Configuration(SELF_ENCRYPTION_FLAG, new Boolean(false).toString(), new Boolean(false));
        String smartCardModel = null;
        if (this.properties.keySet().size() == 0) {
            if (!fullDevices) {
                throw new InvalidConfigurationException();
            }
            if (DownloadLibs.isWindows()) {
                pkcsType = new Integer(3);
            } else {
                this.configurationSmartcardLibrary = new Configuration(PKCS11_MODEL, "Autom\ufffdtico", null);
            }
            this.keyStoreType = new Configuration(KEY_STORE_TYPE, pkcsType.toString(), pkcsType);
            this.saveProperties();
        }
        int ksType = Integer.parseInt(this.properties.getProperty(KEY_STORE_TYPE, "2"));
        if (!fullDevices && ksType != 2) {
            throw new InvalidConfigurationException();
        }
        Boolean selEnc = new Boolean(this.properties.getProperty(SELF_ENCRYPTION_FLAG, "false"));
        this.selfEncryption = new Configuration(SELF_ENCRYPTION_FLAG, selEnc.toString(), selEnc);
        switch (ksType) {
            case 3: {
                pkcsType = new Integer(3);
                this.keyStoreType = new Configuration(KEY_STORE_TYPE, pkcsType.toString(), pkcsType);
                break;
            }
            case 1: {
                pkcsType = new Integer(1);
                this.keyStoreType = new Configuration(KEY_STORE_TYPE, pkcsType.toString(), pkcsType);
                smartCardModel = this.properties.getProperty(PKCS11_MODEL);
                if (smartCardModel == null) {
                    throw new InvalidConfigurationException(new Exception("Modelo de dispositivo n\ufffdo definido"));
                }
                this.configurationSmartcardLibrary = null;
                try {
                    this.configurationSmartcardLibrary = new Configuration(PKCS11_MODEL, smartCardModel, null);
                }
                catch (UnsatisfiedLinkError unsatisfiedLinkError) {
                    // empty catch block
                }
                if (this.configurationSmartcardLibrary != null) break;
                throw new InvalidConfigurationException(new Exception("Biblioteca do dispositivo n\ufffdo carregada"));
            }
            case 2: {
                pkcsType = new Integer(2);
                this.keyStoreType = new Configuration(KEY_STORE_TYPE, pkcsType.toString(), pkcsType);
                String filenameSign = this.properties.getProperty(PKCS12_FILENAME_SIGN);
                String filenameEncrypt = this.properties.getProperty(PKCS12_FILENAME_ENCRYPT);
                File file = null;
                if (filenameSign == null) {
                    throw new InvalidConfigurationException(new Exception("Nome do arquivo PKCS12 (A1) n\ufffdo especificado"));
                }
                file = new File(filenameSign);
                this.configurationPKCS12FileSign = new Configuration(PKCS12_FILENAME_SIGN, file.getAbsolutePath(), file);
                if (filenameEncrypt == null) {
                    throw new InvalidConfigurationException(new Exception("Nome do arquivo PKCS12 (A1) n\ufffdo especificado"));
                }
                file = new File(filenameEncrypt);
                this.configurationPKCS12FileEncrypt = new Configuration(PKCS12_FILENAME_ENCRYPT, file.getAbsolutePath(), file);
                break;
            }
            case 4: {
                pkcsType = new Integer(4);
                this.keyStoreType = new Configuration(KEY_STORE_TYPE, pkcsType.toString(), pkcsType);
                String hsmLib = this.properties.getProperty(HSM_LIB);
                String hsmSlot = this.properties.getProperty(HSM_SLOT);
                if (hsmLib == null) {
                    throw new InvalidConfigurationException(new Exception("Nome do arquivo PKCS#11 do HSM n\ufffdo especificado"));
                }
                this.configurationHsmLib = new Configuration(HSM_LIB, hsmLib, hsmLib);
                if (hsmSlot == null) {
                    throw new InvalidConfigurationException(new Exception("N\ufffdmero do Slot do HSM n\ufffdo especificado"));
                }
                try {
                    Integer.parseInt(hsmSlot);
                }
                catch (NumberFormatException nfe) {
                    throw new InvalidConfigurationException(new Exception("O valor do slot deve ser n\ufffdmero inteiro maior que 0 (zero)"));
                }
                this.configurationHsmSlot = new Configuration(HSM_SLOT, hsmSlot, new Integer(hsmSlot));
                break;
            }
            default: {
                throw new InvalidConfigurationException(new Exception("Tipo inv\ufffdlido de PKCS"));
            }
        }
    }

    public void saveProperties() throws IOException {
        this.properties.put(this.keyStoreType.getName(), this.keyStoreType.getValue());
        this.properties.put(this.selfEncryption.getName(), this.selfEncryption.getValue());
        if (this.configurationPKCS12FileSign != null) {
            this.properties.put(this.configurationPKCS12FileSign.getName(), this.configurationPKCS12FileSign.getValue());
        }
        if (this.configurationPKCS12FileEncrypt != null) {
            this.properties.put(this.configurationPKCS12FileEncrypt.getName(), this.configurationPKCS12FileEncrypt.getValue());
        }
        if (this.configurationSmartcardLibrary != null) {
            this.properties.put(this.configurationSmartcardLibrary.getName(), this.configurationSmartcardLibrary.getValue());
        }
        if (this.configurationHsmLib != null) {
            this.properties.put(this.configurationHsmLib.getName(), this.configurationHsmLib.getValue());
        }
        if (this.configurationHsmSlot != null) {
            this.properties.put(this.configurationHsmSlot.getName(), this.configurationHsmSlot.getValue());
        }
        this.properties.store(new FileOutputStream(this.configFile), "DESIF - Configuracao de Certificacao Digital");
    }

    public void saveTokenOnlyProperties() throws FileNotFoundException, IOException {
        this.properties = new Properties();
        Integer pkcsType = new Integer(1);
        this.keyStoreType = new Configuration(KEY_STORE_TYPE, pkcsType.toString(), pkcsType);
        this.properties.put(this.keyStoreType.getName(), this.keyStoreType.getValue());
        this.selfEncryption = new Configuration(SELF_ENCRYPTION_FLAG, new Boolean(false).toString(), new Boolean(false));
        this.properties.put(this.selfEncryption.getName(), this.selfEncryption.getValue());
        this.configurationSmartcardLibrary = new Configuration(PKCS11_MODEL, "Autom\ufffdtico", null);
        this.configurationPKCS12FileSign = new Configuration(PKCS12_FILENAME_SIGN, "", new File(""));
        this.configurationPKCS12FileEncrypt = new Configuration(PKCS12_FILENAME_ENCRYPT, "", new File(""));
        this.configurationHsmLib = new Configuration(HSM_LIB, "", null);
        this.configurationHsmSlot = new Configuration(HSM_SLOT, "", null);
        this.properties.put(this.configurationPKCS12FileSign.getName(), this.configurationPKCS12FileSign.getValue());
        this.properties.put(this.configurationPKCS12FileEncrypt.getName(), this.configurationPKCS12FileEncrypt.getValue());
        this.properties.put(this.configurationSmartcardLibrary.getName(), this.configurationSmartcardLibrary.getValue());
        this.properties.put(this.configurationHsmLib.getName(), this.configurationHsmLib.getValue());
        this.properties.put(this.configurationHsmSlot.getName(), this.configurationHsmSlot.getValue());
        this.properties.store(new FileOutputStream(this.configFile), "DESIF - Configuracao de Certificacao Digital");
    }

    public Properties getProperties() {
        return this.properties;
    }

    public String getEncoded() {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            this.properties.store(baos, "DESIF - Configuracao de Certificacao Digital");
        }
        catch (IOException iOException) {
            // empty catch block
        }
        String xml = baos.toString();
        return Base64.base64Encode((String)xml);
    }

    public void saveConfiguration(byte[] encoded) throws IOException {
        FileOutputStream fo = new FileOutputStream(this.configFile);
        fo.write(encoded);
        fo.close();
    }
}

