/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.pojo.base;

import java.io.Serializable;

public abstract class AbstractFiltroCNPJCodDependenciaVO
implements Serializable {
    private String cnpjInstituicao;
    private String codigoDependencia;
    private String cnpj;

    public AbstractFiltroCNPJCodDependenciaVO() {
    }

    public AbstractFiltroCNPJCodDependenciaVO(String cnpjInstituicao, String codigoDependencia, String cnpj) {
        this.cnpjInstituicao = cnpjInstituicao;
        this.codigoDependencia = codigoDependencia;
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return this.cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getCnpjInstituicao() {
        return this.cnpjInstituicao;
    }

    public void setCnpjInstituicao(String cnpjInstituicao) {
        this.cnpjInstituicao = cnpjInstituicao;
    }

    public String getCodigoDependencia() {
        return this.codigoDependencia;
    }

    public void setCodigoDependencia(String codigoDependencia) {
        this.codigoDependencia = codigoDependencia;
    }
}

