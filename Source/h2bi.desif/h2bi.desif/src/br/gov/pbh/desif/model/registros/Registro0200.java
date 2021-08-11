

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.TarifaServico;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            RegUtil

public class Registro0200
{

    private TarifaServico tarServ;
    private String numLinha;
    private String registro;
    private String codIdentTarifa;
    private String descTarifa;
    private String codSubtitulo;
    private String token[];
    private int linha;
    private RegUtil regUtil;

    public Registro0200(String token[], int line)
    {
        try
        {
            regUtil = new RegUtil();
            this.token = token;
            linha = line;
            registro = token[1];
            verLinha();
            verDescTarifa();
            verCodIdentTarifa();
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

    private void verCodIdentTarifa()
        throws Exception
    {
        int coluna = 3;
        codIdentTarifa = regUtil.contCaracterRegistro(linha, token[2].trim().toUpperCase(), 20, coluna, registro);
        if(codIdentTarifa.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("Descri\347\343o : ").append(descTarifa).toString();
            regUtil.setErro(linha, "EI011", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verDescTarifa()
        throws Exception
    {
        int coluna = 4;
        descTarifa = regUtil.contCaracterRegistro(linha, token[3].trim(), 100, coluna, registro);
    }

    private void verSubtitulo()
        throws Exception
    {
        int coluna = 5;
        codSubtitulo = regUtil.contCaracterRegistro(linha, token[4].trim(), 30, coluna, registro);
        if(codSubtitulo.equals(""))
            regUtil.setErro(linha, "EG015", coluna, (short)1, registro);
    }

    public TarifaServico getTarifaServico()
    {
        try
        {
            tarServ = new TarifaServico(new Long(Long.parseLong(numLinha)), new Long(Long.parseLong(numLinha)), codIdentTarifa, codSubtitulo, descTarifa);
            return tarServ;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}