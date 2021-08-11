
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractPlanoGeralContaComentado;
import java.io.Serializable;
import java.util.Set;

public class PlanoGeralContaComentado extends AbstractPlanoGeralContaComentado
    implements Serializable
{

    public PlanoGeralContaComentado()
    {
    }

    public PlanoGeralContaComentado(String conta, String contaCosif, Long numLinhaPgcc, String nome, String contaSupe, String codTribDesif, String descConta, 
            String filhos, Integer nivel, Set pgccsPaiFilhosForIdnPgccPai, Set pgccsPaiFilhosForIdnPgccFilho)
    {
        super(conta, contaCosif, numLinhaPgcc, nome, contaSupe, codTribDesif, descConta, nivel, pgccsPaiFilhosForIdnPgccPai, pgccsPaiFilhosForIdnPgccFilho);
    }

    public PlanoGeralContaComentado(String conta, String nome, String codTribDesif, String descConta)
    {
        super(conta, nome, codTribDesif, descConta);
    }
}