package cn.ourpass.zxdata.entity;

import cn.ourpass.zxdata.annotation.XColumn;
import cn.ourpass.zxdata.annotation.XId;
import cn.ourpass.zxdata.annotation.XTable;

/**
 * 文章
 * @author gaozx
 *
 */
@XTable("article")
public class Article implements BaseEntity {

	private static final long serialVersionUID = -3659359242876070229L;
	
	public Article(int id, String title, String content) {
		super();
		this.id = id;
		this.title = title;
		this.content = content;
	}
	public Article() {
		super();
		// TODO Auto-generated constructor stub
	}
	private int id;
	//文章标题
	private String title;
	//文章内容
	private String content;
	//是否隐藏(0 显示 1 隐藏)
	private int hidden;
	
	@XId(isAutoIncrease=true)
	@XColumn(name="id")
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	@XColumn(name="title")
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@XColumn(name="content")
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	
	@XColumn(name="hidden")
	public int getHidden() {
		return hidden;
	}
	public void setHidden(int hidden) {
		this.hidden = hidden;
	}
	
	@Override
	public String toString() {
		return "Article [id=" + id + ", title=" + title + ", content="
				+ content + ", hidden=" + hidden + "]";
	}
	
}
