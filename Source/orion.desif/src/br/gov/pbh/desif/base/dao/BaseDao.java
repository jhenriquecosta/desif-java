
package br.gov.pbh.desif.base.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao
{

    public abstract Object get(Serializable serializable);

    public abstract Object load(Serializable serializable);

    public abstract List findAll();

    public abstract Serializable save(Object obj);

    public abstract void saveOrUpdate(Object obj);

    public abstract void update(Object obj);

    public abstract void delete(Serializable serializable);

    public abstract Serializable deleteAll(String s);

    public abstract void delete(Object obj);
}