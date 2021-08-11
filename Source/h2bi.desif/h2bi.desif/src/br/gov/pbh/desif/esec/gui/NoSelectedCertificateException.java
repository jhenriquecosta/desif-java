
package br.gov.pbh.desif.esec.gui;
public class NoSelectedCertificateException extends Exception
{

    private static final long serialVersionUID = 1L;
    private Exception exception;

    public NoSelectedCertificateException()
    {
    }

    public NoSelectedCertificateException(Exception e)
    {
        exception = e;
    }

    public Exception getException()
    {
        return exception;
    }
}