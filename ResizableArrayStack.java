import java.util.EmptyStackException;

public class ResizableArrayStack {
    private T[] stack;    // Array of stack entries
	private int topIndex; // Index of top entry
   private boolean integrityOK = false;
	private static final int DEFAULT_CAPACITY = 50;
	private static final int MAX_CAPACITY = 10000;
  
   public ResizableArrayStack()
   {
      this(DEFAULT_CAPACITY);
   } // end default constructor
  
   public ResizableArrayStack(int initialCapacity)
   {
      integrityOK = false;
      checkCapacity(initialCapacity);
      
      // The cast is safe because the new array contains null entries
      @SuppressWarnings("unchecked")
      T[] tempStack = (T[])new Object[initialCapacity];
      stack = tempStack;
		topIndex = -1;
      integrityOK = true;
  } // end constructor

  // Doubles the size of the array bag.
   // Precondition: checkInitialization has been called.
	private void doubleCapacity()
	{
      int newLength = 2 * bag.length;
      checkCapacity(newLength);
      bag = Arrays.copyOf(bag, newLength);
	} // end doubleCapacity
   
   // Throws an exception if the client requests a capacity that is too large.
   private void checkCapacity(int capacity)
   {
      if (capacity > MAX_CAPACITY)
         throw new IllegalStateException("Attempt to create a bag whose capacity exceeds " +
                                         "allowed maximum of " + MAX_CAPACITY);
   } // end checkCapacity
   
   // Throws an exception if receiving object is not initialized.
   private void checkintegrity()
   {
      if (!integrityOK)
         throw new SecurityException ("ArrayBag object is corrupt.");
   } // end checkintegrity

   private void ensureCapacity()
   {
       if(topIndex >= stack.length-1)//if array is full, then double size
       {
           int newLength = 2*stack.length;
           checkCapacity(newLength);
           stack = Arrays.copyOf(stack,newLength);
       }//end if
   }//end ensureCapacity

   public void push(T newEntry)
   {
       checkIntegrity();
       ensureCapacity();
       stack[topIndex+1] = newEntry;
       topIndex++;
   }//end push

   public T pop()
   {
       checkIntegrity();
       if(isEmpty())
        throw new EmptyStackException();
       else
       {
           T top = stack[topIndex];
           stack[topIndex] = null;
           topIndex--;
           return top;
       }// end if
   }// end pop

   public T peek()
   {
       checkIntegrity();
       if(isEmpty())
        throw new EmptyStackException();
      else
        return stack[topIndex];
   }//end peek

   public boolean isEmpty()
   {
       return topIndex < 0;
   }//end isEmpty

   public void clear()
   {
       checkIntegrity();
       //remove references to the objects in the stack,
       //but do not deallocate the array
       while(topIndex > -1)
       {
           stack[topIndex]=null;
           topIndex--;
       }//end while
       //Assertion: topIndex is -1
   }//end clear
}
