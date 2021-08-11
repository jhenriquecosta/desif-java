
package br.gov.pbh.desif.ws.xml.vo;

import java.math.BigInteger;

public class TcIdentificadorPessoa
{
   
    public enum Tipo
    {
        PJ(0, 2),
        PF(1, 1),
        NAO_INFORMADO(2, 3);
       
       int pessoaTipo;
       int documento;
       int inscricao;
               
        Tipo(int doc,int insc )
        {
         this.documento = doc;
         this.inscricao = insc;
        }
    
        public int getPessoaTipo()
        {
            return pessoaTipo;
        }
        
        
        public static Tipo getTipo(int pessoaTipo)
        {
            Tipo values[] = values();
            Tipo arr$[] = values;
            int len$ = arr$.length;
            for(int i$ = 0; i$ < len$; i$++)
            {
                Tipo tipo = arr$[i$];
                if(pessoaTipo == tipo.getPessoaTipo())
                    return tipo;
            }
            return null;
        }

    }


    private Tipo tipoPessoa;
    private String documento;
    private String inscricaoMunicipal;

    public TcIdentificadorPessoa()
    {
    }

    public TcIdentificadorPessoa(Tipo tipoPessoa, BigInteger documento, String inscricaoMunicipal)
    {
        this.tipoPessoa = tipoPessoa;
        if(documento != null)
        {
            if(tipoPessoa.equals(Tipo.PF))
                for(this.documento = documento.toString(); this.documento.length() < 11; this.documento = (new StringBuilder()).append("0").append(this.documento).toString());
            else
            if(tipoPessoa.equals(Tipo.PJ))
                for(this.documento = documento.toString(); this.documento.length() < 14; this.documento = (new StringBuilder()).append("0").append(this.documento).toString());
            else
            if(tipoPessoa.equals(Tipo.NAO_INFORMADO))
                this.documento = "00000000000";
        } else
        {
            this.documento = "00000000000";
            this.tipoPessoa = Tipo.NAO_INFORMADO;
        }
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public Tipo getTipoPessoa()
    {
        return tipoPessoa;
    }

    public String getDocumento()
    {
        return documento;
    }

    public String getInscricaoMunicipal()
    {
        return inscricaoMunicipal;
    }

    public void setTipoPessoa(Tipo tipoPessoa)
    {
        this.tipoPessoa = tipoPessoa;
    }

    public void setDocumento(String documento)
    {
        this.documento = documento;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal)
    {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String toString()
    {
        return (new StringBuilder()).append("TcIdentificadorPessoa[tipoPessoa=").append(tipoPessoa).append(",documento=").append(documento).append(",inscricaoMunicipal=").append(inscricaoMunicipal).append(']').toString();
    }

    public int hashCode()
    {
        int prime = 31;
        int result = 1;
        result = 31 * result + (documento != null ? documento.hashCode() : 0);
        result = 31 * result + (inscricaoMunicipal != null ? inscricaoMunicipal.hashCode() : 0);
        result = 31 * result + (tipoPessoa != null ? tipoPessoa.hashCode() : 0);
        return result;
    }

    public boolean equals(Object obj)
    {
        if(this == obj)
            return true;
        if(obj == null)
            return false;
        if(!(obj instanceof TcIdentificadorPessoa))
            return false;
        TcIdentificadorPessoa other = (TcIdentificadorPessoa)obj;
        if(documento == null)
        {
            if(other.documento != null)
                return false;
        } else
        if(!documento.equals(other.documento))
            return false;
        if(inscricaoMunicipal == null)
        {
            if(other.inscricaoMunicipal != null)
                return false;
        } else
        if(!inscricaoMunicipal.equals(other.inscricaoMunicipal))
            return false;
        if(tipoPessoa == null)
        {
            if(other.tipoPessoa != null)
                return false;
        } else
        if(!tipoPessoa.equals(other.tipoPessoa))
            return false;
        return true;
    }
}
