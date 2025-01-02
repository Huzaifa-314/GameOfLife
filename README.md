# Conway's Game of Life  

An interactive implementation of **Conway's Game of Life** built using **JavaFX**. This project demonstrates the principles of cellular automata, where complex patterns emerge from simple rules. The application provides a dynamic and engaging interface to explore, create, and simulate the Game of Life.

---

## 📚 What is Conway's Game of Life?  

Conway's Game of Life, created by mathematician John Horton Conway, is a zero-player game consisting of a grid of cells. Each cell has two states: alive or dead. The game's evolution is determined by these four rules:

1. Any live cell with fewer than two live neighbors dies (underpopulation).  
2. Any live cell with two or three live neighbors survives.  
3. Any live cell with more than three live neighbors dies (overpopulation).  
4. Any dead cell with exactly three live neighbors becomes alive (reproduction).  

For more details and patterns, visit the [Life Lexicon](https://conwaylife.com/ref/lexicon/lex.htm).

---

## 🎮 Features  

- **Interactive Grid Drawing**: Click and drag to toggle cells between alive and dead states.  
- **Simulation Controls**: Start, pause, and reset the simulation.  
- **Zoom and Pan**: Explore large grids with smooth zooming and panning.  
- **Customizable Patterns**: Draw or recreate classic patterns like gliders, oscillators, and still lifes.  

---

## 🚀 Getting Started  

Follow these steps to set up and run the project on your local machine:

### Prerequisites  

- Java Development Kit (JDK) 11 or later  
- JavaFX SDK  

### Installation  

1. **Clone the Repository**:  
   ```
   bash
   git clone https://github.com/yourusername/conways-game-of-life.git
   cd conways-game-of-life
   ```
2. **Set Up JavaFX**:
  Download the JavaFX SDK from openjfx.io.

3. **Run the Application**:
   ```
   javac --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -d bin src/gameoflife/*.java
   java --module-path /path/to/javafx-sdk/lib --add-modules javafx.controls -cp bin gameoflife.GameOfLife
   ```


## 🔍 How to Use  

1. **Draw Patterns**:  
   - Use the left mouse button to toggle cell states.  

2. **Control Simulation**:  
   - Click "Simulate" to start the game.  
   - Use "Clear" to reset the grid.  

3. **Explore Patterns**:  
   - Visit the [Life Lexicon](https://conwaylife.com/ref/lexicon/lex.htm) to find and recreate interesting patterns.  

---

## 🙌 Contributions  

Contributions are welcome! To contribute:  
1. Fork the repository.  
2. Create a new branch for your feature:  
   ```
   bash
   git checkout -b feature-name
   ```
3. Commit your changes:
   ```
   git commit -m "Add new feature"
   ```


## 📜 License  

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.  

---

## 🌟 Acknowledgments  

Special thanks to **Md. Faysal Imran**, Assistant Professor, IUBAT, for his guidance and support during the development of this project.  

---

Enjoy exploring the wonders of Conway's Game of Life! 🎮✨  
