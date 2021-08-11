
package br.gov.pbh.desif.base.dao.impl;

import br.gov.pbh.desif.base.dao.BaseDao;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.Serializable;
import java.util.List;
import org.hibernate.*;

public abstract class BaseDaoImpl
    implements BaseDao
{

    private Session conexao;
    private String evict;

    public abstract Class getReferenceClass();

    public BaseDaoImpl()
    {
        evict = "br.gov.pbh.desif.model.pojo.base.";
        try
        {
            conexao = ((SessionFactory)Contexto.getObject("sessionFactory")).getCurrentSession();
        }
        catch(Exception e)
        {
            conexao = ((SessionFactory)Contexto.getObject("sessionFactory")).openSession();
        }
    }

    public Object get(Serializable key)
    {
        return conexao.get(getReferenceClass(), key);
    }

    public Object load(Serializable key)
    {
        return conexao.load(getReferenceClass(), key);
    }

    public List findAll()
    {
        return conexao.createCriteria(getReferenceClass()).list();
    }

    public Serializable save(Object objeto)
    {
        conexao.flush();
        conexao.clear();
        Serializable s = conexao.save(objeto);
        conexao.flush();
        return s;
    }

    public void saveOrUpdate(Object objeto)
    {
        conexao.saveOrUpdate(objeto);
        conexao.flush();
    }

    public void update(Object objeto)
    {
        conexao.update(objeto);
        conexao.flush();
    }

    public void delete(Serializable id)
    {
        conexao.delete(load(id));
    }

    public void delete(Object objeto)
    {
        conexao.delete(objeto);
    }

    public Serializable deleteAll(String table)
    {
        String sql = "delete from ";
        String limparSessao = "";
        sql = (new StringBuilder()).append(sql).append(table).toString();
        limparSessao = (new StringBuilder()).append(evict).append(table).toString();
        Query sqlQuery = conexao.createQuery(sql);
        return new Integer(sqlQuery.executeUpdate());
    }

    public Session getConexao()
    {
        return conexao;
    }
}