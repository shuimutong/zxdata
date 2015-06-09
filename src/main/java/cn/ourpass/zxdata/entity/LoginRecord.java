package cn.ourpass.zxdata.entity;

import java.util.Date;

import cn.ourpass.zxdata.annotation.XColumn;
import cn.ourpass.zxdata.annotation.XId;
import cn.ourpass.zxdata.annotation.XTable;

/**
 * 登录记录
 * @author gaozx
 *
 */
@XTable("login_record")
public class LoginRecord implements BaseEntity {
	private static final long serialVersionUID = -5031417595210005126L;
	private int id;
	private int loginId;
	private String cookieValue;
	private Date createTime;
	
	public LoginRecord() {
		super();
	}
	
	public LoginRecord(int id, int loginId, String cookieValue, Date createTime) {
		super();
		this.id = id;
		this.loginId = loginId;
		this.cookieValue = cookieValue;
		this.createTime = createTime;
	}

	@XId(isAutoIncrease=true)
	@XColumn(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XColumn(name="login_id")
	public int getLoginId() {
		return loginId;
	}
	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}
	
	@XColumn(name="create_time")
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	
	@XColumn(name="cookie_value")
	public String getCookieValue() {
		return cookieValue;
	}
	public void setCookieValue(String cookieValue) {
		this.cookieValue = cookieValue;
	}
	
}
