import java.security.DrbgParameters.NextBytes;
import java.util.*;
import java.util.EmptyStackException;

import javax.swing.DefaultDesktopManager;
/**
    A class of stacks whose entries are stored in a chain of nodes.
    @author Frank M. Carrano and Timothy M. Henry
    @version 5.0
*/
public final class LinkedStack<T> implements StackInterface<T>
{
	private Node topNode; // References the first node in the chain
  
   public LinkedStack()
   {
      topNode = null;
   } // end default constructor
  
//  < Implementations of the stack operations go here. >
//  . . .

   public void push(T newEntry)
   {
      Node newNode = new Node(newEntry, topNode);
      topNode = newNode;
      //topNode = new Node(newEntry, topNode); // Alternate code
   } // end push

   public T pop()
   {
      T top = peek();  // Might throw EmptyStackException
      // Assertion: topNode != null
      topNode = topNode.getNextNode();
      return top;
   } // end pop

   public T peek()
   {
      if (isEmpty())
         throw new EmptyStackException();
      else
         return topNode.getData();
   } // end peek

   public boolean isEmpty()
   {
   return topNode == null;
   } // end isEmpty

   public void clear()
   {
      topNode = null;
   } // end clear

   public String ConvertToPostfix(String infix)
   {
      LinkedStack operatorStack = new LinkedStack<>(); 
      String postfix = new String("");

      int i = 0;
      while(i < infix.length())
      {
         char nextChar = infix.charAt(i);
         //System.out.println(nextChar);

         if(Character.isLetter(nextChar))
         {
            postfix = postfix + nextChar;
         }
         else if(nextChar == '^')
         {
            operatorStack.push(nextChar);
         }
         else if(CheckIfOperator(nextChar))
         {
            if(!operatorStack.isEmpty())
            {
               String s = operatorStack.peek().toString();
               char peekChar = s.charAt(0);
   
               if(CheckPrecedence(nextChar) <= CheckPrecedence(peekChar))
               {
                  postfix = postfix + s;
                  operatorStack.pop();
               }
            }

            operatorStack.push(nextChar);
         }
         else if(nextChar == '(')
         {
            operatorStack.push(nextChar);
         }
         else if(nextChar == ')')
         {
            String s = operatorStack.pop().toString();
            char topOperator = s.charAt(0);

            while(topOperator != '(')
            {
               postfix = postfix + topOperator;
               
               s = operatorStack.pop().toString();
               topOperator = s.charAt(0);
            }
         }
         i++;
      }

      while(!operatorStack.isEmpty())
      {
         String s = operatorStack.pop().toString();
         char topOperator = s.charAt(0);

         postfix = postfix + topOperator;
      }

      return postfix;
   }

   private boolean CheckIfOperator(char a)
   {
      if(a == '*' || a == '/' || a == '+' || a == '-') return true;
      else return false;
   }

   private int CheckPrecedence(char a)
   {
      if(a == '^') return 3;
      else if(a == '*' || a == '/') return 2;
      else if(a == '+' || a == '-') return 1;
      else return 0;
   }


	private class Node
	{
      private T    data; // Entry in stack
      private Node next; // Link to next node
      
      private Node(T dataPortion)
      {
         this(dataPortion, null);
      } // end constructor
      
      private Node(T dataPortion, Node linkPortion)
      {
         data = dataPortion;
         next = linkPortion;
      } // end constructor
      
      private T getData()
      {
         return data;
      } // end getData
      
      private void setData(T newData)
      {
         data = newData;
      } // end setData
      
      private Node getNextNode()
      {
         return next;
      } // end getNextNode
      
      private void setNextNode(Node nextNode)
      {
         next = nextNode;
      } // end setNextNode
	} // end Node

} // end LinkedStack
