
public class test2 {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 String empleado = "Marcos Rodriguez";
		// String empleado = "Oscar Jauregui";
		 String a�o = "2010";
		 
		 //horas_a_cobrar.listado_horas_cobrar(empleado, a�o);
		 //Float a = horas_a_cobrar.totales_horas_cobrar(empleado, a�o);
		// Float b = horas_a_cobrar.totales_horas_efectivas_cobrar(empleado, a�o);
	//	 System.out.println(a + " "+b);
		 
	//	 Object[][] data = horas_a_compensar.listado_horas_compensar(empleado, a�o);
		 float a = horas_a_compensar_solo_parada.totales_horas_compensadas_SOLO_PARADA(empleado, a�o);
		float b = horas_a_compensar_solo_parada.generadas_a�o_SOLO_PARADA(empleado,a�o);
		float c = horas_a_compensar_solo_parada.disfrutadas_a�o_SOLO_PARADA(empleado, a�o);
	//	 float b = horas_a_compensar.generadas_a�o(empleado, a�o);

}
}