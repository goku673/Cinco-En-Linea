import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;

public class InterfazGrafica  extends  JFrame{




    public InterfazGrafica(){
        setTitle("ventana inicio");
        setSize(740,640);

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        File file = new File("C:\\Users\\win11\\Desktop\\IdeaProjects\\CincoEnraya\\Image\\videoGames.png");
        System.out.println("mi  path correcto" +file);
        BufferedImage image = null;

        try {
            image = ImageIO.read(file);
            image = resize(image,600,400);
        }catch (Exception e){
            JOptionPane.showMessageDialog(null,"nose pudo cargar la imagen de fondo","error",JOptionPane.ERROR_MESSAGE);
            e.printStackTrace();
        }



        JPanel panelPrincipal = new JPanel();

        panelPrincipal.setBackground( Color.DARK_GRAY);
        panelPrincipal.setBorder(BorderFactory.createCompoundBorder(
                        BorderFactory.createLineBorder(Color.BLACK,10,true),
                        BorderFactory.createEmptyBorder(20,20,20,20)
                )
        );

        JLabel imagenFondo = new JLabel(new ImageIcon(image));
        JButton  firsButton = new JButton("Start  game");

        firsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String  nombreJugador1  = JOptionPane.showInputDialog(null,"Porfavor ingrese su nombre Jugado 1",
                        "Ingreso de nombre",JOptionPane.PLAIN_MESSAGE);
                String  nombreJugador2 = JOptionPane.showInputDialog(null,"Porfavor ingrese  su nombre Jugador 2",
                        "Ingreso de nombre",JOptionPane.PLAIN_MESSAGE);
                if((nombreJugador1 != null && !nombreJugador1.isEmpty()) && (nombreJugador2 != null  && !nombreJugador2.isEmpty())){

                    InterfazGrafica2  cincoEnLinea = new InterfazGrafica2(nombreJugador1,nombreJugador2 );
                    cincoEnLinea.setVisible(true);
                }else {
                    JOptionPane.showMessageDialog(null,"Ups te falto ingresar un nombre!!!!!!!","Error",JOptionPane.ERROR_MESSAGE);

                }
            }
        });

        panelPrincipal.add(imagenFondo);
        panelPrincipal.add(firsButton);
        add(panelPrincipal);


    }

    public static BufferedImage resize(BufferedImage img, int newW, int newH) {
        Image tmp = img.getScaledInstance(newW, newH, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(newW, newH, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
}
