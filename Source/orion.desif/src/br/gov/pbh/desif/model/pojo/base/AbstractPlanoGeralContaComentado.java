

package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public abstract class AbstractPlanoGeralContaComentado
    implements Serializable
{

    private Long id;
    private String conta;
    private String contaCosif;
    private Long numLinhaPgcc;
    private String nome;
    private String contaSupe;
    private String codTribDesif;
    private String descConta;
    private Integer nivel;
    private Set pgccsPaiFilhosForIdnPgccPai;
    private Set pgccsPaiFilhosForIdnPgccFilho;

    public AbstractPlanoGeralContaComentado()
    {
        pgccsPaiFilhosForIdnPgccPai = new HashSet(0);
        pgccsPaiFilhosForIdnPgccFilho = new HashSet(0);
    }

    public AbstractPlanoGeralContaComentado(Long numLinhaPgcc)
    {
        pgccsPaiFilhosForIdnPgccPai = new HashSet(0);
        pgccsPaiFilhosForIdnPgccFilho = new HashSet(0);
        this.numLinhaPgcc = numLinhaPgcc;
    }

    public AbstractPlanoGeralContaComentado(String conta, String contaCosif, Long numLinhaPgcc, String nome, String contaSupe, String codTribDesif, String descConta, 
            Integer nivel, Set pgccsPaiFilhosForIdnPgccPai, Set pgccsPaiFilhosForIdnPgccFilho)
    {
        this.pgccsPaiFilhosForIdnPgccPai = new HashSet(0);
        this.pgccsPaiFilhosForIdnPgccFilho = new HashSet(0);
        this.conta = conta;
        this.contaCosif = contaCosif;
        this.numLinhaPgcc = numLinhaPgcc;
        this.nome = nome;
        this.contaSupe = contaSupe;
        this.codTribDesif = codTribDesif;
        this.descConta = descConta;
        this.nivel = nivel;
        this.pgccsPaiFilhosForIdnPgccPai = pgccsPaiFilhosForIdnPgccPai;
        this.pgccsPaiFilhosForIdnPgccFilho = pgccsPaiFilhosForIdnPgccFilho;
    }

    public AbstractPlanoGeralContaComentado(String conta, String nome, String codTribDesif, String descConta)
    {
        pgccsPaiFilhosForIdnPgccPai = new HashSet(0);
        pgccsPaiFilhosForIdnPgccFilho = new HashSet(0);
        this.conta = conta;
        this.nome = nome;
        this.codTribDesif = codTribDesif;
        this.descConta = descConta;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getConta()
    {
        return conta;
    }

    public void setConta(String conta)
    {
        this.conta = conta;
    }

    public String getContaCosif()
    {
        return contaCosif;
    }

    public void setContaCosif(String contaCosif)
    {
        this.contaCosif = contaCosif;
    }

    public Long getNumLinhaPgcc()
    {
        return numLinhaPgcc;
    }

    public void setNumLinhaPgcc(Long numLinhaPgcc)
    {
        this.numLinhaPgcc = numLinhaPgcc;
    }

    public String getNome()
    {
        return nome;
    }

    public void setNome(String nome)
    {
        this.nome = nome;
    }

    public String getContaSupe()
    {
        return contaSupe;
    }

    public void setContaSupe(String contaSupe)
    {
        this.contaSupe = contaSupe;
    }

    public String getCodTribDesif()
    {
        return codTribDesif;
    }

    public void setCodTribDesif(String codTribDesif)
    {
        this.codTribDesif = codTribDesif;
    }

    public String getDescConta()
    {
        return descConta;
    }

    public void setDescConta(String descConta)
    {
        this.descConta = descConta;
    }

    public Integer getNivel()
    {
        return nivel;
    }

    public void setNivel(Integer nivel)
    {
        this.nivel = nivel;
    }

    public Set getPgccsPaiFilhosForIdnPgccPai()
    {
        return pgccsPaiFilhosForIdnPgccPai;
    }

    public void setPgccsPaiFilhosForIdnPgccPai(Set pgccsPaiFilhosForIdnPgccPai)
    {
        this.pgccsPaiFilhosForIdnPgccPai = pgccsPaiFilhosForIdnPgccPai;
    }

    public Set getPgccsPaiFilhosForIdnPgccFilho()
    {
        return pgccsPaiFilhosForIdnPgccFilho;
    }

    public void setPgccsPaiFilhosForIdnPgccFilho(Set pgccsPaiFilhosForIdnPgccFilho)
    {
        this.pgccsPaiFilhosForIdnPgccFilho = pgccsPaiFilhosForIdnPgccFilho;
    }

    public boolean equals(Object obj)
    {
        if(obj == null)
            return false;
        if(getClass() != obj.getClass())
            return false;
        AbstractPlanoGeralContaComentado other = (AbstractPlanoGeralContaComentado)obj;
        return id == other.id || id != null && id.equals(other.id);
    }

    public int hashCode()
    {
        int hash = 3;
        hash = 29 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }
}