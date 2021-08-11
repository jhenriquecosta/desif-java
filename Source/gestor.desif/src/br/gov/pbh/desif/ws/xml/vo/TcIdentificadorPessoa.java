package br.gov.pbh.desif.ws.xml.vo;

import java.math.BigInteger;

public class TcIdentificadorPessoa {
    private Tipo tipoPessoa;
    private String documento;
    private String inscricaoMunicipal;

    public TcIdentificadorPessoa() {
    }

    public TcIdentificadorPessoa(Tipo tipoPessoa, BigInteger documento, String inscricaoMunicipal) {
        this.tipoPessoa = tipoPessoa;
        if (documento != null) {
            if (tipoPessoa.equals((Object)Tipo.PF)) {
                this.documento = documento.toString();
                while (this.documento.length() < 11) {
                    this.documento = "0" + this.documento;
                }
            } else if (tipoPessoa.equals((Object)Tipo.PJ)) {
                this.documento = documento.toString();
                while (this.documento.length() < 14) {
                    this.documento = "0" + this.documento;
                }
            } else if (tipoPessoa.equals((Object)Tipo.NAO_INFORMADO)) {
                this.documento = "00000000000";
            }
        } else {
            this.documento = "00000000000";
            this.tipoPessoa = Tipo.NAO_INFORMADO;
        }
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public Tipo getTipoPessoa() {
        return this.tipoPessoa;
    }

    public String getDocumento() {
        return this.documento;
    }

    public String getInscricaoMunicipal() {
        return this.inscricaoMunicipal;
    }

    public void setTipoPessoa(Tipo tipoPessoa) {
        this.tipoPessoa = tipoPessoa;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public void setInscricaoMunicipal(String inscricaoMunicipal) {
        this.inscricaoMunicipal = inscricaoMunicipal;
    }

    public String toString() {
        return "TcIdentificadorPessoa[tipoPessoa=" + (Object)((Object)this.tipoPessoa) + ",documento=" + this.documento + ",inscricaoMunicipal=" + this.inscricaoMunicipal + ']';
    }

    public int hashCode() {
        int prime = 31;
        int result = 1;
        result = 31 * result + (this.documento == null ? 0 : this.documento.hashCode());
        result = 31 * result + (this.inscricaoMunicipal == null ? 0 : this.inscricaoMunicipal.hashCode());
        result = 31 * result + (this.tipoPessoa == null ? 0 : this.tipoPessoa.hashCode());
        return result;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (!(obj instanceof TcIdentificadorPessoa)) {
            return false;
        }
        TcIdentificadorPessoa other = (TcIdentificadorPessoa)obj;
        if (this.documento == null ? other.documento != null : !this.documento.equals(other.documento)) {
            return false;
        }
        if (this.inscricaoMunicipal == null ? other.inscricaoMunicipal != null : !this.inscricaoMunicipal.equals(other.inscricaoMunicipal)) {
            return false;
        }
        if (this.tipoPessoa == null ? other.tipoPessoa != null : !this.tipoPessoa.equals((Object)other.tipoPessoa)) {
            return false;
        }
        return true;
    }

    public static enum Tipo {
        PJ(2),
        PF(1),
        NAO_INFORMADO(3);
        
        private int pessoaTipo;

        private Tipo(int pessoaTipo) {
            this.pessoaTipo = pessoaTipo;
        }

        public int getPessoaTipo() {
            return this.pessoaTipo;
        }

        public static Tipo getTipo(int pessoaTipo) {
            Tipo[] values;
            for (Tipo tipo : values = Tipo.values()) {
                if (pessoaTipo != tipo.getPessoaTipo()) continue;
                return tipo;
            }
            return null;
        }
    }

}

