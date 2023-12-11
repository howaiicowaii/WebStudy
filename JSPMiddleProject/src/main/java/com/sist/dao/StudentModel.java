package com.sist.dao;

public class StudentModel {
	public StudentVO studentDetailData()
	{
		StudentVO vo=new StudentVO();
		vo.setHakbun(1);
		vo.setName("홍길동");
		vo.setKor(90);
		vo.setEng(80);
		vo.setMath(70);
		return vo;
	}
}
