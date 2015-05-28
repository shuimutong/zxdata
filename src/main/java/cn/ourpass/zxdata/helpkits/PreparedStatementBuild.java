package cn.ourpass.zxdata.helpkits;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

public class PreparedStatementBuild {
	private final static Logger log = Logger.getLogger(PreparedStatementBuild.class);
	
	/**
	 * 处理添加时的会话
	 * @param ps
	 * @param em
	 */
	public static void buildAddStatement(PreparedStatement ps, EntityMap em) {
		List<TypeAndValues> tvList = EntityMapHelps.convertEntityMapToTv(em);
		for (int i = 0; i < tvList.size(); i++) {
			TypeAndValues tv = tvList.get(i);
			try {
				if (tv.getClazz().toString().toLowerCase().contains("string")) {
					ps.setString(i + 1, (String) tv.getValue());
				} else if (tv.getClazz().toString().toLowerCase().contains("int")
						|| tv.getClazz().toString().toLowerCase().contains("integer")) {
					ps.setInt(i + 1, (Integer) tv.getValue());
				} else if (tv.getClazz().toString().toLowerCase()
						.contains("boolean")) {
					ps.setBoolean(i + 1, (Boolean) tv.getValue());
				} else if(tv.getClazz().toString().toLowerCase().contains("date")) {
					ps.setDate(i + 1, new java.sql.Date(((java.util.Date) tv.getValue()).getTime()));
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
	
	/**
	 * 处理删除时的会话
	 * @param ps
	 * @param em
	 */
	public static void buildDeleteStatement(PreparedStatement ps, EntityMap em) {
		try {
			if(em.getKeyValue().getClass().toString().toLowerCase().contains("string")) {
				ps.setString(1, (String)em.getKeyValue());
			} else if (em.getKeyValue().toString().toLowerCase().contains("int")
					|| em.getKeyValue().toString().toLowerCase().contains("integer")) {
				ps.setInt(1, (Integer)em.getKeyValue());
			}
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
	/**
	 * 处理更新时的会话
	 * @param ps
	 * @param em
	 */
	public static void buildUpdateStatement(PreparedStatement ps, EntityMap em) {
		List<TypeAndValues> tvList = EntityMapHelps.convertUpdateEntityMapToTv(em);
		for (int i = 0; i < tvList.size(); i++) {
			TypeAndValues tv = tvList.get(i);
			try {
				if (tv.getClazz().toString().toLowerCase().contains("string")) {
					ps.setString(i + 1, (String) tv.getValue());
				} else if (tv.getClazz().toString().toLowerCase().contains("int")
						|| tv.getClazz().toString().toLowerCase().contains("integer")) {
					ps.setInt(i + 1, (Integer) tv.getValue());
				} else if (tv.getClazz().toString().toLowerCase()
						.contains("boolean")) {
					ps.setBoolean(i + 1, (Boolean) tv.getValue());
				}
			} catch (SQLException e) {
				log.error(e);
			}
		}
	}
	
	public static void buildFindStatement(PreparedStatement ps, EntityMap em) {
		try {
			if(em.getKeyValue().getClass().toString().toLowerCase().contains("string")) {
				ps.setString(1, (String)em.getKeyValue());
			} else if (em.getKeyValue().getClass().toString().toLowerCase().contains("int")
					|| em.getKeyValue().getClass().toString().toLowerCase().contains("integer")) {
				ps.setInt(1, (Integer)em.getKeyValue());
			}
		} catch (SQLException e) {
			log.error(e);
		}
	}
	
}
