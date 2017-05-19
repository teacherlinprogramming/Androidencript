package tw.com.teacherlinprogramming.androidencript;

import android.util.Base64;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;

/**
 * Created by P122509961 on 2017/5/19.
 */

public class UtilityRSA {
    private static String RSA = "RSA";
    public static KeyPair generateRSAKeyPair(int keyLength) {

        try {

            KeyPairGenerator kpg = KeyPairGenerator.getInstance("RSA");

            kpg.initialize(keyLength);

            return kpg.genKeyPair();
        }catch (NoSuchAlgorithmException e)
        {
            e.printStackTrace();

            return  null;
        }
    }

    /**
     * use private key decrypt encrypted context
     * */
    public static byte[] encryptData(byte[] encryptedData, PublicKey publicKey)
    {
        try {
            Cipher cipher = Cipher.getInstance(RSA);

            cipher.init(Cipher.ENCRYPT_MODE,publicKey);

            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }


    /**
     * use private key decrypt encrypted context
     * */
    public static byte[] decryptData(byte[] encryptedData, PrivateKey privateKey)
    {
        try {
            Cipher cipher = Cipher.getInstance(RSA);

            cipher.init(Cipher.DECRYPT_MODE,privateKey);

            return cipher.doFinal(encryptedData);
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    /**
     * read pem file to byte and turn it to PublicKey object
     * */
    public static PublicKey getPublicKey(byte[] keyBytes)
    {
        try {
            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);

            PublicKey publicKey = keyFactory.generatePublic(keySpec);

            return publicKey;
        }catch (Exception e)
        {
            e.printStackTrace();

            return  null;
        }
    }

    /**
     * read pem file to byte and input into this function and output Private object
     * */
    public static PrivateKey getPrivateKey(byte[] keyByte)
    {
        try {
            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyByte);

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);

            PrivateKey privateKey = keyFactory.generatePrivate(keySpec);

            return privateKey;
        }catch (Exception e)
        {
            e.printStackTrace();

            return null;
        }
    }

    public static PublicKey loadPublicKeyFromString(String publicKeyStr)
    {
        try {
            byte[] buffer = Base64.decode(publicKeyStr,Base64.NO_WRAP);

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);

            X509EncodedKeySpec keySpec = new X509EncodedKeySpec(buffer);

            return (RSAPublicKey)keyFactory.generatePublic(keySpec);
        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static PrivateKey loadPrivateKeyFromString(String privateKeyStr)
    {
        try{
            byte[] buffer = Base64.decode(privateKeyStr,Base64.NO_WRAP);

            PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(buffer);

            KeyFactory keyFactory = KeyFactory.getInstance(RSA);

            return (RSAPrivateKey)keyFactory.generatePrivate(keySpec);

        }catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void printPublicKeyInfo(PublicKey publicKey)
    {
        RSAPublicKey rsaPublicKey = (RSAPublicKey)publicKey;
        System.out.println("---RSAPublicKey---");
        System.out.println("Modulus.length = " + rsaPublicKey.getModulus().bitLength());
        System.out.println("Modulus = "+rsaPublicKey.getModulus().toString());
        System.out.println("PublicExponent.length = " + rsaPublicKey.getPublicExponent().bitLength());
        System.out.println("PublicExponent = " + rsaPublicKey.getPublicExponent().toString());
    }

    public static void printPrivateKeyInfo(PrivateKey privateKey)
    {
        RSAPrivateKey rsaPrivateKey = (RSAPrivateKey) privateKey;
        System.out.println("----------RSAPrivateKey ----------");
        System.out.println("Modulus.length = " + rsaPrivateKey.getModulus().bitLength());
        System.out.println("Modulus = " + rsaPrivateKey.getModulus().toString());
        System.out.println("PrivateExponent.length = " + rsaPrivateKey.getPrivateExponent().bitLength());
        System.out.println("PrivatecExponent = " + rsaPrivateKey.getPrivateExponent().toString());
    }
}
