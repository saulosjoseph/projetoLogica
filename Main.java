import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Main {
	
	public static void TouR(String linha) {
		
	}
	
	public static void main(String[] args){
		
		int problemas = 0;
		
		Scanner ler = new Scanner(System.in);
		 
	    System.out.printf("Informe o local do arquivo texto:\n");
	    String nome = ler.nextLine();
	 
	    System.out.printf("\nConteúdo do arquivo texto:\n");
	    try {
	      FileReader arq = new FileReader(nome);
	      BufferedReader lerArq = new BufferedReader(arq);
	 
	      String linha = lerArq.readLine(); // lê a primeira linha
	      // a variável "linha" recebe o valor "null" quando o processo
	      // de repetição atingir o final do arquivo texto
	      problemas = Integer.parseInt(linha); //primeira linha da entrada dá a quantidade de problemas
	      while (problemas >= 0) {
	        System.out.printf("%s\n", linha);
	 
	        linha = lerArq.readLine(); // lê da segunda até a última linha
	        problemas--;
	      }
	 
	      arq.close();
	    } catch (IOException e) {
	        System.err.printf("Erro na abertura do arquivo: %s.\n",
	          e.getMessage());
	    }
	 
	    System.out.println(); 
	    ler.close();
	}
}

