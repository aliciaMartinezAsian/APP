package view;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Entrada {

	private static BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

	public static String pedirCadena(String txtPregunta) {
		return pedirCadena(txtPregunta, null);
	}

	public static Integer pedirEntero(String txtPregunta) {
		return pedirEntero(txtPregunta, null);
	}

	public static Double pedirReal(String txtPregunta) {
		return pedirReal(txtPregunta, null);
	}

	public static String pedirCadena(String txtPregunta, String valorDefecto) {
		String textoIntroducido;
		String valorDevuelto = "";
		boolean hayError;
		if (valorDefecto != null) {
			txtPregunta += " [" + valorDefecto + "]:";
		}
		do {
			System.out.print(txtPregunta + " ");
			try {
				textoIntroducido = teclado.readLine();
				valorDevuelto = (textoIntroducido.length() == 0) ? valorDefecto : textoIntroducido;
				hayError = false;
			} catch (Exception e) {
				System.err.println("Valor introducido no v\u00E1lido. Intente de nuevo.");
				hayError = true;
			}
		} while (hayError);
		return valorDevuelto;
	}
	
	public static Integer pedirEntero(String txtPregunta, Integer valorDefecto) {
		String textoIntroducido;
		Integer valorDevuelto = 0;
		boolean hayError;
		if (valorDefecto != null) {
			txtPregunta += " [" + valorDefecto + "]:";
		}
		do {
			System.out.print(txtPregunta + " ");
			try {
				textoIntroducido = teclado.readLine();
				valorDevuelto = (textoIntroducido.length() == 0) ? valorDefecto : Integer.parseInt(textoIntroducido);  
				hayError = false;
			} catch (Exception e) {
				System.err.println("Valor introducido no v\u00E1lido. Intente de nuevo.");
				hayError = true;
			}
		} while (hayError);
		return valorDevuelto;
	}
	
	public static Double pedirReal(String txtPregunta, Double valorDefecto) {
		String textoIntroducido;
		Double valorDevuelto = 0.0;
		boolean hayError;
		if (valorDefecto != null) {
			txtPregunta += " [" + valorDefecto + "]:";
		}
		do {
			System.out.print(txtPregunta + " ");
			try {
				textoIntroducido = teclado.readLine();
				valorDevuelto = (textoIntroducido.length() == 0) ? valorDefecto : Double.parseDouble(textoIntroducido);  
				hayError = false;
			} catch (Exception e) {
				System.err.println("Valor introducido no v\u00E1lido. Intente de nuevo.");
				hayError = true;
			}
		} while (hayError);
		return valorDevuelto;
	}


}