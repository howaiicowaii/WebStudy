package com.sist.temp;

public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Container c=new Container();
		Model m=c.getBean("board"); // Model => 인터페이스
		m.execute();
		
		Model m1=c.getBean("board");
		m1.execute();
		
		Model m2=c.getBean("board");
		m2.execute();
		
		System.out.println("m="+m);
		System.out.println("m1="+m1);
		System.out.println("m2="+m2); // 두개 다 주소가 동일 => 싱글턴 따로 만들 필요 없다
//		m=c.getBean("member");
//		m.execute();
//		
//		m=c.getBean("food");
//		m.execute();
	}

}
