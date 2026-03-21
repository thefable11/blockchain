package thefable.blockchain;

import java.util.ArrayList;
import java.util.List;

public class Blockchain {
    private List<Block> chain;
    private int difficulty;

    public Blockchain() {
        chain = new ArrayList<>();
        difficulty = 5;
        Block block = new Block("Genesis data", "0");
        block.mineBlock(difficulty);
        chain.add(block);
    }

    public void addBlock(String data) {
        Block block = new Block(data, chain.get(chain.size()-1).getHash());
        System.out.println("Mining...");
        block.mineBlock(difficulty);
        System.out.println("Block mined: " + block.getHash());
        chain.add(block);
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
}
