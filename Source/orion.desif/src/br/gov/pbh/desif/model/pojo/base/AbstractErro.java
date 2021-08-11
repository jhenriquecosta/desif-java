
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractErro
    implements Serializable
{

    private String id;
    private String mensagem;
    private String motivo;
    private Set sistemaErros;
    private Set erroSolucaos;

    public AbstractErro()
    {
        sistemaErros = new HashSet(0);
        erroSolucaos = new HashSet(0);
    }

    public AbstractErro(String id)
    {
        sistemaErros = new HashSet(0);
        erroSolucaos = new HashSet(0);
        this.id = id;
    }

    public AbstractErro(String id, String mensagem, String motivo)
    {
        sistemaErros = new HashSet(0);
        erroSolucaos = new HashSet(0);
        this.id = id;
        this.mensagem = mensagem;
        this.motivo = motivo;
    }

    public AbstractErro(String id, String mensagem, String motivo, Set sistemaErros, Set erroSolucaos)
    {
        this.sistemaErros = new HashSet(0);
        this.erroSolucaos = new HashSet(0);
        this.id = id;
        this.mensagem = mensagem;
        this.motivo = motivo;
        this.sistemaErros = sistemaErros;
        this.erroSolucaos = erroSolucaos;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public String getMensagem()
    {
        return mensagem;
    }

    public void setMensagem(String mensagem)
    {
        this.mensagem = mensagem;
    }

    public String getMotivo()
    {
        return motivo;
    }

    public void setMotivo(String motivo)
    {
        this.motivo = motivo;
    }

    public Set getSistemaErros()
    {
        return sistemaErros;
    }

    public void setSistemaErros(Set sistemaErros)
    {
        this.sistemaErros = sistemaErros;
    }

    public Set getErroSolucaos()
    {
        return erroSolucaos;
    }

    public void setErroSolucaos(Set erroSolucaos)
    {
        this.erroSolucaos = erroSolucaos;
    }
}