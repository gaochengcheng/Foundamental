package basic;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class TestMap {
	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		map.put("d", 4);
		Iterator itr = map.entrySet().iterator();
		while(itr.hasNext()){
			Map.Entry enter = (Map.Entry)itr.next();
			System.out.println(enter);
		}
		//output
		/**
		 * a=1
		   b=2
           c=3
           d=4
		 */
	}
}	
