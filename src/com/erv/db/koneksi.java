/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.erv.db;

import com.erv.function.MiniConnectionPoolManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sql.ConnectionPoolDataSource;
import org.h2.constant.SysProperties;
import org.h2.jdbcx.JdbcDataSource;
import org.h2.tools.Backup;

/**
 *
 * @author JavarieSoft Team
 */
public class koneksi {

//    private static String urlJ = "jdbc:h2:tcp://localhost/~/dbalkesmrta2020";
//    public static String IP="192.168.1.5:9092";
    public static String IP="localhost";
    private static int maxConnections = 25;
//    private static String urlJ = "jdbc:h2:tcp://192.168.1.5:9092/~/dbalkesmrta2020";
    private static String urlJ = "jdbc:h2:tcp://"+IP+"/~/dbalkesmrta2020";
    private static String urlM = "jdbc:h2:mem:";
    private static String username = "sa";
    private static String password = "a38b950762cda39e0e2dee6962ac4279"; //91
    //private static String password = "f5c3919cbc26f811462a693676c40559"; //02
    //public static String IP = "192.168.1.5:9092";
    
    public static String pwstemp;
    protected static MiniConnectionPoolManager poolMgr;

    public koneksi() {
//        try {
//            ConnectionPoolDataSource dataSource = createDataSource();
//            poolMgr = new MiniConnectionPoolManager(dataSource, maxConnections, 50);
//        } catch (SQLException ex) {
//            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
//        }
    }

    public static void createPoolKoneksi() throws SQLException {
        ConnectionPoolDataSource dataSource = createDataSource();
        poolMgr = new MiniConnectionPoolManager(dataSource, maxConnections, 50);
    }

    public static ConnectionPoolDataSource createDataSource() throws SQLException {
        org.h2.jdbcx.JdbcDataSource dataSource = new org.h2.jdbcx.JdbcDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(decryptPws(password));
        dataSource.setURL(urlJ);
        return dataSource;
    }

    public static JdbcDataSource createDS() throws SQLException {
        JdbcDataSource dataSource = new JdbcDataSource();
        dataSource.setUser(username);
        dataSource.setPassword(decryptPws(password));
        dataSource.setURL(urlJ);
        return dataSource;
    }

//    public static Connection getKoneksiJ() throws ClassNotFoundException, SQLException {
//        if (con == null || con.isClosed()) {
//            JdbcDataSource ds = new JdbcDataSource();
//            ds.setURL(urlJ);
//            ds.setUser(username);
//            //ds.setPassword(password);
//            ds.setPassword(decryptPws(password));
//            con = ds.getConnection();
//            System.out.println("Koneksi Open");
//        }
//        return con;
//    }
    public static Connection getKoneksiJ() throws SQLException {
        System.out.println(getStatus());
        System.out.println("Connected to PT.Miranti Adilha");
        if (poolMgr.getActiveConnections() >= maxConnections) {
            poolMgr.dispose();
            createPoolKoneksi();
        }
        return poolMgr.getConnection();
    }

//    public static Connection getKoneksiCabang() throws ClassNotFoundException, SQLException {
//        if (conC == null || conC.isClosed()) {
//            JdbcDataSource ds = new JdbcDataSource();
//            ds.setURL(urlCbg);
//            ds.setUser(username);
//            ds.setPassword(password1);
//            conC = ds.getConnection();
//            System.out.println("Koneksi Cabang Open");
//        }
//        return conC;
//    }
    public static Connection getKoneksiM() throws ClassNotFoundException, SQLException {
        Class.forName("org.h2.Driver");
        return DriverManager.getConnection(urlM, username, password);
    }

    public static String decryptPws(String keyNumber) throws SQLException {
        Connection conTemp;

        String usr = "alkes";
        String pws = "PTMRTAalkes";
        JdbcDataSource dsTemp = new JdbcDataSource();
        dsTemp.setURL(urlJ);
        dsTemp.setUser(usr);
        dsTemp.setPassword(pws);
        conTemp = dsTemp.getConnection();
        System.out.println("Koneksi Open");
        String dec = "select TRIM(CHAR(0) FROM UTF8TOSTRING(DECRYPT('AES', '00', '" + keyNumber + "')))";

        Statement stemp = conTemp.createStatement();
        ResultSet rstemp = stemp.executeQuery(dec);
        if (rstemp.next()) {
            pwstemp = rstemp.getString(1);
        }
        rstemp.close();
        stemp.close();
        conTemp.close();
        return pwstemp;
    }

    public static void backup() throws SQLException {
        Backup.execute(SysProperties.getBaseDir() + "/backup.zip", SysProperties.getBaseDir(), "backup", true);
        System.out.print("Backup Ok" + SysProperties.getBaseDir());
    }

    public static void main(String[] args) {
        try {
            System.out.println("Program started.");
            //ConnectionPoolDataSource dataSource = createDataSource();
            createPoolKoneksi();
            testConnection();
            System.out.println("Restart the database server and press ENTER to continue - ");
            //System.console().readLine();
            testConnection();
            testConnection();
            testConnection();
            poolMgr.dispose();
            System.out.println("Program completed.");
        } catch (SQLException ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(koneksi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private static void testConnection() throws Exception {
        System.out.println("before getConnection(): " + getStatus());
        Connection conn = getKoneksiJ();
        //Connection conn = poolMgr.getValidConnection();
        System.out.println("after getConnection(): " + getStatus());
        System.out.println("connection.isValid()=" + conn.isValid(10));
        System.out.println("after isValid(): " + getStatus());
        conn.close();
        System.out.println("after close(): " + getStatus());
    }

    private static String getStatus() {
        return "activeConnections=" + poolMgr.getActiveConnections()
                + " inactiveConnections=" + poolMgr.getInactiveConnections();

    } // end class DisconnectionTest

    public static MiniConnectionPoolManager getPoolMgr() {
        return poolMgr;
    }

    public static void setPoolMgr(MiniConnectionPoolManager poolMgr) {
        koneksi.poolMgr = poolMgr;
    }

    public static void setUrlJ(String urlJ) {
        koneksi.urlJ = urlJ;
    }

}
