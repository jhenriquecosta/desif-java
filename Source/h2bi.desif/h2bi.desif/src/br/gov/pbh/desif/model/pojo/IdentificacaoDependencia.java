
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractIdentificacaoDependencia;
import java.io.Serializable;
import java.util.Date;

public class IdentificacaoDependencia extends AbstractIdentificacaoDependencia
    implements Serializable
{

    public IdentificacaoDependencia()
    {
    }

    public IdentificacaoDependencia(Long id, Integer tipoDependencia, Long cidade, Long linhaIdentificacaoDependencia, String codigoDependencia, Short opcaoInscricaoMunicipal, Short contabilidadePropria, 
            String cnpjUnificado, String cnpjProprio, String enderecoDependencia, Date dataInicioParalizacao, Date dataFimParalizacao)
    {
        super(id, tipoDependencia, cidade, linhaIdentificacaoDependencia, codigoDependencia, opcaoInscricaoMunicipal, contabilidadePropria, cnpjUnificado, cnpjProprio, enderecoDependencia, dataInicioParalizacao, dataFimParalizacao);
    }
}