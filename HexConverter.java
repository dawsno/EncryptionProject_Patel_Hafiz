
/**
 * Write a description of class HexConverter here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class HexConverter
{
    public static void main(String[] args) 
    {
        System.out.println(asciiToHex("hello world"));
        System.out.println(hexToASCII(asciiToHex("hello world")));
    }

    private static String asciiToHex(String asciiValue)
    {
        char[] chars = asciiValue.toCharArray();
        StringBuffer hex = new StringBuffer();
        for (int i = 0; i < chars.length; i++)
        {
            hex.append(Integer.toHexString((int) chars[i]));
        }
        return hex.toString();
    }

    private static String hexToASCII(String hexValue)
    {
        StringBuilder output = new StringBuilder("");
        for (int i = 0; i < hexValue.length(); i += 2)
        {
            String str = hexValue.substring(i, i + 2);
            output.append((char) Integer.parseInt(str, 16));
        }
        return output.toString();
    }
}