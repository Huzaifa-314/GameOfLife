package gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Duration;

interface CONFIG {
    double CELL_SIZE = 30.0;
    int WINDOW_WIDTH = 1200;
    int WINDOW_HEIGHT = 700;
}

public class GameOfLife extends Application implements CONFIG {

    private boolean isSimulating = false;
    private int lastToggledX = -1;
    private int lastToggledY = -1;

    // Variables for panning
    private double lastMouseX = 0;
    private double lastMouseY = 0;

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setResizable(false);
        primaryStage.setFullScreen(false);

        Pane pane = new Pane();
        VBox root = new VBox();
        HBox buttonBox = new HBox();

        Button drawButton = new Button("Draw on Graph");
        Button simulateButton = new Button("Simulate");
        Button clearButton = new Button("Clear Graph");

        buttonBox.getChildren().addAll(drawButton, simulateButton, clearButton);
        buttonBox.setSpacing(10);
        buttonBox.setStyle("-fx-alignment: center; -fx-padding: 10;");

        Scene scene = new Scene(root, WINDOW_WIDTH, WINDOW_HEIGHT);
        root.getChildren().addAll(pane, buttonBox);

//        int gridWidth = (int) Math.ceil(WINDOW_WIDTH / CELL_SIZE);
//        int gridHeight = (int) Math.ceil(WINDOW_HEIGHT / CELL_SIZE);
        int gridWidth = 100;
        int gridHeight = 100;

        Grid grid = new Grid(gridWidth, gridHeight, CELL_SIZE);
        grid.render(pane);

        // Mouse event handlers for drawing
        pane.setOnMousePressed(event -> {
            if (!isSimulating) {
                toggleCellUnderMouse(event, grid);
            } else {
                lastMouseX = event.getSceneX();
                lastMouseY = event.getSceneY();
            }
        });

        pane.setOnMouseDragged(event -> {
            if (!isSimulating) {
                toggleCellUnderMouse(event, grid);
            } else {
                double deltaX = event.getSceneX() - lastMouseX;
                double deltaY = event.getSceneY() - lastMouseY;

                pane.setTranslateX(pane.getTranslateX() + deltaX);
                pane.setTranslateY(pane.getTranslateY() + deltaY);

                lastMouseX = event.getSceneX();
                lastMouseY = event.getSceneY();
            }
        });

        pane.setOnMouseReleased(event -> {
            lastToggledX = -1;
            lastToggledY = -1;
        });

        // Zooming with scroll event
        pane.setOnScroll(event -> {
            if (event.isControlDown()) {
                double zoomFactor = (event.getDeltaY() > 0) ? 1.1 : 0.9;

                pane.setScaleX(pane.getScaleX() * zoomFactor);
                pane.setScaleY(pane.getScaleY() * zoomFactor);

                event.consume(); // Prevent default scrolling
            }
        });

        // Animation loop for updating the grid
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(200), e -> grid.update()));
        timeline.setCycleCount(Timeline.INDEFINITE);

        drawButton.setOnAction(e -> {
            isSimulating = false; // Stop simulation
            timeline.stop();
            simulateButton.setDisable(false);
        });

        simulateButton.setOnAction(e -> {
            isSimulating = true; // Start simulation
            timeline.play();
            simulateButton.setDisable(true);
            drawButton.setDisable(false);
        });

        clearButton.setOnAction(e -> {
            isSimulating = false; // Stop simulation if running
            timeline.stop();
            grid.clear(); // Clear the grid
            drawButton.setDisable(false); // Enable "Draw on Graph" button
            simulateButton.setDisable(false); // Enable "Simulate" button
        });

        // Set initial states of buttons
        drawButton.setDisable(true); // Start in "Draw on Graph" mode
        simulateButton.setDisable(false);

        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void toggleCellUnderMouse(MouseEvent event, Grid grid) {
        int x = (int) (event.getX() / CONFIG.CELL_SIZE);
        int y = (int) (event.getY() / CONFIG.CELL_SIZE);

        // Only toggle if this is a new cell
        if (x != lastToggledX || y != lastToggledY) {
            grid.toggleCellState(x, y);
            lastToggledX = x;
            lastToggledY = y;
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}