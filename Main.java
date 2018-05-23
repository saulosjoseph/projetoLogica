package projeto;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	// verifica se é necessário usar tabela ou resolução, se for método da tabela,
	// retorna true
	public static boolean TouR(String linha) {
		if (linha.substring(0, 1).equals("T")) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {

		int casosTotal = 0;
		int casoAtual = 1;
		Resolucao re = new Resolucao();

		Scanner ler = new Scanner(System.in);

		System.out.printf("Informe o local do arquivo texto: ");
		String nome = ler.nextLine();

		try {
			FileReader arq = new FileReader(nome);
			BufferedReader lerArq = new BufferedReader(arq);

			String linha = lerArq.readLine(); // lê a primeira linha
			// a variável "linha" recebe o valor "null" quando o processo
			// de repetição atingir o final do arquivo texto
			casosTotal = Integer.parseInt(linha); // primeira linha da entrada dá a quantidade de problemas
			while (casosTotal > 0) {
				linha = lerArq.readLine(); // lê da segunda até a última linha
				//checa se é Tabela ou Resolução
				if (TouR(linha)) {
					//true = tabela
					System.out.println("Problema #" + casoAtual+ "\nEsse é tabela\n");
				} else {
					//retira os 3 primeiros caracteres "RE ";
					linha = linha.replaceFirst("RE ", "");
					//coloca as subexpressões na vetor
					re.subExp(linha);
					//checa se é fnc
					if(!re.fnc(linha)) {
						//false = não fnc;
						System.out.println("Problema #" + casoAtual+ "\nNão está na FNC.\n");
					} else {
						//se for fnc, checa se todas as calusulas são de Horn
						if(!re.hornClause()) {
							//false = nem todas são Horn
							System.out.println("Problema #" + casoAtual +"\nNem todas as cláusulas são de Horn.\n");
						} else {
							//se todas forem Horn, checa se é satisfatível
							if(!re.insat()) {
								System.out.println("Problema #" + casoAtual +"\nSim, é satisfatível.\n");
							} else {
								System.out.println("Problema #" + casoAtual +"\nNão, não é satisfatível.\n");
							}
						}
					}
				}
				casosTotal--;
				casoAtual++;
				re.limpar();
			}

			arq.close();
		} catch (IOException e) {
			System.err.printf("Erro na abertura do arquivo: %s.\n", e.getMessage());
		}

		System.out.println();
		ler.close();
	}
}
