
package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.IdentServicosRemunVariavel;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            RegUtil

public class Registro0300
{

    private IdentServicosRemunVariavel identServRemVar;
    private String numLinha;
    private String registro;
    private String codIdentServRemunVariavel;
    private String descComplementServRemunVariavel;
    private String codSubtitulo;
    private String token[];
    private int linha;
    private RegUtil regUtil;

    public Registro0300(String token[], int line)
    {
        try
        {
            regUtil = new RegUtil();
            this.token = token;
            linha = line;
            registro = token[1];
            verLinha();
            verDescComplementServRemunVariavel();
            verCodIdentServRemunVariavel();
            verSubtitulo();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    private void verLinha()
        throws Exception
    {
        int coluna = 1;
        numLinha = regUtil.contCaracterRegistro(linha, token[0].trim(), 6, coluna, registro);
        if(numLinha.equals(""))
            regUtil.setErro(linha, "EG013", coluna, (short)1, registro);
        else
        if(!regUtil.isInteiro(numLinha))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(numLinha).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        }
        if(!regUtil.validaSequenciaLinha(linha, numLinha))
        {
            String txtSolucao = (new StringBuilder()).append("Numero da linha errado: ").append(numLinha).toString();
            regUtil.setErro(linha, "EG003", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCodIdentServRemunVariavel()
        throws Exception
    {
        int coluna = 3;
        codIdentServRemunVariavel = regUtil.contCaracterRegistro(linha, token[2].trim(), 6, coluna, registro);
        if(codIdentServRemunVariavel.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("Descri\347\343o Servi\347o: ").append(descComplementServRemunVariavel).toString();
            regUtil.setErro(linha, "EI016", coluna, (short)1, registro, txtSolucao);
        } else
        if(!regUtil.isNumeric(codIdentServRemunVariavel))
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(codIdentServRemunVariavel).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verDescComplementServRemunVariavel()
        throws Exception
    {
        int coluna = 4;
        descComplementServRemunVariavel = regUtil.contCaracterRegistro(linha, token[3].trim(), 255, coluna, registro);
    }

    private void verSubtitulo()
        throws Exception
    {
        int coluna = 5;
        codSubtitulo = regUtil.contCaracterRegistro(linha, token[4].trim(), 30, coluna, registro);
        if(codSubtitulo.equals(""))
            regUtil.setErro(linha, "EG015", coluna, (short)1, registro);
    }

    public IdentServicosRemunVariavel getServicosRemVariavel()
    {
        try
        {
            identServRemVar = new IdentServicosRemunVariavel(new Long(Long.parseLong(numLinha)), codIdentServRemunVariavel, new Long(Long.parseLong(numLinha)), codSubtitulo, descComplementServRemunVariavel);
            return identServRemVar;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}