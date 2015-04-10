package cn.ourpass.zxdata.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 数据库列
 * @author gaozx
 *
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface XColumn {
	/**
	 * 列名称
	 * @return
	 */
	String name();
	/**
	 * 自动长度
	 * @return
	 */
	int length() default 0;
	/**
	 * 是否唯一
	 * @return
	 */
	boolean unique() default false;
	/**
	 * 是否允许非空
	 * @return
	 */
	boolean nullable() default true;
}
