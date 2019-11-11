package my15square.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.Toast;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author: Gianni Magliana
 * @description: This class creates a gridView that displays 16 buttons for the game to be played.
 * When program is launched the button board is made, then the buttons are scrambled and the boardSize of the board are set.
 * In this case it will be a 4x4 grid
 * @date: 11/10/19
 * @bugs: Restart button won't work
 */
public class MainActivity extends AppCompatActivity {

    //instance variables
    private static final int col = 4; //col (4x4 grid)
    private static final int boardSize = col * col;
    private static String[] squareList;//array of Strings that represent numbers
    private static int columnWidth;//width and height will be used to make the grid evenly spaced
    private static int columnHeight;
    private static myGV grid;// instance of the myGV class
    public static final String up= "up";
    public static final String down="down";
    public static final String left="left";
    public static final String right="right";






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeBoard();
        randomize();
        setBoardSize();
    }

    /**
     * randomizes tile placement
     */
    private void randomize() {
        int squareIndex;
        String tempSquare;
        Random rng = new Random();

        for (int i = squareList.length - 1; i > 0; i--) {
            squareIndex = rng.nextInt(i+1);
            tempSquare = squareList[squareIndex];
            squareList[squareIndex] = squareList[i];
            squareList[i] = tempSquare;
        }
    }

    /**
     * creates the board with buttons
     * Connects main to layout in XML
     * https://stackoverflow.com/questions/18508354/android-findviewbyid-in-custom-view
     */
    private void initializeBoard() {
        grid = findViewById(R.id.myGrid);
        grid.setNumColumns(col);

        squareList= new String[boardSize];
        for (int i = 0; i < boardSize; i++) {
            squareList[i] = String.valueOf(i);
        }
    }

    /**
     * draws the buttons
     * @param context the view
     */
    private static void drawSquares(Context context) {
        ArrayList<Button> tiles = new ArrayList<>();
        Button button;

        for (int i = 0; i < squareList.length; i++) {
            button = new Button(context);

            if (squareList[i].equals("0")) {
                button.setBackgroundResource(R.drawable.square1);
            } else if (squareList[i].equals("1")) {
                button.setBackgroundResource(R.drawable.square2);
            } else if (squareList[i].equals("2")) {
                button.setBackgroundResource(R.drawable.square3);
            } else if (squareList[i].equals("3") ) {
                button.setBackgroundResource(R.drawable.square4);
            } else if (squareList[i].equals("4")) {
                button.setBackgroundResource(R.drawable.square5);
            } else if (squareList[i].equals("5")) {
                button.setBackgroundResource(R.drawable.square6);
            } else if (squareList[i].equals("6")) {
                button.setBackgroundResource(R.drawable.square7);
            } else if (squareList[i].equals("7")) {
                button.setBackgroundResource(R.drawable.square8);
            } else if (squareList[i].equals("8")) {
                button.setBackgroundResource(R.drawable.square9);
            } else if (squareList[i].equals("9")) {
                button.setBackgroundResource(R.drawable.square10);
            } else if (squareList[i].equals("10")) {
                button.setBackgroundResource(R.drawable.square11);
            } else if (squareList[i].equals("11")) {
                button.setBackgroundResource(R.drawable.square12);
            } else if (squareList[i].equals("12")) {
                button.setBackgroundResource(R.drawable.square13);
            } else if (squareList[i].equals("13")) {
                button.setBackgroundResource(R.drawable.square14);
            } else if (squareList[i].equals("14")) {
                button.setBackgroundResource(R.drawable.square15);
            } else if (squareList[i].equals("15")) {
                button.setBackgroundResource(R.drawable.blank);
            }

            tiles.add(button);
        }
        grid.setAdapter(new GridAdapter(tiles, columnWidth, columnHeight));
    }

    /**
     * https://developer.android.com/reference/android/view/ViewTreeObserver
     */
    private void setBoardSize() {
        ViewTreeObserver observer = grid.getViewTreeObserver();
        observer.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                grid.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                //get boardSize of the View so it can be divided by the number of col
                int viewWidth = grid.getMeasuredWidth();
                int viewHeight = grid.getMeasuredHeight();

                //make board have proportional spaces for each square
                columnWidth = viewWidth / col;
                columnHeight = viewHeight / col;

                int statusHeight = getStatusHeight(getApplicationContext());
                int neededHeight = viewHeight - statusHeight;

                drawSquares(getApplicationContext());
            }
        });
    }

    /**
     *https://stackoverflow.com/questions/3407256/height-of-status-bar-in-android
     * for formatting to screen
     */
    private int getStatusHeight(Context context) {
        int height = 0;
        int viewId = context.getResources().getIdentifier("statusHeight", "size", "android");

        if (viewId > 0) {
            height = context.getResources().getDimensionPixelSize(viewId);
        }
        return height;
    }

    /**
     * this method switches tiles with each other
     * @param context what view this references
     * @param currentSpot spot of first tile
     * @param nextSpot spot of second tile, that switches with first
     */
    private static void switchTiles(Context context, int currentSpot, int nextSpot) {
        String newPosition = squareList[currentSpot + nextSpot];
        squareList[currentSpot + nextSpot] = squareList[currentSpot];
        squareList[currentSpot] = newPosition;
        drawSquares(context);

        if (gameOver()) {
            Toast.makeText(context, "Finished!", Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * gameOver method checks if puzzle is solved
     *
     * @return returns a boolean that is true if the tiles have been arranged in correct numerical order
     * false if otherwise
     */
    private static boolean gameOver() {
        boolean gameWon = false;

        for (int i = 0; i < squareList.length; i++) {
            if (squareList[i].equals(String.valueOf(i))) {
                gameWon = true;
            } else {
                gameWon = false;
                break;
            }
        }
        return gameWon;
    }

    /**
     *This method is called whenever Tiles are switching spots.
     * @param context what view
     * @param direction either 2,4,8,6 for down,left,right,up respectively. Passed from gridView to determine how tiles are changing
     * @param subjectTile Tile that is changing to another spot on grid
     */
    public static void moveTiles(Context context, String direction, int subjectTile) {

        // Upper-left-corner tile
        if (subjectTile == 0) {

            if (direction.equals(right)) switchTiles(context, subjectTile, 1);
            else if (direction.equals(down)) switchTiles(context, subjectTile, col);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-center tiles
        } else if (subjectTile > 0 && subjectTile < col - 1) {
            if (direction.equals(left)) switchTiles(context, subjectTile, -1);
            else if (direction.equals(down)) switchTiles(context, subjectTile, col);
            else if (direction.equals(right)) switchTiles(context, subjectTile, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Upper-right-corner tile
        } else if (subjectTile == col - 1) {
            if (direction.equals(left)) switchTiles(context, subjectTile, -1);
            else if (direction.equals(down)) switchTiles(context, subjectTile, col);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Left-side tiles
        } else if (subjectTile > col - 1 && subjectTile < boardSize - col &&
                subjectTile % col == 0) {
            if (direction.equals(up)) switchTiles(context, subjectTile, -col);
            else if (direction.equals(right)) switchTiles(context, subjectTile, 1);
            else if (direction.equals(down)) switchTiles(context, subjectTile, col);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Right-side AND bottom-right-corner tiles
        } else if (subjectTile == col * 2 - 1 || subjectTile == col * 3 - 1) {
            if (direction.equals(up)) switchTiles(context, subjectTile, -col);
            else if (direction.equals(left)) switchTiles(context, subjectTile, -1);
            else if (direction.equals(down)) {

                // Tolerates only the right-side tiles to switchTiles downwards as opposed to the bottom-
                // right-corner tile.
                if (subjectTile <= boardSize - col - 1) switchTiles(context, subjectTile,
                        col);
                else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();
            } else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-left corner tile
        } else if (subjectTile == boardSize - col) {
            if (direction.equals(up)) switchTiles(context, subjectTile, -col);
            else if (direction.equals(right)) switchTiles(context, subjectTile, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Bottom-center tiles
        } else if (subjectTile < boardSize - 1 && subjectTile > boardSize - col) {
            if (direction.equals(up)) switchTiles(context, subjectTile, -col);
            else if (direction.equals(left)) switchTiles(context, subjectTile, -1);
            else if (direction.equals(right)) switchTiles(context, subjectTile, 1);
            else Toast.makeText(context, "Invalid move", Toast.LENGTH_SHORT).show();

            // Center tiles
        } else {
            if (direction.equals(up)) switchTiles(context, subjectTile, -col);
            else if (direction.equals(left)) switchTiles(context, subjectTile, -1);
            else if (direction.equals(right)) switchTiles(context, subjectTile, 1);
            else switchTiles(context, subjectTile, col);
        }

    }
}