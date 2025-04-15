package interfaz;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;


public class VentanaJuego extends JFrame {

    private Juego juego;
    private BotonCelda[][] botones;

    public VentanaJuego() {
        juego = new Juego();
        botones = new BotonCelda[juego.getTamanio()][juego.getTamanio()];

        setTitle("Histeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 500);
        getContentPane().setLayout(new GridLayout(juego.getTamanio(), juego.getTamanio(), 2, 2));

        for (int i = 0; i < juego.getTamanio(); i++) {
            for (int j = 0; j < juego.getTamanio(); j++) {
                BotonCelda boton = new BotonCelda(i, j);
                boton.setOpaque(true);
                boton.setBackground(Color.LIGHT_GRAY);
                boton.addActionListener(e -> {
                    juego.jugarTurno(boton.getFila(), boton.getColumna());
                    actualizarGrilla();
                    if (juego.estaCompleto()) {
                        JOptionPane.showMessageDialog(this, "¡Ganaste!");
                    }
                });
                botones[i][j] = boton;
                getContentPane().add(boton);
            }
        }

        setVisible(true);
    }

    private void actualizarGrilla() {
        for (int i = 0; i < juego.getTamanio(); i++) {
            for (int j = 0; j < juego.getTamanio(); j++) {
                Color color = juego.getColorEn(i, j);
                if (color == null) {
                    botones[i][j].setBackground(Color.LIGHT_GRAY);
                } else {
                    botones[i][j].setBackground(color);
                }
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new VentanaJuego());
    }
}
