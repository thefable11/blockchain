package thefable.blockchain;

public class Block {
    private String data;
    private long timestamp;
    private String previousHash;
    private String hash;
    private int nonce;

    public Block(String data, String previousHash) {
        this.data = data;
        this.previousHash = previousHash;
        this.timestamp = System.currentTimeMillis();
        this.hash = calculateHash();
    }

    public String calculateHash() {
        return SHA256Util.hash(this.previousHash + this.timestamp + this.nonce + this.data);
    }

    public void mineBlock(int difficulty) {
        String target = "0".repeat(difficulty);

        while(!hash.substring(0, difficulty).equals(target)) {
            nonce++;
            hash = calculateHash();
        }
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
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
