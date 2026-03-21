package thefable.blockchain;


import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        int option;
        Scanner sc = new Scanner(System.in);

        Blockchain bc = new Blockchain();

        while(true) {
            System.out.println("\n---------- BLOCKCHAIN ----------");
            System.out.println("0. Exit");
            System.out.println("1. Add block");
            System.out.println("2. View blockchain");
            System.out.println("3. Validate blockchain");

            option = sc.nextInt();
            sc.nextLine();

            switch(option) {
                case 0:
                    return;
                case 1: ;
                    String data = sc.nextLine();
                    bc.addBlock(data);
                    break;
                case 2:
                    bc.viewBlockchain();
                    break;
                case 3:
                    bc.validateChain();
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }

    }
}