/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  org.springframework.context.ApplicationContext
 *  org.springframework.context.support.ClassPathXmlApplicationContext
 */
package br.gov.pbh.desif.service.contexto;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public final class Contexto {
    private static final ApplicationContext factory = new ClassPathXmlApplicationContext("br/gov/pbh/desif/service/contexto/applicationContext.xml");

    public static Object getObject(String id) 
    {
        return factory.getBean(id);
    }
}

