package main.java.utils;


import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

public class Encriptacio {


    private static String CIPHER_NAME = "AES/CBC/PKCS5PADDING";
    private static int CIPHER_KEY_LEN = 16; //128 bits
    private static String KEY =  "0123456789abcdef";
    private static String initV =  "aaaaaaaaaaaaaaaa";

    /**
     * Encrypt data using AES Cipher (CBC) with 128 bit key
     *
     *
     * @param key  - key to use should be 16 bytes long (128 bits)
     * @param iv - initialization vector
     * @param data - data to encrypt
     * @return encryptedData data in base64 encoding with iv attached at end after a :
     */
    public static String encrypt(String key, String iv, String data) {
        try {
            if (key.length() < CIPHER_KEY_LEN) {
                int numPad = CIPHER_KEY_LEN - key.length();

                for(int i = 0; i < numPad; i++){
                    key += "0"; //0 pad to len 16 bytes
                }

            } else if (key.length() > CIPHER_KEY_LEN) {
                key = key.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
            }


            IvParameterSpec initVector = new IvParameterSpec(iv.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);

            byte[] encryptedData = cipher.doFinal((data.getBytes()));

            String base64_EncryptedData = Base64.getEncoder().encodeToString(encryptedData);
            String base64_IV = Base64.getEncoder().encodeToString(iv.getBytes("UTF-8"));

            return base64_EncryptedData + ":" + base64_IV;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static String encrypt(String data) {
        try {
            if (KEY.length() < CIPHER_KEY_LEN) {
                int numPad = CIPHER_KEY_LEN - KEY.length();

                for(int i = 0; i < numPad; i++){
                    KEY += "0"; //0 pad to len 16 bytes
                }

            } else if (KEY.length() > CIPHER_KEY_LEN) {
                KEY = KEY.substring(0, CIPHER_KEY_LEN); //truncate to 16 bytes
            }


            IvParameterSpec initVector = new IvParameterSpec(initV.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.ENCRYPT_MODE, skeySpec, initVector);

            byte[] encryptedData = cipher.doFinal((data.getBytes()));

            String base64_EncryptedData = Base64.getEncoder().encodeToString(encryptedData);
            String base64_IV = Base64.getEncoder().encodeToString(initV.getBytes("UTF-8"));

            return base64_EncryptedData + ":" + base64_IV;

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    /**
     * Decrypt data using AES Cipher (CBC) with 128 bit key
     *
     * @param key - key to use should be 16 bytes long (128 bits)
     * @param data - encrypted data with iv at the end separate by :
     * @return decrypted data string
     */

    public static String decrypt(String key, String data) {
        try {
            String[] parts = data.split(":");

            IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);

            byte[] original = cipher.doFinal(decodedEncryptedData);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
    public static String decrypt(String data) {
        try {
            String[] parts = data.split(":");

            IvParameterSpec iv = new IvParameterSpec(Base64.getDecoder().decode(parts[1]));
            SecretKeySpec skeySpec = new SecretKeySpec(KEY.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance(CIPHER_NAME);
            cipher.init(Cipher.DECRYPT_MODE, skeySpec, iv);

            byte[] decodedEncryptedData = Base64.getDecoder().decode(parts[0]);

            byte[] original = cipher.doFinal(decodedEncryptedData);

            return new String(original);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(encrypt(KEY, initV, "ericcasanova.m@gmail.com"));
        System.out.println(decrypt(KEY, encrypt(KEY, initV, "ericcasanova.m@gmail.com")));
        System.out.println("FF");
        System.out.println(encrypt(KEY, initV, "monicasegarra3@gmail.com"));
        System.out.println(decrypt(KEY, encrypt(KEY, initV, "monicasegarra3@gmail.com")));

        System.out.println("");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        String a= "ericcasanova.m@gmail.com"+"12345"+"juanito";
        String salida=sha256(a);
        System.out.println("FFFFFFFFFFF"+ salida);
        String b= salida +"ericcasanova.m@gmail.com".length();
        System.out.println(b);
        System.out.println("XX");
    }

    public static String sha256(String base) {
        try{
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] hash = digest.digest(base.getBytes("UTF-8"));
            StringBuffer hexString = new StringBuffer();

            for (int i = 0; i < hash.length; i++) {
                String hex = Integer.toHexString(0xff & hash[i]);
                if(hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }

            return hexString.toString();
        } catch(Exception ex){
            throw new RuntimeException(ex);
        }
    }

}
