package datastructure;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class MyHashTable {
	public static void main(String[] args) {
		//Create Hashtable
		Hashtable<String,Integer> numbers 
			= new Hashtable<String,Integer>();
		numbers.put("1111", 1);
		numbers.put("2222", 2);
		numbers.put("3333", 3);
		numbers.put("4444", 4);
		numbers.put("5555", 5);
		
		//Traverse Hashtable
		Enumeration<String> element = numbers.keys();
		while(element.hasMoreElements()){
			System.out.println(element.nextElement());
		}
		
		Set<String> keys = numbers.keySet();
		for(String key: keys){
			System.out.println(key+ " : "+numbers.get(key));
		}
	}
}
