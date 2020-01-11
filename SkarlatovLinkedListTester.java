package HW2;

import java.util.Iterator;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class SkarlatovLinkedListTester {

	public static void main(String[] args)
	{


		MyDoublyLinkedList<String> list = new MyDoublyLinkedList<String>();
		System.out.println(list.toString());
		list.add(0, "Able");
		System.out.println(list.toString());
		list.add(1, "Baker");
		System.out.println(list.toString());
		list.addFirst("Aaron");
		System.out.println(list.toString());
		System.out.println(list.contains("Baker")); // true
		System.out.println(list.contains("Smith")); // false
		System.out.println(list.contains(null)); // false
		list.add(2, null);
		System.out.println(list.contains(null)); // true
		System.out.println(list.toString());
		System.out.println("Display elements using for-each, which uses an iterator:");
		for (String s : list)
			System.out.println(s);
		System.out.println("Display elements using an iterator directly:");
		for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String s = iter.next();
			System.out.println(s);
			// iter.remove()
		}
		System.out.println(list.toString());
















	}
}
