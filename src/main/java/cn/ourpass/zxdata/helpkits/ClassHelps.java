package cn.ourpass.zxdata.helpkits;


/**
 * Class相关帮助类
 * @author gaozx
 *
 */
public class ClassHelps {
	/**
	 * type是否是Integer类型
	 * @param ea
	 * @return
	 */
	public static boolean isInteger(Class clazz) {
		return clazz.toString().toLowerCase().contains("int") 
				|| clazz.toString().toLowerCase().contains("integer");
	}
	
	/**
	 * EntityAttr的type是否是String类型
	 * @param ea
	 * @return
	 */
	public static boolean isString(Class clazz) {
		return clazz.toString().toLowerCase().contains("string");
	}

	/**
	 * EntityAttr的type是否是Boolean类型
	 * @param ea
	 * @return
	 */
	public static boolean isBoolean(Class clazz) {
		return clazz.toString().toLowerCase().contains("boolean");
	}
}
