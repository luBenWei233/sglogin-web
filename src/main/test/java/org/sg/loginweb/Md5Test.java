package org.sg.loginweb;

import java.io.UnsupportedEncodingException;

import org.springframework.util.DigestUtils;

public class Md5Test {
	public static void main(String[] args) {
	 	String text = "789456";
	 	try {
	 		String password = DigestUtils.md5DigestAsHex(text.getBytes("utf-8"));
	 		System.out.println(password);
	    } catch (UnsupportedEncodingException e) {
	    	e.printStackTrace();
	    }
	}
}
