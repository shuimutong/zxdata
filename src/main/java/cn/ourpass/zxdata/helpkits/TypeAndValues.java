package cn.ourpass.zxdata.helpkits;

public class TypeAndValues {
	private Class clazz;
	private Object value;
	
	public TypeAndValues(Class clazz, Object value) {
		super();
		this.clazz = clazz;
		this.value = value;
	}
	public TypeAndValues() {
		super();
	}
	public Class getClazz() {
		return clazz;
	}
	public void setClazz(Class clazz) {
		this.clazz = clazz;
	}
	public Object getValue() {
		return value;
	}
	public void setValue(Object value) {
		this.value = value;
	}
}
