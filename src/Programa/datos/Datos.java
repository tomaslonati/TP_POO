package Programa.datos;

import java.io.*;


public class Datos {
	static public boolean guardar(Object obj)
	{
		try
		{
			FileOutputStream fout = new FileOutputStream("Agencia.bin");
			ObjectOutputStream out = new ObjectOutputStream(fout);
			//Serialization:
			out.writeObject(obj);       
			out.close();
			return true;
		}
		catch(Exception ex)
		{
			return false;
		}
	}

	static public Object recuperar()
	{
		Object obj;
		try
		{
			FileInputStream fi = new FileInputStream("Agencia.bin");
			ObjectInputStream fs = new ObjectInputStream(fi);
			// Deserialazation:
			obj = fs.readObject();
			fs.close();
		}
		catch(Exception ex)
		{
			obj=null;
		}
		return obj;
	}

}
