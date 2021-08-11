/*
 * Decompiled with CFR 0_125.
 * 
 * Could not load the following classes:
 *  org.hibernate.Criteria
 *  org.hibernate.Query
 *  org.hibernate.Session
 *  org.hibernate.SessionFactory
 */
package br.gov.pbh.desif.base.dao.impl;

import br.gov.pbh.desif.base.dao.BaseDao;
import br.gov.pbh.desif.service.contexto.Contexto;
import java.io.Serializable;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

public abstract class BaseDaoImpl
implements BaseDao {
    private Session conexao;
    private String evict = "br.gov.pbh.desif.model.pojo.base.";

    public abstract Class getReferenceClass();

    public BaseDaoImpl() 
    {
        try
        {
            this.conexao = ((SessionFactory)Contexto.getObject("sessionFactory")).getCurrentSession();
        }
        catch (Exception e) 
        {
            this.conexao = ((SessionFactory)Contexto.getObject("sessionFactory")).openSession();
        }
    }

    @Override
    public Object get(Serializable key) {
        return this.conexao.get(this.getReferenceClass(), key);
    }

    @Override
    public Object load(Serializable key) {
        return this.conexao.load(this.getReferenceClass(), key);
    }

    @Override
    public List findAll() {
        return this.conexao.createCriteria(this.getReferenceClass()).list();
    }

    @Override
    public Serializable save(Object objeto) 
    {
        this.conexao.flush();
        this.conexao.clear();
        Transaction tx2 = conexao.beginTransaction();
           Serializable s = this.conexao.save(objeto);
           this.conexao.flush();
        tx2.commit();
        return s;
    }

    @Override
    public void saveOrUpdate(Object objeto) {
        this.conexao.saveOrUpdate(objeto);
        this.conexao.flush();
    }

    @Override
    public void update(Object objeto) {
        this.conexao.update(objeto);
        this.conexao.flush();
    }

    @Override
    public void delete(Serializable id) {
        this.conexao.delete(this.load(id));
    }

    @Override
    public void delete(Object objeto) {
        this.conexao.delete(objeto);
    }

    @Override
    public Serializable deleteAll(String table) {
        String sql = "delete from ";
        String limparSessao = "";
        sql = sql + table;
        limparSessao = this.evict + table;
        Query sqlQuery = this.conexao.createQuery(sql);
        return new Integer(sqlQuery.executeUpdate());
    }

    public Session getConexao() {
        return this.conexao;
    }
}

