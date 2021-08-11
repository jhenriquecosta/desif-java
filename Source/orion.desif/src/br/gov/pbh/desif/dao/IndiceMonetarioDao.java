// Decompiled by DJ v3.12.12.96 Copyright 2011 Atanas Neshkov  Date: 10/04/2014 17:26:58
// Home Page: http://members.fortunecity.com/neshkov/dj.html  http://www.neshkov.com/dj.html - Check often for new version!
// Decompiler options: packimports(3) fieldsfirst 
// Source File Name:   IndiceMonetarioDao.java

package br.gov.pbh.desif.dao;

import br.gov.pbh.desif.base.dao.BaseDao;
import java.util.Date;
import java.util.List;

public interface IndiceMonetarioDao
    extends BaseDao
{

    public abstract List findByDatas(Date date, Date date1);

    public abstract boolean existeIndice(String s);

    public abstract Date anoUltimoIndiceAtualizado();
}