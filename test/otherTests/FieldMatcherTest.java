package otherTests;

import static org.junit.Assert.*;

import org.junit.Test;

import playingField.FieldPosition;
import aiPlayerStages.FieldMatcher;

public class FieldMatcherTest {

	@Test
	public void matchingTestNoRotationNoMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?-e"
				+ "-ox", 
				
				  "-xo"
				+ "x--"
				+ "-ox", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);

		assertEquals("matches, but result wrong", result, FieldPosition.Right);
	}

	@Test
	public void matchingTestOneRotationNoMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?e-"
				+ "-ox", 
				
				  "o-x"
				+ "x-o"
				+ "-x-", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);

		assertEquals("matches, but result wrong", result, FieldPosition.Center);
	}

	@Test
	public void matchingTestThreeRotationNoMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?--"
				+ "eox", 
				
				  "-x-"
				+ "o-x"
				+ "x-o", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);

		assertEquals("matches, but result wrong", result, FieldPosition.TopLeft);
	}

	@Test
	public void matchingTestNoRotationMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?--"
				+ "eox", 
				
				  "-x-"
				+ "x-o"
				+ "o-x", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);

		assertEquals("matches, but result wrong", result, FieldPosition.TopRight);
	}

	@Test
	public void matchingTestOneRotationMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?e-"
				+ "-ox", 
				
				  "-ox"
				+ "x--"
				+ "-xo", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);

		assertEquals("matches, but result wrong", result, FieldPosition.Center);
	}

	@Test
	public void matchingTestThreeRotationMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?-e"
				+ "-ox", 
				
				  "ox-"
				+ "--x"
				+ "xo-", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);

		assertEquals("matches, but result wrong", result, FieldPosition.Left);
	}

	@Test
	public void matchingTestNoMatch1() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?xe"
				+ "-ox", 
				
				  "ox-"
				+ "--x"
				+ "xo-", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertEquals("match, but should not", result, FieldPosition.Empty);
	}

	@Test
	public void matchingTestNoMatch2() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "---"
				+ "-ox", 
				
				  "ox-"
				+ "--x"
				+ "xo-", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertEquals("match, but should not", result, FieldPosition.Empty);
	}

	@Test
	public void matchingTestNoMatch3() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "x?-"
				+ "x-x", 
				
				  "ox-"
				+ "--x"
				+ "xo-", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertEquals("match, but should not", result, FieldPosition.Empty);
	}

}
