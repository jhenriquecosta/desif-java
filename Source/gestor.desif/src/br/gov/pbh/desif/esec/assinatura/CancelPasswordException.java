/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.assinatura;

public class CancelPasswordException
extends Exception {
    private static final long serialVersionUID = 1L;
    private Exception exception;

    public CancelPasswordException() {
    }

    public CancelPasswordException(Exception e) {
        this.exception = e;
    }

    public Exception getException() {
        return this.exception;
    }
}

