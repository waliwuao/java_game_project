package com.mycat.catperson.ui;

import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.MenuType;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.scene.SubScene;
import com.mycat.catperson.*;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.nio.file.Paths;

public class TestMainMenu extends FXGLMenu {

    private MediaPlayer mediaPlayer;
    private MediaPlayer buttonSoundPlayer;
    private static final String BUTTON_SOUND_FILE = "src/main/resources/cat.mp3";


    public TestMainMenu() {
        super(MenuType.MAIN_MENU);

        Restore.Checkfile();
        Restore.cleanAndValidateLines();

        Pane root = new Pane();
        root.setPrefWidth(FXGL.getAppWidth());
        root.setPrefHeight(FXGL.getAppHeight());

        Image image = new Image("BACKGROUND1.png");
        BackgroundImage backgroundImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.CENTER,
                new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
        );
        root.setBackground(new Background(backgroundImage));

        Button guestButton;
        Button loginButton;

        if (!CatPerson.login) {
            guestButton = createButtonWithImage("YK.png", 384, 192, e -> {
                FXGL.getSceneService().pushSubScene(new LevelSelectSubScene());
            });

            loginButton = createButtonWithImage("DL.png", 384, 192, e -> {
                FXGL.getSceneService().pushSubScene(new LoginSubScene());
            });
        } else {
            guestButton = createButtonWithImage("DC.png", 384, 192, e -> {
                CatPerson.login = false;
                CatPerson.username = null;
                CatPerson.password = null;
                int[][] map = {
                        {5, 9, -9, 7},
                        {-5, 2, 3, -7},
                        {6, 10, -10, 8},
                        {-6, -10, -10, -8},
                        {1, 0, 0, 4}
                };
                Level.setLevel2(map);
                FXGL.getSceneService().popSubScene();
                FXGL.getSceneService().pushSubScene(new TestMainMenu());
            });

            loginButton = createButtonWithImage("XZ.png", 384, 192, e -> {
                FXGL.getSceneService().pushSubScene(new LevelSelectSubScene());
            });
        }

        guestButton.setLayoutX(320);
        guestButton.setLayoutY(200);

        loginButton.setLayoutX(320);
        loginButton.setLayoutY(425);

        Button exitButton = createButtonWithImage("TC.png", 384, 192, e -> {
            FXGL.getGameController().exit();

        });
        exitButton.setLayoutX(320);
        exitButton.setLayoutY(650);

        String ipAddress = "Unknown";
        try {
            InetAddress inetAddress = InetAddress.getLocalHost();
            ipAddress = inetAddress.getHostAddress();
        } catch (UnknownHostException e) {
            System.err.println("Could not get local IP address: " + e.getMessage());
        }

        // Create IP address label
        Label ipLabel = new Label("Local IP: " + ipAddress);
        CatPerson.myip = ipAddress;
        ipLabel.setStyle("-fx-text-fill: white; -fx-font-size: 16px;");
        ipLabel.setLayoutX(450);
        ipLabel.setLayoutY(950);

        root.getChildren().addAll(guestButton, loginButton, exitButton, ipLabel);
        getContentRoot().getChildren().add(root);
    }

    private Button createButtonWithImage(String imagePath, double width, double height, javafx.event.EventHandler<javafx.event.ActionEvent> action) {
        Image image = new Image(imagePath);
        ImageView imageView = new ImageView(image);
        imageView.setFitWidth(width);
        imageView.setFitHeight(height);
        imageView.setPreserveRatio(true);

        Button button = new Button();
        button.setGraphic(imageView);
        button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
        button.setOnAction(action);

        return button;
    }

    private void loadAndPlayMusic(String musicFile) {
        try {
            Media sound = new Media(Paths.get(musicFile).toUri().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading music: " + musicFile + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void loadButtonSound(String soundFile) {
        try {
            Media sound = new Media(Paths.get(soundFile).toUri().toString());
            buttonSoundPlayer = new MediaPlayer(sound);
        } catch (Exception e) {
            System.err.println("Error loading button sound: " + soundFile + " - " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void playButtonSound() {
        if (buttonSoundPlayer != null) {
            buttonSoundPlayer.stop(); // Reset to the beginning
            buttonSoundPlayer.play();
        }
    }

    public class LoginSubScene extends SubScene {

        private static final String TEXT_INPUT_BACKGROUND = "TO.png";
        private static final int TEXT_INPUT_WIDTH = 256;
        private static final int TEXT_INPUT_HEIGHT = 128;

        public LoginSubScene() {
            super();

            Pane root = new Pane();
            root.setPrefWidth(FXGL.getAppWidth());
            root.setPrefHeight(FXGL.getAppHeight());

            Image image = new Image("BACKGROUND1.png");
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );
            root.setBackground(new Background(backgroundImage));

            // Username Input
            Label usernameLabel = new Label("");
            usernameLabel.setLayoutX(384);
            usernameLabel.setLayoutY(150);
            usernameLabel.setStyle("-fx-text-fill: white;");

            TextField usernameField = createTextField();
            usernameField.setPromptText("请输入用户名");
            usernameField.setLayoutX(320);
            usernameField.setLayoutY(180);
            usernameField.setPrefWidth(400);  // Adjusted width
            usernameField.setPrefHeight(40); // Adjusted height

            // Password Input
            Label passwordLabel = new Label("");
            passwordLabel.setLayoutX(384);
            passwordLabel.setLayoutY(280);
            passwordLabel.setStyle("-fx-text-fill: white;");

            PasswordField passwordField = createPasswordField();
            passwordField.setPromptText("请输入密码");
            passwordField.setLayoutX(320);
            passwordField.setLayoutY(310);
            passwordField.setPrefWidth(400);  // Adjusted width
            passwordField.setPrefHeight(40); // Adjusted height

            // Login Button
            Button loginButton = createButtonWithCustomImage("DL.png", 300, 150, "");
            loginButton.setLayoutX(362);
            loginButton.setLayoutY(390);
            loginButton.setOnAction(e -> {
                playButtonSound();
                String username = usernameField.getText();
                String password = passwordField.getText();
                String storedPasswordHash = Restore.getPasswordByUsername(username);

                if (storedPasswordHash != null && storedPasswordHash.equals(Restore.getSignature(password))) {
                    CatPerson.username = username;
                    CatPerson.password = storedPasswordHash;

                    if (Restore.getCountAndMapByUsername(username)) {
                        FXGL.getSceneService().popSubScene();
                        CatPerson.login = true;
                        FXGL.getSceneService().pushSubScene(new LevelSelectSubScene());
                    } else {
                        passwordField.clear();
                        passwordField.setPromptText("加载地图数据失败");
                    }
                } else {
                    passwordField.clear();
                    passwordField.setPromptText("密码或用户名错误");
                }
            });

            // Register Button
            Button registerButton = createButtonWithCustomImage("ZC.png", 300, 150, "");
            registerButton.setLayoutX(362);
            registerButton.setLayoutY(560);
            registerButton.setOnAction(e -> {
                playButtonSound();
                String username = usernameField.getText();
                String password = passwordField.getText();

                if (username.isEmpty() || password.isEmpty()) {
                    if (username.isEmpty()) {
                        usernameField.clear();
                        usernameField.setPromptText("用户名不能为空");
                    }
                    if (password.isEmpty()) {
                        passwordField.clear();
                        passwordField.setPromptText("密码不能为空");
                    }
                } else if (!username.matches("[a-zA-Z0-9]+") || !password.matches("[a-zA-Z0-9]+")) {
                    usernameField.clear();
                    passwordField.clear();
                    usernameField.setPromptText("用户名只能包含大小写字母和数字");
                    passwordField.setPromptText("密码只能包含大小写字母和数字");
                } else {
                    // Check if username already exists
                    if (Restore.getPasswordByUsername(username) != null) {
                        usernameField.clear();
                        passwordField.clear();
                        usernameField.setPromptText("用户名已存在");
                        return; // Exit the registration process
                    }

                    Level.setLevelnum(2);
                    Map.setMap(Level.getlevel());
                    String passwordHash = Restore.getSignature(password);

                    CatPerson.login = true;
                    CatPerson.username = username;
                    CatPerson.password = passwordHash;

                    Restore.updateUserRecord(username);
                    FXGL.getSceneService().popSubScene();
                    FXGL.getSceneService().pushSubScene(new LevelSelectSubScene());
                }
            });

            // Exit Button
            Button exitButton = createButtonWithCustomImage("TC.png", 300, 150, "");
            exitButton.setLayoutX(362);
            exitButton.setLayoutY(730);
            exitButton.setOnAction(e -> {
                playButtonSound();
                FXGL.getSceneService().popSubScene();
            });

            root.getChildren().addAll(usernameLabel, usernameField, passwordLabel, passwordField, loginButton, registerButton, exitButton);
            getContentRoot().getChildren().add(root);
        }

        private TextField createTextField() {
            TextField textField = new TextField();
            textField.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
            return textField;
        }

        private PasswordField createPasswordField() {
            PasswordField passwordField = new PasswordField();
            passwordField.setStyle("-fx-background-color: rgba(255, 255, 255, 0.8);");
            return passwordField;
        }

        private Button createButtonWithCustomImage(String imagePath, double width, double height, String buttonText) {
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);

            Label textLabel = new Label(buttonText);
            textLabel.setStyle("-fx-text-fill: white; -fx-font-size: 14px;");

            StackPane stackPane = new StackPane(imageView, textLabel);
            stackPane.setAlignment(Pos.CENTER);

            Button button = new Button();
            button.setGraphic(stackPane);
            button.setStyle("-fx-background-color: transparent; -fx-padding: 0;");
            button.setPrefWidth(width);
            button.setPrefHeight(height);

            return button;
        }
    }

    public class LevelSelectSubScene extends SubScene {

        private static final int WIDTH = 1024;
        private static final int HEIGHT = 1024;

        public LevelSelectSubScene() {
            super();
            getContentRoot().setPrefWidth(WIDTH);
            getContentRoot().setPrefHeight(HEIGHT);

            Pane layout = new Pane();
            layout.setPrefWidth(WIDTH);
            layout.setPrefHeight(HEIGHT);

            Image image = new Image("BACKGROUND1.png");
            BackgroundImage backgroundImage = new BackgroundImage(
                    image,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundRepeat.NO_REPEAT,
                    BackgroundPosition.CENTER,
                    new BackgroundSize(BackgroundSize.AUTO, BackgroundSize.AUTO, false, false, true, false)
            );

            Background background = new Background(backgroundImage);
            layout.setBackground(background);

            Button level1Button = createButtonWithImage("LEVEL1.png", 516, 128, e -> {
                playButtonSound();
                loadLevel(1);
            });
            level1Button.setLayoutX(256);
            level1Button.setLayoutY(192);

            Button level2Button = createButtonWithImage("LEVEL2.png", 516, 128, e -> {
                playButtonSound();
                loadLevel(2);
            });
            level2Button.setLayoutX(256);
            level2Button.setLayoutY(362);

            Button ljButton = createButtonWithImage("TIME.png", 516, 128, e -> {
                Timer.starttime = (int) (System.currentTimeMillis()/1000);
                CatPerson.timemod = true;
                Level.setLevelnum(1);
                Map.setCount(0);
                Map.setMap(Level.getlevel());
                Map.history.clear();
                FXGL.getGameWorld().reset();
                FXGL.getGameController().startNewGame();
                FXGL.getSceneService().popSubScene();
            });
            ljButton.setLayoutX(256);
            ljButton.setLayoutY(532);

            Button backButton = createButtonWithImage("EXIT.png", 516, 128, e -> {
                playButtonSound();
                FXGL.getSceneService().popSubScene();
                FXGL.getSceneService().pushSubScene(new TestMainMenu());
            });
            backButton.setLayoutX(256);
            backButton.setLayoutY(704);

            layout.getChildren().addAll(level1Button, level2Button, backButton, ljButton);

            getContentRoot().getChildren().add(layout);
        }

        private void loadLevel(int levelNumber) {
            CatPerson.timemod = false;
            switch (levelNumber) {
                case 1:
                    Level.setLevelnum(1);
                    Map.setCount(0);
                    Map.setMap(Level.getlevel());
                    Map.history.clear();
                    break;
                case 2:
                    Level.setLevelnum(2);
                    Map.setMap(Level.getlevel());
                    break;
            }
            FXGL.getGameWorld().reset();
            FXGL.getGameController().startNewGame();
            FXGL.getSceneService().popSubScene();
        }
    }

    public static void addImage(Pane pane, String imagePath, double x, double y, double width, double height) {
        try {
            Image image = new Image(imagePath);
            ImageView imageView = new ImageView(image);

            imageView.setX(x);
            imageView.setY(y);
            imageView.setFitWidth(width);
            imageView.setFitHeight(height);
            imageView.setPreserveRatio(true);

            pane.getChildren().add(imageView);

        } catch (Exception e) {
            System.err.println("Error loading image: " + imagePath);
            e.printStackTrace();
        }
    }
}
