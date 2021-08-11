
package br.gov.pbh.desif.service.relatorios;

import java.util.ArrayList;
import java.util.List;

// Referenced classes of package br.gov.pbh.desif.service.relatorios:
//            LegendaVO

public class TestErrosWS
{

    public TestErrosWS()
    {
    }

    public static void main(String args[])
    {
        List legendaVos = new ArrayList();
        LegendaVO legendaVO = new LegendaVO();
        legendaVO.setCodErro("1");
        legendaVO.setDescricao("teste descri\347\343o do erro");
        LegendaVO legendaVO2 = new LegendaVO();
        legendaVO2.setCodErro("2");
        legendaVO2.setDescricao("descri\347\343o do erro 2");
        legendaVos.add(legendaVO);
        legendaVos.add(legendaVO2);
    }
}
