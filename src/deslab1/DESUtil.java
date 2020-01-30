package deslab1;

/**
 *
 * 
 */
class DESUtil {
    
    //generate 56 bit key
    public static int genKey() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    //encrypt the message with given key
    public static String encrypt(String message, int key){
        String result = "";
        byte[] bin = message.getBytes();       
        //DES encryption here
        return result;
    }
     
    //decrypt the message with the given key
    public static String decrypt(String message, int key){
        String result = "";
        byte[] bin = message.getBytes();      
        //DES decryption here
        return result;
    }
}
