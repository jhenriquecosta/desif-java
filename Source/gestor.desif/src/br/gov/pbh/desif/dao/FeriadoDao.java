/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.dao;

import java.util.Date;
import java.util.List;

public interface FeriadoDao {
    public List<Date> findAllDates();

    public boolean findDateNextYear(Date var1);
}

