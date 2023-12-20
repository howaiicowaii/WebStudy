package com.sist.vo;

public class ProductVO {
/*
 *  PNO                                       NOT NULL NUMBER
 P_NAME                                    NOT NULL VARCHAR2(1000)
 P_GRADE                                            NUMBER(2,1)
 P_PRICE                                   NOT NULL VARCHAR2(100)
 P_IMAGE                                            VARCHAR2(1000)
 P_PERCENT                                          VARCHAR2(500)
 P_LOWER_PRICE                                      VARCHAR2(500)
 P_SHIPMENT                                         VARCHAR2(500)
 P_STACK                                            NUMBER
 P_CATEGORY                                NOT NULL VARCHAR2(500)
 P_EXPIRE_DATE                                      VARCHAR2(500)
 P_DETAIL_TEXT                                      CLOB
 P_DETAIL_IMAGE                                     VARCHAR2(4000)
 P_LIKE                                             NUMBER
 P_HIT                                              NUMBER
 P_REVIEW_NUM                                       NUMBER
 * 
 * 
 */
	private int pno,p_like,p_hit,p_review_num,intPrice,intPercent;
	private double p_grade;
	private String p_name,p_price,p_image,p_percent,p_lower_price,p_shipment,p_stack,p_category,p_expire_date,p_detail_image;
	public int getPno() {
		return pno;
	}
	public void setPno(int pno) {
		this.pno = pno;
	}
	public int getP_like() {
		return p_like;
	}
	public void setP_like(int p_like) {
		this.p_like = p_like;
	}
	public int getP_hit() {
		return p_hit;
	}
	public void setP_hit(int p_hit) {
		this.p_hit = p_hit;
	}
	public int getP_review_num() {
		return p_review_num;
	}
	public void setP_review_num(int p_review_num) {
		this.p_review_num = p_review_num;
	}
	public int getIntPrice() {
		return intPrice;
	}
	public void setIntPrice(int intPrice) {
		this.intPrice = intPrice;
	}
	public int getIntPercent() {
		return intPercent;
	}
	public void setIntPercent(int intPercent) {
		this.intPercent = intPercent;
	}
	public double getP_grade() {
		return p_grade;
	}
	public void setP_grade(double p_grade) {
		this.p_grade = p_grade;
	}
	public String getP_name() {
		return p_name;
	}
	public void setP_name(String p_name) {
		this.p_name = p_name;
	}
	public String getP_price() {
		return p_price;
	}
	public void setP_price(String p_price) {
		this.p_price = p_price;
	}
	public String getP_image() {
		return p_image;
	}
	public void setP_image(String p_image) {
		this.p_image = p_image;
	}
	public String getP_percent() {
		return p_percent;
	}
	public void setP_percent(String p_percent) {
		this.p_percent = p_percent;
	}
	public String getP_lower_price() {
		return p_lower_price;
	}
	public void setP_lower_price(String p_lower_price) {
		this.p_lower_price = p_lower_price;
	}
	public String getP_shipment() {
		return p_shipment;
	}
	public void setP_shipment(String p_shipment) {
		this.p_shipment = p_shipment;
	}
	public String getP_stack() {
		return p_stack;
	}
	public void setP_stack(String p_stack) {
		this.p_stack = p_stack;
	}
	public String getP_category() {
		return p_category;
	}
	public void setP_category(String p_category) {
		this.p_category = p_category;
	}
	public String getP_expire_date() {
		return p_expire_date;
	}
	public void setP_expire_date(String p_expire_date) {
		this.p_expire_date = p_expire_date;
	}
	public String getP_detail_image() {
		return p_detail_image;
	}
	public void setP_detail_image(String p_detail_image) {
		this.p_detail_image = p_detail_image;
	}
}
