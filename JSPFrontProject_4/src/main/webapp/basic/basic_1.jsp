<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
// main
/*
	1. 자바 : main()
	2. 자바스크립트 : window.onload=function(){}
	3. Jquery : $(function(){})
	4. VueJS : mounted()
	5. React : componentDidMounted()
	
	ES5 ==> ES6
	변수 => 자동 지정 변수
	var     let =======> 사용범위 (지역변수 => 메모리 해제 필요한데, var은 벗어나도 해제가 안된다)
	
	=> 변수 선언 => let / const
	   let => 지역변수
	   const => 상수 
	   => 자동 지정 변수 => Jquery / Vue / React  --> 자바스크립트 라이브러리들
	      let i=10  ==> i:int
	      let i=10.5 ==> i:double
	      ------------------------> i:Number : 숫자형 으로 인식
	      let i='A' => i:char
	      let i="A" => i:String
	      ------------------------> i:String : 문자형
	      let i=true => i:Boolean
	      ------------------------> i:Boolean : 논리형
		
	      let i={name:"홍길동",sex:"남자"}
	      ------------------------------
	                                i:Object : 객체형
	                                => JSON(*****)
	                                => ~VO 와 같은 역할
		  let i=[1,2,3,4,5]
	      ------------------ i:Array
	      형변환 
	        숫자변환 => Number("10") = 10
	                  parseInt("10") = 10(정수형)
	        문자변환 => String(10) = "10"
	                  (int)10.5 => X. 자바에서 사용되는 방식
	                  Math.round(10.5)
	                  => String, Date, Math ...
	        논리형  => Boolean(1) => true
	                  Boolean(0) => false
	                  0, 0.0이 아닌 수는 모두 true
	        =======================================
	        연산자
	          + : 산술, 문자열 결합
	              "10"+"20" => "1020"
	              "10"-"20" => -10
	              ---------- parseInt("10")-parseInt("20")
	              
	          / : 정수/정수=실수
	          
	          ==,=== (!=, !==) : 혼용
	             --- 권장
	        
	         연산처리시 변수 선언
	         = undefined : 변수의 초기화 (X) , 변수 선언이 안된 상태
	         = NaN : 연산 처리가 안된 상태
	                 'A'-1 = NaN
	                 ------
	                 금액 : 1000원
	                 <select>
	                   <option value="1">1개</option>
	                   <option>2개</option>
	                   <option>3개</option>
	          		 </select>
	          		 => 1000*2개 => NaN => so, value="1" 값주면 오류 발생없어진다
	         = Infinity : 0으로 나눈 경우   10/0 => 오류 발생
	         =>
	                 let i=
	                 let name=''
	                 ==> Jquery : 충돌 => $ (EL인지 Jquery인지 오류발생할 수 있다)
	         
	     오라클 ========= 데이터 읽기(자바) ========= HTML/JavaScript
	     ====                                    => EL/JSTL 적용
	   데이터 공유 
	      
 */
window.onload=function(){
	console.log("a="+a) // undefined
	// var => 단점 : 메모리 누수 현상 => 그래서 ES6 버전부터 let 으로 사용
	if(true) // 1=true
	{
		var a=10;
		console.log("if:a="+a);
	}
	console.log("if밖 => a="+a); // 오류 발생 X. 지역 벗어나도 메모리 해제 안되서 메모리 누수현상 발생
	
	
}
</script>
</head>
<body>

</body>
</html>