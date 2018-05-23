package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	// verifica se � necess�rio usar tabela ou resolu��o, se for m�todo da tabela,
	// retorna true
	public static boolean TouR(String linha) {
		if (linha.substring(0, 1).equals("T")) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

		int problemas = 0;
		int problema = 1;
		Resolucao re = new Resolucao();

		Scanner ler = new Scanner(System.in);

		System.out.printf("Informe o local do arquivo texto: ");
		String nome = ler.nextLine();

		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine(); // l� a primeira linha
			// a vari�vel "linha" recebe o valor "null" quando o processo
			// de repeti��o atingir o final do arquivo texto
			problemas = Integer.parseInt(linha); // primeira linha da entrada d� a quantidade de problemas
			while (problemas > 0) {
				linha = lerArq.readLine(); // l� da segunda at� a �ltima linha
				//checa se � Tabela ou Resolu��o
				if (TouR(linha)) {
					//true = tabela
					System.out.println("#" + problema+ "\n esse � tabela\n");
				} else {
					//retira os 3 primeiros caracteres "RE ";
					linha = linha.replaceFirst("RE ", "");
					//coloca as subexpress�es na vetor
					re.subExp(linha);
					//checa se � fnc
					if(!re.fnc(linha)) {
						//false = n�o fnc;
						System.out.println("Problema #" + problema+ "\nN�o est� na FNC.\n");
					} else {
						System.out.println("#" + problema+ "\n esse � talvez\n");
					}
				}
				problemas--;
				problema++;
			}

			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		System.out.println();
		ler.close();
	}
}
