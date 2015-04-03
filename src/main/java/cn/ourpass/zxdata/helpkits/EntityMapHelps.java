package cn.ourpass.zxdata.helpkits;

import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.ourpass.zxdata.helpkits.EntityMap.EntityAttr;

public class EntityMapHelps {
	private final static Logger log = Logger.getLogger(EntityMapHelps.class);

	/**
	 * 获取含值对象字符拼接串(id,name,last_name,age ?,?,?,?)
	 * 
	 * @param em
	 * @return
	 */
	public static String[] getColumnNames(EntityMap em) {
		StringBuilder columnNames = new StringBuilder();
		StringBuilder askValues = new StringBuilder();
		List<EntityAttr> list = em.getAttributesList();
		for (EntityAttr ea : list) {
			if (ea.getColumnName().equals(em.getKeyName())
					&& ea.getValue() != null && (Integer) (ea.getValue()) > 0) { // 主键且有值
				columnNames.append(ea.getColumnName()).append(",");
				askValues.append("?,");
			} else if (!ea.getColumnName().equals(em.getKeyName())) {
				columnNames.append(ea.getColumnName()).append(",");
				askValues.append("?,");
			}
		}
		return new String[] {
				columnNames.substring(0, columnNames.length() - 1),
				askValues.substring(0, askValues.length() - 1) };
	}
	
	/**
	 * 创建列名队列(id, name, age)
	 * @param em
	 * @return
	 */
	public static String getEmptyColumnNames(EntityMap em) {
		StringBuilder columnNames = new StringBuilder();
		List<EntityAttr> list = em.getAttributesList();
		for (EntityAttr ea : list) {
			columnNames.append(ea.getColumnName()).append(", ");
		}
		return columnNames.substring(0, columnNames.length()-2).toString();
	}

	/**
	 * 封装对象与值
	 * 
	 * @param em
	 * @return
	 */
	public static List<TypeAndValues> convertEntityMapToTv(EntityMap em) {
		List<TypeAndValues> list = new LinkedList<TypeAndValues>();
		List<EntityAttr> ealist = em.getAttributesList();
		for (EntityAttr ea : ealist) {
			if (ea.getColumnName().equals(em.getKeyName())
					&& ea.getValue() != null && (Integer) (ea.getValue()) > 0) { // 主键且有值
				list.add(new TypeAndValues(ea.getType(), ea.getValue()));
			} else if (!ea.getColumnName().equals(em.getKeyName())) {
				list.add(new TypeAndValues(ea.getType(), ea.getValue()));
			}
		}
		return list;
	}
	
	/**
	 * 封装更新时的对象与值
	 * 
	 * @param em
	 * @return
	 */
	public static List<TypeAndValues> convertUpdateEntityMapToTv(EntityMap em) {
		List<TypeAndValues> list = new LinkedList<TypeAndValues>();
		List<EntityAttr> ealist = em.getAttributesList();
		TypeAndValues idTv = null;
		for (EntityAttr ea : ealist) {
			if (ea.getColumnName().equals(em.getKeyName())
					&& ea.getValue() != null && (Integer) (ea.getValue()) > 0) { // 主键且有值
				idTv = new TypeAndValues(ea.getType(), ea.getValue());
			} else if (!ea.getColumnName().equals(em.getKeyName())) {
				list.add(new TypeAndValues(ea.getType(), ea.getValue()));
			}
		}
		list.add(idTv);
		return list;
	}

	/**
	 * 拼接update的对象属性
	 * 
	 * @param em
	 * @return {"name='1', age=2", "id=32"}
	 */
	public static String[] createUpdateSqlStrs(EntityMap em) {
		StringBuilder columns = new StringBuilder();
		StringBuilder index = new StringBuilder();
		List<EntityAttr> list = em.getAttributesList();
		index.append(em.getKeyColumnName()).append("=?");
		for (EntityAttr ea : list) {
			if (!ea.getColumnName().equals(em.getKeyColumnName())) {
				columns.append(ea.getColumnName()).append("=?")
						.append(", ");
			}
		}
		return new String[] {
				columns.substring(0, columns.length() - 2).toString(),
				index.toString() };
	}
	
	/**
	 * 用结果集填充对象List<E>
	 * @param rs
	 * @param em
	 * @return
	 */
	public <E> List<E> buildEntityByEntityMap(ResultSet rs, EntityMap em) {
		List<E> resultList = new LinkedList<E>();
		try {
			Class clazz = Class.forName(em.getClassName());
			int i = 0;
			while(rs != null && rs.next()) { //滚动结果集
				E ee = (E) clazz.newInstance();
				for(EntityAttr ea : (List<EntityAttr>)em.getAttributesList()) { //循环填充对象属性
					Method me = clazz.getMethod(StringUtilsOfMe.formatAttributeNameToSetMethodName(ea.getName()), ea.getType());
					if(ClassHelps.isString(ea.getType())) {
						me.invoke(ee, rs.getString(ea.getColumnName()));
					} else if(ClassHelps.isInteger(ea.getType())) {
						me.invoke(ee, rs.getInt(ea.getColumnName()));
					}
				}
				resultList.add(ee);
			}
		} catch (Exception e) {
			log.error(e);
		}
		return resultList;
	}
}
