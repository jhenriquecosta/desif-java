
package br.gov.pbh.desif.dao;

import java.util.Date;
import java.util.List;

public interface FeriadoDao
{

    public abstract List findAllDates();

    public abstract boolean findDateNextYear(Date date);
}