
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractRelatorioEstaticoApuracaoIssqnVO;
import java.util.Date;

public class RelatorioEstaticoApuracaoIssqnVO extends AbstractRelatorioEstaticoApuracaoIssqnVO
{

    public RelatorioEstaticoApuracaoIssqnVO()
    {
    }

    public RelatorioEstaticoApuracaoIssqnVO(String nomeMunicipio, String nomeInstituicao, String iniCNPJ, String fimCNPJ, Date dataIniCompetencia, Short tipoConsolidacao)
    {
        super(nomeMunicipio, nomeInstituicao, iniCNPJ, fimCNPJ, dataIniCompetencia, tipoConsolidacao);
    }
}