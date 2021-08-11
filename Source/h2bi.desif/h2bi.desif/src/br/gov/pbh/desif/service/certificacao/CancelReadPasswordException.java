
package br.gov.pbh.desif.service.certificacao;

import java.io.IOException;

public class CancelReadPasswordException extends IOException
{

    private static final long serialVersionUID = 0xb32e7e80aa2cf173L;

    public CancelReadPasswordException()
    {
    }

    public CancelReadPasswordException(String s)
    {
        super(s);
    }
}
