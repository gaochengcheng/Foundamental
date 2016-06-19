package datastructure;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class TestHashTable {
	public static void main(String[] args) {
		Hashtable<String,ArrayList<String>> hashtable = new Hashtable<String,ArrayList<String>>();
		ArrayList<String> list1 = new ArrayList<String>();
		ArrayList<String> list2 = new ArrayList<String>();
		ArrayList<String> list3 = new ArrayList<String>();
		
		list1.add("a2");
		list1.add("a1");
		list2.add("b2");
		list2.add("b1");
		list3.add("c2");
		list3.add("c1");
		
		hashtable.put("a", list1);
		hashtable.put("b", list2);
		hashtable.put("c", list3);
		
		Set<String> keyset = hashtable.keySet();
		for(String key :keyset){
			System.out.println("key is : "+key);
			ArrayList<String> tempList = hashtable.get(key);
			Iterator<String> it = tempList.iterator();
			while(it.hasNext()){
				System.out.println(it.next());
			}
		}
		
	}
}
