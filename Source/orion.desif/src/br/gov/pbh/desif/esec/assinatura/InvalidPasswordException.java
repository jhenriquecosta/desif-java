
package br.gov.pbh.desif.esec.assinatura;


public class InvalidPasswordException extends Exception
{

    private static final long serialVersionUID = 1L;
    private Exception exception;

    public InvalidPasswordException()
    {
    }

    public InvalidPasswordException(Exception e)
    {
        exception = e;
    }

    public Exception getException()
    {
        return exception;
    }
}