/*import org.python.core.PyInstance;  
import org.python.util.PythonInterpreter;  
//import com.project.android.deeplearningengine.*;

public class InterpreterExample  
{  

   PythonInterpreter interpreter = null;  


   public InterpreterExample()  
   {  
      PythonInterpreter.initialize(System.getProperties(),  
                                   System.getProperties(), new String[0]);  

      this.interpreter = new PythonInterpreter();  
   }  

   void execfile( final String fileName )  
   {  
      this.interpreter.execfile(fileName);  
   }  

   PyInstance createClass( final String className, final String opts )  
   {  
      return (PyInstance) this.interpreter.eval(className + "(" + opts + ")");  
   }  

   public static void main( String gargs[] )  
   {  
      InterpreterExample ie = new InterpreterExample();  
      //DBN.test_dbn();
      ie.execfile("hellopy.py");  

      PyInstance hello = ie.createClass("Hello", "None");  

      hello.invoke("run");  
   }  
} */