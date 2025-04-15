package interfaz;

import java.awt.Color;
import java.util.Random;

public class Juego {
	private int tamanio = 5;
	private int CANT_COLORES = 6;
	private Color[][] grilla;
	private Random random;
	private int turnos;


	public Juego() {
		grilla = new Color[tamanio][tamanio];
		random = new Random();
	}

	public void jugarTurno(int fila, int columna) {
		Color nuevoColor = obtenerColorAleatorio();
		grilla[fila][columna] = nuevoColor;
		turnos ++;

		if (tieneVecinoIgual(fila, columna, nuevoColor)) {
			apagarCeldaYVecinos(fila, columna);
		}
	}

	private Color obtenerColorAleatorio() {
		Color[] colores = { Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW, Color.ORANGE, Color.MAGENTA };
		return colores[random.nextInt(CANT_COLORES)];
	}

	private boolean tieneVecinoIgual(int fila, int columna, Color color) {
		int[][] direcciones = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		for (int[] d : direcciones) {
			int f = fila + d[0];
			int c = columna + d[1];
			if (estaEnRango(f, c) && color.equals(grilla[f][c])) {
				return true;
			}
		}
		return false;
	}

	private void apagarCeldaYVecinos(int fila, int columna) {
		grilla[fila][columna] = null;
		int[][] direcciones = { { -1, 0 }, { 1, 0 }, { 0, -1 }, { 0, 1 } };
		for (int[] d : direcciones) {
			int f = fila + d[0];
			int c = columna + d[1];
			if (estaEnRango(f, c)) {
				grilla[f][c] = null;
			}
		}
	}

	private boolean estaEnRango(int fila, int columna) {
		return fila >= 0 && fila < tamanio && columna >= 0 && columna < tamanio;
	}

	public Color getColorEn(int fila, int columna) {
		return grilla[fila][columna];
	}

	public boolean estaCompleto() {
		for (int i = 0; i < tamanio; i++) {
			for (int j = 0; j < tamanio; j++) {
				if (grilla[i][j] == null) {
					return false;
				}
			}
		}
		return true;
	}

	public int getTamanio() {
		return tamanio;
	}
	
	public int getTurnos() {
	    return turnos;
	}

}