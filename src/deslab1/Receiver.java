package deslab1;

import java.net.*;
import java.io.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * 
 */
public class Receiver {
    private static Socket socket = null;
    private static DataInputStream dIn = null;
    public static void main(String[] args) {
        InetAddress addr = null;
        int port = 0;
        String plain="", addrStr, keyStr;
        Scanner in = new Scanner(System.in);
        
        //get address and port
        System.out.println("Enter Connection Address: ");
        addrStr = in.nextLine();
        System.out.println("Enter Connection Port: ");
        port = in.nextInt();        
        try{addr = InetAddress.getByName(addrStr);}
        catch(UnknownHostException e){System.out.println("Invalid address!");}
        
        //try the connection
        try{
            socket = new Socket(addr, port);
            dIn = new DataInputStream(socket.getInputStream());
        }
        catch(IOException e){System.out.println(e);}
        System.out.println("Connected!");
        
        //get the key from user
        System.out.println("Enter Key: ");
        keyStr = in.nextLine();
        
        //receive message
        String ciphertext="";
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(dIn));
            String bufferStr = "";
            while((bufferStr = br.readLine()) != null){
                ciphertext = bufferStr;
            }
        }
        catch(IOException e){System.out.println("error receiving message!");}
        
        //decrypt the message
        SecretKey key = null;
        try {
            byte[] decodedKey = Base64.getDecoder().decode(keyStr);
            key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
            DesEncrypter encrypter = new DesEncrypter(key);
            ciphertext = encrypter.decrypt(ciphertext);
        } catch (Exception ex) {
            System.out.println("Encryption error!");
        }
        
        //display plaintext and ciphertext
        System.out.println("Plain: " + plain);
        System.out.println("Cipher: " + ciphertext);
    }  
}