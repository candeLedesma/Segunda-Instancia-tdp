package src;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map.Entry;
import java.util.Properties;
import javax.swing.JComboBox;


public class Ventana extends JFrame{
	
	private JPanel contentPane;
	private Programa programa;
	private String txt;
	private JTextArea txtRes;
	private JTextArea txtPorcentaje;
	private Properties propiedades;
	private InputStream english;
	private InputStream spanish;
	private JButton btnComenzar;
	private JButton btnDirectorio;
	private JButton spanishBtn;
	private JButton englishBtn;
	private JLabel lblDirectorio;
	private JLabel palabras;
	private JLabel porcentaje;
	private JLabel archivos;
	private JLabel ruta;
	private JComboBox comboBox;
	
	public Ventana() {
	
		try {
			english = Ventana.class.getResourceAsStream("english.p");
			spanish = Ventana.class.getResourceAsStream("spanish.p");
			propiedades= new Properties();
			propiedades.load(spanish);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		programa = new Programa();
		
		setResizable(false);
		programa = new Programa();
		this.setVisible(true);
		this.setSize(700, 700);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);		
		this.setTitle("Winter is Coming");
		contentPane=new JPanel();
		contentPane.setLayout(null);
		this.setContentPane(contentPane);
	
		
		btnDirectorio = new JButton(propiedades.getProperty("load"));
		btnDirectorio.setBounds(348, 86, 130, 29);
		contentPane.add(btnDirectorio);
		OyenteDirectorio oyenteD = new OyenteDirectorio(this);
		btnDirectorio.addActionListener(oyenteD);

		btnComenzar = new JButton(propiedades.getProperty("start"));
		btnComenzar.setBounds(188, 86, 130, 29);
		contentPane.add(btnComenzar);
		OyenteComenzar oyenteC = new OyenteComenzar(this);
		btnComenzar.addActionListener(oyenteC);
		btnComenzar.setEnabled(false);
		
		
		lblDirectorio= new JLabel(propiedades.getProperty("directory"));
		lblDirectorio.setBounds(48, 42, 79, 46);
		contentPane.add(lblDirectorio);
		
		ruta=new JLabel();
		ruta.setBounds(139, 50, 300, 30);
		contentPane.add(ruta);
		
		palabras = new JLabel(propiedades.getProperty("words"));
		palabras.setBounds(48, 152, 79, 46);
		contentPane.add(palabras);
		
		txtRes= new JTextArea();
		txtRes.setBounds(48,210,100,143);
		txtRes.setEditable(false);
		contentPane.add(txtRes);
		
		porcentaje = new JLabel(propiedades.getProperty("percentage"));
		porcentaje.setBounds(245, 167, 100, 16);
		contentPane.add(porcentaje);
		
		txtPorcentaje = new JTextArea();
		txtPorcentaje.setBounds(245,210,100,143);
		txtPorcentaje.setEditable(false);
		contentPane.add(txtPorcentaje);
		
		archivos = new JLabel(propiedades.getProperty("files"));
		archivos.setBounds(448, 167, 61, 16);
		contentPane.add(archivos);
		
		spanishBtn = new JButton(propiedades.getProperty("spanish"));
		spanishBtn.setBounds(565, 6, 117, 29);
		contentPane.add(spanishBtn);
		OyenteIdioma oyente = new OyenteIdioma(this);
		spanishBtn.addActionListener(oyente);
		
		englishBtn = new JButton(propiedades.getProperty("english"));
		englishBtn.setBounds(565, 42, 117, 29);
		contentPane.add(englishBtn);
		englishBtn.addActionListener(oyente);
	
	}

	public JButton getBoton() {
		return btnDirectorio;
	}

	public void setText(String txt) {
		this.txt=txt;
		ruta.setText(txt);
	}
	
	public void comenzar() {
			programa.leerDirectorio(txt);
			mostrarResultado();
			File[] f=programa.getFicheros();
			
			comboBox = new JComboBox();
			comboBox.setBounds(440, 206, 224, 27);
			contentPane.add(comboBox);
			OyenteComboBox c= new OyenteComboBox(this);
			comboBox.addActionListener(c);
			comboBox.addItem("Todos");
			
			for(int i=0; i<f.length; i++) {
				if(f[i].getName().endsWith(".txt")) {
					comboBox.addItem(f[i].getAbsolutePath());
				}
			}
			
	}

	private void mostrarResultado() {
		Entry<String, Integer>[] res = programa.getResultado();
		String palabrasString="";
		String porcentajeString="";
		for(int i=0; i<res.length; i++) {
			if (res[i] != null) {
				palabrasString+= res[i].getKey() +"\n";
				porcentajeString+=res[i].getValue()+"% \n";
			}
		}
		txtRes.setText(palabrasString);
		txtPorcentaje.setText(porcentajeString);
	}

	public JButton getBotonComenzar() {
		return btnComenzar;
	}


	public JButton getSpanishBtn() {
		return spanishBtn;
	}
	
	public JButton getEnglishBtn() {
		return englishBtn;
	}


	public void setSpanish() {
		try {
			propiedades.load(spanish);
			spanish = Ventana.class.getResourceAsStream("spanish.p");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		cargarComponentes(propiedades);
		
	}
	

	public void setEnglish() {
		try {
			propiedades.load(english);
			english = Ventana.class.getResourceAsStream("english.p");
		} catch (IOException e) {
			e.printStackTrace();
		}
		cargarComponentes(propiedades);		
	}
	
	private void cargarComponentes(Properties p) {
		spanishBtn.setText(p.getProperty("spanish"));
		englishBtn.setText(p.getProperty("english"));
		btnComenzar.setText(p.getProperty("start"));
		lblDirectorio.setText(p.getProperty("directory"));	
		palabras.setText(p.getProperty("words"));
		porcentaje.setText(p.getProperty("percentage"));
		archivos.setText(p.getProperty("files"));
		btnDirectorio.setText(p.getProperty("load"));
		
	}

	public JComboBox getComboBox() {
		return comboBox;
	}

	public void setOpcion(String op) {
		
		if(op=="Todos") {
			programa.leerDirectorio(txt);
		}else
			programa.leerTxt(op);
		
		mostrarResultado();
	}
}
