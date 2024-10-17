# Disaster Zone Bot (Java)

This project simulates a disaster zone using a bot that navigates and interacts with various terrain with debris that the bot cannot cross over. It allows users to create a disaster zone with a specified percentage of debris and a terrain type, which the bot can explore, ping, move around, and interact with. The simulation results are saved to a text file, providing a visual representation of the zone and a summary of the bot's activities.

## Components

1. **DisasterZone.java**: Contains the implementation of the `DisasterZone` class, which models the environment where the bot operates. It includes functionalities like simulating debris density, determining bot movements, and calculating distances (ping).

2. **BotController.java**: Manages the bot's navigation and actions within the `DisasterZone`. It handles movements, status updates, and interactions with the debris.

3. **DisasterZoneDriver.java**: The driver program that initiates the simulation. It prompts the user for terrain type and debris density and starts the bot's interaction with the disaster zone.

4. **DisasterZoneTest.java**: Contains JUnit tests for the `DisasterZone` class, verifying behaviors like bot status updates, distance pings, and target retrieval.

5. **Simulation Output**: Generates a text file containing the simulation results and a visual representation of the disaster zone's state after the bot's interactions.

## How to Use

1. **User Input**: 
   - The program prompts the user to choose whether to use a default zone or create a custom one.
   - If a custom zone is chosen, the user selects the terrain type (random, building, or structure) and specifies the percentage of debris (0-100%).

2. **Bot Navigation**:
   - The bot uses a ping method to detect objects or obstacles in the environment.
   - It can move forward, turn left, and navigate around obstacles.
   - The status of the bot, including its direction and location, is displayed throughout the simulation.

3. **Output**:
   - The results of the simulation, including the bot's interactions with the zone and a visual map of the environment, are saved in a text file.

## Compile

```bash
javac DisasterZoneDriver.java

