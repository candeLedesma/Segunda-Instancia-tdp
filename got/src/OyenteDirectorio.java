package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JFileChooser;

public class OyenteDirectorio implements ActionListener{

	private Ventana ventana;
	
	public OyenteDirectorio(Ventana ventana) {
		this.ventana=ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ventana.getBoton()) {
			JFileChooser fileChooser= new JFileChooser();
			fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
			int respuesta = fileChooser.showSaveDialog(null);
			if(respuesta == JFileChooser.APPROVE_OPTION) {
				File file = new File(fileChooser.getSelectedFile().getAbsolutePath());
				ventana.setText(file.getAbsolutePath());
				ventana.getBotonComenzar().setVisible(true);
				ventana.getBotonComenzar().setEnabled(true);
			}
			
		}
	}

}
