

package br.gov.pbh.desif.model.registros;

import br.gov.pbh.desif.model.pojo.IdentificacaoDeclaracao;
import br.gov.pbh.desif.model.pojo.NewIdentificacaoDeclaracao;

// Referenced classes of package br.gov.pbh.desif.model.registros:
//            Registro0000, RegUtil, Data

public class Registro0000New extends Registro0000
{

    private IdentificacaoDeclaracao declaracao;
    private RegUtil regUtil;
    private Data dt;
    private String idnVersao;
    private String tipoArredondamento;
    private String token[];
    private int linha;
    String registro;

    public Registro0000New(String token[], int line)
    {
        super(token, line);
        try
        {
            registro = token[1];
            regUtil = new RegUtil();
            dt = new Data();
            this.token = token;
            verIdnVersao();
            verTipoArredondamento();
        }
        catch(Exception ex)
        {
            ex.printStackTrace();
        }
    }

    public void verIdnVersao()
        throws Exception
    {
        int coluna = 14;
        idnVersao = regUtil.contCaracterRegistro(linha, token[13].trim(), 10, coluna, registro);
        if(idnVersao.equals(""))
            regUtil.setErro(linha, "ED042", coluna, (short)1, registro);
    }

    public void verTipoArredondamento()
        throws Exception
    {
        int coluna = 15;
        tipoArredondamento = regUtil.contCaracterRegistro(linha, token[14].trim(), 1, coluna, registro);
        String moduloDeclaracao = regUtil.contCaracterRegistro(linha, token[8].trim(), 1, 9, registro);
        if(moduloDeclaracao.equals("2"))
        {
            if(tipoArredondamento.equals(""))
            {
                String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o informado: ").append(moduloDeclaracao).toString();
                regUtil.setErro(linha, "ED044", coluna, (short)1, registro, txtSolucao);
            }
            if((!tipoArredondamento.equals("1")) & (!tipoArredondamento.equals("")) & (tipoArredondamento != null))
            {
                String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o informado: ").append(moduloDeclaracao).append(", tipo de arredondamento: ").append(tipoArredondamento).toString();
                regUtil.setErro(linha, "ED045", coluna, (short)1, registro, txtSolucao);
            }
        } else
        if(!regUtil.isInteiro(tipoArredondamento) && !tipoArredondamento.equals("") && tipoArredondamento != null)
        {
            String txtSolucao = (new StringBuilder()).append("Valor invalido, n\343o num\351rico: ").append(tipoArredondamento).toString();
            regUtil.setErro(linha, "EG008", coluna, (short)1, registro, txtSolucao);
        } else
        if(!tipoArredondamento.equals("") && tipoArredondamento != null && (moduloDeclaracao.equals("1") || moduloDeclaracao.equals("3")))
        {
            String txtSolucao = (new StringBuilder()).append("M\363dulo da declara\347\343o informado: ").append(moduloDeclaracao).append(", tipo de arredondamento: ").append(tipoArredondamento).toString();
            regUtil.setErro(linha, "ED049", coluna, (short)1, registro, txtSolucao);
        }
    }

    public NewIdentificacaoDeclaracao getNewDeclaracaoPojo()
    {
        try
        {
            String modDeclaracao = regUtil.contCaracterRegistro(linha, token[8].trim(), 1, 9, registro);
            IdentificacaoDeclaracao idd = super.getDeclaracaoPojo();
            NewIdentificacaoDeclaracao nidd;
            if(modDeclaracao.equals("1") || modDeclaracao.equals("3"))
                nidd = new NewIdentificacaoDeclaracao(idd, new String(idnVersao), null);
            else
                nidd = new NewIdentificacaoDeclaracao(idd, idnVersao, new Short(Short.parseShort(tipoArredondamento)));
            return nidd;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }
}