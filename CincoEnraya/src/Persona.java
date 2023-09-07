import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Persona {
    private String nombre;
    private BufferedImage marca;
    private String rutaImagen;

    public Persona(String nombre, String rutaImagen) {
        this.nombre = nombre;
        this.rutaImagen = rutaImagen;
        try {
            this.marca = ImageIO.read(new File(rutaImagen));
        } catch (IOException e) {
            System.out.println("Error al cargar la imagen");
        }
    }

    public String getNombre() {
        return nombre;
    }

    public String getRutaImagen() {
        return rutaImagen;
    }

    public BufferedImage getMarca() {
        return marca;
    }
}


/*
  "C:\\Users\\win11\\IdeaProjects\\CincoEnraya\\src\\JerryImagen.jpg",
                "C:\\Users\\win11\\IdeaProjects\\CincoEnraya\\src\\tomImagen.jpg",
                "C:\\Users\\win11\\IdeaProjects\\CincoEnraya\\src\\Seya.png",
                "C:\\Users\\win11\\IdeaProjects\\CincoEnraya\\src\\itachi.png"
 */