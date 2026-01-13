import static java.lang.Thread.sleep;
import java.util.Scanner;
import java.util.Random;

public class Game {
    public static void main(String[] args) throws InterruptedException {
        // Initialize Scanner and Random objects
        Scanner sc = new Scanner(System.in);
        Random rd = new Random();

        // Player attributes
        String playerName;
        int playerHealth = 100;
        int playerMaxHealth = 100; 
        int playerAttack = 15;
        int playerGold = 50;
        int playerLevel = 1;
        int playerExp = 0;
        
        // Status effects using bitwise flags
        byte playerStatus = 0b0000;
        final byte POISONED = 0b0001;
        final byte BUFFED = 0b0010;
        final byte DEFENDING = 0b0100;

        // Enemy attributes and names
        int[][] enemies = {
            {30, 8, 15, 25}, // Goblin Stats: {Health, Attack, Gold, Exp} --> Weak
            {50, 12, 25, 40}, // Orc Stats: {Health, Attack, Gold, Exp} --> Medium
            {80, 18, 40, 60}, // Troll Stats: {Health, Attack, Gold, Exp} --> Strong
            {120, 25, 60, 100} // Dragon Stats: {Health, Attack, Gold, Exp} --> BOSS
        };
        String[] enemyNames = {"Goblin", "Orc", "Troll", "Dragon"};

        // Player inventory
        int[] inventory = new int[3];
        inventory[0] = 2; // Health Potions
        inventory[1] = 0; // Strength Potions
        inventory[2] = 0; // Defense Potions

        // Item names
        String[] itemNames = {"Health Potion", "Strength Potion", "Defense Potion"};

        // Welcome message and character creation
        System.out.println("=== Welcome to the T-RPG Game ===");
        System.out.print("\nEnter your character's name: ");
        playerName = sc.nextLine();
        System.out.println("\nHello, " + playerName + "! Your adventure begins now.");
        sleep(1000);

        // Game state variable
        boolean gameRunning = true;

        // Main game loop
        while (gameRunning && playerHealth > 0) {
            // Display main menu
            System.out.println("\n--- Main Menu ---");
            System.out.println("Health: " + playerHealth + "/" + playerMaxHealth);
            System.out.println("Level: " + playerLevel + " | XP: " + playerExp);
            System.out.println("Gold: " + playerGold);
            System.out.println("\n1. Find Enemy");
            System.out.println("2. Inventory");
            System.out.println("3. Shop");
            System.out.println("4. Status");
            System.out.println("5. Relax (Restore Health)");
            System.out.println("0. Exit Game");
            System.out.print("\nChoose an action: ");

            // Get player choice
            int choice = sc.nextInt();
            sc.nextLine();

            // Handle player choices
            switch (choice) {
                case 1 -> {
                    // Find an enemy
                    int enemyIndex = rd.nextInt(enemies.length);
                    int enemyHealth = enemies[enemyIndex][0];
                    int enemyAttack = enemies[enemyIndex][1];
                    int goldReward = enemies[enemyIndex][2];
                    int expReward = enemies[enemyIndex][3];
                    String enemyName = enemyNames[enemyIndex];

                    System.out.println("\n!!! You met a " + enemyName + " !!!");
                    sleep(1000);

                    boolean inBattle = true;

                    // Battle loop
                    while (inBattle && playerHealth > 0 && enemyHealth > 0) {
                        System.out.println("\n--- Battle Round ---");
                        System.out.println("Your Health: " + playerHealth);
                        System.out.println("Enemy Health: " + enemyHealth);
                        System.out.println("\n1. Attack");
                        System.out.println("2. Defend");
                        System.out.println("3. Use Item");
                        System.out.println("4. Flee");
                        System.out.print("\nYour action: ");

                        int battleChoice = sc.nextInt();
                        sc.nextLine();

                        // Reset defending status at the start of the turn
                        playerStatus &= ~DEFENDING;

                        if (battleChoice == 1) {
                            // Player attacks
                            int damage = playerAttack + rd.nextInt(6);

                            // Check for buffed status
                            if ((playerStatus & BUFFED) != 0) {
                                damage = (int) (damage * 1.5);
                                System.out.println("\n(Buffed Attack!)");
                                sleep(500);
                                playerStatus &= ~BUFFED;
                            }
                            // Apply damage to enemy
                            enemyHealth -= damage;
                        }
                        // Player defends
                        else if (battleChoice == 2) {
                            // Set defending status
                            playerStatus |= DEFENDING;
                            System.out.println("\nYou have taken a defensive stance!");
                            sleep(500);
                        }
                        // Use an item
                        else if (battleChoice == 3) {
                            boolean usingItem = true;

                            while (usingItem) {
                                System.out.println("\n--- Inventory ---");
                                System.out.println("1. Health Potion (" + inventory[0] + ")");
                                System.out.println("2. Strength Potion (" + inventory[1] + ")");
                                System.out.println("3. Defense Potion (" + inventory[2] + ")");
                                System.out.println("0. Back to Battle");
                                System.out.print("\nChoose an item to use: ");

                                int itemChoice = sc.nextInt();
                                sc.nextLine();

                                // Use Health Potion
                                if (itemChoice == 1 && inventory[0] > 0) {
                                    inventory[0]--;
                                    int heal = 30 + rd.nextInt(20);
                                    playerHealth += heal;

                                    // Ensure health does not exceed max health
                                    if (playerHealth > playerMaxHealth) {
                                        playerHealth = playerMaxHealth;
                                    }

                                    System.out.println("\nRestored " + heal + " health!");
                                    sleep(1000);
                                    continue;
                                }
                                // Use Strength Potion
                                else if (itemChoice == 2 && inventory[1] > 0) {
                                    inventory[1]--;
                                    playerStatus |= BUFFED;
                                    System.out.println("\nYou feel stronger!");
                                    sleep(1000);
                                    continue;
                                }
                                // Use Defense Potion
                                else if (itemChoice == 3 && inventory[2] > 0) {
                                    inventory[2]--;
                                    playerStatus |= DEFENDING;
                                    System.out.println("\nYou feel more armored!");
                                    sleep(1000);
                                    continue;
                                }
                                else if (itemChoice == 0) {
                                    usingItem = false;
                                }
                                // No such item
                                else {
                                    System.out.println("\nThere is no such item!");
                                    sleep(1000);
                                    continue;
                                }
                            }
                            // Skip enemy attack when using items - this counts as a turn
                            continue;
                        }
                        // Flee
                        else if (battleChoice == 4) {
                            System.out.println("\nYou fled from the battle...");
                            sleep(1000);
                            inBattle = false;
                            continue;
                        }
                        // Invalid action
                        else {
                            System.out.println("Invalid action. Try again.");
                            sleep(1000);
                            continue;
                        }

                        // Check if enemy is defeated
                        if (enemyHealth <= 0) {
                            System.out.println("\n=== YOU WIN! ===");
                            System.out.println("You defeated the " + enemyName + "!");
                            playerGold += goldReward;
                            playerExp += expReward;
                            System.out.println("You gained " + goldReward + " Gold and " + expReward + "XP!");
                            sleep(3000);

                            // Check for level up
                            int expNeeded = playerLevel * 100;
                            if (playerExp >= expNeeded) {
                                playerLevel++;
                                playerExp -= expNeeded;
                                playerMaxHealth += 20;
                                playerAttack += 5;
                                playerHealth = playerMaxHealth;
                                System.out.println("\n*** LEVEL UP! ***");
                                System.out.println("New Level: " + playerLevel);
                                System.out.println("Max Health " + playerHealth);
                                System.out.println("Attack Power " + playerAttack);
                                sleep(3000);
                            }
                            inBattle = false;
                            break;
                        }

                        // Enemy attacks
                        int enemyDamage = enemyAttack + rd.nextInt(6);

                        // Check if player is defending
                        if ((playerStatus & DEFENDING) != 0) {
                            enemyDamage /= 2;
                            System.out.println("You defended the half of the damage!");
                            sleep(500);
                        }

                        // Apply damage to player
                        playerHealth -= enemyDamage;
                        System.out.println(enemyName + " dealt you " + enemyDamage + " damage!");
                        sleep(500);

                        // Check for poison effect
                        if ((playerStatus & POISONED) != 0) {
                            int poisonDamage = 3;
                            playerHealth -= poisonDamage;
                            System.out.println("You lose " + poisonDamage + " health from poison!");
                            sleep(500);
                        }
                    }
                    break;
                }
                case 2 -> {
                    // inventory management
                    boolean inInventory = true;

                    // Inventory loop
                    while (inInventory) {
                        System.out.println("\n--- Inventory ---");
                        for (int i = 0; i < inventory.length; i++) {
                            System.out.println((i + 1) + ". " + itemNames[i] + " (" + inventory[i] + ")");
                        }

                        // Return to main menu option
                        System.out.println("\nPress 0 to return to the Main Menu.");
                        int inventoryChoice = sc.nextInt();
                        sc.nextLine();

                        // Return to main menu
                        if (inventoryChoice == 0) {
                            inInventory = false;
                            break;
                        }

                    }
                    break;
                }
                case 3 -> {
                    boolean inShop = true;

                    while (inShop) {
                        System.out.println("\n--- Shop ---");
                        System.out.println("Your Gold: " + playerGold);
                        System.out.println("1. Health Potion (20 Gold)");
                        System.out.println("2. Strength Potion (30 Gold)");
                        System.out.println("3. Defense Potion (25 Gold)");
                        System.out.println("0. Exit Shop");
                        System.out.print("\nWhat are you buying?: ");

                        int shopChoice = sc.nextInt();
                        sc.nextLine();

                        if (shopChoice >= 1 && shopChoice <= 3) {
                            int[] prices = {20, 30, 25};
                            int price = prices[shopChoice - 1];

                            if (playerGold >= price) {
                                playerGold -= price;
                                inventory[shopChoice - 1]++;
                                System.out.println("\nYou bought a " + itemNames[shopChoice - 1] + "!");
                                sleep(1000);
                                continue;
                            }
                            else if (playerGold < price) {
                                System.out.println("\nYou don't have enough gold!");
                                sleep(1000);
                                continue;
                            }
                            else {
                                System.out.println("\nInvalid choice. Please select a valid option.");
                                sleep(1000);
                                continue;
                            }
                        }
                        else if (shopChoice == 0) {
                            System.out.println("\nSee you, Stranger!");
                            sleep(1000);
                            inShop = false;
                        }
                    }
                    break;
                }
                case 4 -> {
                    boolean inStatus = true;

                    // Status display loop
                    while (inStatus) {
                        System.out.println("\n--- Status ---");
                        System.out.println("Name: " + playerName);
                        System.out.println("Level: " + playerLevel);
                        System.out.println("Health: " + playerHealth + "/" + playerMaxHealth);
                        System.out.println("Attack: " + playerAttack);
                        System.out.println("XP: " + playerExp + "/" + (playerLevel * 100));
                        System.out.println("Gold: " + playerGold);
                        System.out.println("\nStatus Effects:");
                        if ((playerStatus & POISONED) != 0) {
                            System.out.println("[Poisoned]");
                        }
                        if ((playerStatus & BUFFED) != 0) {
                            System.out.println("[Buffed]");
                        }
                        if ((playerStatus & DEFENDING) != 0) {
                            System.out.println("[Defending]");
                        }
                        if (playerStatus == 0) {
                            System.out.println("No active status effects.");
                        }

                        // Return to main menu option
                        System.out.println("\nPress 0 to return to the Main Menu.");
                        int statusChoice = sc.nextInt();
                        sc.nextLine();
                        if (statusChoice == 0) {
                            inStatus = false;
                        }
                    }
                    break;
                }
                case 5 -> {
                    // Relax and restore health
                    int restoredHealth = (playerMaxHealth - playerHealth) / 2;
                    playerHealth += restoredHealth;

                    if (playerHealth >= playerMaxHealth) {
                        playerHealth = playerMaxHealth;
                        System.out.println("\nYou are fully healed!");
                        sleep(1000);
                        break;
                    }

                    System.out.println("\nYou relax and restore " + restoredHealth + " health.");
                    sleep(1000);
                    break;
                }
                case 0 -> {
                    // Exit game
                    gameRunning = false;
                    System.out.println("\nThank you for playing, " + playerName + "! Goodbye!");
                    sleep(1000);
                    break;
                }
                default -> {
                    // Invalid choice
                    System.out.println("\nInvalid choice. Please select a valid option.");
                    sleep(1000);
                    continue;
                }
            }
        }

        // Check for game over condition
        if (playerHealth <= 0) {
            System.out.println("\n=== Game Over ===");
            System.out.println("You are dead...");
            System.out.println("Level reached: " + playerLevel);
            System.out.println("Total Gold collected: " + playerGold);

            sleep(3000);

            System.out.print("\nDo you want to play again? (Y/N): ");

            char exitChoice = sc.next().toUpperCase().charAt(0);
            sc.nextLine();

            if (exitChoice == 'Y') {
                System.out.println("\nLet's try again, " + playerName + "!");
                sleep(1000);
                main(null);
            }
            else if (exitChoice == 'N') {
                System.out.println("\nThank you for playing, " + playerName + "! Goodbye!");
                sleep(1000);
            }
            else {
                System.out.println("\nInvalid choice. Exiting game.");
                System.out.println("\nThank you for playing, " + playerName + "! Goodbye!");
                sleep(1000);
            }
        }

        sc.close();
    }
}