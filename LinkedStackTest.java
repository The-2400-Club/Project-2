public class LinkedStackTest {
	 @SuppressWarnings({ "removal", "unchecked" })
	public static void main(String [] args)
	   {  
	      LinkedStack test = new LinkedStack();
	      // add a few elements
	      test.push(new Integer(1));
	      test.push(new Integer(2));
	      test.push(new Integer(3));
	      test.push(new Integer(4));


	      // checks pop and peek
	      while (!test.isEmpty())
	      {
	         System.out.println("Top element: " + test.peek());
	         System.out.println("Removed: " + test.pop());
	      }
	   } 
	 
}
