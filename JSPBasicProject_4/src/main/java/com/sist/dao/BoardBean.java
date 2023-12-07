package com.sist.dao;
import java.util.*;
// 필요한 데이터를 모아서 한번에 전송할 목적 : Bean (= VO)
// ROW => 한개만 설정이 가능 / List(여러개)
public class BoardBean {
	private int no;
	private String name;
	private String subject;
	private String content;
	private String pwd;
	private Date regdate;
	private int hit;
	// 변수를 감췄으니 변수에 대한 기능 => 읽기/쓰기 가능하게 만들어줘야 한다 => Getter/Setter
	// => lombok (getter/setter 쉽게 쓸 수 있게 해주는)
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public Date getRegdate() {
		return regdate;
	}
	public void setRegdate(Date regdate) {
		this.regdate = regdate;
	}
	public int getHit() {
		return hit;
	}
	public void setHit(int hit) {
		this.hit = hit;
	}
	
}
