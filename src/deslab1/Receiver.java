package deslab1;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

/**
 *
 * 
 */
public class Receiver {
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
        
        
        //receive message
        
        //decrypt message
        
        //display plaintext and ciphertext
        System.out.println("Plain: " + plain);
        System.out.println("Cipher: " + cipher);
    }
    
    public static void decrypt(String message){
        
    }
    
}
