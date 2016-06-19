package stringprocess;

public class TestIterator {
	public static void main(String[] args) {
//		String[] array = {"a","b","c","d","e"};
//		for(String temp:array)
//			System.out.println(temp);
		
		
		//it is wrong
		String[] array = null;
		array[0] = "a";
		array[1] = "b";
		for(String temp:array)
			System.out.println(temp);
	}
}
