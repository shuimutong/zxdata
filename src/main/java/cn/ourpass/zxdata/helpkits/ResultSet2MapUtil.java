package cn.ourpass.zxdata.helpkits;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import cn.ourpass.zxdata.helpbean.DataMap;

public class ResultSet2MapUtil {
	private final static Logger log = Logger.getLogger(ResultSet2MapUtil.class);
	
	/**
	 * 将结果集放到map中
	 * @param rs
	 * @return
	 */
	public static DataMap<String, Object> formatRs2Map(ResultSet rs) {
		DataMap<String, Object> resMap = new DataMap<String, Object>();
		//<columnName, dataType>
		Map<String, String> columnMap = new HashMap<String, String>();
		ResultSetMetaData rm = null;
		try {
			rm = rs.getMetaData();
			int columnNum = rm.getColumnCount();
			for (int i = 1; i <= columnNum; i++) {
				columnMap.put(rm.getColumnName(i), rm.getColumnTypeName(i));
			}
		} catch (SQLException e) {
			log.error(e);
		}
		return null;
	}
}
