package CalApp.calculatorPackage;

/**
* CalApp/calculatorPackage/NegativeHolder.java .
* Generated by the IDL-to-Java compiler (portable), version "3.2"
* from calculator.idl
* lunes 27 de febrero de 2023 19H40' CET
*/

public final class NegativeHolder implements org.omg.CORBA.portable.Streamable
{
  public CalApp.calculatorPackage.Negative value = null;

  public NegativeHolder ()
  {
  }

  public NegativeHolder (CalApp.calculatorPackage.Negative initialValue)
  {
    value = initialValue;
  }

  public void _read (org.omg.CORBA.portable.InputStream i)
  {
    value = CalApp.calculatorPackage.NegativeHelper.read (i);
  }

  public void _write (org.omg.CORBA.portable.OutputStream o)
  {
    CalApp.calculatorPackage.NegativeHelper.write (o, value);
  }

  public org.omg.CORBA.TypeCode _type ()
  {
    return CalApp.calculatorPackage.NegativeHelper.type ();
  }

}
