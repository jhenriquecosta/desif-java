
package br.gov.pbh.desif.ws.xml.vo.services;

import br.gov.pbh.desif.ws.cliente.Erros;
import br.gov.pbh.desif.ws.xml.vo.tiposcomplexos.ProtocoloVO;
import java.util.List;

public class ServicoEnviarDeclaracaoRespostaVO {
    private ProtocoloVO protocolo;
    private List<Erros> mensagens;

    public ProtocoloVO getProtocolo() {
        return this.protocolo;
    }

    public void setProtocolo(ProtocoloVO protocolo) {
        this.protocolo = protocolo;
    }

    public List<Erros> getMensagens() {
        return this.mensagens;
    }

    public void setMensagens(List<Erros> mensagens) {
        this.mensagens = mensagens;
    }
}

