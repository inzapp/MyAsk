package com.myask.domain;

/**
 * 이 클래스는 비밀번호, 비밀번호 확인 폼의 값이 일치하는지 확인하는곳에만 쓰임
 * 만약 일치한다면 UserVO 를 만들어 정보를 복사하고 해당 userVO 를 insert함
 * 그래서 이 클래스에 대한 실제 DB 테이블이나 Mapper는 필요 없음
 */
public class AccountCheckVO {
	private String id;
	private String password;
	private String password2;
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword2() {
		return password2;
	}

	public void setPassword2(String password2) {
		this.password2 = password2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
