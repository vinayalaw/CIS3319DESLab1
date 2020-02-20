package deslab1;

import java.net.*;
import java.io.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 *Sender: establishes connection for sender, sends message, and returns a key
 *
 */
public class Sender {
    private static Socket socket = null;
    private static DataOutputStream dOut = null;
    private static DataInputStream dIn = null;
    public static void main(String[] args) {
        InetAddress addr = null;
        int port = 0;
        String plain="", keyStr="", ciphertext="", addrStr="";
        Scanner in = new Scanner(System.in);
        DesEncrypter encrypter = null;
        
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
            dOut = new DataOutputStream(socket.getOutputStream());
            dIn = new DataInputStream(socket.getInputStream());
        }
        catch(IOException e){System.out.println(e);}
        System.out.println("Connected!");
        SecretKey key = null;
        try {
            key = KeyGenerator.getInstance("DES").generateKey();
            encrypter = new DesEncrypter(key);
            //give the key
            System.out.println("Here is the key: ");
            System.out.println(Base64.getEncoder().encodeToString(key.getEncoded())+"\n\n");
        } catch (Exception ex) {
            System.out.println("Encryption error!");
        }
        
        while(!plain.equals("exit")){
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
                
                //display plaintext and ciphertext
                
            }
            catch(IOException e){System.out.println("error sending message!");}
            plain = "";
            /************Getting a message**********/
            try{
                ciphertext = dIn.readUTF();
                System.out.println("We got the message!");
                //decrypt the message
                
                try {
                    byte[] decodedKey = Base64.getDecoder().decode(keyStr);
                    
                    plain = encrypter.decrypt(ciphertext);
                } catch (Exception ex) {
                    System.out.println("Encryption error!");
                }
                //display plaintext and ciphertext
                System.out.println("Plain: " + plain);
                System.out.println("Cipher: " + ciphertext);
            }
            catch(IOException e){
                System.out.println(e);
            }
            plain = "";
        }
        
        
        //close open vars
        try{
            //close open vars
            socket.close();
            dOut.close();
            in.close();
        }
        catch(IOException e){
            System.out.println(e);
        }
    }
}
