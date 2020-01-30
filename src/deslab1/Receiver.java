package deslab1;

import java.net.*;
import java.io.*;
import java.util.Scanner;

/**
 *
 * 
 */
public class Receiver {
    private static Socket socket = null;
    private static DataInputStream dIn = null;
    public static void main(String[] args) {
        InetAddress addr = null;
        int port = 0, key = 0;
        String plain="", cipher= "", addrStr;
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
        key = in.nextInt();      
        
        //receive message
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(dIn));
            String bufferStr = "";
            while((bufferStr = br.readLine()) != null){
                cipher = bufferStr;
            }
        }
        catch(IOException e){System.out.println("error receiving message!");}
        
        //decrypt message
        plain = DESUtil.decrypt(cipher, key);
        
        //display plaintext and ciphertext
        System.out.println("Plain: " + plain);
        System.out.println("Cipher: " + cipher);
    }  
}