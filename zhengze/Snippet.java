package zhengze;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Snippet {
	public static void main(String[] args) {
		String str = "123.123";
		if (isNumeric(str))
			System.out.println("yes");
		else
			System.out.println("no");
	}

	public static boolean isNumeric(String str) {
		if(str.matches("\\d+(.\\d+)?")){
			return true;
		}
		return false;
		
		// Another method.
//		Pattern pattern = Pattern.compile("\\d+(.\\d+)?");
//		Matcher isNum = pattern.matcher(str.charAt(0) + "");
//		
//		if (!isNum.matches()) {
//			return false;
//		}
//		return true;
	}
}
