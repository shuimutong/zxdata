package cn.ourpass.zxdata.dbhelp;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;


@Component
public class DbServiceImpl implements DbService {
	@Autowired
	private DbConnection dbConnection;
	
	private static List<Connection> connList = new Vector<Connection>();
	
	private final static Logger log = Logger.getLogger(DbServiceImpl.class);

	public Connection getConnection() {
		return dbConnection.openConnection();
	}

	public void closeConnection(Connection conn) {
		try {
			if(conn != null) {
				conn.close();
			}
		} catch (SQLException e) {
			log.error(e);
		}
	}


}
