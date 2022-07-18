package org.sg.loginweb.util;

public enum MyEnum {
	
	IMAGE_REQUEST_URL("/image/**"),
	IMAGE_PATH("/image/"),
	MYSQL("mysql"),
	SQLITE("sqlite");

	private final String value;
	
	private MyEnum(String value) {
		this.value=value;
	}
	
	public String getValue() {
		return value;
	}
}
