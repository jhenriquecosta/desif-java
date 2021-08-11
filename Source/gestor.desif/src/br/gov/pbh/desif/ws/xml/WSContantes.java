/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.xml;

import java.text.SimpleDateFormat;

public interface WSContantes {
    public static final String XML_ENCODING = "UTF-8";
    public static final String XML_VERSION = "1.0";

    public static interface Formatters {
        public static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("yyyy-MM-dd");
        public static final SimpleDateFormat DATETIME_FORMAT = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
    }

}

