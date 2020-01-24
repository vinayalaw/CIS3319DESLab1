package deslab1;
import java.net.*;
import java.io.*;
import java.util.Random;
import java.util.Scanner;
/**
 *
 * 
 */
public class Sender {
    private Socket socket = null;
    
    public static void main(String[] args) {
        InetAddress addr = null;
        int port = 0, key;
        String plain="", cipher= "", addrStr;
        Scanner in = new Scanner(System.in);
        
        //generate key
        
        //display the key
        
        //get address and port
        System.out.println("Enter Connection Address: ");
        addrStr = in.nextLine();
        System.out.println("Enter Connection Port: ");
        port = in.nextInt();
        
        try{
            addr = InetAddress.getByName(addrStr);
        }
        catch(UnknownHostException e){
            System.out.println("Invalid address!");
        }
        
        //try the connection
        try{
            socket = new Socket(addr, port);
        }
        catch(IOException e){
            System.out.println(e);
        }
        System.out.println("Connected!");
        
        
        //get message
        
        //encrypt message
        
        //send message
        
        //display plaintext and ciphertext
        System.out.println("Plain: " + plain);
        System.out.println("Cipher: " + cipher);
    }
    
    public static void encrypt(String message){
        
    }
    
}
