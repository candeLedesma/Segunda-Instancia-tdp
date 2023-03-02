package src;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.Map.Entry;

public class Programa {
	
	private List<File> archivos;
	private Entry<String,Integer>[] palabrasMayorApariciones;
	private int totalPalabras;//?
	private File[] ficheros;
	
	public Programa() {}
	

	public void leerTxt(String name) {
		ini();
		File file = new File(name);
		List<String> palabras = getPalabrasSeparadas(file);
		Map<String, Integer> palabrasConCantApariciones = getPalabrasConCantApariciones(palabras);
		palabrasMayorApariciones = getPalabrasMayorApariciones(palabrasConCantApariciones);
		setPorcentajeApariciones(palabrasMayorApariciones);
	}
	
	public void leerDirectorio(String rutaTxt) {
		ini();
		File file = new File(rutaTxt);
		ficheros = file.listFiles();
		for (int i=0; i < ficheros.length; i++){
			if(ficheros[i].getName().endsWith(".txt")) {
				archivos.add(ficheros[i]);
			}
		}
		
		List<String> palabras = new LinkedList<String>();
		
		for(File f: archivos) {
			palabras.addAll(getPalabrasSeparadas(f));

		}
		
		Map<String, Integer> palabrasConApariciones = getPalabrasConCantApariciones(palabras);
		palabrasMayorApariciones = getPalabrasMayorApariciones(palabrasConApariciones);
		setPorcentajeApariciones(palabrasMayorApariciones);
	}
	
	public File[] getFicheros() {
		return ficheros;
	}

	private void ini() {
		archivos = new LinkedList<File>();
		palabrasMayorApariciones = new Entry[5];
		totalPalabras = 0;
	}

	public Entry<String, Integer>[] getResultado() {
		return palabrasMayorApariciones;
	}

	private void setPorcentajeApariciones(Entry<String, Integer>[] palabras) {
		int aux;
		for(int i=0; i< palabras.length; i++) {
			if(palabras[i] != null) {
				aux = palabras[i].getValue();
				palabras[i].setValue((aux*100)/totalPalabras);
			}
		}
		
	}

	private Entry<String, Integer>[] getPalabrasMayorApariciones(Map<String, Integer> palabras) {
		Entry<String,Integer>[] palabrasMayorApariciones = new Entry[5];
		
		for(int i = 0; i < 5; i++) {
			int maxCantApariciones = 0;
			Entry<String, Integer> palabraAux = null;
			
			for(Entry<String, Integer> palabra: palabras.entrySet()) {
				if(palabra.getValue() >= maxCantApariciones) {
					maxCantApariciones = palabra.getValue();
					palabraAux = palabra;
				}
			}
			
			if(palabraAux != null) {
				palabrasMayorApariciones[i] = palabraAux;
				palabras.remove(palabraAux.getKey());
			}
		}
		
		return palabrasMayorApariciones;
	}

	private List<String> getPalabrasSeparadas(File f) {
		String linea;
		String[] arreglo = null;
		FileReader fr;
		
		try {
			fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);  
			while ((linea = br.readLine()) != null) {
				arreglo = linea.split(" "); // una palabra de la linea en cada posicion del arreglo
			}
			fr.close();    
			br.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(arreglo!=null)
			totalPalabras += arreglo.length;
		
		return toList(arreglo);
	}

	private List<String> toList(String[] arreglo) {
		List<String> lista = new LinkedList<String>();
		if(arreglo!=null)
			for(int i=0; i<arreglo.length; i++) {
				lista.add(arreglo[i]);
			}
		
		return lista;
	}

	private Map<String,Integer> getPalabrasConCantApariciones(List<String> palabras) {
		String aux;
		Map<String,Integer> aparicionesPalabras = new HashMap<String, Integer>();
		
		for(String s: palabras) {
			aux = s.toLowerCase();
			if(aparicionesPalabras.containsKey(aux)) {
				int value = aparicionesPalabras.get(aux)+1;
				aparicionesPalabras.put(aux, value);
			} else
				aparicionesPalabras.put(aux,1);
		}
		
		return aparicionesPalabras;
	}

}
