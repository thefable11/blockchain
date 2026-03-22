# Blockchain in Java

A minimal blockchain implementation built in Java to understand how blockchain works under the hood.        
This project includes core concepts like transactions, wallets, and mining implemented from scratch without any frameworks.     

## Why I built this
I got curious about crypto investing and wanted to understand what actually happens behind the scenes instead of just using existing platforms.      
So I built a simple cli-based single-node blockchain system to learn the fundamentals.     

## Features
- Transaction creation and validation  
- Wallets with public and private key cryptography   
- Proof-of-Work based mining   
- Block structure with hashing  
- CLI-based interaction  

## Tech stack
- Java
- Basic cryptography

## How to run
1. Clone the repo
2. Open in your Intellij IDE 
3. Run the main class

## Example run
---------- BLOCKCHAIN ----------
0. Exit   
1. Create transaction & mine block   
2. View blockchain   
3. Validate blockchain   
4. View balances   
Enter choice: 1   

Choose sender:   
1. A → B  
2. B → A  
Choice: 1   
Enter amount: 50   
Transaction prepared   
Mining...   
Block mined: 0000013abf22404bf03d1ff1d21d3c7d0e4fa18540986bd6aa173689ed2639f8   
Block mined and added to blockchain!  

---------- BLOCKCHAIN ----------  
0. Exit  
1. Create transaction & mine block  
2. View blockchain  
3. Validate blockchain  
4. View balances  
Enter choice: 2  

Block 01:   
Hash: 	000004628288f9f1f00e18549f580f7b1eb65235aba57c01904417a54b75ca83  
Previous Hash: 	0  
Data: 	[]  

Block 11:   
Hash: 	0000013abf22404bf03d1ff1d21d3c7d0e4fa18540986bd6aa173689ed2639f8  
Previous Hash: 	000004628288f9f1f00e18549f580f7b1eb65235aba57c01904417a54b75ca83  
Data: 	[thefable.blockchain.Transaction@3a4afd8d]  

---------- BLOCKCHAIN ----------  
0. Exit  
1. Create transaction & mine block  
2. View blockchain  
3. Validate blockchain  
4. View balances  
Enter choice: 0  
Exiting...  
