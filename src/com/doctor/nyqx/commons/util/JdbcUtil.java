package com.doctor.nyqx.commons.util;

import java.sql.*;  
import java.io.InputStream;  
import java.util.Properties;

/**
 * JDBC连接
 * @author Administrator
 *
 */
public class JdbcUtil {  
	private static String driverName;
    private static String url;  
    private static String dbUser;  
    private static String dbPassword;  
    
  
    /** 
     * 读入filename指定的文件,取出其中的值,赋给三个全局变量: url / dbuser / dbPassword 
     *  
     * @param filename 
     *            属性文件 
     */  
    public static void initParam() {  
        try {  
            // 读入文件,构造输入流  
        	 JdbcUtil test = new JdbcUtil();  
             InputStream in = null;  
            
             //第一种方法，取得src下的属性文件，成功  
             in = test.getClass().getResourceAsStream("/mysqljdbc.properties"); 
  
            // 把输入流传递给Map对象  
            Properties props = new Properties();  
            props.load(in); 
            driverName=props.getProperty("jdbc.driverClassName");  
            url = props.getProperty("jdbc.url");  
            dbUser = props.getProperty("jdbc.username");  
            dbPassword = props.getProperty("jdbc.password");  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
  
    } 
  
    /** 
     * 根据属性文件中获取的参数值,构造连接对象,并返回 
     *  
     * @return 构造好的连接对象 
     */  
    public static Connection getConnection() {  
        initParam();  
        Connection conn = null;  
        try {  
        	Class.forName(driverName);

            conn = DriverManager.getConnection(url, dbUser, dbPassword);  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
        return conn;  
    } 
    

  
    /** 
     * 关闭Resultset对象 
     *  
     * @param rs 
     */  
    public static void close(ResultSet rs) {  
        try {  
            rs.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 关闭Statement对象 
     *  
     * @param stmt 
     */  
    public static void close(Statement stmt) {  
        try {  
            stmt.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
  
    /** 
     * 关闭Connection对象 
     *  
     * @param conn 
     */  
    public static void close(Connection conn) {  
        try {  
            conn.close();  
        } catch (SQLException e) {  
            e.printStackTrace();  
        }  
    }  
}  