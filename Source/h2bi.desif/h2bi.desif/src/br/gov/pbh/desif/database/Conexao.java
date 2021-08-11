
package br.gov.pbh.desif.database;

import java.sql.*;

public class Conexao
{

    private final String driver = "org.hsqldb.jdbcDriver";
    private final String url = "jdbc:hsqldb:hsql://10.27.9.192/des_if";
    private final String usuario = "sa";
    private final String senha = "";
    private Connection connection;

    public Conexao()
    {
    }

    public Connection getConnection()
    {
        if(connection == null)
            try
            {
                Class.forName("org.hsqldb.jdbcDriver");
                connection = DriverManager.getConnection("jdbc:hsqldb:hsql://10.27.9.192/des_if", "sa", "");
            }
            catch(ClassNotFoundException classNotFoundException)
            {
                classNotFoundException.printStackTrace();
            }
            catch(SQLException exception)
            {
                exception.printStackTrace();
            }
        return connection;
    }
}