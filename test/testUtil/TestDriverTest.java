package testUtil;


import org.junit.Test;

import exceptions.InvalidSyntaxException;

public class TestDriverTest {

	@Test
	public void likeExpectedTestVaildSyntax1() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "--o"
				+ "xxe"
				+ "o--",
				(field, dec) -> {});	
	}
	
	@Test
	public void likeExpectedTestVaildSyntax2() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "--o"
				+ "xxe"
				+ "o-x",
				(field, dec) -> {});	
	}
	
	@Test(expected = InvalidSyntaxException.class)
	public void likeExpectedTestInvaildSyntax1() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "--o"
				+ "xxe"
				+ "oo-",
				(field, dec) -> {});	
	}
	
	@Test(expected = InvalidSyntaxException.class)
	public void likeExpectedTestInvaildSyntax2() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "--j"
				+ "xxe"
				+ "oo-",
				(field, dec) -> {});	
	}
	
	@Test(expected = InvalidSyntaxException.class)
	public void likeExpectedTestInvaildSyntax3() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "---o"
				+ "xxe"
				+ "oo-",
				(field, dec) -> {});	
	}
	
	@Test(expected = InvalidSyntaxException.class)
	public void likeExpectedTestInvaildSyntax4() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "-o"
				+ "xxe"
				+ "oo-",
				(field, dec) -> {});	
	}

}
