package com.myask.util;

import java.security.MessageDigest;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.stereotype.Service;

@Service
public class UserUtil {
	private final int MIN_ID_LENGTH = 4;
	private final int MAX_ID_LENGTH = 13;
	private final int MIN_PW_LENGTH = 6;
	private final int MAX_PW_LENGTH = 16;
	private final int MIN_NAME_LENGTH = 1;
	private final int MAX_NAME_LENGTH = 10;

	public UserUtil() {
	}

	private static class Singleton {
		private static final UserUtil INSTANCE = new UserUtil();
	}

	public static UserUtil getInstance() {
		return Singleton.INSTANCE;
	}

	// 적절한 아이디 비밀번호인지 리턴
	public boolean isAccountCondition(String id, String pw, String name) {
		boolean idCondition = isIdCondition(id);
		boolean pwCondition = isPwCondition(pw);
		boolean nameCondition = isNameCondition(name);
		if (!idCondition || !pwCondition || !nameCondition)
			return false;

		return true;
	}

	// 아이디 길이가 조건에 맞지 않다면 false
	private boolean isIdCondition(String id) {

		int idLength = id.length();
		if (!(MIN_ID_LENGTH <= idLength && idLength <= MAX_ID_LENGTH))
			return false;

		// 아이디가 소문자, 대문자, 숫자로만 이루어져있는지 카운트 측정
		int alphabetCount = 0;
		int numCount = 0;
		for (int i = 0; i < idLength; i++) {
			for (char c = 'a'; c <= 'z'; c++) {
				if (id.charAt(i) == c)
					alphabetCount++;
			}

			for (char c = 'A'; c <= 'Z'; c++) {
				if (id.charAt(i) == c)
					alphabetCount++;
			}

			for (char c = '0'; c <= '9'; c++) {
				if (id.charAt(i) == c)
					numCount++;
			}
		}

		// 만약 숫자와 알파벳 이외의 것이 사용되었다면 false
		if (alphabetCount + numCount != idLength)
			return false;

		return true;
	}

	// 비밀번호 길이가 조건에 맞지 않다면 false
	private boolean isPwCondition(String pw) {
		int pwLength = pw.length();
		if (!(MIN_PW_LENGTH <= pwLength && pwLength <= MAX_PW_LENGTH))
			return false;

		// 특수문자에 관한 조건들을 따로 정의할 수 없으므로 길이만 맞으면 true
		return true;
	}

	// 이름 길이가 조건에 맞지 않다면 false
	private boolean isNameCondition(String name) {
		int nameLength = name.length();
		if (!(MIN_NAME_LENGTH <= nameLength && nameLength <= MAX_NAME_LENGTH))
			return false;

		// 특수문자에 관한 조건들을 따로 정의할 수 없으므로 길이만 맞으면 true
		return true;
	}

	// 아이디 비밀번호를 사용해 SHA-512 방식으로 암호화
	public String encrypt(String id, String password) throws Exception {
		if (password == null)
			return "";

		MessageDigest md = MessageDigest.getInstance("SHA-512");
		md.reset();
		md.update(id.getBytes());

		byte[] hashVal = md.digest(password.getBytes());

		return new String(Base64.encodeBase64(hashVal));
	}
}
