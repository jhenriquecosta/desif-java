/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.service.relatorios.RelatorioProtocolo;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.DependenciaVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.TotalizacaoVO;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProtocoloTest {
    public static void main(String[] args) {
        ArrayList<DependenciaVO> dependencias = new ArrayList<DependenciaVO>();
        ArrayList<TotalizacaoVO> totalizacaoVOs = new ArrayList<TotalizacaoVO>();
        DependenciaVO dependenciaVO = new DependenciaVO();
        dependenciaVO.setCnpjProprio("teste Cnpj");
        dependenciaVO.setCodDepe("teste CodDepen");
        dependenciaVO.setCtblPropria(1);
        dependenciaVO.setIndInscMunl(1591030010);
        dependencias.add(dependenciaVO);
        dependencias.add(dependenciaVO);
        TotalizacaoVO totalizacaoVO = new TotalizacaoVO();
        totalizacaoVO.setAliqISSQN(20.3);
        totalizacaoVO.setDeduReceDeclCnso(27.99);
        totalizacaoVO.setDeduReceDeclSubTitu(21.99);
        totalizacaoVO.setReceDeclCnso(78.9);
        totalizacaoVOs.add(totalizacaoVO);
        ProtocoloVO protocoloVO = new ProtocoloVO();
        protocoloVO.setAnoMesInicCmpe("Teste ano mes referencia");
        protocoloVO.setDatEntrega(new Date());
        protocoloVO.setListaDependencia(dependencias);
        protocoloVO.setListaTotalizacao(totalizacaoVOs);
        protocoloVO.setNome("nome da institui\u00e7ao");
        protocoloVO.setTipoCnso(2);
        protocoloVO.setTipoDecl(3);
        RelatorioProtocolo relatorioProtocolo = new RelatorioProtocolo(protocoloVO);
        relatorioProtocolo.gerarProtocolo();
    }
}

