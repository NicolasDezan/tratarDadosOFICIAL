package importararquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class importar {

	public static void main(String[] args) {            //aqui ocorre a adição de dados e a escolha da forma de inserção dos dados
		
		boolean reiniciar = true; //reiniciar é o que caracteriza o loop do programa
		while(reiniciar) {
	    Scanner teclado = new Scanner(System.in);	   	  
		List<Float> dados = new ArrayList();
		System.out.println("Você prefere dar entrada nos dados manualmente(1) ou inserir um arquivo(2)?");
		int escolha = Integer.parseInt(teclado.nextLine());
		while((escolha !=1 && escolha !=2)) {                       // Aqui pode ocorre erro no programa se tiver incompatibilidade string x int
			System.out.println("ERRO. Digite 1 ou 2: ");
			escolha = Integer.parseInt(teclado.nextLine());
		}
		if(escolha == 1) {                                          // Esse é o método de adição de dados direto no terminal
		    System.out.println("Insira os valores. Aperte [enter] quando finalizar: ");
		    
		    dados.add (Float.parseFloat(teclado.nextLine()));
		    boolean verdade = true;
		    while (verdade) {
			try {
				Float numero = Float.parseFloat(teclado.nextLine());
				dados.add(numero);
			} catch (NumberFormatException ex) { verdade = false; }     // O que é esse "NumberFormatException ex"?
		    } 

		}
		
		if(escolha == 2) {                                             //Esse é o método de adição dados por arquivos 
		// importar dados numéricos de um arquivo .txt -> https://www.youtube.com/watch?v=YHV44ZVgab8&t=34s		
		String linha = new String(); //vai receber o conjunto de dados em forma de texto-String
		String nomearquivo = new String();
	    System.out.print("Insira o nome de seu arquivo de texto: ");	    
	    nomearquivo = teclado.nextLine();
		String endereco = "E:\\Meus Dados\\"+nomearquivo;
		File arq = new File (endereco);

		while(!arq.exists() || nomearquivo == "") {         // se o arquivo nao existir vai pedir pra inserir de novo             
			System.out.println("OPA. CONFIRA SE O NOME DO ARQUIVO ESTÁ CORRETO OU SE ELE EXISTE.");
			System.out.println("Lembre-se de indicar o formato do arquivo (ex: .txt)");			
			System.out.println();
			System.out.print("Insira novamente o nome do arquivo: ");
		    nomearquivo = teclado.nextLine();
			endereco = "E:\\Meus Dados\\"+nomearquivo;
			arq = new File (endereco);		 			
			
		}  
		
		System.out.println();		
		if (arq.exists()) {
			
			try {
				FileReader leitorDeArquivo = new FileReader(endereco);
				BufferedReader buffer = new BufferedReader(leitorDeArquivo);      
				
				
				while(true) {
					linha = buffer.readLine();
					dados.add (Float.parseFloat(linha));
					if(linha == null) {
						break;
					}
								
				}
			
				
			} 
				
				catch (Exception e){} // o que é o Exception??
				
		} 

		}
		
		
		
		
	
	
           //calculo da media, desvio padrao e mediana
		if(dados.size() != 0) {
		System.out.println(dados);
		System.out.println();
			// cálculo da média
		
		    float r = 0.0f;
		    for (Float dado : dados) {
		    	r += dado;
		    }
		    float media = r/dados.size();
		    
		    System.out.println("A média dos seus valores é " + media);
		    
		    // cálculo do Desvio Padrão
		    
		    float d = 0.0f;
		    for (Float n : dados) {
		    	d += Math.pow(n-media, 2);
		    }
		    float var = d/(dados.size()-1);
		    float desvpad = (float) Math.sqrt(var);
		    
		    System.out.println("O desvio padrão de seus valores é " + desvpad);
		    
		    // cálculo da mediana		   
		    
		    Collections.sort(dados);
		    int n = dados.size();		
		    int p1 = n/2;
		    if (n % 2 == 0) {           //dados.size é um numero par		    	
		    	int p2 = n/2+1;
		    	Float mediana_0 = dados.get(p1-1) + dados.get(p2-1);	
		    	Float mediana_par = mediana_0/2;
		    	System.out.println("A mediana é " + mediana_par);
		    }
		    if (n % 2 != 0) {       //dados.size é impar
		    	System.out.println("A mediana é " + dados.get(p1-1));}
		    
		    	System.out.println();
		    	
		    	System.out.println("Você gostaria de inserir novos dados?"
		    			+ "Sim(1) "
		    			+ "Não(2) ");
		    	
		    	
			    int escolha2 = Integer.parseInt(teclado.nextLine());
				while((escolha2 !=1 && escolha2 !=2)){                       // Como fazer não dar erro se a pessoa colocar um valor string 
					System.out.println("ERRO. Digite 1 ou 2: ");
					escolha2 = Integer.parseInt(teclado.nextLine());}
				if(escolha2 == 2) {
					reiniciar = false;
				}if(escolha2 == 1) {
					reiniciar = true;
				}
		    
		}
		
	
}
		System.out.println("O programa está finalizado. Até a próxima!"); 
		System.exit(0);
	} 					
}

/*
 * 1) Quando ocorre incompatibilidade float x string o programa "crasha"
 * 2) Eu queria ter feito o looping de forma "mais eficiente". Para conseguir definir o loop tive que 
 * deletar o "public static void tratarDados(...)"
 * 3) O código ficou muito bagunçado 
 */


		