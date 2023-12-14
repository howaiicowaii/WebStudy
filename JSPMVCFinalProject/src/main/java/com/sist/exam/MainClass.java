package com.sist.exam;
import java.lang.reflect.Method;
import java.util.*;
class Model
{
	@RequestMapping("a")
	public void aaa()
	{
		System.out.println("Model:aaa()");
	}
	@RequestMapping("b")
	public void bbb()
	{
		System.out.println("Model:bbb()");
	}
	@RequestMapping("c")
	public void ccc()
	{
		System.out.println("Model:ccc()");
	}
	
}
public class MainClass {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.print("검색어 입력:");
		String fd=sc.next();
		
//		Model model=new Model();
//		if(fd.equals("a"))
//			model.aaa();
//		else if(fd.equals("b"))
//			model.bbb();
//		else if(fd.equals("c"))
//			model.ccc();
		
		// if 문 대신에 클래스안의 각 메소드 위에 @RequestMapping("a") => 요청값이 a 면 aaa() 메소드 호출
		try
		{
			Class clsName=Class.forName("com.sist.exam.Model");
			// 클래스 정보를 읽어온다 => so 메소드 전체 읽어올 수 있다
			Object obj=clsName.getDeclaredConstructor().newInstance();
			
			// 메소드 읽기
			Method[] methods=clsName.getDeclaredMethods();
			for(Method m:methods)
			{
				//System.out.println(m.getName());
				RequestMapping rm=m.getAnnotation(RequestMapping.class);
				if(rm.value().equals(fd)) // ("a") : 요청값 = value
				{
					m.invoke(obj, null); // 메소드를 호출
					break;
				}
			}
		}catch(Exception ex) {}
		
	}

}
