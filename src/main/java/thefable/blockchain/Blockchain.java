package thefable.blockchain;

import java.security.PublicKey;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Blockchain {
    private List<Block> chain;
    private int difficulty;
    private Map<PublicKey, Double> balances;

    public Blockchain(Wallet A, Wallet B) {
        chain = new ArrayList<>();
        difficulty = 5;
        balances = new HashMap<>();
        balances.put(A.getPublicKey(), 100.0);
        balances.put(B.getPublicKey(), 100.0);
        Block block = new Block("0");
        block.mineBlock(difficulty);
        chain.add(block);
    }

    public void addBlock(Block block) {
        // 1. Validate all transactions
        for (Transaction tx : block.getTransactions()) {

            if (!tx.isValid()) {
                System.out.println("Invalid signature!");
                return;
            }

            double senderBalance = getBalance(tx.getSender());

            if (senderBalance < tx.getAmount()) {
                System.out.println("Not enough balance!");
                return;
            }
        }

        System.out.println("Mining...");
        block.mineBlock(difficulty);
        System.out.println("Block mined: " + block.getHash());
        chain.add(block);

        for(Transaction tx : block.getTransactions()) {
            PublicKey sender = tx.getSender();
            PublicKey receiver = tx.getReceiver();
            double amount = tx.getAmount();

            balances.put(sender, getBalance(sender) - amount);
            balances.put(receiver, getBalance(receiver) + amount);
        }
    }

    public void validateChain() {
        String target = "0".repeat(difficulty);

        for(int i = 1; i < chain.size(); i++) {
            Block currBlock = chain.get(i);
            Block prevBlock = chain.get(i-1);

            if(!currBlock.getHash().equals(currBlock.calculateHash())) {
                System.out.println("Blockchain is corrupted");
                return;
            }

            if(!currBlock.getPreviousHash().equals(prevBlock.getHash())) {
                System.out.println("Blockchain is corrupted");
                return;
            }

            if(!currBlock.getHash().substring(0, difficulty).equals(target)) {
                System.out.println("Blockchain is corrupted");
            }
        }

        System.out.println("Blockchain is valid");
    }


    public void viewBlockchain() {

        for(int i = 0; i < chain.size(); i++) {
            System.out.println("\nBlock " + i+1 + ": ");
            System.out.println("Hash: \t" + chain.get(i).getHash());
            System.out.println("Previous Hash: \t" + chain.get(i).getPreviousHash());
            System.out.println("Data: \t" + chain.get(i).getData());
        }
    }

    public void printBalances() {
        for(PublicKey key : balances.keySet()) {
            System.out.println(key + " -> " + balances.get(key));
        }
    }

    public double getBalance(PublicKey key) {
        return balances.getOrDefault(key, 0.0);
    }

    public Block getLatestBlock() {
        return chain.get(chain.size()-1);
    }
}
