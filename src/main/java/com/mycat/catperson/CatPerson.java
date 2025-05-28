package com.mycat.catperson;

import com.almasb.fxgl.app.GameApplication;
import com.almasb.fxgl.app.GameSettings;
import com.almasb.fxgl.app.scene.FXGLMenu;
import com.almasb.fxgl.app.scene.LoadingScene;
import com.almasb.fxgl.app.scene.SceneFactory;
import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.SpawnData;
import com.mycat.catperson.ui.CustomLoadingScene;
import com.mycat.catperson.ui.TestMainMenu;
import javafx.geometry.Point2D;
import javafx.scene.input.KeyCode;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

import java.nio.file.Paths;
import com.almasb.fxgl.time.TimerAction;
import javafx.util.Duration;
import org.jetbrains.annotations.NotNull;

public class CatPerson extends GameApplication {

    public static boolean login = false;
    public static String username;
    public static String password;
    public static double ostartx = 256;
    public static double ostarty = 128;
    public int grid = 1;
    public static MediaPlayer mediaPlayer;
    public static MediaPlayer mediaPlayer1;
    public static String myip;
    public static boolean timemod;

    @Override
    protected void initSettings(GameSettings settings){
        settings.setMainMenuEnabled(true);
        settings.setSceneFactory(new SceneFactory(){
            @NotNull
            @Override
            public FXGLMenu newMainMenu(){
                return new TestMainMenu();
            }
            @NotNull
            public LoadingScene newLoadingScene() {
                return new CustomLoadingScene();
            }
        });

        settings.setTitle("CatPerson");
        settings.setWidth(1024);
        settings.setHeight(1024);
        settings.setVersion("1.0");
    }

    public void creat(double startx,double starty,int i, int j,int grid,String name,String animate_name){
        switch (name){
            case "otblock":
            case "toblock":
            case "ttblock":
            case "ooblock":
                SpawnData catot = new SpawnData()
                        .put("X",startx + j*128 + j*grid)
                        .put("Y",starty + i*128 + i*grid)
                        .put("animate_name",animate_name)
                        .put("name",Map.getnum(i,j));
                FXGL.spawn(name,catot);
                break;
        }
    }

    public void initcreat(){
        for (int i = 0 ; i < 5; i++){
            for (int j = 0 ; j < 4;j++){
                switch (Map.gettype(i, j)) {
                    case "ooblock":
                        creat(ostartx, ostarty, i, j, grid, "ooblock", "OO.png");
                        break;
                    case "otblock":
                        creat(ostartx, ostarty, i, j, grid, "otblock", "OT.png");
                        break;
                    case "toblock":
                        creat(ostartx, ostarty, i, j, grid, "toblock", "TO.png");
                        break;
                    case "ttblock":
                        creat(ostartx, ostarty, i, j, grid, "ttblock", "TT.png");
                        break;
                }
            }
            setFish();
        }
    }

    public static void setFish(){
        FXGL.getGameWorld().getEntitiesByType(EntityType.Fish)
                .forEach(entity -> {
                    int[] position = Map.getposition(Map.getName());
                    double newX = position[0] * 129 + ostartx;
                    double newY = position[1] * 129 + ostarty;
                    entity.setPosition(newX, newY);
                });
    }

    public static void resetFish(Point2D x){
        FXGL.getGameWorld().getEntitiesByType(EntityType.Fish)
                .forEach(entity -> entity.setPosition(x));
    }

    public static void reinitcreat() {
        FXGL.getGameWorld().getEntitiesByType(EntityType.OTBlock, EntityType.OOBlock, EntityType.TOBlock, EntityType.TTBlock)
                .forEach(entity -> {
                    int name = entity.getInt("name");
                    int[] position = Map.getposition(name);
                    double newX = position[0] * 129 + ostartx;
                    double newY = position[1] * 129 + ostarty;
                    entity.setPosition(newX, newY);
                });
        setFish();
    }

    @Override
    protected void initGame() {
        Restore.Checkfile();
        Restore.cleanAndValidateLines();
        FXGL.getGameWorld().getEntitiesByType(EntityType.UP, EntityType.DOWN, EntityType.LEFT, EntityType.RIGHT, EntityType.RE, EntityType.BA, EntityType.EX)
                .forEach(Entity::removeFromWorld);
        FXGL.getGameWorld().getEntitiesByType(EntityType.OTBlock, EntityType.OOBlock, EntityType.TOBlock, EntityType.TTBlock)
                .forEach(Entity::removeFromWorld);
        FXGL.getGameWorld().addEntityFactory(new CatEntityFactory());

        SpawnData back = new SpawnData()
                .put("X",0.0)
                .put("Y",0.0)
                .put("animate_name","BACKGROUND.png")
                .put("name",0);
        FXGL.spawn("background",back);

        SpawnData mao = new SpawnData()
                .put("X",850.0)
                .put("Y",750.0)
                .put("animate_name","MAO.png")
                .put("name",0);
        FXGL.spawn("mao",mao);

        SpawnData up = new SpawnData()
                .put("X",898.0)
                .put("Y",750.0);
        FXGL.spawn("up",up);

        SpawnData down = new SpawnData()
                .put("X",898.0)
                .put("Y",830.0);
        FXGL.spawn("down",down);

        SpawnData left = new SpawnData()
                .put("X",850.0)
                .put("Y",796.0);
        FXGL.spawn("left",left);

        SpawnData right = new SpawnData()
                .put("X",930.0)
                .put("Y",796.0);
        FXGL.spawn("right",right);

        SpawnData sa = new SpawnData()
                .put("X",930.0)
                .put("Y",10.0);
        FXGL.spawn("sa",sa);

        SpawnData re = new SpawnData()
                .put("X",850.0)
                .put("Y",600.0);
        FXGL.spawn("re",re);

        SpawnData ai = new SpawnData()
                .put("X",850.0)
                .put("Y",100.0);
        FXGL.spawn("ai",ai);

        SpawnData ba = new SpawnData()
                .put("X",850.0)
                .put("Y",450.0);
        FXGL.spawn("ba",ba);

        SpawnData ex = new SpawnData()
                .put("X",850.0)
                .put("Y",300.0);
        FXGL.spawn("ex",ex);

        SpawnData se = new SpawnData()
                .put("X",500.0+10)
                .put("Y",820.0)
                .put("type","se");
        FXGL.spawn("se",se);

        SpawnData mi = new SpawnData()
                .put("X",430.0+10)
                .put("Y",820.0)
                .put("type","mi");
        FXGL.spawn("mi",mi);

        SpawnData ho = new SpawnData()
                .put("X",360.0+10)
                .put("Y",820.0)
                .put("type","ho");
        FXGL.spawn("ho",ho);

        SpawnData fish = new SpawnData()
                .put("X",898.0)
                .put("Y",750.0);
        FXGL.spawn("fish",fish);

        AiHelp.bestgoal = 0;
        Timer.click();
        initcreat();
        restartMusic();
        initAutoSave();
    }

    private void initAutoSave() {
        TimerAction autoSaveTimer = FXGL.getGameTimer().runAtInterval(() -> {
            Timer.click();
            if (login) {
                Restore.updateUserRecord(username);
            }
        }, Duration.seconds(0.5));
    }


    private void initMusic() {
        try {
            String musicFile = "src/main/resources/cat.mp3";
            Media sound = new Media(Paths.get(musicFile).toUri().toString());
            mediaPlayer = new MediaPlayer(sound);
            mediaPlayer.setCycleCount(MediaPlayer.INDEFINITE);
            mediaPlayer.setVolume(0.5);
            mediaPlayer.play();
        } catch (Exception e) {
            System.err.println("Error loading or playing music: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void playSound(int num){
        try {
            String musicFile;
            if(num == 1){
                musicFile = "src/main/resources/meow2.mp3";
                Media sound = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer1 = new MediaPlayer(sound);
            }else {
                musicFile = "src/main/resources/meow4.mp3";
                Media sound = new Media(Paths.get(musicFile).toUri().toString());
                mediaPlayer1 = new MediaPlayer(sound);
            }
            mediaPlayer1.setCycleCount(1);
            mediaPlayer1.setVolume(0.5);
            mediaPlayer1.play();
        } catch (Exception e) {
            System.err.println("Error loading or playing music: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public void restartMusic() {
        if (mediaPlayer != null) {
            mediaPlayer.stop();
        }
        initMusic();
    }

    protected void initInput() {
        FXGL.onKeyDown(KeyCode.UP, () -> {
            if(Map.movecontroller(ButtonController.getEntityType(Map.getName()),Map.getName())[2] > 0){
                Map.addhistory(new int[]{2,1,Map.getName()});
                Map.setCount(Map.getCount() + 1);
                Map.movecat(ButtonController.getEntityType(Map.getName()),2,1,Map.getName());
                Timer.click();
                reinitcreat();
            }
        });

        FXGL.onKeyDown(KeyCode.DOWN, () -> {
            if(Map.movecontroller(ButtonController.getEntityType(Map.getName()),Map.getName())[3] > 0){
                Map.addhistory(new int[]{3,1,Map.getName()});
                Map.setCount(Map.getCount() + 1);
                Map.movecat(ButtonController.getEntityType(Map.getName()),3,1,Map.getName());
                Timer.click();
                reinitcreat();
            }
        });

        FXGL.onKeyDown(KeyCode.LEFT, () -> {
            if(Map.movecontroller(ButtonController.getEntityType(Map.getName()),Map.getName())[0] > 0){
                Map.addhistory(new int[]{0,1,Map.getName()});
                Map.setCount(Map.getCount() + 1);
                Map.movecat(ButtonController.getEntityType(Map.getName()),0,1,Map.getName());
                Timer.click();
                reinitcreat();
            }
        });

        FXGL.onKeyDown(KeyCode.RIGHT, () -> {
            if(Map.movecontroller(ButtonController.getEntityType(Map.getName()),Map.getName())[1] > 0){
                Map.addhistory(new int[]{1,1,Map.getName()});
                Map.setCount(Map.getCount() + 1);
                Map.movecat(ButtonController.getEntityType(Map.getName()),1,1,Map.getName());
                Timer.click();
                reinitcreat();
            }
        });
    }

    public static void main(String[] args) {
        launch(args);
    }
}
