package cn.ourpass.zxdata.helpkits;

public class StringUtilsOfMe {
	/**
	 * 验证字符是否为空
	 * @param strs
	 * @return
	 */
	public static boolean isNullStrings(String... strs) {
		boolean isNull = false;
		if(null != strs && strs.length > 0) {
			for(String s : strs) {
				if(null == s || s == "" || s.length() == 0) {
					isNull = true;
					break;
				}
			}
		}
		return isNull;
	}
	
	/**
	 * 通过get方法获取属性名称
	 * @param methodName
	 * @return
	 */
	public static String formatGetMethodToAttributeName(String methodName) {
		String tempAttrName = methodName.substring(3);
		return tempAttrName.substring(0, 1).toLowerCase() + tempAttrName.substring(1);
	}
	
	/**
	 * 通过属性名称获取get方法名
	 * @param attrName
	 * @return
	 */
	public static String formatAttributeNameToGetMethodName(String attrName) {
		return "get" + attrName.substring(0, 1).toUpperCase() + attrName.substring(1);
	}
	
	/**
	 * 通过属性名称获取set方法名
	 * @param attrName
	 * @return
	 */
	public static String formatAttributeNameToSetMethodName(String attrName) {
		return "set" + attrName.substring(0, 1).toUpperCase() + attrName.substring(1);
	}
}
