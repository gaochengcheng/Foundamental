package datastructure;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

public class ParseHashMap {
	public static void main(String[] args) {
		HashMap<String,Integer> map = new HashMap<String,Integer>();
		map.put("a", 1);
		map.put("b", 2);
		map.put("c", 3);
		//This way is more efficient, recommended.
		Iterator<Entry<String, Integer>> iter = map.entrySet().iterator();
		while(iter.hasNext()){
			Map.Entry<String, Integer> entry = iter.next();
			Object key = entry.getKey();
			Object value = entry.getValue();
			System.out.println( key + " " + value);
		}
		
		//This way is less efficient, not recommended.
		Iterator<String> iter2 = map.keySet().iterator();
		while(iter2.hasNext()){
			Object key = iter2.next();
			Object value = map.get(key);
			System.out.println( key + " " + value);
		}
		
		
	}

}
