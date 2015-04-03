package cn.ourpass.zxdata.helpkits;

import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.LinkedList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.ourpass.zxdata.annotation.XColumn;
import cn.ourpass.zxdata.annotation.XId;
import cn.ourpass.zxdata.annotation.XTable;
import cn.ourpass.zxdata.exception.TableNotFoundException;

/**
 * 实体反射对象
 * 
 * @author gaozx
 *
 */
public class EntityMap<E extends Serializable, PK extends Serializable> {
	private final static Logger log = Logger.getLogger(EntityMap.class);

	// 类名
	private String className;
	// 主键名
	private String keyName;
	// 主键对应表列名
	private String keyColumnName;
	// 主键值
	private PK keyValue;
	// 表名
	private String tableName;
	// 属性名称
	private List<EntityAttr> attributesList = new LinkedList<EntityAttr>();

	public EntityMap() {
	}

	public EntityMap(E e) throws TableNotFoundException {
		Class<? extends Object> clazz = e.getClass();
		this.className = clazz.getName();
		// 匹配实体对应的表名
		if (!AnnotationHelp.isHasTheAnnotation(clazz, XTable.class)) {
			throw new TableNotFoundException();
		} else {
			XTable xTable = (XTable) clazz.getAnnotation(XTable.class);
			this.tableName = xTable.value();
		}
		Method[] methods = clazz.getDeclaredMethods();
		// 匹配各个方法
		for (Method me : methods) {
			String columnName = "";
			// 主键查找
			if (null == keyName
					&& AnnotationHelp.isHasTheAnnotation(me, XId.class)) {
				keyName = StringUtilsOfMe.formatGetMethodToAttributeName(me
						.getName());
				keyColumnName = me.getAnnotation(XColumn.class).name();
			}
			// 建立数据库实体映射
			if (AnnotationHelp.isHasTheAnnotation(me, XColumn.class)) {
				String entityName = StringUtilsOfMe
						.formatGetMethodToAttributeName(me.getName());
				columnName = me.getAnnotation(XColumn.class).name();
				try {
					if (columnName.equals(keyColumnName)) {
						this.keyValue = (PK) me.invoke(e, null);
					}
					EntityAttr attr = new EntityAttr(entityName, me.invoke(e,
							null), me.getReturnType(), columnName);
					this.attributesList.add(attr);
				} catch (Exception e1) {
					log.error(e1);
				}
			}
		}
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getKeyName() {
		return keyName;
	}

	public void setKeyName(String keyName) {
		this.keyName = keyName;
	}

	public String getKeyColumnName() {
		return keyColumnName;
	}

	public void setKeyColumnName(String keyColumnName) {
		this.keyColumnName = keyColumnName;
	}

	public PK getKeyValue() {
		return keyValue;
	}

	public void setKeyValue(PK keyValue) {
		this.keyValue = keyValue;
	}

	public List<EntityAttr> getAttributesList() {
		return attributesList;
	}

	public void setAttributesList(List<EntityAttr> attributesList) {
		this.attributesList = attributesList;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	@Override
	public String toString() {
		return "EntityMap [className=" + className + ", keyName=" + keyName
				+ ", keyColumnName=" + keyColumnName + ", keyValue=" + keyValue
				+ ", tableName=" + tableName + ", attributesList="
				+ attributesList + "]";
	}

	/**
	 * 获取对象的对应表名与主键id{tableName, keyName}
	 * 
	 * @param e
	 * @return
	 * @throws TableNotFoundException
	 */
	public String[] getTableNameAndKeyName(E e) throws TableNotFoundException {
		Class clazz = e.getClass();
		// 匹配实体对应的表名
		if (!AnnotationHelp.isHasTheAnnotation(clazz, XTable.class)) {
			throw new TableNotFoundException();
		} else {
			XTable xTable = (XTable) clazz.getAnnotation(XTable.class);
			this.tableName = xTable.value();
			// 匹配各个方法
			for (Method me : clazz.getDeclaredMethods()) {
				// 主键查找
				if (AnnotationHelp.isHasTheAnnotation(me, XId.class)) {
					this.keyName = StringUtilsOfMe
							.formatGetMethodToAttributeName(me.getName());
					break;
				}
			}
		}
		return new String[] { tableName, keyName };
	}
	
	public void buildKeyValue(PK id) {
		this.keyValue = id;
	}

	/**
	 * 属性对象
	 * 
	 * @author gaozx
	 *
	 */
	public class EntityAttr {
		// 属性名
		private String name;
		// 属性值
		private Object value;
		// 属性类型
		private Class type;
		// 数据库对应字段名称
		private String columnName;

		public EntityAttr() {
		}

		public EntityAttr(String name, Object value, Class type,
				String columnName) {
			this.name = name;
			this.value = value;
			this.type = type;
			this.columnName = columnName;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public Object getValue() {
			return value;
		}

		public void setValue(Object value) {
			this.value = value;
		}

		public Class getType() {
			return type;
		}

		public void setType(Class type) {
			this.type = type;
		}

		public String getColumnName() {
			return columnName;
		}

		public void setColumnName(String columnName) {
			this.columnName = columnName;
		}

		@Override
		public String toString() {
			return "EntityAttr [name=" + name + ", value=" + value + ", type="
					+ type + ", columnName=" + columnName + "]";
		}

	}

}
