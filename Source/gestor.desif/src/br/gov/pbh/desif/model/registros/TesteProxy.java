/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.model.registros;

import java.io.PrintStream;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.ProxySelector;
import java.net.SocketAddress;
import java.net.URI;
import java.util.List;

public class TesteProxy {
    public static void main(String[] args) {
        try {
            List<Proxy> l = ProxySelector.getDefault().select(new URI("http://www.yahoo.com/"));
            for (Proxy proxy : l) {
                System.out.println("proxy hostname : " + (Object)((Object)proxy.type()));
                InetSocketAddress addr = (InetSocketAddress)proxy.address();
                if (addr == null) {
                    System.out.println("Sem Proxy");
                    continue;
                }
                System.out.println("proxy hostname : " + addr.getHostName());
                System.out.println("proxy port : " + addr.getPort());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

