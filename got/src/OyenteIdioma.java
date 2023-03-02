package src;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class OyenteIdioma implements ActionListener{
	
	private Ventana ventana;
	
	public OyenteIdioma(Ventana ventana) {
		this.ventana=ventana;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==ventana.getSpanishBtn()) {
			ventana.setSpanish();
		}else if(e.getSource()==ventana.getEnglishBtn()) {
			ventana.setEnglish();;
		}
		
	}

}
