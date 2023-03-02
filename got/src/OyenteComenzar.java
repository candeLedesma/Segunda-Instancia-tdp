package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OyenteComenzar implements ActionListener{
	
	private Ventana ventana;
	
	public OyenteComenzar(Ventana ventana) {
		this.ventana=ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ventana.comenzar();
		ventana.getBotonComenzar().setEnabled(false);;
	}

}
