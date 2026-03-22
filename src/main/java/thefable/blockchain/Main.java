package thefable.blockchain;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Wallet A = new Wallet();
        Wallet B = new Wallet();
        Blockchain bc = new Blockchain(A, B);


        while (true) {
            System.out.println("\n---------- BLOCKCHAIN ----------");
            System.out.println("0. Exit");
            System.out.println("1. Create transaction & mine block");
            System.out.println("2. View blockchain");
            System.out.println("3. Validate blockchain");
            System.out.println("4. View balances");

            System.out.print("Enter choice: ");
            int option = sc.nextInt();
            sc.nextLine();

            switch (option) {
                case 0:
                    System.out.println("Exiting...");
                    return;

                case 1:
                    System.out.println("\nChoose sender:");
                    System.out.println("1. A → B");
                    System.out.println("2. B → A");
                    System.out.print("Choice: ");

                    int choice = sc.nextInt();
                    sc.nextLine();

                    System.out.print("Enter amount: ");
                    int amount = sc.nextInt();
                    sc.nextLine();

                    Wallet sender;
                    Wallet receiver;

                    if (choice == 1) {
                        sender = A;
                        receiver = B;
                    } else if (choice == 2) {
                        sender = B;
                        receiver = A;
                    } else {
                        System.out.println("Invalid choice!");
                        break;
                    }

                    // Create transaction
                    Transaction tx = new Transaction(
                            sender.getPublicKey(),
                            receiver.getPublicKey(),
                            amount
                    );

                    tx.signTransaction(sender);

                    // Create block
                    Block block = new Block(bc.getLatestBlock().getHash());

                    // Add transaction
                    if (block.addTransaction(tx)) {
                        System.out.println("Transaction prepared");
                    } else {
                        System.out.println("Transaction failed!");
                        break;
                    }

                    // Mine and add block
                    bc.addBlock(block);

                    System.out.println("Block mined and added to blockchain!");
                    break;

                case 2:
                    bc.viewBlockchain();
                    break;

                case 3:
                    bc.validateChain();
                    break;

                case 4:
                    bc.printBalances();
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}