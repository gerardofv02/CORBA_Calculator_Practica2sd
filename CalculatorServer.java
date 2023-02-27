import CalApp.*;
import CalApp.calculatorPackage.DivisionByZero;
import CalApp.calculatorPackage.Negative;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;
import org.omg.PortableServer.POA;
import java.util.Properties;
import java.lang.Math;

class calculatorImpl extends calculatorPOA {

   
    public double addition(double a, double b) { //suma
        return a + b;
    }

  
    public double division(double a, double b) throws DivisionByZero { // división con su excepción
        if (b == 0) {
            throw new CalApp.calculatorPackage.DivisionByZero();
        } 
        else {
            return a / b;
        }
    }

    public double multiplication(double a, double b) { //multiplicación
        return a * b;
    }

    public double subtraction(double a, double b) { // resta
        return a - b;
    }
    public double square_root(double a) throws Negative {
        if(a < 0){
            throw new CalApp.calculatorPackage.Negative(); // si es menor que 0 lanzamos excepción
        }
        else{
        return Math.sqrt(a); // si es 0 o mayor devolvemos la raiz cuadrada del número
        }
    }  // funcion de square root creada con su propia excepción añadida anteriormente en idl creada en el servidor
    public double squared(double a) { // al cuadrado
        return a * a;
    }// funcion de elevado al cuadrado creada en el servidor
    private ORB orb;

    public void setORB(ORB orb_val) { 
        orb = orb_val;
    }
    public void shutdown() {
    orb.shutdown(false);
  }
}

public class CalculatorServer {

    public static void main(String args[]) {
        try {
            // creamos e inicializamos el orbe 
            ORB orb = ORB.init(args, null);

            // obtenemos la ruta del POA a través de la función narrow y lo activamos
            POA rootpoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootpoa.the_POAManager().activate();

            // registramos el servant con el orb para poder tener la referencia más adelante
            calculatorImpl myImpl = new calculatorImpl();
            myImpl.setORB(orb);

            // obtenemos la referencia
            org.omg.CORBA.Object ref = rootpoa.servant_to_reference(myImpl);
            calculator href = calculatorHelper.narrow(ref);

            // get the root naming context
            // NameService invokes the name service
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // Use NamingContextExt which is part of the Interoperable
            // Naming Service (INS) specification.
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // bind the Object Reference in Naming
            String name = "Calculator";
            NameComponent path[] = ncRef.to_name(name);
            ncRef.rebind(path, href);

            System.out.println("Ready..");

            // ejecutamos el servidor para que esté disponible para las peticiones de los clientes
            orb.run();
        } catch (Exception e) {
            System.err.println("ERROR: " + e);
            e.printStackTrace(System.out);
        }

        System.out.println("Exiting ...");

    }
}