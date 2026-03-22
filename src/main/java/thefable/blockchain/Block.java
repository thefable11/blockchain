package thefable.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Block {
    private List<Transaction> transactions;
    private long timestamp;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block(String previousHash) {
        this.transactions = new ArrayList<>();
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return SHA256Util.hash(previousHash + timestamp + nonce + transactions.toString());
    }

    public void mineBlock(int difficulty) {
        String target = "0".repeat(difficulty);

        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
    }

    public boolean addTransaction(Transaction transaction) {

        if (transaction == null) return false;

        // Skip validation for genesis block
        if (!previousHash.equals("0")) {
            if (!transaction.isValid()) {
                System.out.println("Invalid transaction. Discarded.");
                return false;
            }
        }

        transactions.add(transaction);
        return true;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }

    public String getData() {
        return transactions.toString();
    }

    public long getTimestamp() {
        return timestamp;
    }

    public String getPreviousHash() {
        return previousHash;
    }

    public String getHash() {
        return hash;
    }

    public long getNonce() {
        return nonce;
    }
}
