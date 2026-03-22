package thefable.blockchain;

import java.security.PublicKey;
import java.util.Base64;

public class Transaction {

    private PublicKey sender;
    private PublicKey receiver;
    private double amount;
    private byte[] signature;

    public Transaction(PublicKey sender, PublicKey receiver, double amount) {
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
    }

    // Create string representation of transaction data
    private String getData() {
        return Base64.getEncoder().encodeToString(sender.getEncoded()) + " | " +
                Base64.getEncoder().encodeToString(receiver.getEncoded()) + " | " +
                amount;
    }

    // Sign transaction
    public void signTransaction(Wallet wallet) {

        // Safety: ensure wallet owns this transaction
        if (!wallet.getPublicKey().equals(sender)) {
            throw new RuntimeException("You cannot sign transactions for other wallets!");
        }

        String data = getData();
        this.signature = wallet.signData(data);
    }

    // Verify transaction
    public boolean isValid() {

        if (sender == null) {
            // Genesis or mining reward later
            return true;
        }

        if (signature == null) {
            System.out.println("No signature in transaction");
            return false;
        }

        String data = getData();

        return Wallet.verifySignature(sender, data, signature);
    }

    public PublicKey getSender() {
        return sender;
    }

    public PublicKey getReceiver() {
        return receiver;
    }

    public double getAmount() {
        return amount;
    }

    public byte[] getSignature() {
        return signature;
    }
}