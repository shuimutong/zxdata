package cn.ourpass.zxdata.helpkits;

import java.lang.reflect.Method;

/**
 * 注解帮助类
 * @author gaozx
 *
 */
public class AnnotationHelp {
	/**
	 * 判断方法是否被clazz注解
	 * @param method
	 * @param clazz
	 * @return
	 */
	public static boolean isHasTheAnnotation(Method method, Class clazz) {
		return method.isAnnotationPresent(clazz);
	}
	/**
	 * 判断类是否被clazz注解
	 * @param sourceClass
	 * @param clazz
	 * @return
	 */
	public static boolean isHasTheAnnotation(Class sourceClass, Class clazz) {
		return sourceClass.isAnnotationPresent(clazz);
	}
}
