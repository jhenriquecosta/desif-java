/*
 * Decompiled with CFR 0_125.
 */
package br.gov.pbh.desif.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {
    private final String driver = "org.hsqldb.jdbcDriver";
    private final String url = "jdbc:hsqldb:hsql://10.27.9.192/des_if";
    private final String usuario = "sa";
    private final String senha = "";
    private Connection connection;

    public Connection getConnection() {
        if (this.connection == null) {
            try {
                Class.forName("org.hsqldb.jdbcDriver");
                this.connection = DriverManager.getConnection("jdbc:hsqldb:hsql://10.27.9.192/des_if", "sa", "");
            }
            catch (ClassNotFoundException classNotFoundException) 
            {
                classNotFoundException.printStackTrace();
            }
            catch (SQLException exception) {
                exception.printStackTrace();
            }
        }
        return this.connection;
    }
}

