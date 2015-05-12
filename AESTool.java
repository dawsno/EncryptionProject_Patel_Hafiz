import java.nio.file.*;
import javax.crypto.*;

public class AESTool {
    static String text;
    public AESTool(String n)
    {
        text=n;
    }
    public static void main(String[] args) throws Exception {
        String FileName = "encryptedtext.txt";
        String FileName2 = "decryptedtext.txt";
        KeyGenerator KeyGen = KeyGenerator.getInstance("AES");
        KeyGen.init(128);

        SecretKey SecKey = KeyGen.generateKey();

        Cipher AesCipher = Cipher.getInstance("AES");


        byte[] byteText = text.getBytes();

        AesCipher.init(Cipher.ENCRYPT_MODE, SecKey);
        byte[] byteCipherText = AesCipher.doFinal(byteText);
        Files.write(Paths.get(FileName), byteCipherText);


        byte[] cipherText = Files.readAllBytes(Paths.get(FileName));

        AesCipher.init(Cipher.DECRYPT_MODE, SecKey);
        byte[] bytePlainText = AesCipher.doFinal(cipherText);
        Files.write(Paths.get(FileName2), bytePlainText);
    }
}