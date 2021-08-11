

package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractRelatorioEstaticoApuracaoSubtituloVO;
import java.util.Date;

public class RelatorioEstaticoApuracaoSubtituloVO extends AbstractRelatorioEstaticoApuracaoSubtituloVO
{

    public RelatorioEstaticoApuracaoSubtituloVO()
    {
    }

    public RelatorioEstaticoApuracaoSubtituloVO(String nomeMunicipio, String nomeInstituicao, Date dataIniCompetencia, String iniCNPJ, String cnpjProprio, String enderecoDependencia, String cnpjUnificado, 
            String codigoDependencia)
    {
        super(nomeMunicipio, nomeInstituicao, dataIniCompetencia, iniCNPJ, cnpjProprio, enderecoDependencia, cnpjUnificado, codigoDependencia);
    }
}