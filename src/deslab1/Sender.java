package deslab1;

import java.net.*;
import java.io.*;
import java.util.Base64;
import java.util.Scanner;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

/**
 *Sender: establishes connection for sender, sends message, and returns a key
 *
 */
public class Sender {
    private static Socket socket = null;
    private static DataOutputStream dOut = null;
    
    public static void main(String[] args) {
        InetAddress addr = null;
        int port = 0;
        String plain="", addrStr, ciphertext="";
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
            dOut = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException e){System.out.println(e);}
        System.out.println("Connected!");
        
        //get message
        System.out.println("Please write your message then type enter: ");
        while(plain.equals("")){
            plain = in.nextLine();
        }       

        //encrypt
        SecretKey key = null;
        try {
            key = KeyGenerator.getInstance("DES").generateKey();
            DesEncrypter encrypter = new DesEncrypter(key);
            ciphertext = encrypter.encrypt(plain);
        } catch (Exception ex) {
            System.out.println("Encryption error!");
        }

        try{
            //send the message
            dOut.writeUTF(ciphertext);
            dOut.flush();
            //close open vars
            socket.close();
            dOut.close();
            in.close();
        }
        catch(IOException e){System.out.println("error sending message!");}
               
        //give the key
        System.out.println("Here is the key: ");        
        System.out.println(Base64.getEncoder().encodeToString(key.getEncoded())+"\n\n");
        
        //display plaintext and ciphertext
        System.out.println("Plain: " + plain);
        System.out.println("Cipher: " + ciphertext);
    }
}