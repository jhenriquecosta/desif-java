
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.*;
import java.util.*;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            RelatorioProtocolo

public class ProtocoloTest
{

    public ProtocoloTest()
    {
    }

    public static void main(String args[])
    {
        List dependencias = new ArrayList();
        List totalizacaoVOs = new ArrayList();
        DependenciaVO dependenciaVO = new DependenciaVO();
        dependenciaVO.setCnpjProprio("teste Cnpj");
        dependenciaVO.setCodDepe("teste CodDepen");
        dependenciaVO.setCtblPropria(Integer.valueOf(1));
        dependenciaVO.setIndInscMunl(Integer.valueOf(0x5ed530fa));
        dependencias.add(dependenciaVO);
        dependencias.add(dependenciaVO);
        TotalizacaoVO totalizacaoVO = new TotalizacaoVO();
        totalizacaoVO.setAliqISSQN(Double.valueOf(20.300000000000001D));
        totalizacaoVO.setDeduReceDeclCnso(Double.valueOf(27.989999999999998D));
        totalizacaoVO.setDeduReceDeclSubTitu(Double.valueOf(21.989999999999998D));
        totalizacaoVO.setReceDeclCnso(Double.valueOf(78.900000000000006D));
        totalizacaoVOs.add(totalizacaoVO);
        ProtocoloVO protocoloVO = new ProtocoloVO();
        protocoloVO.setAnoMesInicCmpe("Teste ano mes referencia");
        protocoloVO.setDatEntrega(new Date());
        protocoloVO.setListaDependencia(dependencias);
        protocoloVO.setListaTotalizacao(totalizacaoVOs);
        protocoloVO.setNome("nome da institui\347ao");
        protocoloVO.setTipoCnso(Integer.valueOf(2));
        protocoloVO.setTipoDecl(Integer.valueOf(3));
        RelatorioProtocolo relatorioProtocolo = new RelatorioProtocolo(protocoloVO);
        relatorioProtocolo.gerarProtocolo();
    }
}
