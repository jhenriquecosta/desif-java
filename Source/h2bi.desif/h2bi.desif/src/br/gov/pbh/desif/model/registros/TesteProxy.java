

package br.gov.pbh.desif.model.registros;

import java.io.PrintStream;
import java.net.*;
import java.util.Iterator;
import java.util.List;

public class TesteProxy
{

    public TesteProxy()
    {
    }

    public static void main(String args[])
    {
        try
        {
            List l = ProxySelector.getDefault().select(new URI("http://www.yahoo.com/"));
            for(Iterator iter = l.iterator(); iter.hasNext();)
            {
                Proxy proxy = (Proxy)iter.next();
                System.out.println((new StringBuilder()).append("proxy hostname : ").append(proxy.type()).toString());
                InetSocketAddress addr = (InetSocketAddress)proxy.address();
                if(addr == null)
                {
                    System.out.println("Sem Proxy");
                } else
                {
                    System.out.println((new StringBuilder()).append("proxy hostname : ").append(addr.getHostName()).toString());
                    System.out.println((new StringBuilder()).append("proxy port : ").append(addr.getPort()).toString());
                }
            }

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
