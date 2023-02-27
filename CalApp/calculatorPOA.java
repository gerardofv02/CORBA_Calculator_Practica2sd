package CalApp;


/**
* CalApp/calculatorPOA.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from calculator.idl
* lunes 27 de febrero de 2023 19H40' CET
*/

public abstract class calculatorPOA extends org.omg.PortableServer.Servant
 implements CalApp.calculatorOperations, org.omg.CORBA.portable.InvokeHandler
{

  // Constructors

  private static java.util.Hashtable _methods = new java.util.Hashtable ();
  static
  {
    _methods.put ("addition", new java.lang.Integer (0));
    _methods.put ("subtraction", new java.lang.Integer (1));
    _methods.put ("multiplication", new java.lang.Integer (2));
    _methods.put ("division", new java.lang.Integer (3));
    _methods.put ("square_root", new java.lang.Integer (4));
    _methods.put ("squared", new java.lang.Integer (5));
    _methods.put ("shutdown", new java.lang.Integer (6));
  }

  public org.omg.CORBA.portable.OutputStream _invoke (String $method,
                                org.omg.CORBA.portable.InputStream in,
                                org.omg.CORBA.portable.ResponseHandler $rh)
  {
    org.omg.CORBA.portable.OutputStream out = null;
    java.lang.Integer __method = (java.lang.Integer)_methods.get ($method);
    if (__method == null)
      throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);

    switch (__method.intValue ())
    {
       case 0:  // CalApp/calculator/addition
       {
         double x = in.read_double ();
         double y = in.read_double ();
         double $result = (double)0;
         $result = this.addition (x, y);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 1:  // CalApp/calculator/subtraction
       {
         double x = in.read_double ();
         double y = in.read_double ();
         double $result = (double)0;
         $result = this.subtraction (x, y);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 2:  // CalApp/calculator/multiplication
       {
         double x = in.read_double ();
         double y = in.read_double ();
         double $result = (double)0;
         $result = this.multiplication (x, y);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 3:  // CalApp/calculator/division
       {
         try {
           double x = in.read_double ();
           double y = in.read_double ();
           double $result = (double)0;
           $result = this.division (x, y);
           out = $rh.createReply();
           out.write_double ($result);
         } catch (CalApp.calculatorPackage.DivisionByZero $ex) {
           out = $rh.createExceptionReply ();
           CalApp.calculatorPackage.DivisionByZeroHelper.write (out, $ex);
         }
         break;
       }

       case 4:  // CalApp/calculator/square_root
       {
         try {
           double x = in.read_double ();
           double $result = (double)0;
           $result = this.square_root (x);
           out = $rh.createReply();
           out.write_double ($result);
         } catch (CalApp.calculatorPackage.Negative $ex) {
           out = $rh.createExceptionReply ();
           CalApp.calculatorPackage.NegativeHelper.write (out, $ex);
         }
         break;
       }

       case 5:  // CalApp/calculator/squared
       {
         double x = in.read_double ();
         double $result = (double)0;
         $result = this.squared (x);
         out = $rh.createReply();
         out.write_double ($result);
         break;
       }

       case 6:  // CalApp/calculator/shutdown
       {
         this.shutdown ();
         out = $rh.createReply();
         break;
       }

       default:
         throw new org.omg.CORBA.BAD_OPERATION (0, org.omg.CORBA.CompletionStatus.COMPLETED_MAYBE);
    }

    return out;
  } // _invoke

  // Type-specific CORBA::Object operations
  private static String[] __ids = {
    "IDL:CalApp/calculator:1.0"};

  public String[] _all_interfaces (org.omg.PortableServer.POA poa, byte[] objectId)
  {
    return (String[])__ids.clone ();
  }

  public calculator _this() 
  {
    return calculatorHelper.narrow(
    super._this_object());
  }

  public calculator _this(org.omg.CORBA.ORB orb) 
  {
    return calculatorHelper.narrow(
    super._this_object(orb));
  }


} // class calculatorPOA
