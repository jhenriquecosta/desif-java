
package br.gov.pbh.desif.model.pojo;

import br.gov.pbh.desif.model.pojo.base.AbstractBalanceteAnaliticoMensal;
import java.io.Serializable;
import java.util.Date;

public class BalanceteAnaliticoMensal extends AbstractBalanceteAnaliticoMensal
    implements Serializable
{

    public BalanceteAnaliticoMensal()
    {
    }

    public BalanceteAnaliticoMensal(Long id, Long linhaBalanceteAnaliticoMensal, String codigoDependencia, Date AnoMesCompetencia, String conta, Double saldoInicial, Double valorDebito, 
            Double valorCredito, Double saldoFinal)
    {
        super(id, linhaBalanceteAnaliticoMensal, codigoDependencia, AnoMesCompetencia, conta, saldoInicial, valorDebito, valorCredito, saldoFinal);
    }

    public BalanceteAnaliticoMensal(String conta, Double saldoInicial, Double valorDebito, Double valorCredito, Double saldoFinal, Date anoMesCompetencia)
    {
        super(conta, saldoInicial, valorDebito, valorCredito, saldoFinal, anoMesCompetencia);
    }
}