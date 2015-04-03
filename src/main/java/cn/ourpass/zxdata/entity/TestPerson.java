package cn.ourpass.zxdata.entity;

import cn.ourpass.zxdata.annotation.XColumn;
import cn.ourpass.zxdata.annotation.XId;
import cn.ourpass.zxdata.annotation.XTable;

/**
 * 测试对象
 * @author gaozx
 *
 */
@XTable("t_person")
public class TestPerson implements BaseEntity {
	private static final long serialVersionUID = 6740955190801463562L;
	
	private int id;
	private String name;
	private String lastName;
	private int age;
	
	public TestPerson() {
		super();
	}
	public TestPerson(int id, String name, String lastName, int age) {
		super();
		this.id = id;
		this.name = name;
		this.lastName = lastName;
		this.age = age;
	}
	@XId(isAutoIncrease=true)
	@XColumn(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XColumn(name="name", length=32)
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@XColumn(name="last_name", length=32)
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@XColumn(name="age")
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public boolean equals(Object obj) {
		if(obj != null && obj instanceof TestPerson) {
			TestPerson tp = (TestPerson)obj;
			if(tp == this) {
				return true;
			} else if(tp.id == this.id && tp.age == this.age 
					&& this.name.equals(tp.name) 
					&& this.lastName.equals(tp.lastName)) {
				return true;
			}
		}
		return false;
	}
	@Override
	public String toString() {
		return "TestPerson [id=" + id + ", name=" + name + ", lastName="
				+ lastName + ", age=" + age + "]";
	}
}
