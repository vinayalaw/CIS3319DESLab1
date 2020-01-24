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
    private static Socket socket = null;
    private static DataOutputStream dOut = null;
    
    public static void main(String[] args) {
        InetAddress addr = null;
        int port = 0, key;
        String plain="", cipher= "", addrStr;
        Scanner in = new Scanner(System.in);
        
        
        //generate key
        key = (int) (Math.random() * 1024);
        //display the key
        System.out.println("Here is the Key: "+key);
        System.out.println("Send it to the receiver");
        
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
            dOut = new DataOutputStream(socket.getOutputStream());
        }
        catch(IOException e){
            System.out.println(e);
        }
        System.out.println("Connected!");       
        
        //get message
        System.out.println("Please write your message then type enter: ");
        plain = in.next();
        
        //encrypt message
        cipher = encrypt(plain, key);
        
        //send message
        try{
            dOut.writeByte(cipher.getBytes().length);
            dOut.writeUTF(cipher);
            dOut.flush();
        }
        catch(IOException e){
            System.out.println("error sending message!");
        }
        
        //display plaintext and ciphertext
        System.out.println("Plain: " + plain);
        System.out.println("Cipher: " + cipher);
    }
    
    public static String encrypt(String message, int key){
        String result = "";
        byte[] bin = message.getBytes();
        
        
        return result;
    }
    
}
