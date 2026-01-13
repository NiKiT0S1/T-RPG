# T-RPG - Text-Based RPG Game

A simple yet engaging text-based RPG game written in Java. Battle enemies, collect gold, level up, and become stronger!

## Features

- **Turn-Based Combat System**: Engage in strategic battles with various enemies
- **Four Enemy Types**: Fight Goblins, Orcs, Trolls, and a fearsome Dragon boss
- **Leveling System**: Gain XP from battles and level up to increase your stats
- **Inventory Management**: Collect and manage potions to aid you in battle
- **Shop System**: Purchase potions to prepare for dangerous encounters
- **Status Effects**: Gain temporary buffs, defensive stances, and manage poison effects
- **Character Progression**: Watch your health, attack power, and gold increase as you advance

## Game Mechanics

### Combat Actions
- **Attack**: Deal damage to the enemy (increased damage when buffed)
- **Defend**: Reduce incoming damage by 50% for this round
- **Use Item**: Access your inventory to use potions
- **Flee**: Escape from the battle

### Items
- **Health Potion** (Cost: 20 Gold) - Restores 30-50 health
- **Strength Potion** (Cost: 30 Gold) - Increases damage by 50% for next attack
- **Defense Potion** (Cost: 25 Gold) - Reduces incoming damage by 50% for this round

### Enemies

| Enemy | Health | Attack | Gold | XP | Difficulty |
|-------|--------|--------|------|----|----|
| Goblin | 30 | 8 | 15 | 25 | Weak |
| Orc | 50 | 12 | 25 | 40 | Medium |
| Troll | 80 | 18 | 40 | 60 | Strong |
| Dragon | 120 | 25 | 60 | 100 | Boss |

### Player Progression
- Starting Health: 100
- Starting Attack: 15
- Starting Gold: 50
- **Per Level Up**:
  - Max Health +20
  - Attack Power +5
  - XP Requirement: Current Level × 100

## Prerequisites

- Java 16 or higher (uses switch expressions - text blocks syntax)
- JDK installed and added to PATH

## How to Compile and Run

### On Windows (PowerShell/CMD)
```bash
# Navigate to the project directory
cd path\to\T-RPG

# Compile the Java file
javac Game.java

# Run the game
java Game
```

### On Linux/Mac
```bash
# Navigate to the project directory
cd path/to/T-RPG

# Compile the Java file
javac Game.java

# Run the game
java Game
```

## How to Play

1. **Start the Game**: Enter your character's name
2. **Main Menu**: Choose your action:
   - **Find Enemy**: Start a battle with a random enemy
   - **Inventory**: View your current items
   - **Shop**: Buy potions with your gold
   - **Status**: Check your character stats and active effects
   - **Relax**: Restore half of your missing health
   - **Exit**: Quit the game

3. **During Battle**:
   - Choose an action (Attack, Defend, Use Item, or Flee)
   - Damage to both you and the enemy is calculated with randomness (±0-5)
   - Defeat the enemy to gain gold and XP
   - Manage your health with potions

4. **Level Up**: When you gain enough XP, you automatically level up with improved stats

5. **Game Over**: When your health reaches 0, you can choose to play again or exit

## Project Structure

```
T-RPG/
├── Game.java           # Main game file
└──  README.md          # This file
```

## Key Code Features

- **Bitwise Flags**: Uses bitwise operations for efficient status effect management
- **Switch Expressions**: Modern Java syntax for clean control flow
- **2D Arrays**: Stores enemy statistics
- **Input Handling**: Robust Scanner usage with input validation
- **Game Loop**: Main game loop with nested battle loop

## Tips for Playing

1. **Manage Your Gold**: Buy potions before fighting stronger enemies
2. **Use Potions Strategically**: Don't waste potions on weak enemies
3. **Defend When Needed**: Use the defend action to survive tough battles
4. **Farm Weak Enemies**: Fight Goblins early to build up your gold and XP
5. **Level Up First**: Reach higher levels before attempting the Dragon

## Future Enhancements

- [ ] Add more enemy types and abilities
- [ ] Implement equipment system (armor, weapons)
- [ ] Add NPC interactions and quests
- [ ] Save/Load game functionality
- [ ] Skill/Spell system
- [ ] Multiple damage types and resistances
- [ ] Boss special abilities

## License

This is a personal project for learning purposes.

## Author

Created as a Pet Project for learning Java game development.

---

**Enjoy your adventure!** 🐉⚔️
