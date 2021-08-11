/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.base.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao {
    public Object get(Serializable var1);

    public Object load(Serializable var1);

    public List findAll();

    public Serializable save(Object var1);

    public void saveOrUpdate(Object var1);

    public void update(Object var1);

    public void delete(Serializable var1);

    public Serializable deleteAll(String var1);

    public void delete(Object var1);
}

