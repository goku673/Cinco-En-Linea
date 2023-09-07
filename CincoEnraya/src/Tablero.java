
public class Tablero {
    private final int FILAS = 8;
    private final int COLUMNAS = 9;
    private Persona[][] celdas;
    private int ultimaFila; // Nueva variable para almacenar la última fila
    public Tablero() {
        celdas = new Persona[FILAS][COLUMNAS];
        for (int i = 0; i < FILAS; i++) {
            for (int j = 0; j < COLUMNAS; j++) {
                celdas[i][j] = null;
            }
        }
    }

    public boolean colocarMarca(int columna, Persona jugador) {
        try {
            for (int i = FILAS - 1; i >= 0; i--) {
                if (celdas[i][columna] == null) {
                    if (i == FILAS - 1 || celdas[i + 1][columna] != null) {
                        celdas[i][columna] = jugador;
                        return true;
                    }
                }
            }
            return false;
        } catch (ArrayIndexOutOfBoundsException e) {
            return false;
        }
    }

    public boolean hayGanador(Persona marca) {
        // Comprobar filas
        for (int i = 0; i < FILAS; i++) {
            int contador = 0;
            for (int j = 0; j < COLUMNAS; j++) {
                if (celdas[i][j] != null && celdas[i][j].equals(marca)) {
                    contador++;
                    if (contador == 5) {
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
        }

        // Comprobar columnas
        for (int j = 0; j < COLUMNAS; j++) {
            int contador = 0;
            for (int i = 0; i < FILAS; i++) {
                if (celdas[i][j] != null && celdas[i][j].equals(marca)) {
                    contador++;
                    if (contador == 5) {
                        return true;
                    }
                } else {
                    contador = 0;
                }
            }
        }

        // Comprobar diagonal principal
        for (int i = 0; i < FILAS - 4; i++) {
            for (int j = 0; j < COLUMNAS - 4; j++) {
                int contador = 0;
                for (int k = 0; k < 5; k++) {
                    if (celdas[i + k][j + k] != null && celdas[i + k][j + k].equals(marca)) {
                        contador++;
                        if (contador == 5) {
                            return true;
                        }
                    } else {
                        contador = 0;
                    }
                }
            }
        }

        // Comprobar diagonal secundaria
        for (int i = 0; i < FILAS - 4; i++) {
            for (int j = 4; j < COLUMNAS; j++) {
                int contador = 0;
                for (int k = 0; k < 5; k++) {
                    if (celdas[i + k][j - k] != null && celdas[i + k][j - k].equals(marca)) {
                        contador++;
                        if (contador == 5) {
                            return true;
                        }
                    } else {
                        contador = 0;
                    }
                }
            }
        }

        return false;
    }
    public int getUltimaFila() {
        return ultimaFila;
    }
    public Persona[][] getCeldas() {
        return celdas;
    }

    public int getFILAS() {
        return FILAS;
    }

    public int getCOLUMNAS() {
        return COLUMNAS;
    }

    public void imprimirTablero() {
        for (int i = 0; i < celdas.length; i++) {
            for (int j = 0; j < celdas[i].length; j++) {
                System.out.print(celdas[i][j] + "\t");
            }
            System.out.println();
        }
    }



    public static  void  main( String [] args) {
        Tablero miTablero = new Tablero();
        miTablero.imprimirTablero();
        System.out.println("...................................");
      //  miTablero.imprimirTablero2();
        miTablero.imprimirTablero();
       // miTablero.colocarMarca(0, "X");

    /*
     miTablero.colocarMarca(0, "X");
        miTablero.colocarMarca(0, "X");
        miTablero.colocarMarca(0, "X");
        miTablero.colocarMarca(0, "X");
     */

        miTablero.imprimirTablero();
        /*
           System.out.println(miTablero.hayGanador("X"));
        miTablero.colocarMarca(1,"Z");
        miTablero.colocarMarca(1,"Z");
        miTablero.colocarMarca(1,"Z");
        miTablero.colocarMarca(1,"Z");
        miTablero.colocarMarca(1,"Z");
        miTablero.imprimirTablero();
        System.out.println(miTablero.hayGanador("Z"));
         */


    }
}

