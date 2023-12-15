package com.sist.temp;

import java.lang.reflect.Method;

class A{
	public void aaa()
	{
		System.out.println("A:aaa() Call...");
	}
	public void bbb()
	{
		System.out.println("A:bbb() Call...");
	}
	public void ccc()
	{
		System.out.println("A:ccc() Call...");
	}
}
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		A a=new A();
//		a.aaa();
//		a.bbb();
//		a.ccc();
		// 자바 코딩이 가능할 때나 사용하는 방법
		// Spring은 라이브러리이기에 자바처럼 이렇게 클래스 초기화해서 메소드 못불러온다.
		// 그러므로 클래스 이름으로 메모리 생성하고 invoke로 메소드 호출한다.
		try
		{
			// A a=new A() (자바 코딩 방식)
			Class clsName=Class.forName("com.sist.temp.A"); // 클래스 메모리 생성(할당?)
			Object obj=clsName.getDeclaredConstructor().newInstance(); // clsName안에 생성되있는 생성자 가져와라
//			A a=(A)obj;
//			a.aaa();
//			a.bbb();
//			a.ccc();
			
			Method[] methods=clsName.getDeclaredMethods();
			for(Method m:methods)
			{
				m.invoke(obj, null);
			}
		}catch(Exception ex) {}
	}

}
