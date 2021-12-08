package source;

import javax.swing.*;
import java.awt.*;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class TrackFocusDemo extends JPanel {
    Picture pic1,pic2,pic3,pic4,pic5, pic6;
    JLabel info;

    static String mayaString="Maya";
    static String anyaString="Anya";
    static String laineString="Laine";
    static String cosmoString="Cosmo";
    static String adeleString="Adele";
    static String alexisString="Alexis";

    String [] comments = {"Ah! Que es esto?", "Esta es Maya", "Esta es Anya", "Esta es Laine", "Este es cosmo",
                           "Esta es Adele", "Este es Alexis"};


    public TrackFocusDemo(){
        super(new BorderLayout());
        JPanel photoPanel= new JPanel(new GridLayout(2,3));

        //------------------------------------------ 1 -------------------------------------
        pic1= new Picture(createImageIcon("/images/"+mayaString+".jpg", mayaString).getImage());
        pic1.setName("pic 1");
        photoPanel.add(pic1);

        //------------------------------------------ 2 -------------------------------------
        pic2= new Picture(createImageIcon("/images/"+anyaString+".jpg", anyaString).getImage());
        pic2.setName("pic 2");
        photoPanel.add(pic2);

        //------------------------------------------ 3 -------------------------------------
        pic3= new Picture(createImageIcon("/images/"+laineString+".jpg", laineString).getImage());
        pic3.setName("pic 3");
        photoPanel.add(pic3);

        //------------------------------------------ 4 -------------------------------------
        pic4= new Picture(createImageIcon("/images/"+laineString+".jpg", laineString).getImage());
        pic4.setName("pic 4");
        photoPanel.add(pic4);

        //------------------------------------------ 5 -------------------------------------
        pic5= new Picture(createImageIcon("/images/"+cosmoString+".jpg", cosmoString).getImage());
        pic5.setName("pic 5");
        photoPanel.add(pic5);

        //------------------------------------------ 6 -------------------------------------
        pic6= new Picture(createImageIcon("/images/"+adeleString+".jpg", adeleString).getImage());
        pic6.setName("pic 6");
        photoPanel.add(pic6);

        info= new JLabel("None selected!");
        setPreferredSize(new Dimension(800,600));
        add(photoPanel, BorderLayout.CENTER);
        add(info, BorderLayout.SOUTH);
        setBorder(BorderFactory.createEmptyBorder(20,20,20,20));

        KeyboardFocusManager focusManager =  KeyboardFocusManager.getCurrentKeyboardFocusManager();
        focusManager.addPropertyChangeListener(new PropertyChangeListener() {
            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                String prop = evt.getPropertyName();
                if("focusOwner".equals(prop) && (evt.getNewValue() instanceof Picture)){
                    Component comp =(Component) evt.getNewValue();
                    String name = comp.getName();
                    int num = Integer.parseInt(name);
                    if (num < 0 || num > comments.length){
                        num=0;
                    }
                    info.setText(comments[num]);
                }
            }
        });
    }

    protected static void createShowGUI(){
        JFrame frame = new JFrame("Photos and Focus");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JComponent newContentPane =  new TrackFocusDemo();
        newContentPane.setOpaque(true);
        frame.setContentPane(newContentPane);
        frame.pack();
        frame.setVisible(true);
    }

    protected static ImageIcon createImageIcon(String path, String description) {
        java.net.URL imageURL = TrackFocusDemo.class.getResource(path);
        if(imageURL==null){
            System.err.println("File not found" + path);
            return null;
        }else{
            return new ImageIcon(imageURL, description);
        }
    }

    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createShowGUI();
            }
        });
    }


}
