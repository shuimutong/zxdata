package cn.ourpass.zxdata.entity;

import java.util.Date;

import cn.ourpass.zxdata.annotation.XColumn;
import cn.ourpass.zxdata.annotation.XId;
import cn.ourpass.zxdata.annotation.XTable;

/**
 * 登录
 * @author gaozx
 *
 */
@XTable("login")
public class Login implements BaseEntity {
	private static final long serialVersionUID = -3050002099479855759L;
	private int id;
	private String email;
	private String password;
	private Date createTime;
	private Date updateTime;
	
	public Login() {
		super();
	}
	public Login(int id, String email, String password, Date createTime,
			Date updateTime) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.createTime = createTime;
		this.updateTime = updateTime;
	}
	
	@XId(isAutoIncrease=true)
	@XColumn(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@XColumn(name="email", length=128)
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	@XColumn(name="password", length=32)
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@XColumn(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@XColumn(name="update_time")
	public Date getUpdateTime() {
		return updateTime;
	}
	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}
	@Override
	public String toString() {
		return "Login [id=" + id + ", email=" + email + ", password="
				+ password + ", createTime=" + createTime + ", updateTime="
				+ updateTime + "]";
	}
	
}
