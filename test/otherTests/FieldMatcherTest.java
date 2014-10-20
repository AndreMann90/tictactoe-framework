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
				+ "?--"
				+ "-ox", 
				
				  "-xo"
				+ "x--"
				+ "-ox", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);
	}

	@Test
	public void matchingTestOneRotationNoMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?--"
				+ "-ox", 
				
				  "o-x"
				+ "x-o"
				+ "-x-", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);
	}

	@Test
	public void matchingTestThreeRotationNoMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?--"
				+ "-ox", 
				
				  "-x-"
				+ "o-x"
				+ "x-o", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);
	}

	@Test
	public void matchingTestNoRotationMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?--"
				+ "-ox", 
				
				  "-x-"
				+ "x-o"
				+ "o-x", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);
	}

	@Test
	public void matchingTestOneRotationMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?--"
				+ "-ox", 
				
				  "-ox"
				+ "x--"
				+ "-xo", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);
	}

	@Test
	public void matchingTestThreeRotationMirror() {
		FieldPosition result = FieldMatcher.fieldsMatch(
				  "-xo"
				+ "?--"
				+ "-ox", 
				
				  "ox-"
				+ "--x"
				+ "xo-", 
				FieldMatcher.DEFAULT_MATCH);
		
		assertNotEquals("should match, but does not", result, FieldPosition.Empty);
	}

}
