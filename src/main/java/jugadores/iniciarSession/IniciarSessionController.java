package main.java.jugadores.iniciarSession;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import main.java.Inicio.PantallaInicialController;
import main.java.utils.PrimaryStageControler;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.security.Key;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;
import java.security.spec.KeySpec;
import java.util.Base64;
import java.util.ResourceBundle;


public class IniciarSessionController extends PrimaryStageControler implements Initializable {
    public static final String RUTE_FXML = "jugadores/iniciarSession/iniciarSession.fxml";
    static private String key = "password";


    private static String secretKey = "boooooooooom!!!!";
    private static String salt = "password";

    @FXML
    private TextField user;
    @FXML
    private PasswordField password;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println("XXXXXXXX");
            System.out.println(encrypt("ericcasanova.m@gmail.com"));
            System.out.println("XXXXXXXX");
            System.out.println(encrypt("ericcasanova.m@gmail.com", key));
            System.out.println("XXXXXXXX");
            System.out.println(openssl_encrypt("ericcasanova.m@gmail.com", key, "aaaaaaaaaaaaaaaa"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void iniciarSession(MouseEvent mouseEvent) {
        Node source = (Node) mouseEvent.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
        PantallaInicialController.idJugadorTemp = 1;
        PantallaInicialController.nameJugadorTemp = "tu nombre";
        PantallaInicialController.emailJugadorTemp = "unemail@gmail.com";
        stage.close();
    }


    private static String openssl_encrypt(String data, String strKey, String strIv) throws Exception {
        //Base64 base64 = new Base64();
        Cipher ciper = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec key = new SecretKeySpec(strKey.getBytes(), "AES");
        IvParameterSpec iv = new IvParameterSpec(strIv.getBytes(), 0, ciper.getBlockSize());

        // Encrypt
        ciper.init(Cipher.ENCRYPT_MODE, key, iv);
        byte[] encryptedCiperBytes = ciper.doFinal(data.getBytes());

        String s = new String(encryptedCiperBytes);
        System.out.println("Ciper : " + s);
        return s;
    }
   /* public void encriptar(String password) throws NoSuchPaddingException, NoSuchAlgorithmException {
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, password);
        byte[] encrypted = cipher.doFinal(texto.getBytes());
        System.err.println("Encrypted: " + new String(Base64.getEncoder().encodeToString(encrypted)));}
    }
    public void desencriptar(String password) throws NoSuchPaddingException, NoSuchAlgorithmException {
        System.out.print("Enter ciphertext: ");
        byte[] encrypted = Base64.getDecoder().decode(password);
        Cipher cipher = Cipher.getInstance("AES/ECB/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, password.getBytes());
        String decrypted = new String(cipher.doFinal(encrypted));
        System.err.println("Decrypted: " + decrypted);

    }
*/

    public static String encrypt(String strToEncrypt, String secret) {
        try {
            byte[] iv = "aaaaaaaaaaaaaaaa".getBytes();
            IvParameterSpec ivspec = new IvParameterSpec(iv);

            SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
            KeySpec spec = new PBEKeySpec(secretKey.toCharArray(), salt.getBytes(), 65536, 256);
            SecretKey tmp = factory.generateSecret(spec);
            SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivspec);
            return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt.getBytes("UTF-8")));
        } catch (Exception e) {
            System.out.println("Error while encrypting: " + e.toString());
        }
        return null;
    }




    private static final String ENCRYPTION_KEY = "password";
    private static final String ENCRYPTION_IV = "aaaaaaaaaaaaaaaa";
    public static String encrypt(String src) {
        try {
            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, makeKey(), makeIv());
            //return Base64.en(cipher.doFinal(src.getBytes()));
            //return Base64.encodeBytes(cipher.doFinal(src.getBytes()));
            return null;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    static AlgorithmParameterSpec makeIv() {
        try {
            return new IvParameterSpec(ENCRYPTION_IV.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    static Key makeKey() {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] key = md.digest(ENCRYPTION_KEY.getBytes("UTF-8"));
            return new SecretKeySpec(key, "AES");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        return null;
    }
}
