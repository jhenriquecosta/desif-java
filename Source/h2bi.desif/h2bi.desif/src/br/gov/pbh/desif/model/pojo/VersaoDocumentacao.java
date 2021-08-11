
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractVersaoDocumentacao;
import java.io.Serializable;
import java.util.Date;

public class VersaoDocumentacao extends AbstractVersaoDocumentacao
    implements Serializable
{

    public VersaoDocumentacao()
    {
    }

    public VersaoDocumentacao(String numVersaoDocumentacao, Date dataInicioVersao, Date dataFimVersao)
    {
        super(numVersaoDocumentacao, dataInicioVersao, dataFimVersao);
    }
}