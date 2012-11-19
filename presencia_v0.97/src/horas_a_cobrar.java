

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.table.DefaultTableModel;


public class horas_a_cobrar {
	
	
	
	public static DefaultTableModel listado_horas_cobrar(String empleado,String a�o){
		DefaultTableModel modelo = null; 
	
		String ID_EMPLEADO = "";
		
	try {	
	
	propiedades Archivopropiedades = new propiedades();
	//String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
	//String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
	//String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
	String servidor_sql = Archivopropiedades.servidor_SQL();
	String basededatos = Archivopropiedades.base_de_datos();
	String jtds_string = servidor_sql + basededatos;
	
	
	//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
	Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
	//Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
	Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
	
	//Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
	Statement s = conexion.createStatement();
	
	String fecha_inicio_a�o = a�o + "-01-01";
	String fecha_fin_a�o = a�o + "-12-31";
	
	
	String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
	ResultSet rs = s.executeQuery (query1);
	//System.out.println (query1); 
	while (rs.next()) 
	{ 
		
		 ID_EMPLEADO = rs.getString(1);
	

	}
	
	
	String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, [Mes Contabilizado], Inicio_hora_extra , Fin_hora_extra ," +
		"Horas_extras_trab , Horas_extras_base , Coeficiente_ajuste ,horas_ordinarias_ajustadas," +
			"horas_ordinarias_cobrar,horas_efectivas_a_cobrar  FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0  and ID_empleado = '"+ID_EMPLEADO+"' and Fecha >= {D '"+fecha_inicio_a�o+"'}  And Fecha <= {D '"+fecha_fin_a�o+"'} ) ORDER BY Fecha"; 
	
	ResultSet rs3 = s.executeQuery (query3);
	

	
	 modelo = new DefaultTableModel();
	
	ResultSetMetaData metaDatos = rs3.getMetaData();
	
	// Se obtiene el n�mero de columnas.
	int numeroColumnas = metaDatos.getColumnCount();

	
	// Se crea un array de etiquetas para rellenar
	Object[] etiquetas = new Object[numeroColumnas];
	
	// Se obtiene cada una de las etiquetas para cada columna
	for (int i = 0; i < numeroColumnas; i++)
	{
	   // Nuevamente, para ResultSetMetaData la primera columna es la 1.
	   etiquetas[i] = metaDatos.getColumnLabel(i + 1);
	}
	
	
	modelo.setColumnIdentifiers(etiquetas);
	
	
	while (rs3.next())
	{
	   // Se crea un array que ser� una de las filas de la tabla.
	   Object [] fila = new Object[numeroColumnas]; // Hay tres columnas en la tabla

	   // Se rellena cada posici�n del array con una de las columnas de la tabla en base de datos.
	   for (int i=0;i<numeroColumnas;i++)
	      fila[i] = rs3.getObject(i+1); // El primer indice en rs es el 1, no el cero, por eso se suma 1.

	   // Se a�ade al modelo la fila completa.
	   modelo.addRow(fila);
	}
	
	

	
		
	}catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	
	
	
	
	return modelo;
	
	
	
	
	}
	
	
	public static Float totales_horas_cobrar (String empleado,String a�o){
		String ID_EMPLEADO = "";
		Float totales = null;
		try {	
			propiedades Archivopropiedades = new propiedades();
			//String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			//String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			//String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			String servidor_sql = Archivopropiedades.servidor_SQL();
			String basededatos = Archivopropiedades.base_de_datos();
			String jtds_string = servidor_sql + basededatos;
			
			
			//Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Driver d = (Driver)Class.forName("net.sourceforge.jtds.jdbc.Driver").newInstance();
			//Driver d = (Driver)Class.forName("com.microsoft.jdbc.sqlserver.SQLServerDriver").newInstance();
			Connection conexion = DriverManager.getConnection("jdbc:jtds:sqlserver:"+jtds_string+"", "sa", "sa");
			
			//Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			
		Statement s = conexion.createStatement();
		//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String fecha_inicio_a�o = a�o + "-01-01";
		String fecha_fin_a�o = a�o + "-12-31";
		
		String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
		ResultSet rs = s.executeQuery (query1);
		//System.out.println (query1); 
		while (rs.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			 ID_EMPLEADO = rs.getString(1);
			//System.out.println (ID_EMPLEADO); 

		}
		
		/*String query3 = "SELECT Id_hora_extra, Id_empleado,Fecha, [Mes Contabilizado], Inicio_hora_extra , Fin_hora_extra ," +
				"Horas_extras_trab , Horas_extras_base , Coeficiente_ajuste ,horas_ordinarias_ajustadas," +
				"horas_ordinarias_cobrar  FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0  and ID_empleado = '"+ID_EMPLEADO+"') ORDER BY Fecha"; 
		*/
		
		
		
		String query2 = "SELECT sum(horas_ordinarias_cobrar) FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0 AND Fecha >= {D '"+fecha_inicio_a�o+"'} AND  Fecha <= {D '"+fecha_fin_a�o +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
		ResultSet rs2 = s.executeQuery (query2);
		//System.out.println (query2); 
		while (rs2.next()) 
		{ 
			//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
			//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
			String Horas = (rs2.getString(1));
			
			if (Horas != null) {
				totales=Float.parseFloat(Horas);
				} else{
				totales = Float.parseFloat("0");	
				}
			
			
			//System.out.println (Horas); 
			
		}
		
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return totales;

	
	
	}
	
	public static Float totales_horas_efectivas_cobrar (String empleado,String a�o){
	
		String ID_EMPLEADO = "";
		Float totales = null;
		try {	
	
			propiedades Archivopropiedades = new propiedades();
			String ruta_servidor_odbc = Archivopropiedades.ruta_servidor_odbc();	
			String nombre_servidor_odbc = Archivopropiedades.nombre_servidor_odbc();	
			String odbc = ruta_servidor_odbc + nombre_servidor_odbc;
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
			Connection conexion = DriverManager.getConnection("jdbc:odbc:Driver={Microsoft Access Driver (*.mdb, *.accdb)};DBQ="+odbc+"", "", "");
			Statement s = conexion.createStatement();
			//SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha_inicio_a�o = a�o + "-01-01";
			String fecha_fin_a�o = a�o + "-12-31";
			
			String query1 = "SELECT Id_empleado, Nombre, Apellidos, (Nombre + ' ' + Apellidos) as aaa FROM Empleados where (Nombre + ' ' + Apellidos) = '"+empleado+"'"; 
			ResultSet rs = s.executeQuery (query1);
			//System.out.println (query1); 
			while (rs.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
				 ID_EMPLEADO = rs.getString(1);
				//System.out.println (ID_EMPLEADO); 

			}
			
			
			/*
			SELECT S_HE_VAC_A�O_ANTERIOR.Id_empleado, S_HE_VAC_A�O_ANTERIOR.A�o, S_HE_VAC_A�O_ANTERIOR.Vacaciones_Inicio_a�o_dias FROM S_HE_VAC_A�O_ANTERIOR GROUP BY S_HE_VAC_A�O_ANTERIOR.Id_empleado, S_HE_VAC_A�O_ANTERIOR.A�o, S_HE_VAC_A�O_ANTERIOR.Vacaciones_Inicio_a�o_dias HAVING (((S_HE_VAC_A�O_ANTERIOR.Id_empleado)=""" & [Id_empleado] & """) AND ((S_HE_VAC_A�O_ANTERIOR.A�o)=" & [Lista21] & "));
			*/
			String query2 = "SELECT sum(horas_efectivas_a_cobrar) FROM Datos_horas_extras  WHERE (horas_ordinarias_cobrar <> 0 AND Fecha >= {D '"+fecha_inicio_a�o+"'} AND  Fecha <= {D '"+fecha_fin_a�o +"'} AND ID_empleado = '"+ID_EMPLEADO+"')"; 
			ResultSet rs2 = s.executeQuery (query2);
			//System.out.println (query2); 
			while (rs2.next()) 
			{ 
				//System.out.println (rs.getInt (1) + " " + rs.getString (2)+ " " + rs.getDate(3)); 
				//String ID_EMPLEADO = rs.getString(1)+ " " + rs.getString (2)+" " + rs.getString (3)+ " " + rs.getString (4);
				Float Horas = (rs2.getFloat(1));
				
				if (Horas != null) {
					totales=Horas;
					} else{
					totales = Float.parseFloat("0");	
					}
				
				//System.out.println (Horas); 
				
				}
			
			
		}catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	return totales;
	}
	
	
		
		
		
		
}
