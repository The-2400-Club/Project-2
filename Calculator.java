public class Calculator
{
    public static void main(String[] args)
    {
        LinkedStack lstack = new LinkedStack<>();

        System.out.println(lstack.ConvertToPostfix("a*b/(c-a)+d*e"));
    }
}