package com.works.digitalSignature;

import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.Signature;
import java.security.spec.ECGenParameterSpec;
import java.security.spec.EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.LinkedHashMap;
import java.util.Map;

import com.google.gson.Gson;
import org.json.JSONObject;

public class DigiSig {


    private static final String SPEC = "secp256k1";
    private static final String ALGO = "SHA256withECDSA";

    private JSONObject sender() throws Exception {

        ECGenParameterSpec ecSpec = new ECGenParameterSpec(SPEC);
        KeyPairGenerator g = KeyPairGenerator.getInstance("EC");
        g.initialize(ecSpec, new SecureRandom());
        KeyPair keypair = g.generateKeyPair();
        PublicKey publicKey = keypair.getPublic();
        PrivateKey privateKey = keypair.getPrivate();

        String plaintext = "Hello Security";
        Map<String, Object> hm = new LinkedHashMap<>();
        hm.put("status", true);
        hm.put("name", "Ali Bilmem");
        //hm.toString().getBytes();

        //...... sign
        Signature ecdsaSign = Signature.getInstance(ALGO);
        ecdsaSign.initSign(privateKey);
        ecdsaSign.update(plaintext.getBytes("UTF-8"));
        byte[] signature = ecdsaSign.sign();
        String pub = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        String sig = Base64.getEncoder().encodeToString(signature);
        //System.out.println(sig);
        //System.out.println(pub);

        JSONObject obj = new JSONObject();
        obj.put("publicKey", pub);
        obj.put("signature", sig);
        obj.put("message", plaintext);
        obj.put("algorithm", ALGO);
        obj.put("datas", hm);

        return obj;
    }

    private boolean receiver(JSONObject obj) throws Exception {

        Signature ecdsaVerify = Signature.getInstance(obj.getString("algorithm"));
        KeyFactory kf = KeyFactory.getInstance("EC");

        EncodedKeySpec publicKeySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(obj.getString("publicKey")));
        PublicKey publicKey = kf.generatePublic(publicKeySpec);

        ecdsaVerify.initVerify(publicKey);
        ecdsaVerify.update(obj.getString("message").getBytes("UTF-8"));
        boolean result = ecdsaVerify.verify(Base64.getDecoder().decode(obj.getString("signature")));

        if ( result ) {
            Object st = obj.get("datas");
            Map<String, Object> hm = (Map<String, Object>) st;
            System.out.println( hm );
        }

        return result;
    }

    public static void main(String[] args){
        try {
            DigiSig digiSig = new DigiSig();
            JSONObject obj = digiSig.sender();
            System.out.println(obj);
            boolean result = digiSig.receiver(obj);
            System.out.println(result);
        } catch (Exception ex) {
            System.err.println("Action Fail - False : " + ex);
        }

    }

}

