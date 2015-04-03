package cn.ourpass.zxdata.helpkits;

import cn.ourpass.zxdata.exception.TableNotFoundException;

/**
 * sql语句创建类
 * 
 * @author gaozx
 *
 */
public class SqlCreateUtils {
	/**
	 * 创建插入sql字符串
	 * 
	 * @param em
	 * @return
	 * @throws TableNotFoundException
	 */
	public static String addSqlMake(EntityMap em) throws TableNotFoundException {
		StringBuilder sql = new StringBuilder();
		String[] nameAndValues = EntityMapHelps.getColumnNames(em);
		sql.append("insert into ").append(em.getTableName())
				.append(" (" + nameAndValues[0] + ")")
				.append(" values (" + nameAndValues[1] + ");");
		return sql.toString();
	}

	/**
	 * 创建根据id删除的sql语句
	 * 
	 * @param em
	 * @return
	 */
	public static String deleteSqlMake(EntityMap em) {
		StringBuilder sql = new StringBuilder();
		sql.append("delete from ").append(em.getTableName()).append(" where ")
				.append(em.getKeyName()).append("=?");
		// delete from test_person where id=?
		return sql.toString();
	}

	public static String updateSqlMake(EntityMap em) {
		StringBuilder sql = new StringBuilder();
		String[] columns = EntityMapHelps.createUpdateSqlStrs(em);
		sql.append("update ").append(em.getTableName()).append(" set ")
				.append(columns[0]).append(" where ").append(columns[1])
				.append(";");
		return sql.toString();
	}

	public static String findSqlMake(EntityMap em) {
		StringBuilder sql = new StringBuilder();
		String columns = EntityMapHelps.getEmptyColumnNames(em);
		sql.append("select ").append(columns).append(" from ")
				.append(em.getTableName()).append(" where ")
				.append(em.getKeyColumnName()).append("=?;");
		return sql.toString();
	}
}
