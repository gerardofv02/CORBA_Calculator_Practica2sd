import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import CalApp.*;
import CalApp.calculatorPackage.DivisionByZero;
import CalApp.calculatorPackage.Negative; // excepción de numeros negativos importada
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import static java.lang.System.out;

public class CalculatorClient {

    static calculator calImpl; // creamos la variable calImpl de tipo calculator
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in)); 

    public static void main(String args[]) {
        double result=0.0,num1=0.0,num2=0.0; //creamos variables

        try {
            // creamos e inicializamos el orbe
            ORB orb = ORB.init(args, null);

            // obtenemos el contexto con la variable del NameService
            org.omg.CORBA.Object objRef = orb.resolve_initial_references("NameService");
            // creamos otra referencia con el contexto de antes
            NamingContextExt ncRef = NamingContextExtHelper.narrow(objRef);

            // obtenemos la referencia del objeto
            String name = "Calculator";
            calImpl = calculatorHelper.narrow(ncRef.resolve_str(name));

            //System.out.println(calculatorImpl);
            int ch=1;

       while(ch!=0)
       {
        System.out.println("1. Addition"); //opcion de suma
        System.out.println("2. Subtraction"); //opcion de resta
        System.out.println("3. Multiplication"); //opcion de multiplicacion
        System.out.println("4. Division"); //opcion de division
        System.out.println("5. Square Root"); // opcion de raiz cuadrada para que el cliente pueda escogerla en el siguiente switch
        System.out.println("6. Squared"); // opción de elevado al cuadrado para que el cliente pueda escogerla en el siguiente switch
        System.out.println("0. Exit"); //opciond e slaida

        BufferedReader in1=new BufferedReader(new InputStreamReader(System.in)); //creamos la variable inl para recibir lo que el cliente desee hacer

        System.out.println("enter your choice:");
        ch=Integer.parseInt(in1.readLine());
        
        if(ch==0)
          break;

        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));

        System.out.println("enter number1:");
        num1=Double.parseDouble(in.readLine());


        switch(ch)
        {
          case 1: // suma
                  System.out.println("enter number2:");
                   num2=Double.parseDouble(in.readLine());
              result=calImpl.addition(num1,num2);
              break;

          case 2: // resta
                  System.out.println("enter number2:");
                  num2=Double.parseDouble(in.readLine());
              result=calImpl.subtraction(num1,num2);
              break;

          case 3: //multiplicación
                  System.out.println("enter number2:");
                 num2=Double.parseDouble(in.readLine());
              result=calImpl.multiplication(num1,num2);
              break;

          case 4: // división con su excepción
                  System.out.println("enter number2:");
                  num2=Double.parseDouble(in.readLine());
              try {
              result=calImpl.division(num1,num2);
              } catch (DivisionByZero de) {
                out.println("Division by zero!!!");
               }
              break;
              case 5: // caso de la raiz cuadrada que hacemos un try catch para ver si es menor que 0 o 0 o mayor que 0 
              try{
                result=calImpl.square_root(num1); // si fuese mayor o 0 devolvemos el resultado
              } catch(Negative ne){
                  out.println("Cant be negative!"); // si es menor que 0 entramos en ekl catch  de la excepción negative y decimos que no puede ser negativa ya que esto sería si no un número imaginario
                }
                break;
              case 6: //caso de elevado al cuadrado
                result=calImpl.squared(num1); 
              break;
        }
            System.out.println("result is:"+result); // impresión del resultado

      }


            
    }         catch (Exception e) {
            System.out.println("ERROR : " + e);
            e.printStackTrace(System.out);
        }

    //static float getFloat(String number) throws Exception {
       // out.print(number + ": ");
       // return Float.parseFloat(br.readLine());
   // }
}
}