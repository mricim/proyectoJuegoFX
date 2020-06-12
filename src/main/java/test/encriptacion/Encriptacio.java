package main.java.test.encriptacion;

import sun.misc.BASE64Encoder;
import sun.security.x509.*;

import javax.crypto.*;
import javax.crypto.spec.IvParameterSpec;
import java.io.*;
import java.math.BigInteger;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.*;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.Date;
import java.util.Enumeration;

public class Encriptacio {

    public static final String FILE_ROUTE = "src/exercici/";
    private static final String KEY_FILE_ROUTE = FILE_ROUTE + "key.txt";
    private static char[] pwdArray = "password".toCharArray();
    private static KeyStore ks;
    private static int id = 0;
    public static final byte[] IV_PARAM = {0x00,
            0x04,
            0x08,
            0x0C,
            0x01,
            0x05,
            0x09,
            0x0D,
            0x02,
            0x06,
            0x0A,
            0x0E,
            0x03,
            0x07,
            0x0B,
            0x0F};

    /**
     * This method generates a SecretKey
     *
     * @param keySize the size of the SecretKey in bytes
     * @return a SecretKey of size n
     */

    public static SecretKey generadorDeClausSimetriques(int keySize) {
        SecretKey sKey = null;
        if ((keySize == 128) || (keySize == 192) || (keySize == 256)) {
            try {
                KeyGenerator kgen = KeyGenerator.getInstance("AES");
                kgen.init(keySize);
                sKey = kgen.generateKey();
            } catch (NoSuchAlgorithmException ex) {
                System.err.println("Generador no disponible.");
            }
        }
        return sKey;
    }

    public KeyPair generadorDeClausAsimetriques(int len) {
        KeyPair keys = null;
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("RSA");
            keyGen.initialize(len);
            keys = keyGen.genKeyPair();
        } catch (Exception ex) {
            System.err.println("Generador no disponible.");
        }
        System.out.println();
        return keys;
    }

    public static byte[] menu2(SecretKey clauSecretaSimetrica) {

        byte[] encryptedData = null;
        byte[] dataToEncrypt = null;
        Cipher cipher = null;
        String texto = "Leónov fue uno de los veinte pilotos de la Fuerza Aérea Soviética seleccionado para formar parte del primer grupo de cosmonautas en 1960. Como todos los cosmonautas soviéticos, Leónov fue miembro del Partido Comunista de la Unión Soviética (PCUS).\n" +
                "\n" +
                "Su paseo espacial debía realizarse originalmente en la misión Vosjod 1, pero fue cancelada, por lo que el acontecimiento histórico se produjo durante el vuelo de la Vosjod 2. Estuvo fuera de la nave durante 12 minutos y nueve segundos el 18 de marzo de 1965, unido con la nave por una correa de 5,35 metros. Al final del paseo espacial, el traje espacial de Leónov se había inflado en el vacío del espacio hasta el punto de que no podía volver a entrar en la esclusa de aire. Tuvo que abrir una válvula para permitir que la presión del traje descendiera y ser capaz de volver a entrar en la cápsula. Leónov había pasado un año y medio en entrenamiento intensivo de ingravidez para la misión.\n" +
                "\n" +
                "A partir de enero de 2011, Leónov se convirtió en el último superviviente de los cinco cosmonautas del programa Vosjod.\n" +
                "\n" +
                "En 1968, Leónov fue seleccionado para ser comandante de un vuelo circunlunar de la Soyuz. Sin embargo, como todos los vuelos de prueba no tripulados de este proyecto fracasaron, y la misión Apolo 8 ya había dado ese paso en la carrera espacial de los estadounidenses, el vuelo fue cancelado. También fue seleccionado para ser el primer soviético en la Luna, a bordo de la nave espacial LOK/N1. Este proyecto también fue cancelado. (Por cierto, el plan de la misión requería un peligroso paseo espacial entre los vehículos lunares, algo que contribuyó a su selección). Leónov pudo haber sido comandante de la malograda misión Soyuz 11 en 1971 a la Salyut 1, la primera estación espacial tripulada, pero su tripulación fue reemplazada por la de reserva después de que el cosmonauta Valeri Kubásov fuera sospechoso de haber contraído la tuberculosis.\n" +
                "\n" +
                "Leónov pudo haber mandado la siguiente misión a la Saliut 1, pero también fue cancelada después de la muerte de los miembros de la tripulación de la Soyuz 11, perdiéndose además la estación. Los siguientes dos Saliut (en realidad la estación militar Almaz) se perdieron en el lanzamiento. A partir de la Saliut 4, Leónov fue trasladado a proyectos más prestigiosos.\n" +
                "\n" +
                "El segundo viaje al espacio de Leónov fue igualmente significativo: fue el comandante de la mitad soviética de la misión Apolo-Soyuz –la Soyuz 19— la primera misión espacial conjunta entre la Unión Soviética y los Estados Unidos.";
        dataToEncrypt = texto.getBytes();
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);
            encryptedData = cipher.doFinal(dataToEncrypt);
            String dadesEncriptadesEnString = new BASE64Encoder().encode(encryptedData);
            System.out.println("Texto: \n" + texto);
            System.out.println("==========================================================");
            System.out.println("dadesEncriptadesEnString: \n" + dadesEncriptadesEnString);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("menu 2(): FINAL");
        }


        return encryptedData;
    }

    public static void menu3(SecretKey clauSecretaSimetrica, byte[] dadesEncriptadesEnByte) {
        String decryptedText = null;
        Cipher cipher = null;
        byte[] decryptedData = null;

        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.DECRYPT_MODE, clauSecretaSimetrica, iv);
            decryptedData = cipher.doFinal(dadesEncriptadesEnByte);
            decryptedText = new String(decryptedData);
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println("menu 3(): FINAL");
        }

        System.out.println(decryptedText);
    }


    public void menu4(SecretKey clauSecretaSimetrica) {

        loadKeyStoreSymmetricKey();
        KeyStore.SecretKeyEntry secret = new KeyStore.SecretKeyEntry(clauSecretaSimetrica);
        KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(pwdArray);
        try {
            int keyStoreSize = ks.size();
            FileOutputStream fos = new FileOutputStream(FILE_ROUTE + "ExerciciKeyStore.jceks");
            ks.setEntry("secretKey" + keyStoreSize, secret, password);
            ks.store(fos, pwdArray);
            System.out.println("secretKey" + keyStoreSize);
            fos.close();
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            System.out.println("menu 4(): FINAL");
        }


    }

    public void listAliases() {
        Enumeration<String> aliases = null;
        try {
            aliases = ks.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                System.out.println("alias: " + alias);
            }
        } catch (KeyStoreException e) {
            e.printStackTrace();
        }

    }

    public void menu5(String alias) {
        loadKeyStoreSymmetricKey();
        try {
            if (ks.containsAlias(alias)) {
                KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(pwdArray);
                KeyStore.SecretKeyEntry secretKeyEntry = (KeyStore.SecretKeyEntry) ks.getEntry(alias, password);
                SecretKey secretKey = secretKeyEntry.getSecretKey();
                String encodedKey = new String(secretKey.getEncoded());
                System.out.println(encodedKey);
            } else {
                System.out.println("El alias " + alias + " no esta almacenado en el KeyStore, introduce uno de nuevo");
            }

        } catch (KeyStoreException | UnrecoverableEntryException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        } finally {
            System.out.println("menu 5(): FINAL");
        }


    }

    private void deleteEntries() {
        loadKeyStoreSymmetricKey();
        Enumeration<String> aliases = null;
        try {
            aliases = ks.aliases();
            while (aliases.hasMoreElements()) {
                String alias = aliases.nextElement();
                FileOutputStream fos = new FileOutputStream(FILE_ROUTE + "ExerciciKeyStore.jceks");
                ks.deleteEntry(alias);
                ks.store(fos, pwdArray);
                fos.close();
            }
        } catch (KeyStoreException | IOException | CertificateException | NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    private void loadKeyStoreSymmetricKey() {

        Path path = Paths.get(FILE_ROUTE + "ExerciciKeyStore.jceks");
        try {
            if (!Files.exists(path)) {
                System.out.println("Creo fichero y guardo ks");
                ks = KeyStore.getInstance(KeyStore.getDefaultType());
                ks.load(null, pwdArray);
                FileOutputStream fos = new FileOutputStream(FILE_ROUTE + "ExerciciKeyStore.jceks");
                ks.store(fos, pwdArray);
                fos.close();
            } else {
                System.out.println("Cargando fichero " + path.getFileName());
                /*for symmetric key we need to use JCEKS instance, for asymmetric keys (pub or private)
                we need to use JKS
                 */
                ks = KeyStore.getInstance("JCEKS");
                ks.load(new FileInputStream(path.toString()), pwdArray);
            }

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadKeyStoreAsymmetricKey() {

        Path path = Paths.get(FILE_ROUTE + "ExerciciKeyStoreAsym.jks");
        try {
            if (!Files.exists(path)) {
                System.out.println("Creo fichero y guardo ks");
                ks = KeyStore.getInstance(KeyStore.getDefaultType());
                ks.load(null, pwdArray);
                FileOutputStream fos = new FileOutputStream(FILE_ROUTE + "ExerciciKeyStoreAsym.jks");
                ks.store(fos, pwdArray);
                fos.close();
            } else {
                System.out.println("Cargando fichero " + path.getFileName());
                /*for symmetric key we need to use JCEKS instance, for asymmetric keys (pub or private)
                we need to use JKS
                 */
                ks = KeyStore.getInstance("JKS");
                ks.load(new FileInputStream(path.toString()), pwdArray);
            }

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (CertificateException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public byte[][] menu12(SecretKey clauSecretaSimetrica, KeyPair clauPublicaIPrivada) {
        Cipher cipher = null;
        byte[] msg = null;
        byte[][] encWrappedData = new byte[2][];
        PublicKey pub = clauPublicaIPrivada.getPublic();
        String texto = "Leónov fue uno de los veinte pilotos de la Fuerza Aérea Soviética seleccionado para formar parte del primer grupo de cosmonautas en 1960. Como todos los cosmonautas soviéticos, Leónov fue miembro del Partido Comunista de la Unión Soviética (PCUS).\n" +
                "\n" +
                "Su paseo espacial debía realizarse originalmente en la misión Vosjod 1, pero fue cancelada, por lo que el acontecimiento histórico se produjo durante el vuelo de la Vosjod 2. Estuvo fuera de la nave durante 12 minutos y nueve segundos el 18 de marzo de 1965, unido con la nave por una correa de 5,35 metros. Al final del paseo espacial, el traje espacial de Leónov se había inflado en el vacío del espacio hasta el punto de que no podía volver a entrar en la esclusa de aire. Tuvo que abrir una válvula para permitir que la presión del traje descendiera y ser capaz de volver a entrar en la cápsula. Leónov había pasado un año y medio en entrenamiento intensivo de ingravidez para la misión.\n" +
                "\n" +
                "A partir de enero de 2011, Leónov se convirtió en el último superviviente de los cinco cosmonautas del programa Vosjod.\n" +
                "\n" +
                "En 1968, Leónov fue seleccionado para ser comandante de un vuelo circunlunar de la Soyuz. Sin embargo, como todos los vuelos de prueba no tripulados de este proyecto fracasaron, y la misión Apolo 8 ya había dado ese paso en la carrera espacial de los estadounidenses, el vuelo fue cancelado. También fue seleccionado para ser el primer soviético en la Luna, a bordo de la nave espacial LOK/N1. Este proyecto también fue cancelado. (Por cierto, el plan de la misión requería un peligroso paseo espacial entre los vehículos lunares, algo que contribuyó a su selección). Leónov pudo haber sido comandante de la malograda misión Soyuz 11 en 1971 a la Salyut 1, la primera estación espacial tripulada, pero su tripulación fue reemplazada por la de reserva después de que el cosmonauta Valeri Kubásov fuera sospechoso de haber contraído la tuberculosis.\n" +
                "\n" +
                "Leónov pudo haber mandado la siguiente misión a la Saliut 1, pero también fue cancelada después de la muerte de los miembros de la tripulación de la Soyuz 11, perdiéndose además la estación. Los siguientes dos Saliut (en realidad la estación militar Almaz) se perdieron en el lanzamiento. A partir de la Saliut 4, Leónov fue trasladado a proyectos más prestigiosos.\n" +
                "\n" +
                "El segundo viaje al espacio de Leónov fue igualmente significativo: fue el comandante de la mitad soviética de la misión Apolo-Soyuz –la Soyuz 19— la primera misión espacial conjunta entre la Unión Soviética y los Estados Unidos.";

        String encryptedTexto = null;
        System.out.println("Texto : " + texto);
        System.out.println("================================================");
        msg = texto.getBytes();
        try {
            //encrypt texto con clau simetrica
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);
            byte[] encMsg = cipher.doFinal(msg);
            encryptedTexto = new BASE64Encoder().encode(encMsg);
            //embolcall clau simetrica con asimetrica(RSA)
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.WRAP_MODE, pub);
            byte[] encKey = cipher.wrap(clauSecretaSimetrica);
            encWrappedData[0] = encMsg;
            encWrappedData[1] = encKey;

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } finally {
            System.out.println("menu 12(): FINAL");
        }

        System.out.println("Encrypted texto : " + encryptedTexto);
        System.out.println("=============================================");
        System.out.println("Data: " + new String(encWrappedData[0]));
        System.out.println("ClauSimetricaEncriptada: " + new String(encWrappedData[1]));

        return encWrappedData;
    }

    public void menu13(KeyPair clauPublicaIPrivada, byte[][] dadesIClauEncriptadesEnByte) {
        PrivateKey pvKey = clauPublicaIPrivada.getPrivate();
        SecretKey secretKey = null;
        String decryptedText = null;
        Cipher cipher;
        String wrapAlgorithm = "RSA/ECB/PKCS1Padding";
        String decryptAlgorithm = "AES/CBC/PKCS5Padding";
        byte[] decryptedData = new byte[1];


        try {
            //unwrap secretKey
            cipher = Cipher.getInstance(wrapAlgorithm);
            cipher.init(Cipher.UNWRAP_MODE, pvKey);
            secretKey = (SecretKey) cipher.unwrap(dadesIClauEncriptadesEnByte[1], "AES", Cipher.SECRET_KEY);

            //decryptText
            cipher = Cipher.getInstance(decryptAlgorithm);
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
            decryptedData = cipher.doFinal(dadesIClauEncriptadesEnByte[0]);
            decryptedText = new String(decryptedData);

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } finally {
            System.out.println("menu 13(): FINAL");
        }

        System.out.println("Decrypted text: " + decryptedText);

    }

    public void menu14(KeyPair clauPublicaIPrivada) {
        loadKeyStoreAsymmetricKey();
        PrivateKey privateKey = clauPublicaIPrivada.getPrivate();
        String fileName = "ExerciciKeyStoreAsym.jks";
        try {
            int keyStoreSize = ks.size();
            String alias = "privateKey" + keyStoreSize;
            FileOutputStream fos = new FileOutputStream(FILE_ROUTE + fileName);
            X509Certificate[] certificateChain = {generateCertificate("cn=Unknown", clauPublicaIPrivada, 365, "SHA256withRSA")};
            ks.setKeyEntry(alias, privateKey, pwdArray, certificateChain);
            ks.store(fos, pwdArray);
            System.out.println("Guardando entry " + alias + " en ruta:  " + FILE_ROUTE + fileName);
            System.out.println("privateKey" + keyStoreSize);
            fos.close();
        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (GeneralSecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("menu 14(): FINAL");
        }


    }


    public void menu15(String alias) {
        loadKeyStoreAsymmetricKey();

        try {
            if (ks.containsAlias(alias)) {
                KeyStore.ProtectionParameter password = new KeyStore.PasswordProtection(pwdArray);
                KeyStore.PrivateKeyEntry privateKeyEntry = (KeyStore.PrivateKeyEntry) ks.getEntry(alias, password);
                PrivateKey privateKey = privateKeyEntry.getPrivateKey();
                String encodedKey = new String(privateKey.getEncoded());
                System.out.println(encodedKey);
            } else {
                System.out.println("El alias " + alias + " no esta almacenado en el KeyStore, introduce uno de nuevo");
            }

        } catch (KeyStoreException e) {
            e.printStackTrace();
        } catch (UnrecoverableEntryException e) {
            e.printStackTrace();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }finally {
            System.out.println("menu 15(): FINAL");
        }


    }

    public void menu21(SecretKey clauSecretaSimetrica) {

        String texto = "Leónov fue uno de los veinte pilotos de la Fuerza Aérea Soviética seleccionado para formar parte del primer grupo de cosmonautas en 1960. Como todos los cosmonautas soviéticos, Leónov fue miembro del Partido Comunista de la Unión Soviética (PCUS).\n" +
                "\n" +
                "Su paseo espacial debía realizarse originalmente en la misión Vosjod 1, pero fue cancelada, por lo que el acontecimiento histórico se produjo durante el vuelo de la Vosjod 2. Estuvo fuera de la nave durante 12 minutos y nueve segundos el 18 de marzo de 1965, unido con la nave por una correa de 5,35 metros. Al final del paseo espacial, el traje espacial de Leónov se había inflado en el vacío del espacio hasta el punto de que no podía volver a entrar en la esclusa de aire. Tuvo que abrir una válvula para permitir que la presión del traje descendiera y ser capaz de volver a entrar en la cápsula. Leónov había pasado un año y medio en entrenamiento intensivo de ingravidez para la misión.\n" +
                "\n" +
                "A partir de enero de 2011, Leónov se convirtió en el último superviviente de los cinco cosmonautas del programa Vosjod.\n" +
                "\n" +
                "En 1968, Leónov fue seleccionado para ser comandante de un vuelo circunlunar de la Soyuz. Sin embargo, como todos los vuelos de prueba no tripulados de este proyecto fracasaron, y la misión Apolo 8 ya había dado ese paso en la carrera espacial de los estadounidenses, el vuelo fue cancelado. También fue seleccionado para ser el primer soviético en la Luna, a bordo de la nave espacial LOK/N1. Este proyecto también fue cancelado. (Por cierto, el plan de la misión requería un peligroso paseo espacial entre los vehículos lunares, algo que contribuyó a su selección). Leónov pudo haber sido comandante de la malograda misión Soyuz 11 en 1971 a la Salyut 1, la primera estación espacial tripulada, pero su tripulación fue reemplazada por la de reserva después de que el cosmonauta Valeri Kubásov fuera sospechoso de haber contraído la tuberculosis.\n" +
                "\n" +
                "Leónov pudo haber mandado la siguiente misión a la Saliut 1, pero también fue cancelada después de la muerte de los miembros de la tripulación de la Soyuz 11, perdiéndose además la estación. Los siguientes dos Saliut (en realidad la estación militar Almaz) se perdieron en el lanzamiento. A partir de la Saliut 4, Leónov fue trasladado a proyectos más prestigiosos.\n" +
                "\n" +
                "El segundo viaje al espacio de Leónov fue igualmente significativo: fue el comandante de la mitad soviética de la misión Apolo-Soyuz –la Soyuz 19— la primera misión espacial conjunta entre la Unión Soviética y los Estados Unidos.";


        Cipher cipher = null;
        byte[] dataToEncrypt;
        CipherOutputStream cos = null;
        dataToEncrypt = texto.getBytes();
        Path path = Paths.get(FILE_ROUTE + "menu21TextoCifrado.txt");

        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);
            System.out.println("Is file: " + path.getFileName() + " deleted? " + Files.deleteIfExists(path));
            cos = new CipherOutputStream(new FileOutputStream(FILE_ROUTE + "menu21TextoCifrado.txt"), cipher);
            cos.write(dataToEncrypt);
            cos.close();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("menu 21(): FINAL");
        }

//        System.out.println("Guardo fichero encriptado");
    }

    public void menu22(SecretKey clauSecretaSimetrica, KeyPair clauPublicaIPrivada) {
        String texto = "Leónov fue uno de los veinte pilotos de la Fuerza Aérea Soviética seleccionado para formar parte del primer grupo de cosmonautas en 1960. Como todos los cosmonautas soviéticos, Leónov fue miembro del Partido Comunista de la Unión Soviética (PCUS).\n" +
                "\n" +
                "Su paseo espacial debía realizarse originalmente en la misión Vosjod 1, pero fue cancelada, por lo que el acontecimiento histórico se produjo durante el vuelo de la Vosjod 2. Estuvo fuera de la nave durante 12 minutos y nueve segundos el 18 de marzo de 1965, unido con la nave por una correa de 5,35 metros. Al final del paseo espacial, el traje espacial de Leónov se había inflado en el vacío del espacio hasta el punto de que no podía volver a entrar en la esclusa de aire. Tuvo que abrir una válvula para permitir que la presión del traje descendiera y ser capaz de volver a entrar en la cápsula. Leónov había pasado un año y medio en entrenamiento intensivo de ingravidez para la misión.\n" +
                "\n" +
                "A partir de enero de 2011, Leónov se convirtió en el último superviviente de los cinco cosmonautas del programa Vosjod.\n" +
                "\n" +
                "En 1968, Leónov fue seleccionado para ser comandante de un vuelo circunlunar de la Soyuz. Sin embargo, como todos los vuelos de prueba no tripulados de este proyecto fracasaron, y la misión Apolo 8 ya había dado ese paso en la carrera espacial de los estadounidenses, el vuelo fue cancelado. También fue seleccionado para ser el primer soviético en la Luna, a bordo de la nave espacial LOK/N1. Este proyecto también fue cancelado. (Por cierto, el plan de la misión requería un peligroso paseo espacial entre los vehículos lunares, algo que contribuyó a su selección). Leónov pudo haber sido comandante de la malograda misión Soyuz 11 en 1971 a la Salyut 1, la primera estación espacial tripulada, pero su tripulación fue reemplazada por la de reserva después de que el cosmonauta Valeri Kubásov fuera sospechoso de haber contraído la tuberculosis.\n" +
                "\n" +
                "Leónov pudo haber mandado la siguiente misión a la Saliut 1, pero también fue cancelada después de la muerte de los miembros de la tripulación de la Soyuz 11, perdiéndose además la estación. Los siguientes dos Saliut (en realidad la estación militar Almaz) se perdieron en el lanzamiento. A partir de la Saliut 4, Leónov fue trasladado a proyectos más prestigiosos.\n" +
                "\n" +
                "El segundo viaje al espacio de Leónov fue igualmente significativo: fue el comandante de la mitad soviética de la misión Apolo-Soyuz –la Soyuz 19— la primera misión espacial conjunta entre la Unión Soviética y los Estados Unidos.";


        Cipher cipher = null;
        byte[] dataToEncrypt;
        byte[] encryptedData;
        byte[] encKey;
        CipherOutputStream cos = null;
        BufferedOutputStream keyFile = null;
        PublicKey pub = clauPublicaIPrivada.getPublic();


        dataToEncrypt = texto.getBytes();


        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.ENCRYPT_MODE, clauSecretaSimetrica, iv);
            cos = new CipherOutputStream(new FileOutputStream(FILE_ROUTE + "menu22TextoCifrado.txt"), cipher);
            cos.write(dataToEncrypt);
            cos.close();

            //CipherOutputStream ciphers while it writes in the file, no need to do cipher.doFinal()
            cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");
            cipher.init(Cipher.WRAP_MODE, pub);
            encKey = cipher.wrap(clauSecretaSimetrica);
            keyFile = new BufferedOutputStream(new FileOutputStream(FILE_ROUTE + "menu22Key.txt"));
            keyFile.write(encKey);
            keyFile.close();


        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } finally {
            System.out.println("menu 22(): FINAL");
        }

//        System.out.println("Guardo fichero encriptado");
    }

    public void menu31(SecretKey clauSecretaSimetrica) {
        Cipher cipher = null;
        String decryptedData;
        CipherInputStream cis;
        try {
            cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
            cipher.init(Cipher.DECRYPT_MODE, clauSecretaSimetrica, iv);
            cis = new CipherInputStream(new FileInputStream(FILE_ROUTE + "menu21TextoCifrado.txt"), cipher);
            BufferedReader br = new BufferedReader(new InputStreamReader(cis));
            System.out.println("Dades encryptades en String: ");

            while ((decryptedData = br.readLine()) != null) {
                System.out.println(decryptedData);
            }
            br.close();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            System.out.println("menu 31(): FINAL");
        }

    }


    public void menu32(KeyPair clauPublicaIPrivada,SecretKey clauSimetrica) {
        PrivateKey privateKey = clauPublicaIPrivada.getPrivate();
        try {
            //key.txt
            decryptTextFileAsymetric(privateKey, "RSA/ECB/PKCS1Padding", "menu22Key.txt");

            //texto.txt
            decryptTextFileSymetric(clauSimetrica, "AES/CBC/PKCS5Padding", "menu22TextoCifrado.txt");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        }finally {
            System.out.println("menu 32(): FINAL");
        }


    }

    private void decryptTextFileAsymetric(PrivateKey privateKey, String algorithm, String fileName) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        Cipher cipher;
        String decryptedData;
        SecretKey secretKey = null;

        File keyFile = new File(FILE_ROUTE+fileName);
        FileInputStream fis = new FileInputStream(keyFile);
        byte[] clauEncriptadaEnByte =  new byte[(int)keyFile.length()];
        fis.read(clauEncriptadaEnByte);
        fis.close();

        cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.UNWRAP_MODE, privateKey);
        secretKey = (SecretKey) cipher.unwrap(clauEncriptadaEnByte,"AES",Cipher.SECRET_KEY);
        decryptedData = new String(secretKey.getEncoded());
        System.out.println("DecryptedSecretKey: "+decryptedData);
    }
    private void decryptTextFileSymetric(SecretKey secretKey, String algorithm, String fileName) throws NoSuchAlgorithmException, NoSuchPaddingException, InvalidKeyException, InvalidAlgorithmParameterException, IOException {
        Cipher cipher;
        CipherInputStream cis;
        String decryptedData;
        cipher = Cipher.getInstance(algorithm);
        IvParameterSpec iv = new IvParameterSpec(IV_PARAM);
        cipher.init(Cipher.DECRYPT_MODE, secretKey, iv);
        cis = new CipherInputStream(new FileInputStream(FILE_ROUTE + fileName), cipher);
        BufferedReader br = new BufferedReader(new InputStreamReader(cis));
        System.out.println("Dades encryptades en String: ");

        while ((decryptedData = br.readLine()) != null) {
            System.out.println(decryptedData);
        }
        br.close();
    }

    public static KeyStore getKs() {
        return ks;
    }

    private X509Certificate generateCertificate(String dn, KeyPair keyPair, int validity, String sigAlgName) throws GeneralSecurityException, IOException {
        PrivateKey privateKey = keyPair.getPrivate();

        X509CertInfo info = new X509CertInfo();

        Date from = new Date();
        Date to = new Date(from.getTime() + validity * 1000L * 24L * 60L * 60L);

        CertificateValidity interval = new CertificateValidity(from, to);
        BigInteger serialNumber = new BigInteger(64, new SecureRandom());
        X500Name owner = new X500Name(dn);
        AlgorithmId sigAlgId = new AlgorithmId(AlgorithmId.md5WithRSAEncryption_oid);

        info.set(X509CertInfo.VALIDITY, interval);
        info.set(X509CertInfo.SERIAL_NUMBER, new CertificateSerialNumber(serialNumber));
        info.set(X509CertInfo.SUBJECT, owner);
        info.set(X509CertInfo.ISSUER, owner);
        info.set(X509CertInfo.KEY, new CertificateX509Key(keyPair.getPublic()));
        info.set(X509CertInfo.VERSION, new CertificateVersion(CertificateVersion.V3));
        info.set(X509CertInfo.ALGORITHM_ID, new CertificateAlgorithmId(sigAlgId));

        // Sign the cert to identify the algorithm that's used.
        X509CertImpl certificate = new X509CertImpl(info);
        certificate.sign(privateKey, sigAlgName);

        // Update the algorith, and resign.
        sigAlgId = (AlgorithmId) certificate.get(X509CertImpl.SIG_ALG);
        info.set(CertificateAlgorithmId.NAME + "." + CertificateAlgorithmId.ALGORITHM, sigAlgId);
        certificate = new X509CertImpl(info);
        certificate.sign(privateKey, sigAlgName);

        return certificate;
    }
}
