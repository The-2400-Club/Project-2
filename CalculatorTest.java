import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

class CalculatorTest {
	
	StackInterface<Integer> stack = new LinkedStack<>();
	StackInterface<Integer> stack2 = new ResizeableArrayStack<>();
	
    public void StackTest() {}
    public ExpectedException exception = ExpectedException.none();
    
    @Test //Test 1
    public void testIsEmptyTrue() 
    {
        assertTrue(stack.isEmpty());
        assertTrue(stack2.isEmpty());
    }
    
    @Test //Test 2
    public void testIsEmptyFalse() 
    {
        stack.push(1);
        stack2.push(1);
        assertFalse(stack.isEmpty());
        assertFalse(stack2.isEmpty());
    }
    
    @Test //Test 3
    public void testLinkedStackPop() 
    {	
        stack.push(8);
        stack.push(9);
        assertEquals( new Integer(9), stack.pop());
    }
    
    @Test //Test 4
    public void testLinkedStackPush() 
    {
    	stack.push(12);
        stack.push(-10);
        assertEquals(stack.peek(), new Integer(-10));
    }

    @Test //Test 5
    public void testLinkedStackPeek() 
    {
    	stack.push(6);
        stack.push(7);
        assertEquals( new Integer(7), stack.peek() );
    }
    
    @Test //Test 6
    public void testResizeableStackPop()
    {	
    	stack2.push(105);
        stack2.push(3);
        assertEquals( new Integer(3), stack2.pop());
    }
    
    @Test //Test 7
    public void testResizeableStackPush()
    {
    	stack2.push(25);
        stack2.push(205);
        assertEquals(stack2.peek(), new Integer(205));
    }

    @Test //Test 8
    public void testResizeableStackPeek()
    {
    	stack2.push(3);
        stack2.push(4);
        assertEquals( new Integer(4), stack2.peek() );
    }
    //tests
   
}