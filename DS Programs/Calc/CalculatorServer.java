import CalculatorApp.*;
import org.omg.CORBA.*;
import org.omg.CosNaming.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;

public class CalculatorServer {
  public static void main(String args[]) {
    try {
      ORB orb = ORB.init(args, null);
      POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
      rootpoa.the_POAManager().activate();

      CalculatorImpl calculatorImpl = new CalculatorImpl();

      org.omg.CORBA.Object ref = rootpoa.servant_to_reference(calculatorImpl);
      Calculator href = CalculatorHelper.narrow(ref);

      org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
      NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

      String name = "Calculator";
      NameComponent path[] = ncRef.to_name(name);
      ncRef.rebind(path, href);

      System.out.println("Calculator Server ready...");

      orb.run();
    } catch(Exception e) {
      System.err.println("ERROR: " + e);
      e.printStackTrace(System.out);
    }
  }
}

class CalculatorImpl extends CalculatorPOA {
  private ORB orb;

  public void setORB(ORB orb_val) {
    orb = orb_val;
  }

  public float add(float num1, float num2) {
    return num1 + num2;
  }

  public float subtract(float num1, float num2) {
    return num1 - num2;
  }

  public float multiply(float num1, float num2) {
    return num1 * num2;
  }

  public float divide(float num1, float num2) {
    return num1 / num2;
  }

  public void shutdown() {
    orb.shutdown(false);
  }
}

