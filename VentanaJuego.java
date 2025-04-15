package interfaz;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class VentanaJuego extends JFrame {

    private static final long serialVersionUID = 1L;
    private Juego juego;
    private BotonCelda[][] botones;
    private JLabel labelTurnos;
    private int record = Integer.MAX_VALUE;

    public VentanaJuego() {
        juego = new Juego();
        botones = new BotonCelda[juego.getTamanio()][juego.getTamanio()];

        setTitle("Histeria");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 500, 550); // un poco más alto por el label
        getContentPane().setLayout(new BorderLayout());

        // Panel superior con el contador de turnos
        labelTurnos = new JLabel("Turnos: 0");
        JPanel panelSuperior = new JPanel();
        panelSuperior.add(labelTurnos);
        getContentPane().add(panelSuperior, BorderLayout.NORTH);

        // Panel central con la grilla
        JPanel panelGrilla = new JPanel(new GridLayout(juego.getTamanio(), juego.getTamanio(), 2, 2));
        for (int i = 0; i < juego.getTamanio(); i++) {
            for (int j = 0; j < juego.getTamanio(); j++) {
                BotonCelda boton = new BotonCelda(i, j);
                boton.setOpaque(true);
                boton.setBackground(Color.LIGHT_GRAY);
                boton.setFocusPainted(false);
                boton.setBorder(null);
                boton.addActionListener(e -> {
                    juego.jugarTurno(boton.getFila(), boton.getColumna());
                    actualizarGrilla();
                    labelTurnos.setText("Turnos: " + juego.getTurnos());

                    if (juego.estaCompleto()) {
                        int turnosUsados = juego.getTurnos();
                        String mensaje = "¡Ganaste en " + turnosUsados + " turnos!";
                        if (turnosUsados < record) {
                            record = turnosUsados;
                            mensaje += "\n¡Nuevo récord!";
                        } else {
                            mensaje += "\nRécord actual: " + record + " turnos.";
                        }
                        JOptionPane.showMessageDialog(this, mensaje);
                    }
                });
                botones[i][j] = boton;
                panelGrilla.add(boton);
            }
        }
        getContentPane().add(panelGrilla, BorderLayout.CENTER);

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
