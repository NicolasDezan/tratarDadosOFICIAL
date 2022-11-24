package view;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class Tela1 {

	private JFrame frame;
	private JTextField mediaResultado;
	private JTextField medianaResultado;
	private JTextField desvPadResultado;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Tela1 window = new Tela1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	/**
	 * Create the application.
	 */
	
	
	public Tela1() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 678, 411);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JButton selecionarArquivo = new JButton("Selecionar arquivo");
		selecionarArquivo.setForeground(new Color(0, 0, 0));
		selecionarArquivo.setFont(new Font("Tahoma", Font.PLAIN, 30));
		selecionarArquivo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				List<Float> dados = new ArrayList();
				String linha = new String();                                                                                 //vai receber o conjunto de dados em forma de texto-String
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
							if(linha == null) {break;}}}catch (Exception e1){}	
					
					
				} 
				float r = 0.0f;
			    for (Float dado : dados) {
			    	r += dado;
			    }
			    float media = r/dados.size();
			    float d = 0.0f;
			    for (Float n : dados) {
			    	d += Math.pow(n-media, 2);
			    }
			    float var = d/(dados.size()-1);
			    float desvpad = (float) Math.sqrt(var);
			    mediaResultado.setText(String.valueOf(media));
			    desvPadResultado.setText(String.valueOf(desvpad));
			    Collections.sort(dados);
			    int n = dados.size();		
			    int p1 = n/2;
			    if (n % 2 == 0) {           //dados.size é um numero par		    	
			    	int p2 = n/2+1;
			    	Float mediana_0 = dados.get(p1-1) + dados.get(p2-1);	
			    	Float mediana_par = mediana_0/2;
				    medianaResultado.setText(String.valueOf(mediana_par));

			    }
			    if (n % 2 != 0) {       //dados.size é impar
			    medianaResultado.setText(String.valueOf(dados.get(p1-1)));}


			}
		});
		selecionarArquivo.setBounds(133, 51, 365, 45);
		frame.getContentPane().add(selecionarArquivo);
		
		JLabel lblNewLabel = new JLabel("Média");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(31, 223, 123, 38);
		frame.getContentPane().add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Mediana");
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel_1.setBounds(31, 271, 123, 38);
		frame.getContentPane().add(lblNewLabel_1);
		
		JLabel lblDesvioPadro = new JLabel("Desvio Padrão");
		lblDesvioPadro.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblDesvioPadro.setBounds(31, 319, 152, 38);
		frame.getContentPane().add(lblDesvioPadro);
		
		mediaResultado = new JTextField();
		mediaResultado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		mediaResultado.setBounds(103, 223, 96, 33);
		frame.getContentPane().add(mediaResultado);
		mediaResultado.setColumns(10);
		
		medianaResultado = new JTextField();
		medianaResultado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		medianaResultado.setColumns(10);
		medianaResultado.setBounds(113, 271, 96, 33);
		frame.getContentPane().add(medianaResultado);
		
		desvPadResultado = new JTextField();
		desvPadResultado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		desvPadResultado.setColumns(10);
		desvPadResultado.setBounds(166, 323, 96, 33);
		frame.getContentPane().add(desvPadResultado);
	}
}
