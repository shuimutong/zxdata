package cn.ourpass.zxdata.exception;

public class TableNotFoundException extends Exception {
	private static final long serialVersionUID = 422929867564313699L;
	public TableNotFoundException() {
		super("Entity doesn't has the table annotation");
	}

}
