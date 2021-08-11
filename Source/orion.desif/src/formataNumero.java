import br.gov.pbh.desif.model.registros.RegUtil;
import java.io.PrintStream;

public class formataNumero
{

    int posicao;

    public formataNumero()
    {
    }

    public String iniciar(String entrada)
    {
        String numero = entrada;
        RegUtil util = new RegUtil();
        if(util.verificaCasasDecimais(numero))
        {
            numero = verificaPontoDecimal(numero);
            numero = (new StringBuilder()).append("R$ ").append(inserePontoMilhar(numero)).toString();
            System.out.println((new StringBuilder()).append("N\372mero formatado: ").append(numero).toString());
        } 
        else
        {
            System.out.println("Deu erro!!!!!!");
        }
        return numero;
    }

    public String verificaPontoDecimal(String entrada)
    {
        String resultado = entrada;
        for(int i = 0; i < resultado.length(); i++)
            if('.' == resultado.charAt(i))
            {
                posicao = i;
                resultado = trocaPontoPorVirgula(resultado);
            }

        return resultado;
    }

    public String trocaPontoPorVirgula(String entrada)
    {
        String resultadoTroca = entrada = entrada.replace('.', ',');
        return resultadoTroca;
    }

    public String inserePontoMilhar(String entrada)
    {
        String resultadoInserePonto = entrada;
        int cont = 1;
        for(int i = posicao - 1; i > 0; i--)
        {
            char ponto = '.';
            if(cont == 3)
            {
                resultadoInserePonto = (new StringBuilder()).append(resultadoInserePonto.substring(0, i)).append(ponto).append(resultadoInserePonto.substring(i, resultadoInserePonto.length())).toString();
                cont = 0;
            }
            cont++;
        }

        return resultadoInserePonto;
    }
}