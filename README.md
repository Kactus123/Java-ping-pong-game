# Java-ping-pong-game
A classic two-player Ping Pong game built in Java using Swing

It started as a university project and was later **extended and improved independently** to refine the game logic, structure, and user experience.

The focus of the project is on **object-oriented design**, game loops, collision detection, and real-time input handling.

---

## Features
- Two-player local gameplay
- Keyboard-controlled paddles
- Real-time ball physics and collision detection
- Dynamic scoring system
- Win condition (first player to reach 5 points)
- Restart and new game functionality
- Player name input at the start of the game

---

## How the Game Works
- Player 1 controls the left paddle using **W / S**
- Player 2 controls the right paddle using **Arrow Up / Arrow Down**
- The ball bounces off paddles and window boundaries
- Ball speed increases after paddle collisions
- A point is awarded when the opponent misses the ball
- When a player reaches 5 points, the game ends and a restart option is shown

---

## Technical Highlights

### Object-Oriented Design
The game is structured using clear, focused classes:
- `Panel` – main game loop, rendering, and input handling
- `Game_Ball` – ball movement and collision behaviour
- `Paddle` – player-controlled paddles
- `Score_system` – score tracking and win condition logic
- `Frame` – application window setup

