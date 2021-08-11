
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractAlerta
    implements Serializable
{

    private String id;
    private String mensagem;
    private String motivo;
    private Set sistemaAlertas;
    private Set erroSolucaos;

    public AbstractAlerta()
    {
        sistemaAlertas = new HashSet(0);
        erroSolucaos = new HashSet(0);
    }

    public AbstractAlerta(String id)
    {
        sistemaAlertas = new HashSet(0);
        erroSolucaos = new HashSet(0);
        this.id = id;
    }

    public AbstractAlerta(String id, String mensagem, String motivo)
    {
        sistemaAlertas = new HashSet(0);
        erroSolucaos = new HashSet(0);
        this.id = id;
        this.mensagem = mensagem;
        this.motivo = motivo;
    }

    public AbstractAlerta(String id, String mensagem, String motivo, Set sistemaAlertas, Set erroSolucaos)
    {
        this.sistemaAlertas = new HashSet(0);
        this.erroSolucaos = new HashSet(0);
        this.id = id;
        this.mensagem = mensagem;
        this.motivo = motivo;
        this.sistemaAlertas = sistemaAlertas;
        this.erroSolucaos = erroSolucaos;
    }

    public Set getErroSolucaos()
    {
        return erroSolucaos;
    }

    public void setErroSolucaos(Set erroSolucaos)
    {
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

    public Set getSistemaAlertas()
    {
        return sistemaAlertas;
    }

    public void setSistemaAlertas(Set sistemaAlertas)
    {
        this.sistemaAlertas = sistemaAlertas;
    }
}