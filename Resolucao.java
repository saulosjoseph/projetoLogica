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
	
	public boolean hornClause() {
		int negativo = 0;
		int variavel = 0;
		boolean saida = true;
		for (int j = 0; j < vector.size(); j++) {
			for (int i = 0; i < vector.elementAt(j).length(); i++) {
				if (vector.elementAt(j).charAt(i) == 'P'
						|| vector.elementAt(j).charAt(i) == 'Q'
						|| vector.elementAt(j).charAt(i) == 'R'
						|| vector.elementAt(j).charAt(i) == 'S') {
					variavel++;
				}
				if (vector.elementAt(j).charAt(i) == '~') {
					negativo++;
				}
			}
			if (variavel - negativo > 1) {
				saida = false;
				j = vector.size();
			}
			negativo = 0;
			variavel = 0;
		}
		return saida;
	}

	public boolean insat() {
		char literal;
		for (int i = 0; i < 4; i++) {
			literal = Character.toChars(i+80)[0];
			if (vector.contains("(" + literal + ")")) {		//se no vetor tem as cláusulas atômicas
				for (int j = 0; j < vector.size(); j++) {
					String clausula = vector.elementAt(j);
					if (clausula.contains("~" + literal)) {		//se na cláusula tem a negação do literal, retira o literal da cláusula
						String newClause = removerLiteral(clausula, literal);
						vector.set(j, newClause);
					}
					if (clausula.contains("" + literal) && 		//se tiver o literal na cláusula, remove a cláusula
							!(clausula.equals("(" + literal + ")") || clausula.equals("(~" + literal + ")"))) {
						vector.remove(j);
					}
				}
			}

			if (vector.contains("(~" + literal + ")")) {		//se no vetor tem as cláusulas atômicas negativas
				for (int j = 0; j < vector.size(); j++) {
					String clausula = vector.elementAt(j);
					if (clausula.contains("+" + literal) ||clausula.contains(literal + "v")) {		//remove o literal da cláusula
						String newClause = removerLiteral(clausula, literal);
						vector.set(j, newClause);
					}
					if (clausula.contains("~" + literal) && 		//remove a cláusula
							!(clausula.equals("(" + literal + ")") || clausula.equals("(~" + literal + ")"))) {
						vector.remove(j);
					}					
				}
			}
		}
		
		for (int i = 0; i < 4; i++) {
			literal = Character.toChars(i+80)[0];
			if (vector.contains("(" + literal + ")") && vector.contains("(~" + literal + ")")){
				return true;
			}
		}
		
		return false;
	}
	
	public String removerLiteral(String clausula, char literal) {
		String saida = clausula;
		if (clausula.contains("~" + literal)) {
			if (clausula.contains("v~" + literal)) {
				saida = clausula.replace("+-" + literal, "");
			} 
			if (clausula.contains("~" + literal + "v")) {
				saida = clausula.replace("~" + literal + "v", "");
			}
		} else {
			if (clausula.contains("v" + literal)) {
				saida = clausula.replace("v" + literal, "");
			} 
			if (clausula.contains(literal + "v")) {
				saida = clausula.replace(literal + "v", "");
			}
		}
		return saida;
	}
	
	public void limpar() {
		vector.clear();
	}



}
