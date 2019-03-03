/*
DWIP PATEL
Mr. Le
ICS4U
January 22, 2018

This is a remake of the classic board game SORRY, which features 2, 3, or 4 human players.
This program may seem to be VERY lengthy, however there was lots of code repeated for different
uses for 16 individual buttons which could not be made into a method because of how small and
specific the changes were. Don't worry, I commented the difference between different
things in the methods at the beginning and not for each line since the differences are minor
and there is no point of commenting the same thing for 2000 line.
*/

/*
SOURCES
http://www.informit.com/articles/article.aspx?p=2359759
https://docs.oracle.com/javafx/2/ui_controls/button.htm
https://www.hasbro.com/common/instruct/Sorry.PDF
https://www.youtube.com/watch?v=SpL3EToqaXA&index=5&list=PL6gx4Cwl9DGBzfXLWLSYVy8EbTdpGbUIG
http://demandware.edgesuite.net/sits_pod37/dw/image/v2/BBTK_PRD/on/demandware.static/-/Sites-master-catalog/default/dw6c7d7ff3/images/hi-res/17184_2.jpg


*/

import javafx.animation.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import java.util.ArrayList;
import java.util.Random;

public class Sorry extends Application {

    //main stage where most things occur
    Stage window;
    //array list of all the cards, red pieces, yellow pieces, green pieces, and blue pieces
    public static ArrayList<ImageView> cardList = new ArrayList<ImageView>();
    public static ArrayList<ImageView> redList = new ArrayList<ImageView>();
    public static ArrayList<ImageView> yellowList = new ArrayList<ImageView>();
    public static ArrayList<ImageView> greenList = new ArrayList<ImageView>();
    public static ArrayList<ImageView> blueList = new ArrayList<ImageView>();
    //for number of players
    public static int num = 0;
    //for random card
    public static int random;
    //button to chose card
    public static Button cardButton;
    //layout of the main game
    public static Group gameLayout;
    //image views for all images that needed to be static, board, red, green, blue and yellow pieces
    public static ImageView boardIV;
    public static ImageView red1IV, red2IV, red3IV, red4IV;
    public static ImageView yellow1IV, yellow2IV, yellow3IV, yellow4IV;
    public static ImageView green1IV, green2IV, green3IV, green4IV;
    public static ImageView blue1IV, blue2IV, blue3IV, blue4IV;
    //images for all the pieces
    public static Image red1, red2, red3, red4;
    public static Image yellow1, yellow2, yellow3, yellow4;
    public static Image green1, green2, green3, green4;
    public static Image blue1, blue2, blue3, blue4;
    //keeps track of turn
    public static int turn = 1;

    public static int y = 0;
    //used for input for card 7
    public static int indicator = 0;
    //all static buttons needed for selecting a move
    public static Button moveRed1, moveRed2, moveRed3, moveRed4;
    public static Button moveBlue1, moveBlue2, moveBlue3, moveBlue4;
    public static Button moveYellow1, moveYellow2, moveYellow3, moveYellow4;
    public static Button moveGreen1, moveGreen2, moveGreen3, moveGreen4;
    //to keep track of the direction to move for card 10
    public static int direction;
    //layout for card 7 needed to be static because it was used by a button
    public static VBox seven;
    //used to take the user input when card 7 is chosen
    public static int newInput;


    public static void main(String[] args) {
        launch(args);
    }

    //method to run the whole code
    @Override
    public void start(Stage primaryStage) {
        //main stage
        window = primaryStage;

        //Main start button initialized and text set to "Start". Then the button's color and text size and font was changed
        Button startButton = new Button("Start");
        startButton.setStyle("-fx-font: 22 arial; -fx-base: #FE5353");
        startButton.setPrefSize(125, 35);

        //Instructions button on the main screen initialized and text set to "Instructions". Then the button's color and text size and font was changed
        Button instructions = new Button("Instructions");
        instructions.setStyle("-fx-font: 12 arial; -fx-base: #F89A87");
        instructions.setPrefSize(90, 25);

        //got images for the main background and the second part of the background that moves
        Image mainMenuBackground = new Image("MainMenuBackground.JPG");
        Image mainMenuPieces = new Image("MenuGamePieces.PNG");

        //made the background an imageview and set the height as 630 pixels and kept the ratio
        ImageView mainBackgroundIV = new ImageView();
        mainBackgroundIV.setImage(mainMenuBackground);
        mainBackgroundIV.setFitHeight(630);
        mainBackgroundIV.setPreserveRatio(true);

        //made this background an imageview and set the position to 630 by 630 pixels
        ImageView mainPiecesIV = new ImageView();
        mainPiecesIV.setImage(mainMenuPieces);
        mainPiecesIV.setX(315);
        mainPiecesIV.setY(315);

        //created a transition
        RotateTransition rotatePieces = new RotateTransition();
        //set it to rotate the given imageview (mainPiecesIV)
        rotatePieces.setNode(mainPiecesIV);
        //se it to rotate 360 degrees
        rotatePieces.setFromAngle(0);
        rotatePieces.setToAngle(360);
        //made the rotating fluent
        rotatePieces.setInterpolator(Interpolator.LINEAR);
        //made it rotate non-stop
        rotatePieces.setCycleCount(Timeline.INDEFINITE);
        //made it fully rotate every 5 seconds
        rotatePieces.setDuration(Duration.seconds(5));
        rotatePieces.play();


        //vertical layout for the buttons on the main menu. Made padding around the layout. Positioned to the center
        VBox mainMenuButtons = new VBox(5);
        mainMenuButtons.getChildren().addAll(startButton, instructions);
        mainMenuButtons.setPadding(new Insets(70, 0, 70, 0));
        mainMenuButtons.setAlignment(Pos.BOTTOM_CENTER);

        //layout to stack things on top one another
        StackPane mainMenuLayout = new StackPane();
        mainMenuLayout.getChildren().addAll(mainBackgroundIV, mainPiecesIV, mainMenuButtons);

        //buttons to go back to menu and next/ back page
        Button next = new Button("Next");
        Button previous = new Button("Previous");
        Button back = new Button("Menu");
        Button back2 = new Button("Menu");

        //Created and wrote the instructions text and changed the the style, font, and size for each
        Text object = new Text("OBJECT");
        object.setFont(Font.font("Goudy Stout", FontWeight.BOLD, 25));
        Text objectDescription = new Text("To be the first player to get all four of your pawns from your color START to your" + "\n" + "color HOME.");
        objectDescription.setFont(Font.font("Gabriola", 20));
        Text gamePlay = new Text("GAME PLAY");
        gamePlay.setFont(Font.font("Goudy Stout", FontWeight.BOLD, 25));
        Text gamePlayDescription = new Text("TO START A PAWN" + "\n" + "On your first turn, you must draw either a 1 or a 2 in order to move a pawn from start." + "\n" +
                "If none of those cards are drawn, then your turn is skipped and the next person goes." + "\n" +
                "On all turns, take the top card on the Draw pile and move as directed on the card." + "\n" + "\n" +
                "JUMPING" + "\n" + "You may jump over your own or another player's pawn that's in your way," + "\n" +
                "counting it as one space.");
        gamePlayDescription.setFont(Font.font("Gabriola", 20));

        //horizontal layout for instructions buttons, added spacing, padding, set to bottom center
        HBox instructionButtons = new HBox(40);
        instructionButtons.getChildren().addAll(back, next);
        instructionButtons.setAlignment(Pos.BOTTOM_CENTER);
        instructionButtons.setPadding(new Insets(102, 0, 0, 0));

        //vertical layout for instructions text, added spacing, padding, set to top center, changed the background colour
        VBox instructionsLayout = new VBox(10);
        instructionsLayout.getChildren().addAll(object, objectDescription, gamePlay, gamePlayDescription, instructionButtons);
        instructionsLayout.setAlignment(Pos.TOP_CENTER);
        instructionsLayout.setPadding(new Insets(30, 0, 30, 0));
        instructionsLayout.setStyle("-fx-background-color: #F9B5A7");

        //Created and wrote the cards description text and changed the the style, font, and size for each
        Text cards = new Text("THE CARDS");
        cards.setFont(Font.font("Goudy Stout", FontWeight.BOLD, 25));
        Text cardsDescription = new Text(
                "1 - Starts a pawn out or moves pawn forward 1 space." + "\n" +
                        "2 - Starts a pawn out or moves one pawn forward 2 spaces." + "\n" + "Whether you move or cannot move, draw again and move accordingly." + "\n" +
                        "3 - Moves one pawn forward 3 spaces." + "\n" +
                        "4 - Moves one pawn backwards 4 spaces." + "\n" +
                        "5 - Moves one pawn forward 5 spaces." + "\n" +
                        "7 - Moves one pawn forward 7 spaces or can split the forward move between any two pawns." + "\n" +
                        "8 - Moves one pawn forward 8 spaces." + "\n" +
                        "10 - Moves one pawn forward 10 spaces or moves one pawn backwards 1 space." + "\n" +
                        "11 - Moves one pawn forward 11 spaces." + "\n" +
                        "opponent's (not in Start, Safety Zone, or Home)." + "\n" +
                        "12 - Moves one pawn forward 12 spaces." + "\n" +
                        "(0)SORRY! - This card allows your to take one pawn from your Start and place it on any space" + "\n" +
                        "that is occupied by any opponent. This bumps that opponent's pawn back to its Start." + "\n" +
                        "If there is no pawn on your Start or no opponent's pawn on any space you can move to," + "\n" +
                        "you forfeit your move.");
        cardsDescription.setFont(Font.font("Gabriola", 19));

        //horizontal layout for second set of instructions buttons, added spacing, set to bottom center
        HBox instructionButtons2 = new HBox(40);
        instructionButtons2.getChildren().addAll(back2, previous);
        instructionButtons2.setAlignment(Pos.BOTTOM_CENTER);

        //vertical layout for cards text, added spacing, padding, set to top center, changed the background colour
        VBox instructionsLayout2 = new VBox(10);
        instructionsLayout2.getChildren().addAll(cards, cardsDescription, instructionButtons2);
        instructionsLayout2.setAlignment(Pos.TOP_CENTER);
        instructionsLayout2.setPadding(new Insets(30, 0, 0, 0));
        instructionsLayout2.setStyle("-fx-background-color: #F9B5A7");

        //text and buttons to chose how many players wanted, and back button to go to menu
        Text p = new Text("PLAYERS");
        p.setFont(Font.font("Goudy Stout", FontWeight.BOLD, 20));

        Button p2 = new Button("2 Players");
        p2.setPrefSize(100, 30);
        Button p3 = new Button("3 Players");
        p3.setPrefSize(100, 30);
        Button p4 = new Button("4 Players");
        p4.setPrefSize(100, 30);
        Button back3 = new Button("Back");

        //vertical layout for player info. Set spacing, padding and colour
        VBox playerLayout = new VBox(20);
        playerLayout.getChildren().addAll(p, p2, p3, p4, back3);
        playerLayout.setPadding(new Insets(30, 30, 30, 30));
        playerLayout.setAlignment(Pos.CENTER);
        playerLayout.setStyle("-fx-background-color: #F9B5A7");


        //set up scenes for the main menu, both instructions, and player menu. Set dimensions for all scenes and added layout
        Scene mainMenu = new Scene(mainMenuLayout, 630, 630);
        Scene instructionsMenu = new Scene(instructionsLayout, 630, 630);
        Scene instructionsMenu2 = new Scene(instructionsLayout2, 630, 630);
        Scene playerMenu = new Scene(playerLayout, 630,630);

        //goes to main menu when clicked
        startButton.setOnAction(e -> window.setScene(playerMenu));
        //goes to main menu when clicked
        back3.setOnAction(e -> window.setScene(mainMenu));
        //goes to first instructions menu when clicked
        instructions.setOnAction(e -> window.setScene(instructionsMenu));
        //goes to main menu when clicked
        back.setOnAction(e -> window.setScene(mainMenu));
        //goes to main menu when clicked
        back2.setOnAction(e -> window.setScene(mainMenu));
        //goes to second instructions menu when clicked
        next.setOnAction(e -> window.setScene(instructionsMenu2));
        //goes to first instructions menu when clicked
        previous.setOnAction(e -> window.setScene(instructionsMenu));


        //gets the images for all the cards from 0 to 12
        Image card0 = new Image("0.JPG");
        Image card1 = new Image("1.JPG");
        Image card2 = new Image("2.JPG");
        Image card3 = new Image("3.JPG");
        Image card4 = new Image("4.JPG");
        Image card5 = new Image("5.JPG");
        Image card6 = new Image("6.JPG");
        Image card7 = new Image("7.JPG");
        Image card8 = new Image("8.JPG");
        Image card9 = new Image("9.JPG");
        Image card10 = new Image("10.JPG");
        Image card11 = new Image("11.JPG");
        Image card12 = new Image("12.JPG");

        //makes all the image of the cards imageviews
        ImageView cardIV0 = new ImageView();
        cardIV0.setImage(card0);
        ImageView cardIV1 = new ImageView();
        cardIV1.setImage(card1);
        ImageView cardIV2 = new ImageView();
        cardIV2.setImage(card2);
        ImageView cardIV3 = new ImageView();
        cardIV3.setImage(card3);
        ImageView cardIV4 = new ImageView();
        cardIV4.setImage(card4);
        ImageView cardIV5 = new ImageView();
        cardIV5.setImage(card5);
        ImageView cardIV6 = new ImageView();
        cardIV6.setImage(card6);
        ImageView cardIV7 = new ImageView();
        cardIV7.setImage(card7);
        ImageView cardIV8 = new ImageView();
        cardIV8.setImage(card8);
        ImageView cardIV9 = new ImageView();
        cardIV9.setImage(card9);
        ImageView cardIV10 = new ImageView();
        cardIV10.setImage(card10);
        ImageView cardIV11 = new ImageView();
        cardIV11.setImage(card11);
        ImageView cardIV12 = new ImageView();
        cardIV12.setImage(card12);

        //set the width of each card to 125 and keeps the ratio
        cardIV0.setFitWidth(125);
        cardIV0.setPreserveRatio(true);
        cardIV1.setFitWidth(125);
        cardIV1.setPreserveRatio(true);
        cardIV2.setFitWidth(125);
        cardIV2.setPreserveRatio(true);
        cardIV3.setFitWidth(125);
        cardIV3.setPreserveRatio(true);
        cardIV4.setFitWidth(125);
        cardIV4.setPreserveRatio(true);
        cardIV5.setFitWidth(125);
        cardIV5.setPreserveRatio(true);
        cardIV6.setFitWidth(125);
        cardIV6.setPreserveRatio(true);
        cardIV7.setFitWidth(125);
        cardIV7.setPreserveRatio(true);
        cardIV8.setFitWidth(125);
        cardIV8.setPreserveRatio(true);
        cardIV9.setFitWidth(125);
        cardIV9.setPreserveRatio(true);
        cardIV10.setFitWidth(125);
        cardIV10.setPreserveRatio(true);
        cardIV11.setFitWidth(125);
        cardIV11.setPreserveRatio(true);
        cardIV12.setFitWidth(125);
        cardIV12.setPreserveRatio(true);

        //adds each card imageview to a list
        cardList.add(cardIV0);
        cardList.add(cardIV1);
        cardList.add(cardIV2);
        cardList.add(cardIV3);
        cardList.add(cardIV4);
        cardList.add(cardIV5);
        cardList.add(cardIV6);
        cardList.add(cardIV7);
        cardList.add(cardIV8);
        cardList.add(cardIV9);
        cardList.add(cardIV10);
        cardList.add(cardIV11);
        cardList.add(cardIV12);

        //image for board
        Image board = new Image("board.png");

        //gets images for each piece, red, blue, green, yellow
        red1 = new Image("red1.png");
        red2 = new Image("red2.png");
        red3 = new Image("red3.png");
        red4 = new Image("red4.png");

        blue1 = new Image("blue1.png");
        blue2 = new Image("blue2.png");
        blue3 = new Image("blue3.png");
        blue4 = new Image("blue4.png");

        green1 = new Image("green1.png");
        green2 = new Image("green2.png");
        green3 = new Image("green3.png");
        green4 = new Image("green4.png");

        yellow1 = new Image("yellow1.png");
        yellow2 = new Image("yellow2.png");
        yellow3 = new Image("yellow3.png");
        yellow4 = new Image("yellow4.png");

        //makes board and imageview
        boardIV = new ImageView();
        boardIV.setImage(board);

        //makes imageview for red pieces
        red1IV = new ImageView();
        red1IV.setImage(red1);
        red1IV.setFitHeight(40);
        red2IV = new ImageView();
        red2IV.setImage(red2);
        red2IV.setFitHeight(40);
        red3IV = new ImageView();
        red3IV.setImage(red3);
        red3IV.setFitHeight(40);
        red4IV = new ImageView();
        red4IV.setImage(red4);
        red4IV.setFitHeight(40);

        //adds imageviews to array
        redList.add(red1IV);
        redList.add(red2IV);
        redList.add(red3IV);
        redList.add(red4IV);

        //makes imageview for blue pieces
        blue1IV = new ImageView();
        blue1IV.setImage(blue1);
        blue1IV.setFitHeight(40);
        blue2IV = new ImageView();
        blue2IV.setImage(blue2);
        blue2IV.setFitHeight(40);
        blue3IV = new ImageView();
        blue3IV.setImage(blue3);
        blue3IV.setFitHeight(40);
        blue4IV = new ImageView();
        blue4IV.setImage(blue4);
        blue4IV.setFitHeight(40);

        //adds imageveiws to array
        blueList.add(blue1IV);
        blueList.add(blue2IV);
        blueList.add(blue3IV);
        blueList.add(blue4IV);

        //makes imageview for blue pieces
        green1IV = new ImageView();
        green1IV.setImage(green1);
        green1IV.setFitHeight(40);
        green2IV = new ImageView();
        green2IV.setImage(green2);
        green2IV.setFitHeight(40);
        green3IV = new ImageView();
        green3IV.setImage(green3);
        green3IV.setFitHeight(40);
        green4IV = new ImageView();
        green4IV.setImage(green4);
        green4IV.setFitHeight(40);

        //adds imageveiws to array
        greenList.add(green1IV);
        greenList.add(green2IV);
        greenList.add(green3IV);
        greenList.add(green4IV);

        //makes imageview for yellow pieces
        yellow1IV = new ImageView();
        yellow1IV.setImage(yellow1);
        yellow1IV.setFitHeight(40);
        yellow2IV = new ImageView();
        yellow2IV.setImage(yellow2);
        yellow2IV.setFitHeight(40);
        yellow3IV = new ImageView();
        yellow3IV.setImage(yellow3);
        yellow3IV.setFitHeight(40);
        yellow4IV = new ImageView();
        yellow4IV.setImage(yellow4);
        yellow4IV.setFitHeight(40);

        //adds imageviews to array
        yellowList.add(yellow1IV);
        yellowList.add(yellow2IV);
        yellowList.add(yellow3IV);
        yellowList.add(yellow4IV);

        //button for drawing a card. Changed the font, ont sizem and color of button, and changed the dimensions
        cardButton = new Button("CARD");
        cardButton.setStyle("-fx-font: 22 arial; -fx-base: #FFFFFF");
        cardButton.setPrefSize(125, 200);

        //placed the button at these coordinates
        cardButton.setTranslateX(660);
        cardButton.setTranslateY(400);

        //new layout to place everything on top of one another. Everything is added individually on purpose because specific things are changed.
        gameLayout = new Group();
        gameLayout.getChildren().add(boardIV);
        gameLayout.getChildren().add(cardButton);
        gameLayout.getChildren().add(red1IV);
        gameLayout.getChildren().add(red2IV);
        gameLayout.getChildren().add(red3IV);
        gameLayout.getChildren().add(red4IV);
        gameLayout.getChildren().add(yellow1IV);
        gameLayout.getChildren().add(yellow2IV);
        gameLayout.getChildren().add(yellow3IV);
        gameLayout.getChildren().add(yellow4IV);
        gameLayout.getChildren().add(green1IV);
        gameLayout.getChildren().add(green2IV);
        gameLayout.getChildren().add(green3IV);
        gameLayout.getChildren().add(green4IV);
        gameLayout.getChildren().add(blue1IV);
        gameLayout.getChildren().add(blue2IV);
        gameLayout.getChildren().add(blue3IV);
        gameLayout.getChildren().add(blue4IV);


        //scene where the game is
        Scene game = new Scene(gameLayout, 860,640);

        //sets for 2 players, sets num to 2 and changes the scene
        p2.setOnAction(e -> {
            num = 2;
            window.setScene(game);
            AlertBox.display("Turn", "Red goes first!!");
        });
        //sets for 3 players, sets num to 3 and changes the scene
        p3.setOnAction(e -> {
            num = 3;
            window.setScene(game);
            AlertBox.display("Turn", "Red goes first!!");
        });
        //sets for 4 players, sets num to 4 and changes the scene
        p4.setOnAction(e -> {
            num = 4;
            window.setScene(game);
            AlertBox.display("Turn", "Red goes first!!");
        });


        //sets coordinates for each piece at specific places
        red1IV.setX(420);
        red1IV.setY(510);
        red2IV.setX(460);
        red2IV.setY(510);
        red3IV.setX(420);
        red3IV.setY(540);
        red4IV.setX(460);
        red4IV.setY(540);

        yellow1IV.setX(140);
        yellow1IV.setY(50);
        yellow2IV.setX(180);
        yellow2IV.setY(50);
        yellow3IV.setX(140);
        yellow3IV.setY(80);
        yellow4IV.setX(180);
        yellow4IV.setY(80);

        green1IV.setX(510);
        green1IV.setY(145);
        green2IV.setX(550);
        green2IV.setY(145);
        green3IV.setX(510);
        green3IV.setY(175);
        green4IV.setX(550);
        green4IV.setY(175);

        blue1IV.setX(55);
        blue1IV.setY(420);
        blue2IV.setX(95);
        blue2IV.setY(420);
        blue3IV.setX(55);
        blue3IV.setY(450);
        blue4IV.setX(95);
        blue4IV.setY(450);

        //starts the game when the card is drawn, checks who's turn it is and does the corresponding action
        cardButton.setOnAction(e -> {
            //if 4 players
            if (num == 4) {
                //if red's turn
                if (turn == 1) {
                    //choses random card
                    random = randomNum();
                    //displays card and player number
                    AlertBox.display("Card", "Player 1 number: " + random);
                    //method to change layout
                    showButtons(random);

                }
                //same as if turn is 1, but this is for blue's turn
                else if (turn == 2) {
                    random = randomNum();
                    AlertBox.display("Card", "Player 2 number: " + random);

                    showButtons(random);

                }
                //same as if turn is 1, but this is for yellow's turn
                else if (turn == 3) {
                    random = randomNum();
                    AlertBox.display("Card", "Player 3 number: " + random);

                    showButtons(random);

                }
                //same as if turn is 1, but this is for greens's turn
                else if (turn == 4) {
                    random = randomNum();
                    AlertBox.display("Card", "Player 4 number: " + random);

                    showButtons(random);

                }
                //checks if anyone wins
                checkWin();
            }
            //if 3 players
            else if (num == 3) {
                //if red's turn
                if (turn == 1) {
                    //chooses random card
                    random = randomNum();
                    //displays card and player number
                    AlertBox.display("Card", "Player 1 number: " + random);
                    //method to change layout
                    showButtons(random);

                }
                //same as if turn is 1, but this is for blue's turn
                else if (turn == 2) {
                    random = randomNum();
                    AlertBox.display("Card", "Player 2 number: " + random);

                    showButtons(random);

                }
                //same as if turn is 1, but this is for yellow's turn
                else if (turn == 3) {
                    random = randomNum();
                    AlertBox.display("Card", "Player 3 number: " + random);

                    showButtons(random);

                }
                //in order to change it to player 1's turn since there is no forth player
                else {
                    turn = 1;

                }
                //checks if anyone wins
                checkWin();
            }
            //if 2 players
            else if (num == 2) {
                //if red's turn
                if (turn == 1) {
                    //chooses random card
                    random = randomNum();
                    //displays card and player number
                    AlertBox.display("Card", "Player 1 number: " + random);
                    //method to change layout
                    showButtons(random);

                }
                //same as if turn is 1, but this is for blue's turn
                else if (turn == 2) {
                    random = randomNum();
                    AlertBox.display("Card", "Player 2 number: " + random);

                    showButtons(random);

                }
                //in order to change it to player 1's turn since there is no third or forth player
                else {
                    turn = 1;
                }
                //checks if anyone wins
                checkWin();
            }
        });

        //sets starting scene to main menu, changes the title
        window.setScene(mainMenu);
        window.setTitle("SORRY! The Game");
        window.show();
    }

    //for zero card if player 1's turn
    public void cardZero1(VBox type) {
        //the code for all of these are the same, the only difference is that each button checks for a different piece
        //whatever button it is, that's the piece it checks to bump
        //the coordinates for each piece are also different
        //they all are if it is player 1's turn.
        //the first one has commenting and the rest is the same

        moveBlue1.setOnAction(e -> {
            //checks if checks if blue piece is on the board
            if (blue1IV.getX() == 0 || blue1IV.getX() == 600 || blue1IV.getY() == 0 || blue1IV.getY() == 600) {
                //loops to check each red piece
                for (int i = 0; i < redList.size(); i++) {
                    //if it is, then it checks if you have a red piece
                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {
                        //sets the red piece's coordinates to the blue piece
                        redList.get(i).setX(blue1IV.getX());
                        redList.get(i).setY(blue1IV.getY());
                        //sends blue piece to exact starting point
                        blue1IV.setX(55);
                        blue1IV.setY(420);

                        turn = 2;
                        //changes layout back to the card
                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }
                    //if there is no red piece available
                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make this move");

                        turn = 2;
                        //changes layout to the card
                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue2.setOnAction(e -> {
            if (blue2IV.getX() == 0 || blue2IV.getX() == 600 || blue2IV.getY() == 0 || blue2IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(blue2IV.getX());
                        redList.get(i).setY(blue2IV.getY());

                        blue2IV.setX(95);
                        blue2IV.setY(420);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue3.setOnAction(e -> {
            if (blue3IV.getX() == 0 || blue3IV.getX() == 600 || blue3IV.getY() == 0 || blue3IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(blue3IV.getX());
                        redList.get(i).setY(blue3IV.getY());

                        blue3IV.setX(55);
                        blue3IV.setY(450);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue4.setOnAction(e -> {
            if (blue4IV.getX() == 0 || blue4IV.getX() == 600 || blue4IV.getY() == 0 || blue4IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(blue4IV.getX());
                        redList.get(i).setY(blue4IV.getY());

                        blue4IV.setX(95);
                        blue4IV.setY(450);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });

        moveYellow1.setOnAction(e -> {
            if (yellow1IV.getX() == 0 || yellow1IV.getX() == 600 || yellow1IV.getY() == 0 || yellow1IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(yellow1IV.getX());
                        redList.get(i).setY(yellow1IV.getY());

                        yellow1IV.setX(140);
                        yellow1IV.setY(50);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow2.setOnAction(e -> {
            if (yellow2IV.getX() == 0 || yellow2IV.getX() == 600 || yellow2IV.getY() == 0 || yellow2IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(yellow2IV.getX());
                        redList.get(i).setY(yellow2IV.getY());

                        yellow2IV.setX(180);
                        yellow2IV.setY(50);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow3.setOnAction(e -> {
            if (yellow3IV.getX() == 0 || yellow3IV.getX() == 600 || yellow3IV.getY() == 0 || yellow3IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(yellow3IV.getX());
                        redList.get(i).setY(yellow3IV.getY());

                        yellow3IV.setX(140);
                        yellow3IV.setY(80);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow4.setOnAction(e -> {
            if (yellow4IV.getX() == 0 || yellow4IV.getX() == 600 || yellow4IV.getY() == 0 || yellow4IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(yellow4IV.getX());
                        redList.get(i).setY(yellow4IV.getY());

                        yellow4IV.setX(180);
                        yellow4IV.setY(80);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });

        moveGreen1.setOnAction(e -> {
            if (green1IV.getX() == 0 || green1IV.getX() == 600 || green1IV.getY() == 0 || green1IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(green1IV.getX());
                        redList.get(i).setY(green1IV.getY());

                        green1IV.setX(510);
                        green1IV.setY(145);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen2.setOnAction(e -> {
            if (green2IV.getX() == 0 || green2IV.getX() == 600 || green2IV.getY() == 0 || green2IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(green2IV.getX());
                        redList.get(i).setY(green2IV.getY());

                        green2IV.setX(550);
                        green2IV.setY(145);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen3.setOnAction(e -> {
            if (green3IV.getX() == 0 || green3IV.getX() == 600 || green3IV.getY() == 0 || green3IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(green3IV.getX());
                        redList.get(i).setY(green3IV.getY());

                        green3IV.setX(510);
                        green3IV.setY(175);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen4.setOnAction(e -> {
            if (green4IV.getX() == 0 || green4IV.getX() == 600 || green4IV.getY() == 0 || green4IV.getY() == 600) {

                for (int i = 0; i < redList.size(); i++) {

                    if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                    }
                    else {

                        redList.get(i).setX(green4IV.getX());
                        redList.get(i).setY(green4IV.getY());

                        green4IV.setX(550);
                        green4IV.setY(145);

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 2;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
    }

    //for zero card if player 2's turn
    public void cardZero2(VBox type) {
        //the code for all of these are the same, the only difference is that each button checks for a different piece
        //whatever button it is, that's the piece it checks to bump
        //the coordinates for each piece are also different
        //they all are if it is player 2's turn.
        //the first one has commenting and the rest is the same

        moveYellow1.setOnAction(e -> {
            //checks if yellow piece is on the board
            if (yellow1IV.getX() == 0 || yellow1IV.getX() == 600 || yellow1IV.getY() == 0 || yellow1IV.getY() == 600) {
                //loops for each blue piece
                for (int i = 0; i < blueList.size(); i++) {
                    //if yellow is on the board it checks if you have a red piece
                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {
                        //sets blue piece to yellow's location
                        blueList.get(i).setX(yellow1IV.getX());
                        blueList.get(i).setY(yellow1IV.getY());

                        //sends yellow back to start
                        yellow1IV.setX(140);
                        yellow1IV.setY(50);

                        turn = 3;
                        //changes to card layout
                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }
                    //if not blue piece available
                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;
                        //changes to card layout
                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow2.setOnAction(e -> {
            if (yellow2IV.getX() == 0 || yellow2IV.getX() == 600 || yellow2IV.getY() == 0 || yellow2IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(yellow2IV.getX());
                        blueList.get(i).setY(yellow2IV.getY());

                        yellow2IV.setX(180);
                        yellow2IV.setY(50);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow3.setOnAction(e -> {
            if (yellow3IV.getX() == 0 || yellow3IV.getX() == 600 || yellow3IV.getY() == 0 || yellow3IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(yellow3IV.getX());
                        blueList.get(i).setY(yellow3IV.getY());

                        yellow3IV.setX(140);
                        yellow3IV.setY(80);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow4.setOnAction(e -> {
            if (yellow4IV.getX() == 0 || yellow4IV.getX() == 600 || yellow4IV.getY() == 0 || yellow4IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(yellow4IV.getX());
                        blueList.get(i).setY(yellow4IV.getY());

                        yellow4IV.setX(180);
                        yellow4IV.setY(80);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });

        moveGreen1.setOnAction(e -> {
            if (green1IV.getX() == 0 || green1IV.getX() == 600 || green1IV.getY() == 0 || green1IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(green1IV.getX());
                        blueList.get(i).setY(green1IV.getY());

                        green1IV.setX(510);
                        green1IV.setY(145);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen2.setOnAction(e -> {
            if (green2IV.getX() == 0 || green2IV.getX() == 600 || green2IV.getY() == 0 || green2IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(green2IV.getX());
                        blueList.get(i).setY(green2IV.getY());

                        green2IV.setX(550);
                        green2IV.setY(145);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen3.setOnAction(e -> {
            if (green3IV.getX() == 0 || green3IV.getX() == 600 || green3IV.getY() == 0 || green3IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(green3IV.getX());
                        blueList.get(i).setY(green3IV.getY());

                        green3IV.setX(510);
                        green3IV.setY(175);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen4.setOnAction(e -> {
            if (green4IV.getX() == 0 || green4IV.getX() == 600 || green4IV.getY() == 0 || green4IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(green4IV.getX());
                        blueList.get(i).setY(green4IV.getY());

                        green4IV.setX(550);
                        green4IV.setY(145);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });

        moveRed1.setOnAction(e -> {
            if (red1IV.getX() == 0 || red1IV.getX() == 600 || red1IV.getY() == 0 || red1IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(red1IV.getX());
                        blueList.get(i).setY(red1IV.getY());

                        red1IV.setX(420);
                        red1IV.setY(510);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed2.setOnAction(e -> {
            if (red2IV.getX() == 0 || red2IV.getX() == 600 || red2IV.getY() == 0 || red2IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(red2IV.getX());
                        blueList.get(i).setY(red2IV.getY());

                        red2IV.setX(460);
                        red2IV.setY(510);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed3.setOnAction(e -> {
            if (red3IV.getX() == 0 || red3IV.getX() == 600 || red3IV.getY() == 0 || red3IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(red3IV.getX());
                        blueList.get(i).setY(red3IV.getY());

                        red3IV.setX(420);
                        red3IV.setY(540);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed4.setOnAction(e -> {
            if (red4IV.getX() == 0 || red4IV.getX() == 600 || red4IV.getY() == 0 || red4IV.getY() == 600) {

                for (int i = 0; i < blueList.size(); i++) {

                    if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                    }
                    else {

                        blueList.get(i).setX(red4IV.getX());
                        blueList.get(i).setY(red4IV.getY());

                        red4IV.setX(460);
                        red4IV.setY(540);

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 3;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
    }

    //for zero card if player 3's turn
    public void cardZero3(VBox type) {
        //the code for all of these are the same, the only difference is that each button checks for a different piece
        //whatever button it is, that's the piece it checks to bump
        //the coordinates for each piece are also different
        //they all are if it is player 3's turn.
        //the first one has commenting and the rest is the same

        moveGreen1.setOnAction(e -> {
            //if green is on the board
            if (green1IV.getX() == 0 || green1IV.getX() == 600 || green1IV.getY() == 0 || green1IV.getY() == 600) {
                //loops for every yellow
                for (int i = 0; i < yellowList.size(); i++) {
                    //checks if you have a yellow
                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {
                        //sends yellow to green's location
                        yellowList.get(i).setX(green1IV.getX());
                        yellowList.get(i).setY(green1IV.getY());
                        //sends green back to start
                        green1IV.setX(510);
                        green1IV.setY(145);

                        turn = 4;
                        //changes to card layout
                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;
                        //changes to card layout
                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen2.setOnAction(e -> {
            if (green2IV.getX() == 0 || green2IV.getX() == 600 || green2IV.getY() == 0 || green2IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(green2IV.getX());
                        yellowList.get(i).setY(green2IV.getY());

                        green2IV.setX(550);
                        green2IV.setY(145);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen3.setOnAction(e -> {
            if (green3IV.getX() == 0 || green3IV.getX() == 600 || green3IV.getY() == 0 || green3IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(green3IV.getX());
                        yellowList.get(i).setY(green3IV.getY());

                        green3IV.setX(510);
                        green3IV.setY(175);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveGreen4.setOnAction(e -> {
            if (green4IV.getX() == 0 || green4IV.getX() == 600 || green4IV.getY() == 0 || green4IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(green4IV.getX());
                        yellowList.get(i).setY(green4IV.getY());

                        green4IV.setX(550);
                        green4IV.setY(145);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });

        moveRed1.setOnAction(e -> {
            if (red1IV.getX() == 0 || red1IV.getX() == 600 || red1IV.getY() == 0 || red1IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(red1IV.getX());
                        yellowList.get(i).setY(red1IV.getY());

                        red1IV.setX(420);
                        red1IV.setY(510);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed2.setOnAction(e -> {
            if (red2IV.getX() == 0 || red2IV.getX() == 600 || red2IV.getY() == 0 || red2IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(red2IV.getX());
                        yellowList.get(i).setY(red2IV.getY());

                        red2IV.setX(460);
                        red2IV.setY(510);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed3.setOnAction(e -> {
            if (red3IV.getX() == 0 || red3IV.getX() == 600 || red3IV.getY() == 0 || red3IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(red3IV.getX());
                        yellowList.get(i).setY(red3IV.getY());

                        red3IV.setX(420);
                        red3IV.setY(540);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed4.setOnAction(e -> {
            if (red4IV.getX() == 0 || red4IV.getX() == 600 || red4IV.getY() == 0 || red4IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(red4IV.getX());
                        yellowList.get(i).setY(red4IV.getY());

                        red4IV.setX(460);
                        red4IV.setY(540);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });

        moveBlue1.setOnAction(e -> {
            if (blue1IV.getX() == 0 || blue1IV.getX() == 600 || blue1IV.getY() == 0 || blue1IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(blue1IV.getX());
                        yellowList.get(i).setY(blue1IV.getY());

                        blue1IV.setX(55);
                        blue1IV.setY(420);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make this move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue2.setOnAction(e -> {
            if (blue2IV.getX() == 0 || blue2IV.getX() == 600 || blue2IV.getY() == 0 || blue2IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(blue2IV.getX());
                        yellowList.get(i).setY(blue2IV.getY());

                        blue2IV.setX(95);
                        blue2IV.setY(420);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue3.setOnAction(e -> {
            if (blue3IV.getX() == 0 || blue3IV.getX() == 600 || blue3IV.getY() == 0 || blue3IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(blue3IV.getX());
                        yellowList.get(i).setY(blue3IV.getY());

                        blue3IV.setX(55);
                        blue3IV.setY(450);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue4.setOnAction(e -> {
            if (blue4IV.getX() == 0 || blue4IV.getX() == 600 || blue4IV.getY() == 0 || blue4IV.getY() == 600) {

                for (int i = 0; i < yellowList.size(); i++) {

                    if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                    }
                    else {

                        yellowList.get(i).setX(blue4IV.getX());
                        yellowList.get(i).setY(blue4IV.getY());

                        blue4IV.setX(95);
                        blue4IV.setY(450);

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 4;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
    }

    //for zero card if player 4's turn
    public void cardZero4(VBox type) {
        //the code for all of these are the same, the only difference is that each button checks for a different piece
        //whatever button it is, that's the piece it checks to bump
        //the coordinates for each piece are also different
        //they all are if it is player 4's turn.

        moveRed1.setOnAction(e -> {
            //checks if this red piece is on the board
            if (red1IV.getX() == 0 || red1IV.getX() == 600 || red1IV.getY() == 0 || red1IV.getY() == 600) {
                //loops through each green piece
                for (int i = 0; i < greenList.size(); i++) {
                    //if red is on the board, checks if you have a green
                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {
                        //sends green to red's location
                        greenList.get(i).setX(red1IV.getX());
                        greenList.get(i).setY(red1IV.getY());
                        //sends red backto start
                        red1IV.setX(420);
                        red1IV.setY(510);

                        turn = 1;
                        //changes to card layout
                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;
                        //changes to card layout
                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed2.setOnAction(e -> {
            if (red2IV.getX() == 0 || red2IV.getX() == 600 || red2IV.getY() == 0 || red2IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(red2IV.getX());
                        greenList.get(i).setY(red2IV.getY());

                        red2IV.setX(460);
                        red2IV.setY(510);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed3.setOnAction(e -> {
            if (red3IV.getX() == 0 || red3IV.getX() == 600 || red3IV.getY() == 0 || red3IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(red3IV.getX());
                        greenList.get(i).setY(red3IV.getY());

                        red3IV.setX(420);
                        red3IV.setY(540);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveRed4.setOnAction(e -> {
            if (red4IV.getX() == 0 || red4IV.getX() == 600 || red4IV.getY() == 0 || red4IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(red4IV.getX());
                        greenList.get(i).setY(red4IV.getY());

                        red4IV.setX(460);
                        red4IV.setY(540);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });

        moveBlue1.setOnAction(e -> {
            if (blue1IV.getX() == 0 || blue1IV.getX() == 600 || blue1IV.getY() == 0 || blue1IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(blue1IV.getX());
                        greenList.get(i).setY(blue1IV.getY());

                        blue1IV.setX(55);
                        blue1IV.setY(420);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make this move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue2.setOnAction(e -> {
            if (blue2IV.getX() == 0 || blue2IV.getX() == 600 || blue2IV.getY() == 0 || blue2IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(blue2IV.getX());
                        greenList.get(i).setY(blue2IV.getY());

                        blue2IV.setX(95);
                        blue2IV.setY(420);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue3.setOnAction(e -> {
            if (blue3IV.getX() == 0 || blue3IV.getX() == 600 || blue3IV.getY() == 0 || blue3IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(blue3IV.getX());
                        greenList.get(i).setY(blue3IV.getY());

                        blue3IV.setX(55);
                        blue3IV.setY(450);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveBlue4.setOnAction(e -> {
            if (blue4IV.getX() == 0 || blue4IV.getX() == 600 || blue4IV.getY() == 0 || blue4IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(blue4IV.getX());
                        greenList.get(i).setY(blue4IV.getY());

                        blue4IV.setX(95);
                        blue4IV.setY(450);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });

        moveYellow1.setOnAction(e -> {
            if (yellow1IV.getX() == 0 || yellow1IV.getX() == 600 || yellow1IV.getY() == 0 || yellow1IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(yellow1IV.getX());
                        greenList.get(i).setY(yellow1IV.getY());

                        yellow1IV.setX(140);
                        yellow1IV.setY(50);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow2.setOnAction(e -> {
            if (yellow2IV.getX() == 0 || yellow2IV.getX() == 600 || yellow2IV.getY() == 0 || yellow2IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(yellow2IV.getX());
                        greenList.get(i).setY(yellow2IV.getY());

                        yellow2IV.setX(180);
                        yellow2IV.setY(50);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow3.setOnAction(e -> {
            if (yellow3IV.getX() == 0 || yellow3IV.getX() == 600 || yellow3IV.getY() == 0 || yellow3IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(yellow3IV.getX());
                        greenList.get(i).setY(yellow3IV.getY());

                        yellow3IV.setX(140);
                        yellow3IV.setY(80);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
        moveYellow4.setOnAction(e -> {
            if (yellow4IV.getX() == 0 || yellow4IV.getX() == 600 || yellow4IV.getY() == 0 || yellow4IV.getY() == 600) {

                for (int i = 0; i < greenList.size(); i++) {

                    if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                    }
                    else {

                        greenList.get(i).setX(yellow4IV.getX());
                        greenList.get(i).setY(yellow4IV.getY());

                        yellow4IV.setX(180);
                        yellow4IV.setY(80);

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);

                        break;
                    }

                    if (i == 3) {
                        AlertBox.display("Illegal move", "Can't make a move");

                        turn = 1;

                        gameLayout.getChildren().remove(type);
                        gameLayout.getChildren().addAll(cardButton);
                        break;
                    }
                }
            }
            else {
                AlertBox.display("Illegal move", "Can't make this move");
            }
        });
    }

    //detects what card is chosen
    public void showButtons(int random) {

        //needed to make new images, and imageviews because when the same ones were used, it
        //did not allow any piece to move and created random new static images

        //getting images for new pieces
        Image red12 = new Image("2red1.png");
        Image red22 = new Image("2red2.png");
        Image red32 = new Image("2red3.png");
        Image red42 = new Image("2red4.png");

        Image blue12 = new Image("2blue1.png");
        Image blue22 = new Image("2blue2.png");
        Image blue32 = new Image("2blue3.png");
        Image blue42 = new Image("2blue4.png");

        Image green12 = new Image("2green1.png");
        Image green22 = new Image("2green2.png");
        Image green32 = new Image("2green3.png");
        Image green42 = new Image("2green4.png");

        Image yellow12 = new Image("2yellow1.png");
        Image yellow22 = new Image("2yellow2.png");
        Image yellow32 = new Image("2yellow3.png");
        Image yellow42 = new Image("2yellow4.png");

        //making new images into new imageviews with height set to 40
        ImageView red1IV2 = new ImageView();
        red1IV2.setImage(red12);
        red1IV2.setFitHeight(40);
        ImageView red2IV2 = new ImageView();
        red2IV2.setImage(red22);
        red2IV2.setFitHeight(40);
        ImageView red3IV2 = new ImageView();
        red3IV2.setImage(red32);
        red3IV2.setFitHeight(40);
        ImageView red4IV2 = new ImageView();
        red4IV2.setImage(red42);
        red4IV2.setFitHeight(40);

        ImageView blue1IV2 = new ImageView();
        blue1IV2.setImage(blue12);
        blue1IV2.setFitHeight(40);
        ImageView blue2IV2 = new ImageView();
        blue2IV2.setImage(blue22);
        blue2IV2.setFitHeight(40);
        ImageView blue3IV2 = new ImageView();
        blue3IV2.setImage(blue32);
        blue3IV2.setFitHeight(40);
        ImageView blue4IV2 = new ImageView();
        blue4IV2.setImage(blue42);
        blue4IV2.setFitHeight(40);

        ImageView green1IV2 = new ImageView();
        green1IV2.setImage(green12);
        green1IV2.setFitHeight(40);
        ImageView green2IV2 = new ImageView();
        green2IV2.setImage(green22);
        green2IV2.setFitHeight(40);
        ImageView green3IV2 = new ImageView();
        green3IV2.setImage(green32);
        green3IV2.setFitHeight(40);
        ImageView green4IV2 = new ImageView();
        green4IV2.setImage(green42);
        green4IV2.setFitHeight(40);

        ImageView yellow1IV2 = new ImageView();
        yellow1IV2.setImage(yellow12);
        yellow1IV2.setFitHeight(40);
        ImageView yellow2IV2 = new ImageView();
        yellow2IV2.setImage(yellow22);
        yellow2IV2.setFitHeight(40);
        ImageView yellow3IV2 = new ImageView();
        yellow3IV2.setImage(yellow32);
        yellow3IV2.setFitHeight(40);
        ImageView yellow4IV2 = new ImageView();
        yellow4IV2.setImage(yellow42);
        yellow4IV2.setFitHeight(40);

        //16 buttons, one for each piece was made and an imageview was added to each button
        moveRed1 = new Button();
        moveRed1.setGraphic(red1IV2);
        moveRed2 = new Button();
        moveRed2.setGraphic(red2IV2);
        moveRed3 = new Button();
        moveRed3.setGraphic(red3IV2);
        moveRed4 = new Button();
        moveRed4.setGraphic(red4IV2);

        moveYellow1 = new Button();
        moveYellow1.setGraphic(yellow1IV2);
        moveYellow2 = new Button();
        moveYellow2.setGraphic(yellow2IV2);
        moveYellow3 = new Button();
        moveYellow3.setGraphic(yellow3IV2);
        moveYellow4 = new Button();
        moveYellow4.setGraphic(yellow4IV2);

        moveGreen1 = new Button();
        moveGreen1.setGraphic(green1IV2);
        moveGreen2 = new Button();
        moveGreen2.setGraphic(green2IV2);
        moveGreen3 = new Button();
        moveGreen3.setGraphic(green3IV2);
        moveGreen4 = new Button();
        moveGreen4.setGraphic(green4IV2);

        moveBlue1 = new Button();
        moveBlue1.setGraphic(blue1IV2);
        moveBlue2 = new Button();
        moveBlue2.setGraphic(blue2IV2);
        moveBlue3 = new Button();
        moveBlue3.setGraphic(blue3IV2);
        moveBlue4 = new Button();
        moveBlue4.setGraphic(blue4IV2);

        //made new layout with all buttons side by side by color
        VBox a = new VBox(3);
        a.getChildren().addAll(moveRed1, moveRed2, moveRed3, moveRed4);
        VBox b = new VBox(3);
        b.getChildren().addAll(moveBlue1, moveBlue2, moveBlue3, moveBlue4);
        VBox c = new VBox(3);
        c.getChildren().addAll(moveYellow1, moveYellow2, moveYellow3, moveYellow4);
        VBox d = new VBox(3);
        d.getChildren().addAll(moveGreen1, moveGreen2, moveGreen3, moveGreen4);

        //layout with all the buttons
        HBox abcd = new HBox(3);
        abcd.getChildren().addAll(a, b, c, d);

        //if card 0 is picked
        if (random == 0) {

            Label label0 = new Label("Bump back");
            Button noMove0 = new Button("No move");
            //card zero layout
            VBox zero = new VBox(10);
            zero.getChildren().addAll(label0, abcd, noMove0);
            //changes to the zero layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(zero);
            zero.setTranslateX(643.5);
            zero.setTranslateY(330);
            //if no move, skip turn
            noMove0.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(zero);
                gameLayout.getChildren().add(cardButton);
            });

            //if player 1, run cardZero(zero), if player two run cardZero2...etc.
            if (turn == 1) {
                cardZero1(zero);
            }
            else if (turn == 2) {
                cardZero2(zero);
            }
            else if (turn == 3) {
                cardZero3(zero);
            }
            else if (turn == 4) {
                cardZero4(zero);
            }


        }
        //if card is 1
        else if (random == 1) {
            //buttons and labels for the one layout
            Label label = new Label("Move piece 1 space");
            Button startMove = new Button("Move from start");
            Button noMove1 = new Button("No move");
            //the one layout
            VBox one = new VBox(10);
            one.getChildren().addAll(startMove, label, abcd, noMove1);
            //changes to the one layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(one);
            one.setTranslateX(643.5);
            one.setTranslateY(330);
            //if no move, skips turn
            noMove1.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(one);
                gameLayout.getChildren().add(cardButton);
            });

            startMove.setOnAction(e -> {
                //all for statements are similar, the only difference is that each one is for each player
                //the first one is for player 1, second one if for player two etc.
                //each of them only have different colored pieces it is taking care of
                //since they are all similar, only the first one is commented
                if (turn == 1) {
                    //loops for all red pieces
                    for (int i = 0; i < redList.size(); i++) {
                        //if piece not in start
                        if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                        }
                        //if piece in start
                        else {
                            //send piece to the red starting spot
                            redList.get(i).setX(440);
                            redList.get(i).setY(600);
                            //changes to next turn
                            turn = 2;
                            //changes back to card layout
                            gameLayout.getChildren().remove(one);
                            gameLayout.getChildren().addAll(cardButton);

                            break;
                        }
                        //if all are outside, then can't start one off
                        if (i == 3) {
                            AlertBox.display("Illegal move", "Can't make this move");
                            break;
                        }
                    }

                }
                else if (turn == 2) {

                    for (int i = 0; i < blueList.size(); i++) {

                        if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                        }
                        else {

                            blueList.get(i).setX(0);
                            blueList.get(i).setY(440);

                            turn = 3;

                            gameLayout.getChildren().remove(one);
                            gameLayout.getChildren().addAll(cardButton);

                            break;
                        }

                        if (i == 3) {
                            AlertBox.display("Illegal move", "Can't make this move");
                            break;
                        }
                    }

                }
                else if (turn == 3) {

                    for (int i = 0; i < yellowList.size(); i++) {

                        if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                        }
                        else {

                            yellowList.get(i).setX(160);
                            yellowList.get(i).setY(0);

                            turn = 4;

                            gameLayout.getChildren().remove(one);
                            gameLayout.getChildren().addAll(cardButton);

                            break;
                        }

                        if (i == 3) {
                            AlertBox.display("Illegal move", "Can't make this move");
                            break;
                        }
                    }

                }
                else if (turn == 4) {

                    for (int i = 0; i < greenList.size(); i++) {

                        if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                        }
                        else {

                            greenList.get(i).setX(600);
                            greenList.get(i).setY(160);

                            turn = 1;

                            gameLayout.getChildren().remove(one);
                            gameLayout.getChildren().addAll(cardButton);

                            break;
                        }

                        if (i == 3) {
                            AlertBox.display("Illegal move", "Can't make this move");
                            break;
                        }
                    }
                }
            });
            //moves piece 1
            standardCard(one, 1);

        }
        else if (random == 2) {
            //buttons and labels for the two layout
            Label label2 = new Label("Move piece 2 spaces");
            Button startMove2 = new Button("Move from start");
            Button noMove2 = new Button("No move");
            //the two layout
            VBox two = new VBox(10);
            two.getChildren().addAll(startMove2, label2, abcd, noMove2);
            //changes to the two layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(two);
            two.setTranslateX(643.5);
            two.setTranslateY(330);
            //if no move, skips turn
            noMove2.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(two);
                gameLayout.getChildren().add(cardButton);
            });

            startMove2.setOnAction(e -> {
                //all for statements are similar, the only difference is that each one is for each player
                //the first one is for player 1, second one if for player two etc.
                //each of them only have different colored pieces it is taking care of
                //since they are all similar, only the first one is commented

                if (turn == 1) {
                    //loops for each piece
                    for (int i = 0; i < redList.size(); i++) {
                        //checks if piece is on the board or in safety
                        if ((redList.get(i).getX() == 0 || redList.get(i).getX() == 600 || redList.get(i).getY() == 0 || redList.get(i).getY() == 600) || (redList.get(i).getX() == 520 && redList.get(i).getY() < 600)) {

                        }
                        //if in start
                        else {
                            //move piece to starting block
                            redList.get(i).setX(440);
                            redList.get(i).setY(600);
                            //changes to card layout
                            gameLayout.getChildren().remove(two);
                            gameLayout.getChildren().addAll(cardButton);

                            break;
                        }
                        //if no piece is in start
                        if (i == 3) {
                            AlertBox.display("Illegal move", "Can't make this move");
                            break;
                        }
                    }

                }
                else if (turn == 2) {

                    for (int i = 0; i < blueList.size(); i++) {

                        if ((blueList.get(i).getX() == 0 || blueList.get(i).getX() == 600 || blueList.get(i).getY() == 0 || blueList.get(i).getY() == 600) || (blueList.get(i).getY() == 520 && blueList.get(i).getX() > 0)) {

                        }
                        else {

                            blueList.get(i).setX(0);
                            blueList.get(i).setY(440);

                            gameLayout.getChildren().remove(two);
                            gameLayout.getChildren().addAll(cardButton);

                            break;
                        }

                        if (i == 3) {
                            AlertBox.display("Illegal move", "Can't make this move");
                            break;
                        }
                    }

                }
                else if (turn == 3) {

                    for (int i = 0; i < yellowList.size(); i++) {

                        if ((yellowList.get(i).getX() == 0 || yellowList.get(i).getX() == 600 || yellowList.get(i).getY() == 0 || yellowList.get(i).getY() == 600) || (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() > 0)) {

                        }
                        else {

                            yellowList.get(i).setX(160);
                            yellowList.get(i).setY(0);

                            gameLayout.getChildren().remove(two);
                            gameLayout.getChildren().addAll(cardButton);

                            break;
                        }

                        if (i == 3) {
                            AlertBox.display("Illegal move", "Can't make this move");
                            break;
                        }
                    }

                }
                else if (turn == 4) {

                    for (int i = 0; i < greenList.size(); i++) {

                        if ((greenList.get(i).getX() == 0 || greenList.get(i).getX() == 600 || greenList.get(i).getY() == 0 || greenList.get(i).getY() == 600) || (greenList.get(i).getY() == 80 && greenList.get(i).getX() < 600)) {

                        }
                        else {

                            greenList.get(i).setX(600);
                            greenList.get(i).setY(160);

                            gameLayout.getChildren().remove(two);
                            gameLayout.getChildren().addAll(cardButton);

                            break;
                        }

                        if (i == 3) {
                            AlertBox.display("Illegal move", "Can't make this move");
                            break;
                        }
                    }

                }

                y = 1;

            });

            //if its the second turn then standardCard, but if it is the first then, twoCard
            if (y == 1) {
                standardCard(two, 2);
            }
            else {
                twoCard(two, 2);
            }

        }
        //if card is 3
        else  if (random == 3) {
            //label and button needed for the three layout
            Label label3 = new Label("Move piece 3 spaces");
            Button noMove3 = new Button("No move");
            //the three layout
            VBox three = new VBox(10);
            three.getChildren().addAll(label3, abcd, noMove3);
            //changes to the three layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(three);
            three.setTranslateX(643.5);
            three.setTranslateY(330);
            //if no move, skip turn
            noMove3.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(three);
                gameLayout.getChildren().add(cardButton);
            });
            //moves piece 3 spaces
            standardCard(three, 3);

        }
        else if (random == 4) {
            //label and button needed for the four layout
            Label label4 = new Label("Move piece back 4 spaces");
            Button noMove4 = new Button("No move");
            //the for layout
            VBox four = new VBox(10);
            four.getChildren().addAll(label4, abcd, noMove4);
            //changes to the four layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(four);
            four.setTranslateX(643.5);
            four.setTranslateY(330);
            //if no move, skips turn
            noMove4.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(four);
                gameLayout.getChildren().add(cardButton);
            });
            //moves backward 4
            standardCardBack(four, 4);


        }
        //if card is 5
        else if (random == 5) {
            //labels and buttons for the five layout
            Label label5 = new Label("Move piece 5 spaces");
            Button noMove5 = new Button("No move");
            //the five layout
            VBox five = new VBox(10);
            five.getChildren().addAll(label5, abcd, noMove5);
            //changes to the five layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(five);
            five.setTranslateX(643.5);
            five.setTranslateY(330);
            //if no move, skips turn
            noMove5.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(five);
                gameLayout.getChildren().add(cardButton);
            });
            //moves 5 spaces
            standardCard(five, 5);

        }
        else if (random == 7) {
            //labels and buttons for the seven and seven2 layout
            Label label7 = new Label("Move piece 7, or split between 2 pieces");
            Button move7 = new Button("Move");
            Button split = new Button("Split");
            Label label72 = new Label("Move 7 spaces");
            Button noMove7 = new Button("No move");

            HBox hBox = new HBox(20);
            hBox.getChildren().addAll(move7, split);
            //the seven layout
            seven = new VBox(10);
            seven.getChildren().addAll(label7, hBox, noMove7);
            //changes to the seven layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(seven);
            seven.setTranslateX(643.5);
            seven.setTranslateY(330);
            //if no move, skips turn
            noMove7.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(seven);
                gameLayout.getChildren().add(cardButton);
            });
            //seven2 layout if the player decided to move 7 instead of splitting
            VBox seven2 = new VBox( 10);
            seven2.getChildren().addAll(label72, abcd);

            move7.setOnAction(e -> {
                //changes to the seven2 layout and sets it to specific coordinates
                gameLayout.getChildren().remove(seven);
                gameLayout.getChildren().add(seven2);
                seven2.setTranslateX(643.5);
                seven2.setTranslateY(330);
                //moves 7 spaces
                standardCard(seven2, 7);
            });
            //to split the seven to two pieces
            split.setOnAction(e -> {
                //new stage to take input
                Stage window = new Stage();
                //textfield to take the input
                TextField input = new TextField();
                Label label = new Label("Press confirm, then a piece");

                Button inputButton1 = new Button("Confirm");
                //layout of input window
                VBox vBox = new VBox(10);
                vBox.getChildren().addAll(input, label, inputButton1, abcd);
                //confirm button
                inputButton1.setOnAction(event -> {
                    //indicated its the first move
                    if (indicator == 0) {
                        //checks if it is an integer
                        if (isNumeric(input.getText())) {
                            //converts to an int
                            newInput = Integer.parseInt(input.getText());
                            //checks if it is greater than 7
                            if (newInput >= 7) {
                                AlertBox.display("Invalid", "Needs to be less than 7");
                            }
                            //if it is not greater
                            else {
                                //then it moves that amount
                                sevenCard(newInput);
                                //indicator changes to indicate the second move
                                indicator = 1;
                            }
                        }
                    }
                    //indicated its the second move
                    else if (indicator == 1) {

                        AlertBox.display("Move", "Select piece to move " + (7 - newInput) + " pieces");
                        //move the amount left out of 7
                        standardCard(seven, (7 - newInput));
                        //indicator changes back to show it is first move
                        indicator = 0;
                    }

                });
                //setting layout for the scene and the scene for the stage
                Scene scene = new Scene(vBox);
                window.setScene(scene);
                window.show();

            });

        }
        //if the card is 8
        else if (random == 8) {
            //labels and buttons for layout eight
            Label label8 = new Label("Move piece 8 spaces");
            Button noMove8 = new Button("No move");
            //layout eigth
            VBox eight = new VBox(10);
            eight.getChildren().addAll(label8, abcd, noMove8);
            //changes to the eight layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(eight);
            eight.setTranslateX(643.5);
            eight.setTranslateY(330);
            //if no moves, skips turn
            noMove8.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(eight);
                gameLayout.getChildren().add(cardButton);
            });
            //moves 8 pieces
            standardCard(eight, 8);

        }
        //if card is 10
        else if (random == 10) {
            //labels and buttons for layout 10
            Label label10 = new Label("Move piece 10 forward, or 1 backward");
            Button forward = new Button("Forward");
            Button back = new Button("Back");
            Button noMove10 = new Button("No move");

            HBox hBox = new HBox(20);
            hBox.getChildren().addAll(forward, back);
            //layout ten
            VBox ten = new VBox(10);
            ten.getChildren().addAll(label10, hBox, abcd, noMove10);
            //changes to the ten layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(ten);
            ten.setTranslateX(643.5);
            ten.setTranslateY(330);
            //if no move, skips turn
            noMove10.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(ten);
                gameLayout.getChildren().add(cardButton);
            });
            //if forward is pressed then the piece moves forward 10 spaces
            forward.setOnAction(e -> standardCard(ten, 10));
            //if back is pressed it moves back 1 space
            back.setOnAction(e -> standardCardBack(ten, 1));

        }//if card is 11
        else if (random == 11) {
            //labels and buttons for the eleven layout
            Label label11 = new Label("Move piece 11 spaces");
            Button noMove11 = new Button("No move");
            //the eleven layout
            VBox eleven = new VBox(10);
            eleven.getChildren().addAll(label11, abcd, noMove11);
            //changes to the eleven layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(eleven);
            eleven.setTranslateX(643.5);
            eleven.setTranslateY(330);
            //if no move, skips turn
            noMove11.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(eleven);
                gameLayout.getChildren().add(cardButton);
            });
            //moves piece 11 spaces
            standardCard(eleven, 11);

        }
        //if card is 12
        else if (random == 12) {
            //labels and buttons for layout twelve
            Label label12 = new Label("Move piece 12 spaces");
            Button noMove12 = new Button("No move");
            //layout twelve
            VBox twelve = new VBox(10);
            twelve.getChildren().addAll(label12, abcd, noMove12);
            //changes to the twelve layout and sets it to specific coordinates
            gameLayout.getChildren().remove(cardButton);
            gameLayout.getChildren().add(twelve);
            twelve.setTranslateX(643.5);
            twelve.setTranslateY(330);
            //if no move, skips turn
            noMove12.setOnAction(e -> {
                if (turn == 4) { turn = 1; }
                else { turn += 1;}
                gameLayout.getChildren().remove(twelve);
                gameLayout.getChildren().add(cardButton);
            });
            //moves piece 12 spaces
            standardCard(twelve, 12);

        }
    }

    //method to move card forward
    public void standardCard(VBox type, int movement) {
        //all the buttons do very similar things, therefore only the first button is commented
        //each button moves the corresponding piece
        //the "type" is used to remove that certain layout and re add the card layout

        moveRed1.setOnAction(e -> {
            //checks if player 1 turn
            if (turn == 1) {
                //if it is, it checks if the chosen button's piece (in this case red1) is on the board or in the safety zone
                if ((red1IV.getX() == 0 || red1IV.getX() == 600 || red1IV.getY() == 0 || red1IV.getY() == 600) || (red1IV.getX() == 520 && red1IV.getY() < 600)) {
                    //moves the piece a certain amount of blocka according to the card
                    move(red1IV, movement);

                    turn = 2;
                    //changes the layout to the card
                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                //if the piece (red1 in this case) is still in start
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed2.setOnAction(e -> {
            if (turn == 1) {
                if ((red2IV.getX() == 0 || red2IV.getX() == 600 || red2IV.getY() == 0 || red2IV.getY() == 600) || (red2IV.getX() == 520 && red2IV.getY() < 600)) {

                    move(red2IV, movement);

                    turn = 2;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed3.setOnAction(e -> {
            if (turn == 1) {
                if ((red3IV.getX() == 0 || red3IV.getX() == 600 || red3IV.getY() == 0 || red3IV.getY() == 600) || (red3IV.getX() == 520 && red3IV.getY() < 600)) {
                    move(red3IV, movement);

                    turn = 2;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed4.setOnAction(e -> {
            if (turn == 1) {
                if ((red4IV.getX() == 0 || red4IV.getX() == 600 || red4IV.getY() == 0 || red4IV.getY() == 600) || (red4IV.getX() == 520 && red4IV.getY() < 600)) {
                    move(red4IV, movement);

                    turn = 2;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveBlue1.setOnAction(e -> {
            if (turn == 2) {
                if ((blue1IV.getX() == 0 || blue1IV.getX() == 600 || blue1IV.getY() == 0 || blue1IV.getY() == 600) || (blue1IV.getY() == 520 && blue1IV.getX() > 0)) {
                    move(blue1IV, movement);

                    turn = 3;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveBlue2.setOnAction(e -> {
            if (turn == 2) {
                if ((blue2IV.getX() == 0 || blue2IV.getX() == 600 || blue2IV.getY() == 0 || blue2IV.getY() == 600) || (blue2IV.getY() == 520 && blue2IV.getX() > 0)) {
                    move(blue2IV, movement);

                    turn = 3;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveBlue3.setOnAction(e -> {
            if (turn == 2) {
                if ((blue3IV.getX() == 0 || blue3IV.getX() == 600 || blue3IV.getY() == 0 || blue3IV.getY() == 600) || (blue3IV.getY() == 520 && blue3IV.getX() > 0)) {
                    move(blue3IV, movement);

                    turn = 3;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveBlue4.setOnAction(e -> {
            if (turn == 2) {
                if ((blue4IV.getX() == 0 || blue4IV.getX() == 600 || blue4IV.getY() == 0 || blue4IV.getY() == 600) || (blue4IV.getY() == 520 && blue4IV.getX() > 0)) {
                    move(blue4IV, movement);

                    turn = 3;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow1.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow1IV.getX() == 0 || yellow1IV.getX() == 600 || yellow1IV.getY() == 0 || yellow1IV.getY() == 600) || (yellow1IV.getX() == 80 && yellow1IV.getY() > 0)) {
                    move(yellow1IV, movement);

                    turn = 4;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow2.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow2IV.getX() == 0 || yellow2IV.getX() == 600 || yellow2IV.getY() == 0 || yellow2IV.getY() == 600) || (yellow2IV.getX() == 80 && yellow2IV.getY() > 0)) {
                    move(yellow2IV, movement);

                    turn = 4;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow3.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow3IV.getX() == 0 || yellow3IV.getX() == 600 || yellow3IV.getY() == 0 || yellow3IV.getY() == 600) || (yellow3IV.getX() == 80 && yellow3IV.getY() > 0)) {
                    move(yellow3IV, movement);

                    turn = 4;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow4.setOnAction(e -> {
            if (turn == 4) {
                if ((yellow4IV.getX() == 0 || yellow4IV.getX() == 600 || yellow4IV.getY() == 0 || yellow4IV.getY() == 600) || (yellow4IV.getX() == 80 && yellow4IV.getY() > 0)) {
                    move(yellow4IV, movement);

                    turn = 4;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen1.setOnAction(e -> {
            if (turn == 4) {
                if ((green1IV.getX() == 0 || green1IV.getX() == 600 || green1IV.getY() == 0 || green1IV.getY() == 600) || (green1IV.getY() == 80 && green1IV.getX() < 600)) {
                    move(green1IV, movement);

                    turn = 1;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen2.setOnAction(e -> {
            if (turn == 4) {
                if ((green2IV.getX() == 0 || green2IV.getX() == 600 || green2IV.getY() == 0 || green2IV.getY() == 600) || (green2IV.getY() == 80 && green2IV.getX() < 600)) {
                    move(green2IV, movement);

                    turn = 1;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen3.setOnAction(e -> {
            if (turn == 4) {
                if ((green3IV.getX() == 0 || green3IV.getX() == 600 || green3IV.getY() == 0 || green3IV.getY() == 600) || (green3IV.getY() == 80 && green3IV.getX() < 600)) {
                    move(green3IV, movement);

                    turn = 1;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen4.setOnAction(e -> {
            if (turn == 4) {
                if ((green4IV.getX() == 0 || green4IV.getX() == 600 || green4IV.getY() == 0 || green4IV.getY() == 600) || (green4IV.getY() == 80 && green4IV.getX() < 600)) {
                    move(green4IV, movement);

                    turn = 1;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
    }

    //method to move card backwards
    public void standardCardBack(VBox type, int movement) {
        //all the buttons do very similar things, therefore only the first button is commented
        //each button moves the corresponding piece
        //the "type" is used to remove that certain layout and re add the card layout

        moveRed1.setOnAction(e -> {
            //checks if player 1 turn
            if (turn == 1) {
                //checks if this specific piece is on the board or in safety zone
                if ((red1IV.getX() == 0 || red1IV.getX() == 600 || red1IV.getY() == 0 || red1IV.getY() == 600) || (red1IV.getX() == 520 && red1IV.getY() < 600)) {
                    //if it is, it moves back a given amount
                    moveBack(red1IV, movement);

                    turn = 2;
                    //changed to card layout
                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                //if this certain piece is in start
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed2.setOnAction(e -> {
            if (turn == 1) {
                if ((red2IV.getX() == 0 || red2IV.getX() == 600 || red2IV.getY() == 0 || red2IV.getY() == 600) || (red2IV.getX() == 520 && red2IV.getY() < 600)) {

                    moveBack(red2IV, movement);

                    turn = 2;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed3.setOnAction(e -> {
            if (turn == 1) {
                if ((red3IV.getX() == 0 || red3IV.getX() == 600 || red3IV.getY() == 0 || red3IV.getY() == 600) || (red3IV.getX() == 520 && red3IV.getY() < 600)) {
                    moveBack(red3IV, movement);

                    turn = 2;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed4.setOnAction(e -> {
            if (turn == 1) {
                if ((red4IV.getX() == 0 || red4IV.getX() == 600 || red4IV.getY() == 0 || red4IV.getY() == 600) || (red4IV.getX() == 520 && red4IV.getY() < 600)) {
                    moveBack(red4IV, movement);

                    turn = 2;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveBlue1.setOnAction(e -> {
            if (turn == 2) {
                if ((blue1IV.getX() == 0 || blue1IV.getX() == 600 || blue1IV.getY() == 0 || blue1IV.getY() == 600) || (blue1IV.getY() == 520 && blue1IV.getX() > 0)) {
                    moveBack(blue1IV, movement);

                    turn = 3;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveBlue2.setOnAction(e -> {
            if (turn == 2) {
                if ((blue2IV.getX() == 0 || blue2IV.getX() == 600 || blue2IV.getY() == 0 || blue2IV.getY() == 600) || (blue2IV.getY() == 520 && blue2IV.getX() > 0)) {
                    moveBack(blue2IV, movement);

                    turn = 3;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveBlue3.setOnAction(e -> {
            if (turn == 2) {
                if ((blue3IV.getX() == 0 || blue3IV.getX() == 600 || blue3IV.getY() == 0 || blue3IV.getY() == 600) || (blue3IV.getY() == 520 && blue3IV.getX() > 0)) {
                    moveBack(blue3IV, movement);

                    turn = 3;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveBlue4.setOnAction(e -> {
            if (turn == 2) {
                if ((blue4IV.getX() == 0 || blue4IV.getX() == 600 || blue4IV.getY() == 0 || blue4IV.getY() == 600) || (blue4IV.getY() == 520 && blue4IV.getX() > 0)) {
                    moveBack(blue4IV, movement);

                    turn = 3;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow1.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow1IV.getX() == 0 || yellow1IV.getX() == 600 || yellow1IV.getY() == 0 || yellow1IV.getY() == 600) || (yellow1IV.getX() == 80 && yellow1IV.getY() > 0)) {
                    moveBack(yellow1IV, movement);

                    turn = 4;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow2.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow2IV.getX() == 0 || yellow2IV.getX() == 600 || yellow2IV.getY() == 0 || yellow2IV.getY() == 600) || (yellow2IV.getX() == 80 && yellow2IV.getY() > 0)) {
                    moveBack(yellow2IV, movement);

                    turn = 4;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow3.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow3IV.getX() == 0 || yellow3IV.getX() == 600 || yellow3IV.getY() == 0 || yellow3IV.getY() == 600) || (yellow3IV.getX() == 80 && yellow3IV.getY() > 0)) {
                    moveBack(yellow3IV, movement);

                    turn = 4;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow4.setOnAction(e -> {
            if (turn == 4) {
                if ((yellow4IV.getX() == 0 || yellow4IV.getX() == 600 || yellow4IV.getY() == 0 || yellow4IV.getY() == 600) || (yellow4IV.getX() == 80 && yellow4IV.getY() > 0)) {
                    moveBack(yellow4IV, movement);

                    turn = 4;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen1.setOnAction(e -> {
            if (turn == 4) {
                if ((green1IV.getX() == 0 || green1IV.getX() == 600 || green1IV.getY() == 0 || green1IV.getY() == 600) || (green1IV.getY() == 80 && green1IV.getX() < 600)) {
                    moveBack(green1IV, movement);

                    turn = 1;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen2.setOnAction(e -> {
            if (turn == 4) {
                if ((green2IV.getX() == 0 || green2IV.getX() == 600 || green2IV.getY() == 0 || green2IV.getY() == 600) || (green2IV.getY() == 80 && green2IV.getX() < 600)) {
                    moveBack(green2IV, movement);

                    turn = 1;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen3.setOnAction(e -> {
            if (turn == 4) {
                if ((green3IV.getX() == 0 || green3IV.getX() == 600 || green3IV.getY() == 0 || green3IV.getY() == 600) || (green3IV.getY() == 80 && green3IV.getX() < 600)) {
                    moveBack(green3IV, movement);

                    turn = 1;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen4.setOnAction(e -> {
            if (turn == 4) {
                if ((green4IV.getX() == 0 || green4IV.getX() == 600 || green4IV.getY() == 0 || green4IV.getY() == 600) || (green4IV.getY() == 80 && green4IV.getX() < 600)) {
                    moveBack(green4IV, movement);

                    turn = 1;

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
    }

    //method to move specifically if player gets card 7
    public void sevenCard(int movement) {
        //all the buttons do very similar things, therefore only the first button is commented
        //each button moves the corresponding piece
        //the biggest difference of this method is that it does not change the turn, or the layout back to the card layout

        moveRed1.setOnAction(e -> {
            //checks if its player 1's turn
            if (turn == 1) {
                //checks if this piece is on the board or safety zone
                if ((red1IV.getX() == 0 || red1IV.getX() == 600 || red1IV.getY() == 0 || red1IV.getY() == 600) || (red1IV.getX() == 520 && red1IV.getY() < 600)) {
                    //moves the piece a given amount
                    move(red1IV, movement);
                }
                //if the piece is in start
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed2.setOnAction(e -> {
            if (turn == 1) {
                if ((red2IV.getX() == 0 || red2IV.getX() == 600 || red2IV.getY() == 0 || red2IV.getY() == 600) || (red2IV.getX() == 520 && red2IV.getY() < 600)) {
                    move(red2IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed3.setOnAction(e -> {
            if (turn == 1) {
                if ((red3IV.getX() == 0 || red3IV.getX() == 600 || red3IV.getY() == 0 || red3IV.getY() == 600) || (red3IV.getX() == 520 && red3IV.getY() < 600)) {
                    move(red3IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed4.setOnAction(e -> {
            if (turn == 1) {
                if ((red4IV.getX() == 0 || red4IV.getX() == 600 || red4IV.getY() == 0 || red4IV.getY() == 600) || (red4IV.getX() == 520 && red4IV.getY() < 600)) {
                    move(red4IV, movement);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveBlue1.setOnAction(e -> {
            if (turn == 2) {
                if ((blue1IV.getX() == 0 || blue1IV.getX() == 600 || blue1IV.getY() == 0 || blue1IV.getY() == 600) || (blue1IV.getY() == 520 && blue1IV.getX() > 0)) {
                    move(blue1IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveBlue2.setOnAction(e -> {
            if (turn == 2) {
                if ((blue2IV.getX() == 0 || blue2IV.getX() == 600 || blue2IV.getY() == 0 || blue2IV.getY() == 600) || (blue2IV.getY() == 520 && blue2IV.getX() > 0)) {
                    move(blue2IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveBlue3.setOnAction(e -> {
            if (turn == 2) {
                if ((blue3IV.getX() == 0 || blue3IV.getX() == 600 || blue3IV.getY() == 0 || blue3IV.getY() == 600) || (blue3IV.getY() == 520 && blue3IV.getX() > 0)) {
                    move(blue3IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveBlue4.setOnAction(e -> {
            if (turn == 2) {
                if ((blue4IV.getX() == 0 || blue4IV.getX() == 600 || blue4IV.getY() == 0 || blue4IV.getY() == 600) || (blue4IV.getY() == 520 && blue4IV.getX() > 0)) {
                    move(blue4IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow1.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow1IV.getX() == 0 || yellow1IV.getX() == 600 || yellow1IV.getY() == 0 || yellow1IV.getY() == 600) || (yellow1IV.getX() == 80 && yellow1IV.getY() > 0)) {
                    move(yellow1IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow2.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow2IV.getX() == 0 || yellow2IV.getX() == 600 || yellow2IV.getY() == 0 || yellow2IV.getY() == 600) || (yellow2IV.getX() == 80 && yellow2IV.getY() > 0)) {
                    move(yellow2IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow3.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow3IV.getX() == 0 || yellow3IV.getX() == 600 || yellow3IV.getY() == 0 || yellow3IV.getY() == 600) || (yellow3IV.getX() == 80 && yellow3IV.getY() > 0)) {
                    move(yellow3IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow4.setOnAction(e -> {
            if (turn == 4) {
                if ((yellow4IV.getX() == 0 || yellow4IV.getX() == 600 || yellow4IV.getY() == 0 || yellow4IV.getY() == 600) || (yellow4IV.getX() == 80 && yellow4IV.getY() > 0)) {
                    move(yellow4IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen1.setOnAction(e -> {
            if (turn == 4) {
                if ((green1IV.getX() == 0 || green1IV.getX() == 600 || green1IV.getY() == 0 || green1IV.getY() == 600) || (green1IV.getY() == 80 && green1IV.getX() < 600)) {
                    move(green1IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen2.setOnAction(e -> {
            if (turn == 4) {
                if ((green2IV.getX() == 0 || green2IV.getX() == 600 || green2IV.getY() == 0 || green2IV.getY() == 600) || (green2IV.getY() == 80 && green2IV.getX() < 600)) {
                    move(green2IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen3.setOnAction(e -> {
            if (turn == 4) {
                if ((green3IV.getX() == 0 || green3IV.getX() == 600 || green3IV.getY() == 0 || green3IV.getY() == 600) || (green3IV.getY() == 80 && green3IV.getX() < 600)) {
                    move(green3IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen4.setOnAction(e -> {
            if (turn == 4) {
                if ((green4IV.getX() == 0 || green4IV.getX() == 600 || green4IV.getY() == 0 || green4IV.getY() == 600) || (green4IV.getY() == 80 && green4IV.getX() < 600)) {
                    move(green4IV, movement);
                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
    }

    //method to move specifically if player gets card 2
    public void twoCard(VBox type, int movement) {
        //all the buttons do very similar things, therefore only the first button is commented
        //each button moves the corresponding piece
        //the biggest difference of this method is that it does not change turn number because player needs to go again

        moveRed1.setOnAction(e -> {
            //checks if it player 1
            if (turn == 1) {
                //if it is, it checks if this piece is on the board or in safety
                if ((red1IV.getX() == 0 || red1IV.getX() == 600 || red1IV.getY() == 0 || red1IV.getY() == 600) || (red1IV.getX() == 520 && red1IV.getY() < 600)) {
                    //moves the piece a given amount
                    move(red1IV, movement);
                    //changes the layout to the card layout
                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                //if the piece is in start
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed2.setOnAction(e -> {
            if (turn == 1) {
                if ((red2IV.getX() == 0 || red2IV.getX() == 600 || red2IV.getY() == 0 || red2IV.getY() == 600) || (red2IV.getX() == 520 && red2IV.getY() < 600)) {
                    move(red2IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed3.setOnAction(e -> {
            if (turn == 1) {
                if ((red3IV.getX() == 0 || red3IV.getX() == 600 || red3IV.getY() == 0 || red3IV.getY() == 600) || (red3IV.getX() == 520 && red3IV.getY() < 600)) {
                    move(red3IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveRed4.setOnAction(e -> {
            if (turn == 1) {
                if ((red4IV.getX() == 0 || red4IV.getX() == 600 || red4IV.getY() == 0 || red4IV.getY() == 600) || (red4IV.getX() == 520 && red4IV.getY() < 600)) {
                    move(red4IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);


                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveBlue1.setOnAction(e -> {
            if (turn == 2) {
                if ((blue1IV.getX() == 0 || blue1IV.getX() == 600 || blue1IV.getY() == 0 || blue1IV.getY() == 600) || (blue1IV.getY() == 520 && blue1IV.getX() > 0)) {
                    move(blue1IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }

        });
        moveBlue2.setOnAction(e -> {
            if (turn == 2) {
                if ((blue2IV.getX() == 0 || blue2IV.getX() == 600 || blue2IV.getY() == 0 || blue2IV.getY() == 600) || (blue2IV.getY() == 520 && blue2IV.getX() > 0)) {
                    move(blue2IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveBlue3.setOnAction(e -> {
            if (turn == 2) {
                if ((blue3IV.getX() == 0 || blue3IV.getX() == 600 || blue3IV.getY() == 0 || blue3IV.getY() == 600) || (blue3IV.getY() == 520 && blue3IV.getX() > 0)) {
                    move(blue3IV, movement);


                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveBlue4.setOnAction(e -> {
            if (turn == 2) {
                if ((blue4IV.getX() == 0 || blue4IV.getX() == 600 || blue4IV.getY() == 0 || blue4IV.getY() == 600) || (blue4IV.getY() == 520 && blue4IV.getX() > 0)) {
                    move(blue4IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow1.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow1IV.getX() == 0 || yellow1IV.getX() == 600 || yellow1IV.getY() == 0 || yellow1IV.getY() == 600) || (yellow1IV.getX() == 80 && yellow1IV.getY() > 0)) {
                    move(yellow1IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow2.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow2IV.getX() == 0 || yellow2IV.getX() == 600 || yellow2IV.getY() == 0 || yellow2IV.getY() == 600) || (yellow2IV.getX() == 80 && yellow2IV.getY() > 0)) {
                    move(yellow2IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow3.setOnAction(e -> {
            if (turn == 3) {
                if ((yellow3IV.getX() == 0 || yellow3IV.getX() == 600 || yellow3IV.getY() == 0 || yellow3IV.getY() == 600) || (yellow3IV.getX() == 80 && yellow3IV.getY() > 0)) {
                    move(yellow3IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveYellow4.setOnAction(e -> {
            if (turn == 4) {
                if ((yellow4IV.getX() == 0 || yellow4IV.getX() == 600 || yellow4IV.getY() == 0 || yellow4IV.getY() == 600) || (yellow4IV.getX() == 80 && yellow4IV.getY() > 0)) {
                    move(yellow4IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen1.setOnAction(e -> {
            if (turn == 4) {
                if ((green1IV.getX() == 0 || green1IV.getX() == 600 || green1IV.getY() == 0 || green1IV.getY() == 600) || (green1IV.getY() == 80 && green1IV.getX() < 600)) {
                    move(green1IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen2.setOnAction(e -> {
            if (turn == 4) {
                if ((green2IV.getX() == 0 || green2IV.getX() == 600 || green2IV.getY() == 0 || green2IV.getY() == 600) || (green2IV.getY() == 80 && green2IV.getX() < 600)) {
                    move(green2IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen3.setOnAction(e -> {
            if (turn == 4) {
                if ((green3IV.getX() == 0 || green3IV.getX() == 600 || green3IV.getY() == 0 || green3IV.getY() == 600) || (green3IV.getY() == 80 && green3IV.getX() < 600)) {
                    move(green3IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
        moveGreen4.setOnAction(e -> {
            if (turn == 4) {
                if ((green4IV.getX() == 0 || green4IV.getX() == 600 || green4IV.getY() == 0 || green4IV.getY() == 600) || (green4IV.getY() == 80 && green4IV.getX() < 600)) {
                    move(green4IV, movement);

                    gameLayout.getChildren().remove(type);
                    gameLayout.getChildren().addAll(cardButton);

                }
                else {
                    AlertBox.display("Illegal move", "Can't make this move");
                }
            }
        });
    }

    //method checking which player wins depending on if they have all their color pieces in home
    public void checkWin() {
        int w = 0;
        int x = 0;
        int y = 0;
        int z = 0;
        //loops 4 times to check if any set of pieces is in HOME, and if they are, it says who wins
        for (int i = 0; i < 4; i++) {
            if (redList.get(i).getX() == 520 && redList.get(i).getY() == 360) {
                w++;
                if (w == 4) { AlertBox.display("Game Over", "Red wins!!"); }
            }

            if (blueList.get(i).getX() == 240 && blueList.get(i).getY() == 520) {
                x++;
                if (x == 4) { AlertBox.display("Game Over", "Blue wins!!"); }
            }

            if (yellowList.get(i).getX() == 80 && yellowList.get(i).getY() == 240) {
                y++;
                if (y == 4) { AlertBox.display("Game Over", "Yellow wins!!"); }
            }

            if (greenList.get(i).getX() == 360 && greenList.get(i).getY() == 80) {
                z++;
                if (z == 4) { AlertBox.display("Game Over", "Green wins!!"); }
            }
        }

    }

    //method is for moving pieces given the number of spaces
    public static void move(ImageView piece, int random) {

        //to check which color piece it is
        if (redList.contains(piece)) {
            for (int i = 0; i < random; i++) {

                //to see if the piece is approaching or on the safety zone
                if (piece.getX() == 520 && piece.getY() > 360 && piece.getY() <= 600) {
                    piece.setY(piece.getY() - 40);

                }
                /*else if (piece.getX() == 520 && piece.getY() == 360) {

                }*/
                //to make the piece move up one block if it is on the left side of the board
                else if (piece.getX() == 0 && piece.getY() <= 600 && piece.getY() > 0) {
                    piece.setY(piece.getY() - 40);
                }
                //to make the piece move right if the piece is on the top of the board
                else if (piece.getY() == 0 && piece.getX() >= 0 && piece.getX() < 600) {
                    piece.setX(piece.getX() + 40);
                }
                //to make the piece move down if the piece is on the right of the board
                else if (piece.getX() == 600 && piece.getY() >= 0 && piece.getY() < 600) {
                    piece.setY(piece.getY() + 40);
                }
                //to make the piece move left if the piece is on the bottom of the board
                else if (piece.getY() == 600 && piece.getX() <= 600 && piece.getX() > 0) {
                    piece.setX(piece.getX() - 40);
                }
            }
        }
        //to check which color piece it is
        else if (yellowList.contains(piece)) {

            for (int i = 0; i < random; i++) {

                //to see if the piece is approaching or on the safety zone
                if (piece.getX() == 80 && piece.getY() < 240 && piece.getY() >= 0) {
                    piece.setY(piece.getY() + 40);
                }
                /*else if (piece.getX() == 520 && piece.getY() == 360) {

                }*/
                //to make the piece move up one block if it is on the left side of the board
                else if (piece.getX() == 0 && piece.getY() <= 600 && piece.getY() > 0) {
                    piece.setY(piece.getY() - 40);
                }
                //to make the piece move right if the piece is on the top of the board
                else if (piece.getY() == 0 && piece.getX() >= 0 && piece.getX() < 600) {
                    piece.setX(piece.getX() + 40);
                }
                //to make the piece move down if the piece is on the right of the board
                else if (piece.getX() == 600 && piece.getY() >= 0 && piece.getY() < 600) {
                    piece.setY(piece.getY() + 40);
                }
                //to make the piece move left if the piece is on the bottom of the board
                else if (piece.getY() == 600 && piece.getX() <= 600 && piece.getX() > 0) {
                    piece.setX(piece.getX() - 40);
                }
            }
        }
        //to check which color piece it is
        else if (blueList.contains(piece)) {

            for (int i = 0; i < random; i++) {

                //to see if the piece is approaching or on the safety zone
                if (piece.getY() == 520 && piece.getX() >= 0 && piece.getX() < 240) {
                    piece.setX(piece.getX() + 40);
                }
                /*else if (piece.getX() == 520 && piece.getY() == 360) {

                }*/
                //to make the piece move up one block if it is on the left side of the board
                else if (piece.getX() == 0 && piece.getY() <= 600 && piece.getY() > 0) {
                    piece.setY(piece.getY() - 40);
                }
                //to make the piece move right if the piece is on the top of the board
                else if (piece.getY() == 0 && piece.getX() >= 0 && piece.getX() < 600) {
                    piece.setX(piece.getX() + 40);
                }
                //to make the piece move down if the piece is on the right of the board
                else if (piece.getX() == 600 && piece.getY() >= 0 && piece.getY() < 600) {
                    piece.setY(piece.getY() + 40);
                }
                //to make the piece move left if the piece is on the bottom of the board
                else if (piece.getY() == 600 && piece.getX() <= 600 && piece.getX() > 0) {
                    piece.setX(piece.getX() - 40);
                }
            }
        }
        //to check which color piece it is
        else if (greenList.contains(piece)) {

            for (int i = 0; i < random; i++) {

                //to see if the piece is approaching or on the safety zone
                if (piece.getY() == 80 && piece.getX() <= 600 && piece.getX() > 360) {
                    piece.setX(piece.getX() + 40);
                }
                /*else if (piece.getX() == 520 && piece.getY() == 360) {

                }*/
                //to make the piece move up one block if it is on the left side of the board
                else if (piece.getX() == 0 && piece.getY() <= 600 && piece.getY() > 0) {
                    piece.setY(piece.getY() - 40);
                }
                //to make the piece move right if the piece is on the top of the board
                else if (piece.getY() == 0 && piece.getX() >= 0 && piece.getX() < 600) {
                    piece.setX(piece.getX() + 40);
                }
                //to make the piece move down if the piece is on the right of the board
                else if (piece.getX() == 600 && piece.getY() >= 0 && piece.getY() < 600) {
                    piece.setY(piece.getY() + 40);
                }
                //to make the piece move left if the piece is on the bottom of the board
                else if (piece.getY() == 600 && piece.getX() <= 600 && piece.getX() > 0) {
                    piece.setX(piece.getX() - 40);
                }
            }
        }
    }

    //method that moves the piece backwards
    public static void moveBack(ImageView piece, int back) {
        //loops for as many moves provided in brackets
        for (int i = 0; i < back; i++) {

            //to make the piece move down if it is on the left side of the board
            if (piece.getX() == 0 && piece.getY() < 600 && piece.getY() >= 0) {
                piece.setY(piece.getY() + 40);
            }
            //to make the piece move right if the piece is on the bottom of the board
            else if (piece.getY() == 600 && piece.getX() < 600 && piece.getX() >= 0) {
                piece.setX(piece.getX() + 40);
            }
            //to make the piece move up if the piece is on the right of the board
            else if (piece.getX() == 600 && piece.getY() > 0 && piece.getY() <= 600) {
                piece.setY(piece.getY() - 40);
            }
            //to make the piece move left if the piece is on the top of the board
            else if (piece.getY() == 0 && piece.getX() > 0 && piece.getX() <= 600) {
                piece.setX(piece.getX() - 40);
            }
        }
    }


    //method used to genereate a random number
    public static int randomNum() {
        Random rand = new Random();
        int x = rand.nextInt(13);
        //sets the max number that can be generated

        while (x == 6 || x == 9) {
            //if the random number is 6 or 9, it keeps looping so it becomes another number
            x = rand.nextInt(13);
        }
        return x;
    }

    //used to check if a given character is a string or an integer
    public static boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
        }
        catch(NumberFormatException nfe){
            return false;
        }
        return true;
    }

}
