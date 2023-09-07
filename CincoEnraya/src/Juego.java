
import javax.swing.JOptionPane;

public class Juego {
    private Tablero tablero;
    private Persona jugador1;
    private Persona jugador2;
    private Persona jugadorActual;

    public Juego(Persona persona1, Persona persona2, Tablero tablero) {
        this.tablero = tablero;
        this.jugador1 = persona1;
        this.jugador2 = persona2;
        this.jugadorActual = jugador1;
    }

    public void jugar(int columna) {
        if (this.tablero.colocarMarca(columna, this.jugadorActual)) {
            if (this.tablero.hayGanador(jugadorActual)) {

            }
        } else {
            JOptionPane.showMessageDialog(null, "Columna llena. Intenta en otra columna.");
        }
        cambiarJugador(); // Mover esta línea aquí
    }

    public Persona getJugadorActual() {
        return jugadorActual;
    }

    public void cambiarJugador() {
        if (this.jugadorActual == this.jugador1) {
            this.jugadorActual = this.jugador2;
        } else {
            this.jugadorActual = this.jugador1;
        }
    }

    public void reiniciarJuego() {
        this.tablero = new Tablero();
        this.jugadorActual = jugador1;
    }
}
