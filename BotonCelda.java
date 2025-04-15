package interfaz;

import javax.swing.JButton;

public class BotonCelda extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int fila;
    private int columna;

    public BotonCelda(int fila, int columna) {
        this.fila = fila;
        this.columna = columna;
        this.setOpaque(true);
        this.setBorderPainted(true);
    }

    public int getFila() {
        return fila;
    }

    public int getColumna() {
        return columna;
    }
}