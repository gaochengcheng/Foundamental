package datastructure;

import java.util.ArrayList;
import java.util.Iterator;

public class TestArrayList {
	public static void main(String[] args) {
		ArrayList<String> list = new ArrayList<String>();
		for(int i = 0; i < 10; i++)
			list.add(i+"");
		System.out.println(list.size());
		Iterator<String> it = list.iterator();
		while(it.hasNext())
			System.out.println(it.next());
	}
}
