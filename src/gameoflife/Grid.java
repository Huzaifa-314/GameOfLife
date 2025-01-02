package gameoflife;

import javafx.scene.layout.Pane;

public class Grid {
    private final int width;
    private final int height;
    private final double cellSize;
    private final Cell[][] cells;

    public Grid(int width, int height, double cellSize) {
        this.width = width;
        this.height = height;
        this.cellSize = cellSize;
        this.cells = new Cell[width][height];
        
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y, cellSize);
            }
        }
    }

    public void render(Pane pane) {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                Cell cell = cells[x][y];
                cell.setPosition(0, 0); // Adjust origin as needed
                pane.getChildren().add(cell.getRectangle());
            }
        }
    }

    public void update() {
        boolean[][] nextState = new boolean[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int liveNeighbors = countLiveNeighbors(x, y);
                if (cells[x][y].isAlive()) {
                    nextState[x][y] = liveNeighbors == 2 || liveNeighbors == 3;
                } else {
                    nextState[x][y] = liveNeighbors == 3;
                }
            }
        }

        // Apply the next state to the grid
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].setAlive(nextState[x][y]);
            }
        }
    }
    
    public void clear() {
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y].setAlive(false);
            }
        }
    }
    

    private int countLiveNeighbors(int x, int y) {
        int count = 0;

        for (int dx = -1; dx <= 1; dx++) {
            for (int dy = -1; dy <= 1; dy++) {
                if (dx == 0 && dy == 0) continue;

                int neighborX = x + dx;
                int neighborY = y + dy;

                // Check bounds and count live neighbors
                if (neighborX >= 0 && neighborX < width && neighborY >= 0 && neighborY < height) {
                    if (cells[neighborX][neighborY].isAlive()) {
                        count++;
                    }
                }
            }
        }

        return count;
    }

    public void toggleCellState(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            cells[x][y].toggleAlive();
        }
    }
}