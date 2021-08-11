/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  org.springframework.beans.factory.config.PropertyPlaceholderConfigurer
 *  org.springframework.core.io.FileSystemResource
 *  org.springframework.core.io.Resource
 */
package br.gov.pbh.desif.service.contexto;

import java.io.File;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class PropertyPlaceholderConfigurerDESIF
extends PropertyPlaceholderConfigurer {
    private Resource configurarResource(Resource resource) {
        String recurso = resource.getFilename();
        File arquivo = new File(System.getProperty("user.home") + File.separator + ".desif" + File.separator + recurso);
        return new FileSystemResource(arquivo);
    }

    public void setLocation(Resource arg0) {
        super.setLocation(this.configurarResource(arg0));
    }
}

