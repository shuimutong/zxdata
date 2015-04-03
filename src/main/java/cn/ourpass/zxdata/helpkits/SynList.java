package cn.ourpass.zxdata.helpkits;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class SynList<E> implements Serializable {
	private static final long serialVersionUID = 1446471254928658644L;
	private final List<E> list = new LinkedList<E>();
	public SynList() {
		
	}
}
