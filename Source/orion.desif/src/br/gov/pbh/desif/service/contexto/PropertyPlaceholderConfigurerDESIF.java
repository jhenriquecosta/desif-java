package br.gov.pbh.desif.service.contexto;

import java.io.File;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

public class PropertyPlaceholderConfigurerDESIF extends PropertyPlaceholderConfigurer
{

    public PropertyPlaceholderConfigurerDESIF()
    {
    }

    private Resource configurarResource(Resource resource)
    {
        String recurso = resource.getFilename();
        File arquivo = new File((new StringBuilder()).append(System.getProperty("user.home")).append(File.separator).append(".desif").append(File.separator).append(recurso).toString());
        return new FileSystemResource(arquivo);
    }

    public void setLocation(Resource arg0)
    {
        super.setLocation(configurarResource(arg0));
    }
}
