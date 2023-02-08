import java.util.ArrayList;
//import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheckerUtility {

	public static boolean isValidLength(String password) throws LengthException{
		if (password.length() >= 6) {
			return true;
			}
		else {
			throw new LengthException("Password must be at least 6 characters long");
		}
	}
	
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		for (int i = 0; i < password.length(); i++){
			char c = password.charAt(i);
			if (Character.isUpperCase(c)){
				return true;
			}
		}
		throw new NoUpperAlphaException("Password must contain at least 1 uppercase alpha character");
	}
	
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException{
		for (int i = 0; i < password.length(); i++){
			char c = password.charAt(i);
			if (Character.isLowerCase(c)){
				return true;
			}
		}
		throw new NoLowerAlphaException("Password must contain at least 1 lowercase alpha character");
	}
	
	public static boolean hasDigit(String password)  throws NoDigitException{
		for (int i = 0; i < password.length(); i++){
			char c = password.charAt(i);
			if (Character.isDigit(c)){
				return true;
			}
		}
		throw new NoDigitException("Password must contain at least 1 digit");
	}
	
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException{
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		boolean hasSpecialChar = !matcher.matches();
		if (!hasSpecialChar) {
			throw new NoSpecialCharacterException("Password must contain at least 1 special character");
		}
		return hasSpecialChar;
	}
	
	public static boolean NoSameCharInSequence(String password) throws InvalidSequenceException{
		for (int i = 0; i < password.length() - 2; i++){
			char c = password.charAt(i);
			if (c == password.charAt(i+1) && c == password.charAt(i + 2)){
				throw new InvalidSequenceException ("Password cannot contain more than 2 of the same character in sequence ");
			}
		}
		return false;
	}
	
	public static boolean isWeakPassword(String password) throws WeakPasswordException{
	    if (password.length() >= 6 && password.length() <= 9) {
	        throw new WeakPasswordException("Password is weak as length is between 6 and 9 ");
	    }
	    return false;
	}
	
	public static boolean hasBetweenSixAndNineChars (String password){
		return password.length() >= 6 && password.length() <= 9;
		}
	
	
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException{
		if (!password.equals(passwordConfirm)){
			throw new UnmatchedException("Passwords do not match");
		}
	}
	
	public static boolean comparePasswordsWithReturn (String password, String passwordConfirm){
		return password.equals(passwordConfirm);
	}

	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException, NoDigitException, NoSpecialCharacterException, InvalidSequenceException {
		  if (password.length() < 6){
		    throw new LengthException("Password length must be at least 6 characters");
		  }
		  if (!hasUpperAlpha(password)){
		    throw new NoUpperAlphaException("Password must contain at least 1 uppercase alphabetic character");
		  }
		  if (!hasLowerAlpha(password)){
		    throw new NoLowerAlphaException("Password must contain at least 1 lowercase alphabetic character");
		  }
		  if (!hasDigit(password)){
		    throw new NoDigitException("Password must contain at least 1 numeric character");
		  }
		  if (!hasSpecialChar(password)){
		    throw new NoSpecialCharacterException("Password must contain at least 1 special character");
		  }
		  if (NoSameCharInSequence(password)){
		    throw new InvalidSequenceException("Password must not contain more than 2 of the same character in sequence");
		  }
		  return true;
		}

	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
	    ArrayList<String> invalidPasswords = new ArrayList<>();
	    for (String password : passwords) {
	        try {
	            if (!isValidPassword(password)) 
	            { invalidPasswords.add(password + " " + "INVALID");}
	        } catch (LengthException e) {
	            invalidPasswords.add(password + " " + e.getMessage());
	        } catch (NoUpperAlphaException e) {
	            invalidPasswords.add(password + " " + e.getMessage());
	        } catch (NoLowerAlphaException e) {
	            invalidPasswords.add(password + " " + e.getMessage());
	        } catch (NoDigitException e) {
	            invalidPasswords.add(password + " " + e.getMessage());
	        } catch (NoSpecialCharacterException e) {
	            invalidPasswords.add(password + " " + e.getMessage());
	        } catch (InvalidSequenceException e) {
	            invalidPasswords.add(password + " " + e.getMessage());
	        }
	    }
	    return invalidPasswords;
	}
	static class LengthException extends Exception{
		LengthException(String message) 
		{super(message);}
	}
	static class NoUpperAlphaException extends Exception{
		NoUpperAlphaException(String message)
		{super(message);}
	}
	static class NoLowerAlphaException extends Exception{
		NoLowerAlphaException(String message)
		{super(message);}
	}
	static class NoDigitException extends Exception{
		NoDigitException(String message)
		{super(message);}
	}
	static class NoSpecialCharacterException extends Exception{
	    NoSpecialCharacterException(String message) 
	    {super(message);}
	}
	static class InvalidSequenceException extends Exception{
		InvalidSequenceException(String message)
		{super(message);}
	}
	static class WeakPasswordException extends Exception{
		WeakPasswordException (String message)
		{super(message);}
	}
	public static class UnmatchedException extends Exception{
		public UnmatchedException(String message)
		{super(message);}
	}	
	
}
