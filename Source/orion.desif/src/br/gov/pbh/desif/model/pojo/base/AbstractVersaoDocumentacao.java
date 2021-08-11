

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.Date;

public abstract class AbstractVersaoDocumentacao
    implements Serializable
{

    private String numVersaoDocumentacao;
    private Date dataInicioVersao;
    private Date dataFimVersao;

    public AbstractVersaoDocumentacao()
    {
    }

    public AbstractVersaoDocumentacao(String numVersaoDocumentacao, Date dataInicioVersao, Date dataFimVersao)
    {
        this.numVersaoDocumentacao = numVersaoDocumentacao;
        this.dataInicioVersao = dataInicioVersao;
        this.dataFimVersao = dataFimVersao;
    }

    public Date getDataFimVersao()
    {
        return dataFimVersao;
    }

    public void setDataFimVersao(Date dataFimVersao)
    {
        this.dataFimVersao = dataFimVersao;
    }

    public Date getDataInicioVersao()
    {
        return dataInicioVersao;
    }

    public void setDataInicioVersao(Date dataInicioVersao)
    {
        this.dataInicioVersao = dataInicioVersao;
    }

    public String getNumVersaoDocumentacao()
    {
        return numVersaoDocumentacao;
    }

    public void setNumVersaoDocumentacao(String numVersaoDocumentacao)
    {
        this.numVersaoDocumentacao = numVersaoDocumentacao;
    }
}