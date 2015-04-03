package cn.ourpass.zxdata.dbhelp;

import java.sql.Connection;

public interface DbService {
	/**
	 * 获得连接
	 * @return
	 */
	Connection getConnection();
	
	/**
	 * 关闭连接
	 * @param conn
	 */
	void closeConnection(Connection conn);
}
