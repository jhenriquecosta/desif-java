/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.esec.assinatura;

public class InvalidPasswordException
extends Exception {
    private static final long serialVersionUID = 1L;
    private Exception exception;

    public InvalidPasswordException() {
    }

    public InvalidPasswordException(Exception e) {
        this.exception = e;
    }

    public Exception getException() {
        return this.exception;
    }
}

