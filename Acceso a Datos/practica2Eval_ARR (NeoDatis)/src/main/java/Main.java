import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Scanner;

import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.IValuesQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

public class Main {

	private static final String rutaNeodatis = "E:\\pruebas\\examen.neodatis";
	private static final String rutaCSVdepartamentos = "E:\\pruebas\\departamentos.csv";
	private static final String rutaCSVempleados = "E:\\pruebas\\empleados.csv";

	public static void main(String[] args) {

		ODB db = ODBFactory.open(rutaNeodatis);
		menu();
		String op;
		while (true) {
			op = opcionTeclado();
			if (op.equals("1")) {
				opcion1(db);
				System.out.println("\r\n");
				db.commit();
			} else if (op.equals("2")) {
				opcion2(db);
				System.out.println("\r\n");
			} else if (op.equals("3")) {
				opcion3(db);
				System.out.println("\r\n");
			} else if (op.equals("4")) {
				db.close();
				opcion4(db);
			} else {
				System.out.println("Opcion inválida\r\n");
			}
			menu();
		}

	}

	private static void opcion1(ODB db) {
		ArrayList<String[]> departamentos = leerCSV(rutaCSVdepartamentos);
		ArrayList<String[]> empleados = leerCSV(rutaCSVempleados);
		almacenarDepartamentos(db, departamentos);
		almacenarEmpleados(db, empleados);
	}

	private static void opcion2(ODB db) {

		IQuery query = new CriteriaQuery(Departamento.class);
		query.orderByAsc("codigoD");

		org.neodatis.odb.Objects<Departamento> departamentos = db.getObjects(query);
		System.out.println("\r\n");
		for (Departamento dep : departamentos) {
			System.out.println("" + "DEPARTAMENTO:" + dep.getCodigoD() + "\r\n" + "NOMBRE:" + dep.getNombreD() + "\r\n"
					+ "LOCALIDAD:" + dep.getLocalidad() + "\r\n" + "--------------------------------------");
		}

		query = new CriteriaQuery(Empleado.class);
		query.orderByAsc("codigoE");

		org.neodatis.odb.Objects<Empleado> empleados = db.getObjects(query);
		System.out.println("\r\n");
		for (Empleado emp : empleados) {
			int codDepE = emp.getDepartamento();
			String nomDepE = "";
			for (Departamento dep : departamentos) {
				if (codDepE == dep.getCodigoD()) {
					nomDepE = dep.getNombreD();
					System.out.println("" + "EMPLEADO:" + emp.getCodigoE() + "\r\n" + "NOMBRE:" + emp.getNombreE()
							+ "\r\n" + "APELLIDOS:" + emp.getApellidos() + "\r\n" + "DEPARTAMENTO:" + nomDepE + "\r\n"
							+ "--------------------------------------");
					break;
				}
			}
		}

	}

	private static void opcion3(ODB db) {

		// CONSULTA A: Nombres de los empleados que trabajan en el departamento 10.
		consultaA(db);

		// CONSULTA B: Número de empleados del departamento de Ventas.
		consultaB(db);

		// CONSULTA B: Por cada departamento, el número de empleados.
		consultaC(db);

	}

	private static void consultaA(ODB db) {
		ICriterion criterio = org.neodatis.odb.core.query.criteria.Where.equal("departamento", 10);

		IValuesQuery camposQuery = new ValuesCriteriaQuery(Empleado.class, criterio).field("nombreE");
		Values values = db.getValues(camposQuery);

		System.out.println("\r\nEMPLEADOS DEL DEPARTAMENTO 10:");
		for (ObjectValues objectValues : values) {
			System.out.println(objectValues.getByAlias("nombreE"));
		}

	}

	private static void consultaB(ODB db) {
		IQuery query = new CriteriaQuery(Departamento.class);
		query.orderByAsc("codigoD");
		org.neodatis.odb.Objects<Departamento> departamentos = db.getObjects(query);

		int codVentas = 0;
		for (Departamento dep : departamentos) {
			if (dep.getNombreD().toLowerCase().equals("ventas")) {
				codVentas = dep.getCodigoD();
			}
		}

		ICriterion criterio = org.neodatis.odb.core.query.criteria.Where.equal("departamento", codVentas);
		IValuesQuery camposQuery2 = new ValuesCriteriaQuery(Empleado.class, criterio).count("codigoE")
				.groupBy("departamento");
		Values values2 = db.getValues(camposQuery2);

		System.out.println("\r\nNUMERO EMPLEADOS EN VENTAS:");
		for (ObjectValues objectValues : values2) {
			BigInteger count = (BigInteger) objectValues.getByAlias("codigoE");
			System.out.println("Cuenta de nombres: " + count);
		}

	}

	private static void consultaC(ODB db) {
		IQuery query = new CriteriaQuery(Departamento.class);
		query.orderByAsc("codigoD");
		org.neodatis.odb.Objects<Departamento> departamentos = db.getObjects(query);

		IValuesQuery camposQuery = new ValuesCriteriaQuery(Empleado.class).count("codigoE").groupBy("departamento")
				.field("departamento", "d");
		Values values2 = db.getValues(camposQuery);

		System.out.println("\r\nNUMERO EMPLEADOS POR DEPARTAMENTO:");
		for (ObjectValues objectValues : values2) {
			BigInteger count = (BigInteger) objectValues.getByAlias("codigoE");
			for (Departamento dep : departamentos) {
				int i = (Integer) objectValues.getByAlias("d");
				if (i == dep.getCodigoD()) {
					System.out.println(dep.getNombreD().toUpperCase() + ": " + count);
					break;
				}
			}
		}
	}

	private static void opcion4(ODB db) {
		System.exit(0);
	}

	private static ArrayList<String[]> leerCSV(String ruta) {
		BufferedReader br;
		ArrayList<String[]> list = new ArrayList<String[]>();
		try {
			br = new BufferedReader(new FileReader(ruta));
			String campos[];
			while (br.ready()) {
				campos = br.readLine().split(",");
				System.out.println(campos[0]);
				list.add(campos);
			}
			br.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return list;
	}

	private static void almacenarDepartamentos(ODB db, ArrayList<String[]> list) {
		for (String[] string : list) {
			Departamento departamento = new Departamento(Integer.parseInt(string[0]), string[1], string[2]);
			db.store(departamento);
		}
	}

	private static void almacenarEmpleados(ODB db, ArrayList<String[]> list) {
		for (String[] string : list) {
			Empleado empleado = new Empleado(Integer.parseInt(string[0]), string[1], string[2], string[3],
					Float.parseFloat(string[4]), Integer.parseInt(string[5]));
			db.store(empleado);
		}
	}

	private static String opcionTeclado() {
		System.out.print("Introduce una opción: ");
		String s = new Scanner(System.in).next();
		return s;
	}

	private static void menu() {
		System.out.println("" + "################\r\n" + "##### MENU #####\r\n" + "################\r\n" + "\r\n"
				+ "1.Guardar en NeoDatis\r\n" + "2.Visualizar departamentos y empleados\r\n"
				+ "3.Mostrar consultas <...>\r\n" + "4.Salir\r\n" + "");
	}

}