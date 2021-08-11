/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.ws.jaxws;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

@WebService(name="DesIfServiceDelegate", targetNamespace="https://bhissdigital.pbh.gov.br/desif-ws/")
@SOAPBinding(parameterStyle=SOAPBinding.ParameterStyle.BARE)
public interface DesIfServiceDelegate {
    @WebMethod
    @WebResult(name="output", targetNamespace="https://bhissdigital.pbh.gov.br/desif-ws/", partName="result")
    public ReceberResponse receber(@WebParam(name="input", targetNamespace="https://bhissdigital.pbh.gov.br/desif-ws/", partName="parameters") ReceberRequest var1);
}

