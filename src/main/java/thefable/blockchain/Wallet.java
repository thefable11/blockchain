package thefable.blockchain;

import java.security.*;
import java.security.spec.ECGenParameterSpec;
import java.util.Base64;

public class Wallet {

    private PrivateKey privateKey;
    private PublicKey publicKey;

    public Wallet() {
        generateKeyPair();
    }

    private void generateKeyPair() {
        try {
            KeyPairGenerator keyGen = KeyPairGenerator.getInstance("EC");
            ECGenParameterSpec ecSpec = new ECGenParameterSpec("secp256r1");

            keyGen.initialize(ecSpec);
            KeyPair keyPair = keyGen.generateKeyPair();

            this.privateKey = keyPair.getPrivate();
            this.publicKey = keyPair.getPublic();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] signData(String data) {
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initSign(privateKey);
            signature.update(data.getBytes());

            return signature.sign();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean verifySignature(PublicKey publicKey, String data, byte[] signatureBytes) {
        try {
            Signature signature = Signature.getInstance("SHA256withECDSA");
            signature.initVerify(publicKey);
            signature.update(data.getBytes());

            return signature.verify(signatureBytes);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public PublicKey getPublicKey() {
        return publicKey;
    }

    public String getPublicKeyAsString() {
        return Base64.getEncoder().encodeToString(publicKey.getEncoded());
    }
}
