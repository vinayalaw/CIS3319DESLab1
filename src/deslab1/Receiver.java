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
    private static DataOutputStream dOut = null;
    private static DataInputStream dIn = null;
    public static void main(String[] args) {
        int port = 8888;
        String plain="", keyStr="", ciphertext="";
        Scanner in = new Scanner(System.in);
        DesEncrypter encrypter = null;
        SecretKey key = null;
        
        try{
            //connect
            server = new ServerSocket(port);
            socket = server.accept();
            System.out.println("Connected!");
            //receive a message
            dIn = new DataInputStream(socket.getInputStream());
            dOut = new DataOutputStream(socket.getOutputStream());
            System.out.println("Enter Key: ");
            keyStr = in.nextLine();
            byte[] decodedKey = Base64.getDecoder().decode(keyStr);
            key = new SecretKeySpec(decodedKey, 0, decodedKey.length, "DES");
            encrypter = new DesEncrypter(key);           
        }
        catch(Exception e){
            System.out.println(e);
        }
        
        while(!plain.equals("exit")){
            /************Getting a message**********/
            try{
                ciphertext = dIn.readUTF();
                System.out.println("We got the message!");
                //decrypt the message
                System.out.println("Plain: " + plain);
                System.out.println("Cipher: " + ciphertext);
                try {                                        
                    plain = encrypter.decrypt(ciphertext);
                } catch (Exception ex) {
                    System.out.println("Encryption error!");
                }
                //display plaintext and ciphertext
                
            }
            catch(IOException e){
                System.out.println(e);
            }
            plain = "";
            /*******Sending a message*********/
            //get message
            System.out.println("Please write your message then type enter: ");
            while(plain.equals("")){
                plain = in.nextLine();
                try{
                    ciphertext = encrypter.encrypt(plain);
                }
                catch(Exception e){
                    System.out.println(e);
                }
            }
            try{
                //send the message
                dOut.writeUTF(ciphertext);
                dOut.flush();
                //give the key
                System.out.println("Here is the key: ");
                System.out.println(Base64.getEncoder().encodeToString(key.getEncoded())+"\n\n");
                
                //display plaintext and ciphertext
                System.out.println("Plain: " + plain);
                System.out.println("Cipher: " + ciphertext);
            }
            catch(IOException e){System.out.println("error sending message!");}
            
            
            plain = "";
        }
        
        try{
            socket.close();
            in.close();
            dIn.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
