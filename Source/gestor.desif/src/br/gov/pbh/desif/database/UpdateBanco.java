/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.gov.pbh.desif.database;

import br.gov.pbh.desif.dao.CodTributacaoDesifDao;
import br.gov.pbh.desif.dao.impl.CidadeDaoImpl;
import br.gov.pbh.desif.dao.impl.CodTribuMunicipalDaoImpl;
import br.gov.pbh.desif.dao.impl.CodTributacaoDesifDaoImpl;
import br.gov.pbh.desif.model.pojo.Cidade;
import br.gov.pbh.desif.model.pojo.CodigoTributacaoDesif;
import br.gov.pbh.desif.model.pojo.CodigoTributacaoMunicipal;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author jhenr
 */
public class UpdateBanco
{
    public void updateAliquota() throws InterruptedException 
    {
        
           Date dataInicio = null;
             try
             {
                 SimpleDateFormat formataData = new SimpleDateFormat("dd/MM/yyyy");
                 dataInicio = formataData.parse("01/01/2013");
             }
             catch (ParseException ex) 
             {
                ex.printStackTrace();
             }
        CodTributacaoDesifDaoImpl codTribDesifs = new CodTributacaoDesifDaoImpl(); 
        List<CodigoTributacaoDesif> tribList = codTribDesifs.findAll();
        
        long cid_id=2103307L;
        CidadeDaoImpl cidDao = new CidadeDaoImpl();
        Cidade cidPoco = (Cidade)cidDao.get(cid_id);
        System.out.println("Cidade: "+cidPoco.getNomeCidade());
        int contador=1;
         CodTribuMunicipalDaoImpl tribMunDao = new CodTribuMunicipalDaoImpl();
       //  tribMunDao.deleteAll("CodigoTributacaoMunicipal");
         Object retorno;
        for (CodigoTributacaoDesif item : tribList) 
        {
           
             
           //  tribMunDao = new CodTribuMunicipalDaoImpl();
             System.out.println("Reg.: " +  contador + " Codigo: "+item.getId()+ " Desc: "+ item.getDescCodigoTributacao() );
             CodigoTributacaoMunicipal tribMunPoco = new CodigoTributacaoMunicipal();
             CodigoTributacaoMunicipal loadData = new CodigoTributacaoMunicipal();
             loadData =(CodigoTributacaoMunicipal) tribMunDao.get(item.getId());
             if (loadData == null)
             {
                 tribMunPoco.setId(item.getId());
                 tribMunPoco.setCodTributacao(item);
                 tribMunPoco.setCidade(cidPoco);
                 tribMunPoco.setDataInicioVigencia(dataInicio);
                 tribMunPoco.setValorAliquota(5);
                 retorno=tribMunDao.save(tribMunPoco);
                 System.out.println("Retorno: "+retorno.toString());
                 Thread.sleep(2000);
             }
                 contador++;
                 
         //    Thread.sleep(1000);
             
        }
        
    }
    public static void main(String[] args) throws InterruptedException
    {
       UpdateBanco p = new UpdateBanco();
       p.updateAliquota();
       System.out.println("fim...");
    }
}
