import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;
public class InterfazGrafica2 extends JFrame {
    private Tablero tablero;
    private Juego juego;
    private JButton[][] casillas;
    private JLabel jugadorActualLabel;
    private boolean juegoTerminado; // Variable para indicar si el juego ha terminado

    private  String rutaImagenSeleccionada;
    public InterfazGrafica2(String nombreJugador1, String nombreJugador2) {
        setTitle("Cinco en línea");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);

        tablero = new Tablero();
        Persona jugador1 = new Persona(nombreJugador1, "C:\\Users\\win11\\Desktop\\IdeaProjects\\CincoEnraya\\Image\\tomImagen.jpg"); // Ruta de la imagen del jugador 1
        Persona jugador2 = new Persona(nombreJugador2, "C:\\Users\\win11\\Desktop\\IdeaProjects\\CincoEnraya\\Image\\Seya.png"); // Ruta de la imagen del jugador 2
        juego = new Juego(jugador1, jugador2, tablero);
        juegoTerminado = false; // Inicialmente el juego no ha terminado

        JPanel panelPrincipal = new JPanel(new BorderLayout());
        JPanel panelTablero = new JPanel(new GridLayout(tablero.getFILAS(), tablero.getCOLUMNAS()));
        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.CENTER));
        jugadorActualLabel = new JLabel("Turno: " + juego.getJugadorActual().getNombre());

        casillas = new JButton[tablero.getFILAS()][tablero.getCOLUMNAS()];
        for (int i = 0; i < tablero.getFILAS(); i++) {
            for (int j = 0; j < tablero.getCOLUMNAS(); j++) {
                casillas[i][j] = new JButton();
                casillas[i][j].setBackground(Color.WHITE);
                casillas[i][j].addActionListener(new CasillaActionListener(j));
                panelTablero.add(casillas[i][j]);
            }
        }

        panelBotones.add(jugadorActualLabel);

        panelPrincipal.add(panelTablero, BorderLayout.CENTER);
        panelPrincipal.add(panelBotones, BorderLayout.SOUTH);

        add(panelPrincipal);

        actualizarTablero();
    }

    private void actualizarTablero() {
        Persona[][] celdas = tablero.getCeldas();
        for (int i = 0; i < tablero.getFILAS(); i++) {
            for (int j = 0; j < tablero.getCOLUMNAS(); j++) {
                Persona jugador = celdas[i][j];
                ImageIcon icono;
                if (jugador != null) {
                    ImageIcon jugadorIcono = new ImageIcon(jugador.getMarca());
                    Image jugadorImagen = jugadorIcono.getImage();
                    Image jugadorRedimensionada = jugadorImagen.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
                    icono = new ImageIcon(jugadorRedimensionada);
                } else {
                    icono = new ImageIcon("ruta_imagen_celda_vacia.jpg"); // Ruta de la imagen de celda vacía
                }
                casillas[i][j].setIcon(icono);
            }
        }
    }

    private void deshabilitarCasillas() {
        for (int i = 0; i < tablero.getFILAS(); i++) {
            for (int j = 0; j < tablero.getCOLUMNAS(); j++) {
                casillas[i][j].setEnabled(false);
            }
        }
    }

    // mi ventana para mostrar

    private class CasillaActionListener implements ActionListener {
        private int columna;

        public CasillaActionListener(int columna) {
            this.columna = columna;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (!juegoTerminado) { // Verificar si el juego ha terminado
                juego.jugar(columna);

                if (tablero.hayGanador(juego.getJugadorActual())) {
                    // Crear ventana emergente para mostrar al ganador
                    JFrame frameGanador = new JFrame("¡Felicidades!");
                    frameGanador.setSize(400, 400);
                    JPanel panelPrincipal = new JPanel(new BorderLayout());

                    // Agregar imagen del jugador ganador y su nombre en la parte superior de la ventana
                    JLabel labelImagen = new JLabel();
                    ImageIcon iconoGanador = new ImageIcon(juego.getJugadorActual().getMarca());
                    int ancho = 200; // Ancho de la imagen
                    int alto = 200; // Alto de la imagen
                    Image imagenOriginal = iconoGanador.getImage();
                    BufferedImage imagenFutura = new BufferedImage(ancho, alto, BufferedImage.TYPE_INT_ARGB);
                    Graphics2D g = imagenFutura.createGraphics();
                    g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                    g.setClip(new Ellipse2D.Float(0, 0, ancho, alto));
                    g.drawImage(imagenOriginal, 0, 0, ancho, alto, null);
                    g.dispose();
                    ImageIcon iconoFuturista = new ImageIcon(imagenFutura);
                    labelImagen.setIcon(iconoFuturista);
                    labelImagen.setBorder(BorderFactory.createEmptyBorder(0,90,0,0));

                    JLabel labelNombre = new JLabel("<html><center>Felicidades<br>" + juego.getJugadorActual().getNombre().toUpperCase() + "</center><br>¡Tu premio es nada!</html>");
                    labelNombre.setHorizontalAlignment(SwingConstants.CENTER);
                    Font font=new Font("Arial",Font.BOLD,30);
                    labelNombre.setFont(font);
                    labelNombre.setForeground(Color.WHITE);
                    labelNombre.setForeground(new Color(250, 11, 11));

                    panelPrincipal.setBackground(new Color(158, 67, 186)); // Lila oscuro
                    panelPrincipal.add(labelImagen,BorderLayout.NORTH);
                    panelPrincipal.add(labelNombre,BorderLayout.CENTER);

                    // Establecer el panel principal en la ventana emergente
                    frameGanador.setContentPane(panelPrincipal);
                    frameGanador.setLocationRelativeTo(null); // Centrar la ventana emergente en pantalla
                    frameGanador.setVisible(true);

                    juegoTerminado = true; // Establecer el juego como terminado
                    deshabilitarCasillas(); // Deshabilitar las casillas de juego);
                } else {
                    jugadorActualLabel.setText("Turno: " + juego.getJugadorActual().getNombre());
                    actualizarTablero();
                }
            }
        }
    }
}
