package com.sist.main;

import java.util.Arrays;
import java.io.UnsupportedEncodingException;
import java.net.*;

public class MainClass {

	public static void main(String[] args) throws UnsupportedEncodingException {
		// TODO Auto-generated method stub
		String name="홍길동";
		System.out.println(URLEncoder.encode(name,"UTF-8"));
	}

}
