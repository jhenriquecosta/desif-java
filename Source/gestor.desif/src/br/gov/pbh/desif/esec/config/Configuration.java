/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.config;

public class Configuration {
    private String name;
    private String value;
    private Object object;

    public Configuration(String name, String value, Object object) {
        this.name = name;
        this.value = value;
        this.object = object;
    }

    public String getName() {
        return this.name;
    }

    public Object getObject() {
        return this.object;
    }

    public String getValue() {
        return this.value;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setObject(Object object) {
        this.object = object;
    }

    public void setValue(String value) {
        this.value = value;
    }
}

