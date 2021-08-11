/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.config;

public class InvalidConfigurationException
extends Exception {
    private static final long serialVersionUID = 1L;
    private Exception exception;

    public InvalidConfigurationException() {
    }

    public InvalidConfigurationException(Exception e) {
        this.exception = e;
    }

    public Exception getException() {
        return this.exception;
    }
}

