package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class OyenteComboBox implements ActionListener{
	
	private Ventana ventana;
	
	public OyenteComboBox(Ventana ventana) {
		this.ventana=ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ventana.getComboBox()) {
			ventana.setOpcion(ventana.getComboBox().getSelectedItem().toString());
		}
		
	}

}