
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


/**
 * STUDENT tests for the methods of PasswordChecker
 * @author 
 * Phuc Bui 
 *
 */
public class PasswordCheckerTest_STUDENT extends PasswordCheckerUtility {
	private ArrayList<String>passwords;
	
	@Before
	public void setUp() throws Exception {
		passwords = new ArrayList<>();
		passwords.add("Password1#");
		passwords.add("p@ssword2");
	}

	@After
	public void tearDown() throws Exception {
		passwords = null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("abcde1#"));
			PasswordCheckerUtility.isValidPassword("abc1#");
			fail("Should have thrown an exception");
		} catch (Exception e) {
			assertEquals("The password must be at least 6 characters long", e.getMessage());
		}	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("abcde1#"));
			PasswordCheckerUtility.isValidPassword("abcde1#");
			fail("Should have thrown an exception");
		} catch (Exception e) {
			assertEquals("The password must contain at least one uppercase alphabetic character", e.getMessage());
		}

	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("ABCDE1#"));
			PasswordCheckerUtility.isValidPassword("ABCDE1#");
			fail("Should have thrown an exception");
		} catch (Exception e) {
			assertEquals("The password must contain at least one lowercase alphabetic character", e.getMessage());
		}
	}
	/**
	 * Test if the password contains 6 to 9 characters
	 * This test should throw a WeakPasswordException for second case
	 */
	@Test
	public void testIsWeakPassword()
	{
		try {
		    PasswordCheckerUtility.isWeakPassword("abc");
		        fail("Should have thrown WeakPasswordException");
		    } catch (WeakPasswordException e) {
		        // success
		    }

		try {
		        PasswordCheckerUtility.isWeakPassword("abcdefghij");
		        // success, no exception thrown
		    } catch (Exception e) {
		        fail("Should not have thrown exception");
		    }	
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		try {
			PasswordCheckerUtility.isValidPassword("abcdefgabc");
			PasswordCheckerUtility.isValidPassword("abccdefg");
			fail("An InvalidSequenceException should have been thrown");
		} catch (InvalidSequenceException e) {
			// success
		} catch (Exception e) {
			fail("Unexpected exception: " + e.getMessage());
		}
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		try {
			assertTrue(PasswordCheckerUtility.hasDigit("password"));
			} catch (NoDigitException e) {
			fail("Password must contain at least one digit");
			}
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		try {
			assertTrue(PasswordCheckerUtility.isValidPassword("abcDE1#"));
		} catch (Exception e) {
			fail("Valid password threw an exception: " + e.getMessage());
		}
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {
		ArrayList<String> invalidPasswords = PasswordCheckerUtility.getInvalidPasswords(passwords);
		assertEquals(invalidPasswords.size(), 2);
		assertEquals(invalidPasswords.get(0), "Password1#");
		assertEquals(invalidPasswords.get(1), "p@ssword2");
	}
	
}
