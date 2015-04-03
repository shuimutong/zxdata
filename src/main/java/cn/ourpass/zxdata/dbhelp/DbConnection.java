package cn.ourpass.zxdata.dbhelp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import cn.ourpass.zxdata.helpkits.StringUtilsOfMe;

/**
 * 数据库连接
 * @author gaozx
 *
 */
@Component
public class DbConnection {
	private static String dbDriverName;
	private static String dbConnUrl;
	private static String dbUsername;
	private static String dbPassword;
	private Connection conn;
	
	private final static Logger log = Logger.getLogger(DbConnection.class);
	
	public DbConnection() {
		if(StringUtilsOfMe.isNullStrings(dbDriverName, dbConnUrl, dbUsername, dbPassword)) {
			loadDbProperties();
		}
	}
	
	/**
	 * 加载数据库配置文件
	 */
	private void loadDbProperties() {
		Properties dbProperties = new Properties();
//		InputStream is = DbConnection.class.getResourceAsStream("E:/workspace1112/ourpass/src/main/resources/"+"database.properties");
		try {
			InputStream is = new FileInputStream("E:/workspace1112/ourpass/src/main/resources/"+"database.properties");
			dbProperties.load(is);
			dbDriverName = dbProperties.getProperty("driver");
			dbConnUrl = dbProperties.getProperty("url");
			dbUsername = dbProperties.getProperty("username");
			dbPassword = dbProperties.getProperty("password");
		} catch (IOException e) {
			log.error(e);
		}
	}
	
	/**
	 * 打开连接
	 * @return
	 */
	public Connection openConnection() {
		try {
			Class.forName(dbDriverName);
			conn = DriverManager.getConnection(dbConnUrl, dbUsername, dbPassword);
		} catch (ClassNotFoundException e) {
			log.error(e);
		} catch (SQLException e) {
			log.error(e);
		}
		return conn;
	}
	
	/**
	 * 关闭连接
	 * @return
	 */
	public void closeConnection() {
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
}
