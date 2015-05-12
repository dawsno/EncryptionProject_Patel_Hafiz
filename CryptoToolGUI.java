import java.util.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.io.*;
import java.security.*;
import javax.crypto.*;
import java.nio.file.*;
public class CryptoToolGUI extends JPanel {
    private JTextArea Message;
    private JLabel Label1;
    private JButton RESB;
    private JButton AESB;
    private JButton DESB;
    private JButton HEXB;
    private JButton RailB;
    private JButton Show;
    private JButton Decrypt;
    private JLabel Label2;
    private JTextArea EMessage;
    private int x;
    private String encoded;
    private String decoded;
    public static final String PRIVATE_KEY_FILE = "C:/keys/private.key";
    public static final String PUBLIC_KEY_FILE = "C:/keys/public.key";
    public CryptoToolGUI() {
        //construct components
        Message = new JTextArea (5, 5);
        Label1 = new JLabel ("Insert Message Below");
        RESB = new JButton ("RES");
        AESB = new JButton ("AES");
        DESB = new JButton ("DES");
        HEXB = new JButton ("Hex Convert");
        RailB = new JButton ("RailFence");
        Show = new JButton ("Show Encrypted Text");
        Decrypt = new JButton ("Decrypt");
        
        //adjust size and set layout
        setPreferredSize (new Dimension (784, 638));
        setLayout (null);

        //add components
        add (Message);
        add (Label1);
        add (RESB);
        add (AESB);
        add (DESB);
        add (HEXB);
        add (RailB);
        add (Show);
        add (Decrypt);

        //set component bounds 
        Message.setBounds (20, 35, 735, 220);
        Label1.setBounds (20, 5, 150, 30);
        RESB.setBounds (20, 275, 100, 25);
        AESB.setBounds (125, 275, 100, 25);
        DESB.setBounds (230, 275, 100, 25);
        HEXB.setBounds (335, 275, 105, 25);
        RailB.setBounds (445, 275, 100, 25);
        Show.setBounds (20, 305, 205, 25);
        Decrypt.setBounds (285, 550, 185, 60);
        //set action listeners
        RESB.addActionListener(new ActionListener() {//encrypt RSA
                @Override
                public void actionPerformed(ActionEvent e) {
                    x=1;
                    RSATool rsa=new RSATool();
                    try {

                        // Check if the pair of keys are present else generate those.
                        if (!rsa.areKeysPresent()) {
                            // Method generates a pair of keys using the RSA algorithm and stores it
                            // in their respective files
                            rsa.generateKey();
                        }

                        final String originalText = Message.getText();
                        ObjectInputStream inputStream = null;

                        // Encrypt the string using the public key
                        inputStream = new ObjectInputStream(new FileInputStream(PUBLIC_KEY_FILE));
                        final PublicKey publicKey = (PublicKey) inputStream.readObject();
                        final byte[] cipherText = rsa.encrypt(originalText, publicKey);

                        // Decrypt the cipher text using the private key.
                        inputStream = new ObjectInputStream(new FileInputStream(PRIVATE_KEY_FILE));
                        final PrivateKey privateKey = (PrivateKey) inputStream.readObject();
                        final String plainText = rsa.decrypt(cipherText, privateKey);
                        encoded=cipherText.toString();
                        decoded=plainText.toString();
                        // Printing the Original, Encrypted and Decrypted Text
                        //System.out.println("Original Text: " + originalText);
                        //System.out.println("Encrypted Text: " +cipherText.toString());
                        //System.out.println("Decrypted Text: " + plainText);

                    } catch (Exception a) {
                        a.printStackTrace();
                    }
                }
            });  

        AESB.addActionListener(new ActionListener() {//next iteration. if no input given uses defaults
                @Override
                public void actionPerformed(ActionEvent e) {
                    x=2;
                    AESTool aes=new AESTool(Message.getText());
                    try {
                    aes.main(new String[0]);
                } catch (Exception a) {
                    
                }
                }
            }); 
            
            DESB.addActionListener(new ActionListener() {//next iteration. if no input given uses defaults
                @Override
                public void actionPerformed(ActionEvent e) {
                   x=3;
                   DESTool des=new DESTool(Message.getText());
                    try {
                    des.main(new String[0]);
                    encoded=des.getE();
                    decoded=des.getD();
                } catch (Exception a) {
                    
                }
                }
            }); 
            
            HEXB.addActionListener(new ActionListener() {//next iteration. if no input given uses defaults
                @Override
                public void actionPerformed(ActionEvent e) {
                   x=4;
                   HexConverter hex= new HexConverter();
                   encoded=hex.asciiToHex(Message.getText());
                }
            }); 
            
            RailB.addActionListener(new ActionListener() {//next iteration. if no input given uses defaults
                @Override
                public void actionPerformed(ActionEvent e) {
                   x=5;
                   RailFence rail=new RailFence();
                   try{encoded=rail.encode(Message.getText());}
                   catch (Exception a){}
                }
            });
            
            Show.addActionListener(new ActionListener() {//next iteration. if no input given uses defaults
                @Override
                public void actionPerformed(ActionEvent e) {
                   switch(x)
                   {
                       case 1: System.out.println(encoded);
                       break;
                       
                       case 2:try{Scanner in = new Scanner(new FileReader("encryptedtext.txt"));
                           while (in.hasNext()) { System.out.println (in.next()); }
                        }
                        catch (Exception a){}
                        break;
                       
                       case 3: System.out.println(encoded);
                       break;
                       case 4:System.out.println((encoded));
                       break;
                       case 5: 
                   System.out.println((encoded));
                   
                       break;
                       default: System.out.println(Message.getText());
                       break;
                }
            }
            });
            Decrypt.addActionListener(new ActionListener() {//next iteration. if no input given uses defaults
                @Override
                public void actionPerformed(ActionEvent e) {
                   switch(x)
                   {
                       case 1: System.out.println(decoded);
                       break;
                       
                       case 2:try{Scanner in = new Scanner(new FileReader("decryptedtext.txt"));
                           while (in.hasNext()) { System.out.println (in.next()); }
                        }
                        catch (Exception a){}
                        break;
                       
                       case 3: System.out.println(decoded);
                       break;
                       case 4:HexConverter hex= new HexConverter();
                   System.out.println(hex.hexToASCII(encoded));
                       break;
                       case 5: RailFence rail=new RailFence();
                   try{System.out.println(rail.decode(encoded));}
                   catch (Exception a){}
                       break;
                       default: System.out.println(Message.getText());
                       break;
                }
            }
    });
}
    
    public static void main (String[] args) {
        JFrame frame = new JFrame ("MyPanel");
        frame.setDefaultCloseOperation (JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add (new CryptoToolGUI());
        frame.pack();
        frame.setVisible (true);
    }
}
