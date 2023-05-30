import CalculatorApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import java.util.*; 

public class CalculatorClient {
  public static void main(String args[]) {
    try {
      ORB orb = ORB.init(args, null);

      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      String name = "Calculator";
      Calculator calculator = CalculatorHelper.narrow(ncRef.resolve_str(name));

      float a;
      float b;
      int ch;
      Scanner in = new Scanner(System.in); 
      System.out.println("1.Addition");
      System.out.println("2.Subtraction");
      System.out.println("3.Multiplication");
      System.out.println("4.Division");
      System.out.println("5.Exit");
      do
      {
      		System.out.print("Enter your choice : ");  
      		ch = in.nextInt();  
      		switch(ch)
      		{
                 	case 1:
					System.out.println("Enter first number : ");
					a = in.nextFloat();
					System.out.println("Enter second number : ");
					b = in.nextFloat();
					System.out.println("Addition: " + calculator.add(a, b));	
					break;
		 	case 2:
					System.out.println("Enter first number : ");
					a = in.nextFloat();
					System.out.println("Enter second number : ");
					b = in.nextFloat();
					System.out.println("Subtraction: " + calculator.subtract(a, b));
					break;
	         	case 3:
					System.out.println("Enter first number : ");
					a = in.nextFloat();
					System.out.println("Enter second number : ");
					b = in.nextFloat();
					System.out.println("Multiplication: " + calculator.multiply(a, b));
					break;
                 	case 4:
					System.out.println("Enter first number : ");
					a = in.nextFloat();
					System.out.println("Enter second number : ");
					b = in.nextFloat();
					System.out.println("Division: " + calculator.divide(a, b));
					break;
	         	case 5:
					break;
	 	 }
 

      
      }while(ch<=5);
      
      

    } catch(Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
  }
}

