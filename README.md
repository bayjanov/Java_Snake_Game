# Java_Snake_Game
An example of using Java Swing (GUI) to create a simple snake game in an OOP where the user can navigate a snake to eat apples and get longer as it does. When the game is over user can Retry the button on the Game Over screen.

============================================================================================

This program is a simple implementation of the classic game "Snake" using Java and Swing. The game window is a 600x600 black panel, where the snake is made up of green squares and the apple is a red oval. The player controls the snake's movement with arrow keys, trying to eat the apple while avoiding collisions with the walls and the snake's body. The score is incremented each time the snake eats an apple.

The program uses object-oriented programming principles and includes a NewGamePanel class that extends JPanel and implements ActionListener. The NewGamePanel class includes several instance variables, such as the snake's body parts, apples eaten, and the apple's location. It also includes methods to handle game logic, such as checking for collisions, drawing the snake and apple, and handling game over conditions.

When the game ends, a "Game Over" message is displayed in large red letters on the screen, along with the final score. A "Retry" button is also displayed to allow the player to start a new game.

Shortly, this program provides a simple example of how to create a game using Java and Swing, and demonstrates the use of object-oriented principles such as inheritance and encapsulation.
