package es.studium.ServletPractica;

import java.util.ArrayList;

public class Modelo {
	//Atributos
	private ArrayList<Integer> numeros = new ArrayList<Integer>();
	
// Constructor
	Modelo(String numerosStr){
		try {
			// Llama a la función String.split()
			String[] tablaNumeros = numerosStr.split(",");

			// Procesa los números
			for (String numeroStr : tablaNumeros) {
				this.numeros.add(Integer.parseInt(numeroStr));
			}
			
		} catch (NumberFormatException e) {
			System.err.println("Error: "+e);
			// La cadena no se puede convertir a un número
			throw new NumberFormatException("Error en array introducido."); 
		}
	}

// Métodos
	protected String getMsg(int numero) {
		//implementar el buscar, los datos con las funciones buscar(), y conseguirIndex(), y devolverlo al controlador (Servlet)
		String msg ="";
		boolean existe = buscar(numero);
		if(existe){
			msg = "El numero <strong>"+numero+"</strong> esta en  posicion "+getIndex(numero).toString()+"."+"<br>";
			int indice = 0;
			msg+="[";
			for (Integer numeroExaminado : this.numeros) {
				if(getIndex(numero).contains(indice)) {
					msg+="<strong>";
				}
				msg += numeroExaminado;
				if(getIndex(numero).contains(indice)) {
					msg+="</strong>";
				}
				msg+=" ";
				indice++;
			}
			msg+="]";
		}
		else {
			msg = "El numero <strong>"+numero+"</strong> no existe en la lista.";
		}
		return msg;
	}
	private boolean buscar(int numero) {
		Integer busqueda = Integer.valueOf(numero);
		boolean existe = this.numeros.contains(busqueda);
		if (existe) {return true;}
		else {return false;}
	}
	private ArrayList<Integer> getIndex(int numero) {
		ArrayList<Integer> index = new ArrayList<Integer>();
		Integer busqueda = Integer.valueOf(numero);
		int indice = 0;
		for (Integer numeroExaminado : this.numeros) {
			if (numeroExaminado == numero) {
				index.add(indice);
			}
			indice++;
		}
		//index = this.numeros.indexOf(busqueda); //solo funciona par aun valor
		return index;
	}
}