/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.service.relatorios;

import br.gov.pbh.desif.service.relatorios.LegendaVO;
import java.util.ArrayList;

public class TestErrosWS {
    public static void main(String[] args) {
        ArrayList<LegendaVO> legendaVos = new ArrayList<LegendaVO>();
        LegendaVO legendaVO = new LegendaVO();
        legendaVO.setCodErro("1");
        legendaVO.setDescricao("teste descri\u00e7\u00e3o do erro");
        LegendaVO legendaVO2 = new LegendaVO();
        legendaVO2.setCodErro("2");
        legendaVO2.setDescricao("descri\u00e7\u00e3o do erro 2");
        legendaVos.add(legendaVO);
        legendaVos.add(legendaVO2);
    }
}

