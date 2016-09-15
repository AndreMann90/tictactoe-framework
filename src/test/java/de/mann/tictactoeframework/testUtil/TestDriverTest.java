package de.mann.tictactoeframework.testUtil;

import de.mann.tictactoeframework.exceptions.InvalidSyntaxException;
import org.junit.Test;

public class TestDriverTest {

	@Test
	public void likeExpectedTestValidSyntax1() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "--o"
				+ "xxe"
				+ "o--",
				(field, dec) -> {});	
	}
	
	@Test
	public void likeExpectedTestValidSyntax2() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "--o"
				+ "xxe"
				+ "o-x",
				(field, dec) -> {});	
	}
	
	@Test(expected = InvalidSyntaxException.class)
	public void likeExpectedTestInvalidSyntax1() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "--o"
				+ "xxe"
				+ "oo-",
				(field, dec) -> {});	
	}
	
	@Test(expected = InvalidSyntaxException.class)
	public void likeExpectedTestInvalidSyntax2() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "--j"
				+ "xxe"
				+ "oo-",
				(field, dec) -> {});	
	}
	
	@Test(expected = InvalidSyntaxException.class)
	public void likeExpectedTestInvalidSyntax3() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "---o"
				+ "xxe"
				+ "oo-",
				(field, dec) -> {});	
	}
	
	@Test(expected = InvalidSyntaxException.class)
	public void likeExpectedTestInvalidSyntax4() throws InvalidSyntaxException {

		TestDriver.likeExpected(
				  "-o"
				+ "xxe"
				+ "oo-",
				(field, dec) -> {});	
	}

}
