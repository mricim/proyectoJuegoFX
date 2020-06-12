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
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.awt.*;
import java.net.URL;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.ResourceBundle;


public class IniciarSessionController extends PrimaryStageControler implements Initializable {
    public static final String RUTE_FXML = "jugadores/iniciarSession/iniciarSession.fxml";
    static private String texto="password";

    @FXML
    private TextField user;
    @FXML
    private PasswordField password;



    @Override
    public void initialize(URL location, ResourceBundle resources) {
        try {
            System.out.println(openssl_encrypt(texto,"ericcasanova.m@gmail.com","00000000"));
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
}
