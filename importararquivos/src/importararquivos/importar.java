package importararquivos;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

public class importar {

	public static void main(String[] args) {
		Scanner teclado = new Scanner(System.in);
		List<Float> dados = new ArrayList();
		
		System.out.println("Você prefere dar entrada nos dados manualmente(1) ou inserir um arquivo(2)?");
		int escolha = Integer.parseInt(teclado.nextLine());		
		if(escolha == 1) {manual(dados);}
		if(escolha == 2) {arquivo(dados);}		
		
		float media = calcularMedia(dados);    
		calcularMediana(dados);
		calcularDesvioPadrao(dados, media);

		System.out.println();
		System.out.println("Fim");
		

	}
	
	public static List<Float> manual(List<Float> dados){                                                       //receber os dados pelo teclado
		Scanner teclado = new Scanner(System.in);
		dados.add (Float.parseFloat(teclado.nextLine()));
	    boolean verdade = true;
	    while (verdade) {
		try {
			Float numero = Float.parseFloat(teclado.nextLine());
			dados.add(numero);
		} catch (NumberFormatException ex) { verdade = false; }   
	    } 
		System.out.println(dados);
		return dados;
		
	}

	public static List<Float> arquivo(List<Float> dados){                                                      //importar arquivo do computador
		                                                                                                              // importar dados numéricos de um arquivo .txt -> https://www.youtube.com/watch?v=YHV44ZVgab8&t=34s		
				String linha = new String();                                                                                 //vai receber o conjunto de dados em forma de texto-String
			    System.out.println("Selecione seu arquivo.");	    
				JFileChooser chooser = new JFileChooser();			                                                                  //escolher arquivo: https://www.youtube.com/watch?v=1bE0vmWqd94
				FileNameExtensionFilter filter = new FileNameExtensionFilter("Selecione um arquivo txt","txt");                                      //opcional: determinar o tipo de arquivo 
				chooser.setFileFilter(filter);                                                                                                      //setar o filtro				
				int retorno = chooser.showOpenDialog(null);                                                                             //mandando abrir a janela, o arquivo que for selecionado, irá para a variavel "retorno"
				
				if(retorno==JFileChooser.APPROVE_OPTION) {
					JOptionPane.showMessageDialog(null, chooser.getSelectedFile().getAbsolutePath());
				}
				
				File arq = chooser.getSelectedFile();
				
				if (arq.exists()) {
					try {
						FileReader leitorDeArquivo = new FileReader(arq);
						BufferedReader buffer = new BufferedReader(leitorDeArquivo);   						
						while(true) {
							linha = buffer.readLine();
							dados.add (Float.parseFloat(linha));
							if(linha == null) {break;}}}catch (Exception e){}			
				} 
		return dados;
	}

	public static float calcularMedia(List<Float> dados) {		
		float r = 0.0f;
	    for (Float dado : dados) {
	    	r += dado;
	    }
	    float media = r/dados.size();
	    System.out.println("A média dos seus valores é " + media);
	    return media;
	}
	
	public static float calcularMediana(List<Float> dados) {
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
	    return 0;
	}

	public static float calcularDesvioPadrao(List<Float> dados, float media) {
	    float d = 0.0f;
	    for (Float n : dados) {
	    	d += Math.pow(n-media, 2);
	    }
	    float var = d/(dados.size()-1);
	    float desvpad = (float) Math.sqrt(var);
	    
	    System.out.println("O desvio padrão de seus valores é " + desvpad);
	    return 0;
	}}




		