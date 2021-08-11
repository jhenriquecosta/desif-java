

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.PlanoGeralContaComentado;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            RegUtil

public class Registro0100
{

    private PlanoGeralContaComentado pgcc;
    private String numLinha;
    private String registro;
    private String conta;
    private String nome;
    private String descConta;
    private String contaSuperior;
    private String contaCosif;
    private String codTribDesif;
    private String token[];
    private int linha;
    private RegUtil regUtil;

    public Registro0100(String token[], int line)
    {
        try
        {
            regUtil = new RegUtil();
            this.token = token;
            linha = line;
            registro = token[1];
            verLinha();
            verConta();
            verNome();
            verDescConta();
            verContaSuperior();
            verContaCosif();
            verCodTribDesif();
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

    private void verConta()
        throws Exception
    {
        int coluna = 3;
        conta = regUtil.contCaracterRegistro(linha, token[2].trim(), 30, coluna, registro);
        if(conta.equals(""))
            regUtil.setErro(linha, "EG016", coluna, (short)1, registro);
    }

    private void verNome()
        throws Exception
    {
        int coluna = 4;
        nome = regUtil.contCaracterRegistro(linha, token[3].trim(), 100, coluna, registro);
        if(nome.equals(""))
            regUtil.setErro(linha, "EI003", coluna, (short)1, registro);
    }

    private void verDescConta()
        throws Exception
    {
        int coluna = 5;
        descConta = regUtil.contCaracterRegistro(linha, token[4].trim(), 600, coluna, registro);
    }

    private void verContaSuperior()
        throws Exception
    {
        int coluna = 6;
        contaSuperior = regUtil.contCaracterRegistro(linha, token[5].trim(), 30, coluna, registro);
        if(contaSuperior.equals(conta))
        {
            String txtSolucao = (new StringBuilder()).append("Conta: ").append(conta).toString();
            regUtil.setErro(linha, "EI006", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verContaCosif()
        throws Exception
    {
        int coluna = 7;
        contaCosif = regUtil.contCaracterRegistro(linha, token[6].trim(), 20, coluna, registro);
        if(contaCosif.equals(""))
        {
            String txtSolucao = (new StringBuilder()).append("Conta: ").append(conta).append(" Conta Superior: ").append(contaSuperior).toString();
            regUtil.setErro(linha, "EI018", coluna, (short)1, registro, txtSolucao);
        }
    }

    private void verCodTribDesif()
        throws Exception
    {
        int coluna = 8;
        codTribDesif = regUtil.contCaracterRegistro(linha, token[7].trim(), 20, coluna, registro);
    }

    public PlanoGeralContaComentado getPGCCPojo()
    {
        try
        {
            pgcc = new PlanoGeralContaComentado(conta, contaCosif, new Long(Long.parseLong(numLinha)), nome, contaSuperior, codTribDesif, descConta, null, Integer.valueOf(0), null, null);
            return pgcc;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}