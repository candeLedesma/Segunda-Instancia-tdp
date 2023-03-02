package src;

public class Launcher {

	public static void main(String arg[]) {
		javax.swing.SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Ventana();
			}
		});
	}
}
