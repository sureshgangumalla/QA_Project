package com.quality.project.JUnit.passwordConfigTest;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.quality.project.password.IPasswordParam;
import com.quality.project.password.PasswordEncrypt;
import com.quality.project.password.PasswordValidator;

public class PasswordEncryptTest {
	
	PasswordEncrypt passwordEncrypt;
	
	@Before
    public void setUp() {
		passwordEncrypt = new PasswordEncrypt();
    }
	
	@Test
	public void encryptTestOne() {	
		String test = "811B1E58DBEDEFF72B4965DFEDBBB69C11512A2CB269614E22F4BBDB710967C9";
		String result = passwordEncrypt.hashPassword("Testing123");
		assertTrue("Password Encrypted Successfully", test.equals(result));
	}
	
	@Test
	public void encryptTestTwo() {	
		String test = "CD2589E43E6A7BA760FA28F8A6B3C38777E0C4B4DDC9646DC97AF31EA8299A0B";
		String result = passwordEncrypt.hashPassword("Test2");
		assertTrue("Password Encrypted Successfully", test.equals(result));
	}
	
	@Test
	public void encryptTestThree() {	
		String test = "E26019CE48ADEBEF79A322206FC633FCF1C6230A84812E0E528414C009ABD98D";
		String result = passwordEncrypt.hashPassword("1");
		assertTrue("Password Encrypted Successfully", test.equals(result));
	}
	
	@Test
	public void encryptTestFour() {	
		String test = "822A909DF620076EC20EFBF555DA79C9EA783530279AAE69EF5C89E2F2059225";
		String result = passwordEncrypt.hashPassword("One");
		assertTrue("Password Encrypted Successfully", test.equals(result));
	}
	
	@Test
	public void encryptTestFive() {	
		String test = "C4E23CD2AA7CBE0A0DD4C1FD8F06FC9AC0C84AB15B52CC8141A58EF4BBEFCA03";
		String result = passwordEncrypt.hashPassword("@");
		assertTrue("Password Encrypted Successfully", test.equals(result));
	}
	
	@Test
	public void encryptTestSix() {	
		String test = "822A909DF620076EC20EFBF555DA79C9EA783530279AAE69EF5C89E2F2059225";
		String result = passwordEncrypt.hashPassword("One");
		assertTrue("Password Encrypted Successfully", test.equals(result));
	}

}
