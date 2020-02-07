package deslab1;

import java.net.*;
import java.io.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * 
 * @author vinay
 */
public class Receiver {
    private static Socket socket = null;
    private static ServerSocket server = null;
    private static DataInputStream dIn = null;
    public static void main(String[] args) {
        int port = 8888;
        String plain="", keyStr="", ciphertext="";
        Scanner in = new Scanner(System.in);

        try{
            //connect
            server = new ServerSocket(port);
            socket = server.accept();
            System.out.println("Connected!");
            //get the key
            System.out.println("Enter Key: ");
            keyStr = in.nextLine();
            //receive a message
            BufferedReader br = new BufferedReader(new InputStreamReader(dIn));
            String bufferStr = "";
            while((bufferStr = br.readLine()) != null){
                ciphertext = bufferStr;
            }
            //close open vars
            socket.close();
            in.close();
            dIn.close();
        }
        catch(IOException e){
            System.out.println(e);
        }      
        
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