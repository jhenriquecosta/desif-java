
package br.gov.pbh.desif.ws.xml.vo.services;

import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import java.util.List;

public class ServicoEnviarDeclaracaoRespostaVO
{

    private ProtocoloVO protocolo;
    private List mensagens;

    public ServicoEnviarDeclaracaoRespostaVO()
    {
    }

    public ProtocoloVO getProtocolo()
    {
        return protocolo;
    }

    public void setProtocolo(ProtocoloVO protocolo)
    {
        this.protocolo = protocolo;
    }

    public List getMensagens()
    {
        return mensagens;
    }

    public void setMensagens(List mensagens)
    {
        this.mensagens = mensagens;
    }
}
