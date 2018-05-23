package projeto;

import java.util.Vector;

public class Resolucao {
	
	private Vector<String> vector = new Vector<String>();
	
	public void subExp(String exp) {
		int cont = 0;
		String aux = "";
		for (int i = 0; i < exp.length(); i++) {
			if (exp.charAt(i) == '(') {
				cont++;
			}
			if (exp.charAt(i) == ')') {
				cont--;
			}
			if (cont != 0) {
				aux += exp.charAt(i);
			} else if (exp.charAt(i) != '&') {
				aux += ")";
				if (!vector.contains(aux)) {
					vector.add(aux);

				}
				aux = "";
			}
		}
	}
	
	
	public boolean fnc(String exp) {
		int cont = 0;
		boolean saida = true;
		for (int i = 0; i < exp.length(); i++) {
			if (exp.charAt(i) == '(') {
				cont++;
			}
			if (exp.charAt(i) == ')') {
				cont--;
			}
			if (cont > 0) { // está dentro da expressão
				if (exp.charAt(i) != 'P' && exp.charAt(i) != 'Q'
						&& exp.charAt(i) != 'R' && exp.charAt(i) != 'S'
						&& exp.charAt(i) != 'v' && exp.charAt(i) != '~'
						&& exp.charAt(i) != '(' && exp.charAt(i) != ' '){
					saida = false;
					i = exp.length();

				}
			} else if (exp.charAt(i) != '&' && exp.charAt(i) != ')' && exp.charAt(i) != ' ') {
				saida = false;
				i = exp.length();
			}
		}
		return saida;
	}

}
