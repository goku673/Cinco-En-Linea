

import org.junit.Assert;
import org.junit.Test;

    public class PersonaTest {

        @Test
        public void testPersona() {
            String nombre = "Juan";
            String rutaImagen = "C:\\Users\\win11\\Desktop\\IdeaProjects\\CincoEnraya\\Image\\JerryImagen.jpg";
            Persona persona = new Persona(nombre, rutaImagen);

            Assert.assertNotNull(persona.getMarca());
        }

        @Test
        public void testNombre (){
            String nombre = "Juan";
            String rutaImagen = "C:\\Users\\win11\\Desktop\\IdeaProjects\\CincoEnraya\\Image\\JerryImagen.jpg";
            Persona persona = new Persona(nombre, rutaImagen);
            Assert.assertEquals(nombre, persona.getNombre());

        }

        @Test
        public  void testRutaImagen(){

            String nombre = "Juan";
            String rutaImagen = "C:\\Users\\win11\\Desktop\\IdeaProjects\\CincoEnraya\\Image\\JerryImagen.jpg";
            Persona persona = new Persona(nombre, rutaImagen);
            Assert.assertEquals(rutaImagen, persona.getRutaImagen());
        }
    }

