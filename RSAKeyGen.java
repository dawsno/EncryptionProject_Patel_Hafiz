import java.security.*;
import java.security.spec.*;
import java.math.*;
import java.io.*;
/**
 * Write a description of class KeyGen here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class RSAKeyGen
{
    // instance variables - replace the example below with your own
    static Key publicKey;
    static Key privateKey;

    /**
     * Constructor for objects of class KeyGen
     */

    public static void main(String[] args) throws Exception {
        KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");
        kpg.initialize(2048);
        KeyPair kp = kpg.genKeyPair();
        publicKey = kp.getPublic();
        privateKey = kp.getPrivate();
        kpg.getAlgorithm();
        System.out.println(publicKey);
        System.out.println(privateKey);

        KeyFactory fact = KeyFactory.getInstance("RSA");
        RSAPublicKeySpec pub = fact.getKeySpec(kp.getPublic(),
                RSAPublicKeySpec.class);
        RSAPrivateKeySpec priv = fact.getKeySpec(kp.getPrivate(),
                RSAPrivateKeySpec.class);
       System.out.println(pub);
        System.out.println(priv);
        saveToFile("RSApublic.key", pub.getModulus(),
            pub.getPublicExponent());
        saveToFile("RSAprivate.key", priv.getModulus(),
            priv.getPrivateExponent());

  
    }

    static void saveToFile(String fileName,
    BigInteger mod, BigInteger exp) throws IOException {
        ObjectOutputStream oout = new ObjectOutputStream(
                new BufferedOutputStream(new FileOutputStream(fileName)));
        try {
            oout.writeObject(mod);
            oout.writeObject(exp);
        } catch (Exception e) {
            throw new IOException("Unexpected error", e);
        } finally {
            oout.close();
        }
    }
}

