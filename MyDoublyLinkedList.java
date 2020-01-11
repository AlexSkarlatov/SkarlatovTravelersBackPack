package HW2;
/*
 * Name: Alexander Skarlatov
 * Date: 1/11/18
 * Assignment: HW 1 : implement a doubly linked list data structure
 * Class: CSC 364
 * Description:
 *
 */



import java.util.Iterator;
//import IndexOutOfBoundsException;
import java.util.ListIterator;
import java.util.NoSuchElementException;

public class MyDoublyLinkedList<E> extends MyAbstractSequentialList<E> implements Cloneable
{
	//data fields
	//declare dummy head node
	Node<E> head = new Node<E>(null); //dummy head node data field
	int size = 0;					 //size data field
	//constructors
	public  MyDoublyLinkedList(){
		head.next = head;
		head.previous = head;
	}
	public MyDoublyLinkedList(E[] elts){
		super(elts);
	}
	//static node inner class
	private static class Node<E>{
		//data fields
		E elt = null;
		Node<E> next = null;
		Node<E> previous = null;
		//constructor
		public Node(E element){
			this.elt = element;
		}
		public void setElement(E e)
		{
			this.elt = e;
		}
	}

	//methods below
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
		{
			return true;//reference o and reference this(the caller) both point to the same memory location
		}
		else if(!(o instanceof MyDoublyLinkedList<?>))
		{
			boolean ab = !(o instanceof MyDoublyLinkedList<?>);
			System.out.println(!(o instanceof MyDoublyLinkedList<?>)+ " zzzz"+ab );
			//if o is NOT of type myDoubly linked list, conclusion is NOT EQUAL TO
			//then return false
			return false;
		}
		MyDoublyLinkedList<E> comparingThis = (MyDoublyLinkedList<E>)o;
		if(comparingThis.size != this.size)
		{
			return false;// size of both objects is not same, can conclude not Equal thus return false
		}
		else{
			//get two iterators
			Iterator<E> selfObjIter = this.iterator();
			Iterator<E> otherObjIter = comparingThis.iterator();
			//next step is to iterate over all of both lists and return false
						//in the case that both x and y not equal to eachother
			//if x != y ) return false
			//E self;
			//E otherItem;
			while(selfObjIter.hasNext())
			{
				//does this code test the first one
				//shoudl i declare these guys outside of the loop,
				//i probalby shoudo
				E self = selfObjIter.next();
				E otherItem = otherObjIter.next();
				//***mental note*** remember to put the contains if code here for accouning
				//for null elts
				//if(e == null ? current.elt == null :  e.equals(current.elt))
				//if(otherItem == null ? self == null : otherItem.equal(self))
				//if(self != otherItem)
				if(otherItem == null ? self != null : !(otherItem.equals(self)))
				{
					return false;
				}
			}//exiting while
			/*
			 * for (Iterator<String> iter = list.iterator(); iter.hasNext();) {
			String s = iter.next();
			System.out.println(s);
			// iter.remove()
			 */
		}//exiting else
		return true;
	}//end of equals method
	@Override
	public Object clone(){
		try
		{
			MyDoublyLinkedList<E> theClone = (MyDoublyLinkedList<E>) super.clone();
			theClone.head = new Node<E>(null);
			theClone.head.next = theClone.head;
			theClone.head.previous = theClone.head;//shortent this varname to something like nDH
			theClone.size = 0;
			Node<E> current = this.head.next;
			for(int i = 0 ; i < size ; i++ )
			{
				theClone.add(current.elt);
				current = current.next;
			}
			return theClone;//needs to be paramterized
		}
		catch (CloneNotSupportedException ex)
		{
			System.out.println("something went wrong");
			return null;
		}
	}


	// make this more efficient by spliting it down themiddle.
	//this works
	public Node<E> getNode(int index)
	{
		if(index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("the size is: " + size);
		}
		Node<E> current = head;
		for (int i = -1 ; i < index ; i++ )
		{
			current = current.next;
		}
		return current;
	}

	//this works
	 public void add(E e) {
		    add(size, e);
		  }
	@Override
	public void add(int index, E e) {
		if( index < 0 || index > size)
		{
			throw  new IndexOutOfBoundsException("something went wrong: size is: " + size);
		}
		Node<E> previous = head;
		Node<E>	current = head;
		Node<E> ins = new Node<E>(e);
		current = getNode(index);
		previous = current.previous;
		//code to add the node
		previous.next = ins;
		current.previous = ins;
		ins.next = current;
		ins.previous = previous;
		size++;
	}

	//this works
	@Override
	public String toString()
	{
		StringBuilder result = new StringBuilder("[");
		Node<E> current = head.next;

		for(int i = -1 ; i < size-1 ; i++)
		{
			result.append(current.elt);
			current = current.next;
			if(current != head)
			{
				result.append(", ");
			}
		}
		result.append("]");
		return result.toString();
	}

	//this works
	@Override
	public void clear() {
		//finished!
		head.next = head;
		head.previous = head;
		size = 0;
	}

	//this works
	@Override
	public boolean contains(E e)
	{
		Node<E> current = head.next;
		while (current != head)
		{
			if(e == null ? current.elt == null :  e.equals(current.elt)) //current.elt == e)
			{
				return true;
			}
			current = current.next;
		}
		return false;
	}

	//this works
	@Override
	public E get(int index)
	{
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("index too small is now below 0");
		//this one appears to be working flawlessly
		//  Auto-generated method stub
		Node<E> current = head;
		if(index < size/2)
		{
			for(int i = -1; i < index; i++)
			{
				current = current.next;
			}
		}
		else{
			for(int i = size; i > index ; i--)
			{
				current = current.previous;
			}
		}
		return current.elt;
	}

	//tests successful works great
	@Override
	public int indexOf(E o) {
		//  Auto-generated method stub
		Node<E> current = head.next;
		for(int i = 0; i < size; i++)
		{
			E e = current.elt;
			if( o == null ?   e == null   : o.equals(e))
			{
				return i;
			}
			current = current.next;
		}
		return -1;
	}

	//correct here
	@Override
	public int lastIndexOf(E o) {
		//  Auto-generated method stub
		Node<E> current = head.previous;
		for(int i = size -1; i >= 0 ; i--)
		{
			//HERE IS PROBLEM
			E e = current.elt;
			if(o == null ? e == null : o.equals(e) )
			{
				return i;
			}
			current = current.previous;
		}
		return -1;
	}

	//this works
	@Override
	public E remove(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("int is too high or too low cannot remove");
		if(index == 0)
			return removeFirst();
		if(index == size-1)
			return removeLast();
		Node<E> current = getNode(index);
		Node<E> previous = current.previous;
		Node<E> temp = current;
		//add code to remove here
		temp = current;//return this for later
		current = current.next;//move current forward
		previous.next = current;//link previous to current
		current.previous = previous;//link current to previous thus eliminating the one that we started with
		size--;

		return temp.elt;

	}

	//this works
	@Override
	public Object set(int index, E e) {
		if(index < 0 || index >= size)
			throw new IndexOutOfBoundsException("provided index is either too high or too low, the size is" + size);

		Node<E> current = getNode(index);
		Node<E> temp = new Node<>(current.elt);
		current.elt = e;//assigns new value to the list node
		return temp.elt;//returns what was there previously
		//specifically it return the element and not the node itself
	}

	//this works
	@Override
	public E getFirst() {
		//
		Node<E> current = head.next;
		return current.elt;

	}

	//this works
	@Override
	public E getLast() {
		//
		Node<E> current = head.previous;
		return current.elt;
		//return head.previous.elt;//alternatively
	}

	//this works
	@Override
	public void addFirst(E e)
	{
		add(0,e);
	}

	public int size(){
		return this.size;
	}
	//this works
	@Override
	public void addLast(E e)
	{
		// Auto-generated method stub
		add( size , e );
	}

	//this works
	@Override
	public E removeFirst() {
		Node<E> current = head.next;
		if(current == head)
		{
			throw new NoSuchElementException("this list is already emptyr");
		}
		//return remove(0);
		Node<E> temp = head.next;
		//code to remove the first element is inserted here...
		current = current.next;//advance from index 0 to index 1
		head.next = current;//link head to index 1
		current.previous = head;//link index 1 to head thus making it the new index 0
		temp.previous = null;
		temp.next = null;
		size--;
		return temp.elt;
	}

	//works fine
	@Override
	public E removeLast() {

		Node<E> current = head.previous;
		Node<E> temp ;
		if( current == head || size == 0)
		{
			throw new NoSuchElementException("thius list is already empty you cant remove3 anything from an empty list");
		}
		//code to remove the last element is inserted here...
		temp = current;
		current = current.previous;
		head.previous = current;
		current.next = head;
		size--;
		return temp.elt;
		//return remove(size-1);//alternatively
	}

	//this works
	@Override
	public ListIterator<E> listIterator(int index)
	{
		return new MyDoublyLinkedListIterator(index);
	}

	public static enum MyState {CANNOT_REMOVE, CAN_REMOVE_CURRENT,CAN_REMOVE_PREVIOUS};
	private class MyDoublyLinkedListIterator implements ListIterator<E>{
		//data fields
		private Node<E> nextNode;//node that holds next element in the iteration
		private int nextIndex;//index of nextNode
		MyState iterState ;
		//constructors
		//default
		public MyDoublyLinkedListIterator()
		{
			iterState = MyState.CANNOT_REMOVE;
			nextNode = head.next;
		}
		private MyDoublyLinkedListIterator(int index)
		{
			if(index < 0 || index > size)
			{
				throw new IndexOutOfBoundsException("iterator index out of bounds");
			}
			iterState = MyState.CANNOT_REMOVE;
			nextNode = getNode(index);
			nextIndex = index ;
		}
		//methods
		@Override
		public void add(E o) {

			Node<E> ins = new Node<>(o);
			Node<E> prev = nextNode.previous;
			nextNode.previous = ins;
			prev.next = ins;
			ins.next = nextNode;
			ins.previous = prev;
			size++;
			iterState = MyState.CANNOT_REMOVE;
		}
		//////////////////////////////////////////////////////////////
		@Override
		public boolean hasNext()
		{
			return (nextIndex < size );
		}

		@Override
		public boolean hasPrevious()
		{
			return ( nextIndex > 0);
		}

////////////////////////////////////////////////////////////////////////////////////////
		@Override
		public E next() {
			//change the enumerator state iterstate to can remove next
			//change iterSTate here
			iterState = MyState.CAN_REMOVE_PREVIOUS;
			if(nextIndex >= size)
			{
				throw new NoSuchElementException("you are at the end of the list");
			}
			Node<E> temp = nextNode;//return elt of nextNode
			nextNode = nextNode.next;//advance nextNode right
			nextIndex++;//advance index
			return temp.elt;
		}
		/////////////////////////////////////////////////////

		@Override
		public int nextIndex() {
			return nextIndex ;
		}

		@Override
		public E previous() {
			if(!hasPrevious())
			{
				throw new NoSuchElementException("you are at the beginning of the list");
			}
			iterState = MyState.CAN_REMOVE_CURRENT;//change iterState
			Node<E> temp = nextNode.previous;//return temp.elt
			nextNode = nextNode.previous;
			nextIndex--;//sub the index
			return temp.elt;
		}

		//this works
		@Override
		public int previousIndex() {
			return nextIndex - 1;
		}


		@Override
		public void remove() {
			switch(iterState){
			case CAN_REMOVE_CURRENT:
				Node<E> temp = nextNode.previous;
				nextNode = nextNode.next;//inc one forward
				//link the two nodes above
				temp.next = nextNode;
				nextNode.previous = temp;
				//CHANGE ITERSTATE TO CANNOT_REMOVE
				iterState = MyState.CANNOT_REMOVE;
				//adjust value of size
				size--;
				//nextIndex--;//not in this one
				break;
				//can remove previous is consequence of calling next()
			case CAN_REMOVE_PREVIOUS:
				Node<E> temp2 = nextNode.previous;
				temp2 = temp2.previous; //for temp i will have to jump back twice
				//nextNode stays the same
				//linke these two together
				temp2.next = nextNode;
				nextNode.previous = temp2;
				//change iterstate to cannot_remove
				iterState = MyState.CANNOT_REMOVE;
				size--;//adjust value of size
				nextIndex--;
				break;
			case CANNOT_REMOVE:
				//THROW STATE EXCEPTION HERE
				throw new IllegalStateException("words");
				//break;
			}
		}

		@Override
		public void set(E e) {
			switch(iterState){
			case CANNOT_REMOVE:
				throw new IllegalStateException("hello");

				//break;
			case CAN_REMOVE_CURRENT:
				//previous has been called
				Node<E> temp = new Node<>(previous());
				nextNode.elt = e;
				break;
			case CAN_REMOVE_PREVIOUS:
				//next() has been called
				nextNode.previous.elt = e;
				break;
			default:
				break;

			}
		}
	}
}
