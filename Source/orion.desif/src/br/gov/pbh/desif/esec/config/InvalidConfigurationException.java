

package br.gov.pbh.desif.esec.config;


public class InvalidConfigurationException extends Exception
{

    private static final long serialVersionUID = 1L;
    private Exception exception;

    public InvalidConfigurationException()
    {
    }

    public InvalidConfigurationException(Exception e)
    {
        exception = e;
    }

    public Exception getException()
    {
        return exception;
    }
}