/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.gui;

public class NoSelectedCertificateException
extends Exception {
    private static final long serialVersionUID = 1L;
    private Exception exception;

    public NoSelectedCertificateException() {
    }

    public NoSelectedCertificateException(Exception e) {
        this.exception = e;
    }

    public Exception getException() {
        return this.exception;
    }
}

