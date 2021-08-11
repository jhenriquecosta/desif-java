/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.xml.vo.tiposcomplexos;

import java.util.Date;
import java.util.List;

public class ProtocoloVO {
    private String raizCnpj;
    private Long codigo;
    private String nome;
    private String anoMesInicCmpe;
    private Integer tipoDecl;
    private Integer tipoCnso;
    private Date datEntrega;
    public List<DependenciaVO> listaDependencia;
    public List<TotalizacaoVO> listaTotalizacao;

    public String getRaizCnpj() {
        return this.raizCnpj;
    }

    public void setRaizCnpj(String raizCnpj) {
        this.raizCnpj = raizCnpj;
    }

    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getAnoMesInicCmpe() {
        return this.anoMesInicCmpe;
    }

    public void setAnoMesInicCmpe(String anoMesInicCmpe) {
        this.anoMesInicCmpe = anoMesInicCmpe;
    }

    public Integer getTipoDecl() {
        return this.tipoDecl;
    }

    public void setTipoDecl(Integer tipoDecl) {
        this.tipoDecl = tipoDecl;
    }

    public Integer getTipoCnso() {
        return this.tipoCnso;
    }

    public void setTipoCnso(Integer tipoCnso) {
        this.tipoCnso = tipoCnso;
    }

    public Date getDatEntrega() {
        return this.datEntrega;
    }

    public void setDatEntrega(Date datEntrega) {
        this.datEntrega = datEntrega;
    }

    public List<DependenciaVO> getListaDependencia() {
        return this.listaDependencia;
    }

    public void setListaDependencia(List<DependenciaVO> listaDependencia) {
        this.listaDependencia = listaDependencia;
    }

    public List<TotalizacaoVO> getListaTotalizacao() {
        return this.listaTotalizacao;
    }

    public void setListaTotalizacao(List<TotalizacaoVO> listaTotalizacao) {
        this.listaTotalizacao = listaTotalizacao;
    }

    public Long getCodigo() {
        return this.codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }
}

