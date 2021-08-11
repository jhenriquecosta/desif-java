
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractGuiaEstaticaVO;
import java.util.Date;

public class GuiaEstaticaVO extends AbstractGuiaEstaticaVO
{

    public GuiaEstaticaVO()
    {
    }

    public GuiaEstaticaVO(String nomeInstituicao, Date dataInicioCompetencia, String cnpjInstituicao, Short opcaoInscricaoMunicipal, String codigoDependencia, Short tipoDeclaracao)
    {
        super(nomeInstituicao, dataInicioCompetencia, cnpjInstituicao, opcaoInscricaoMunicipal, codigoDependencia, tipoDeclaracao);
    }
}