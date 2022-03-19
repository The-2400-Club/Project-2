public class Calculator
{
    public static void main(String[] args)
    {
        //Test ConvertToPostfix
        System.out.println(ConvertToPostfix("a*b/(c-a)+d*e"));

        //Test evaluatePostfix
        System.out.println(evaluatePostfix("23*42-/56*+"));
    }


    private static boolean CheckIfOperator(char a)
    {
       if(a == '*' || a == '/' || a == '+' || a == '-' || a == '^') return true;
       else return false;
    }

    private static int CheckPrecedence(char a)
    {
       if(a == '^') return 3;
       else if(a == '*' || a == '/') return 2;
       else if(a == '+' || a == '-') return 1;
       else return 0;
    }

    private static String ConvertToPostfix(String infix)
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

    private static int evaluatePostfix(String postfix)
    {
       ResizeableArrayStack valueStack = new ResizeableArrayStack<>();
       
       int i = 0;
       while(i < postfix.length())
       {
          char nextChar = postfix.charAt(i);
 
          if(Character.isDigit(nextChar))
          {
              valueStack.push(nextChar);
          }
          else if(CheckIfOperator(nextChar))
          {
              String two = valueStack.pop().toString();
              String one = valueStack.pop().toString();

              int operandTwo = Integer.parseInt(two);
              int operandOne = Integer.parseInt(one);

              int result = 0;
              switch(nextChar)
              {
                case '+':
                    result = operandOne + operandTwo;
                    break;
                case '-':
                    result = operandOne - operandTwo;
                    break;
                case '*':
                    result = operandOne * operandTwo;
                    break;
                case '/':
                    result = operandOne / operandTwo;
                    break;
                case '^':
                    result = (int)Math.pow(operandOne, operandTwo);
                    break;
                default:
                    result = 0;
                    break;
              }

              valueStack.push(result);
          }

          i++;
       }

       String s = valueStack.peek().toString();
       int value = Integer.parseInt(s);
       
       return value;
    }

}