package com.mycat.catperson;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.Texture;
import com.mycat.catperson.ui.FailSubScene;
import com.mycat.catperson.ui.WinSubScene;
import javafx.scene.image.Image;

public class Timer extends Component {
    public static int starttime;
    public static int endtime;
    public static int showtime;

    public static int seconds = 0;
    public static int minutes = 0;
    public static int hours = 0;


    public static Texture getTexture(int x) {
        switch (x) {
            case 0:
                return new Texture(new Image("num0.png"));
            case 1:
                return new Texture(new Image("num1.png"));
            case 2:
                return new Texture(new Image("num2.png"));
            case 3:
                return new Texture(new Image("num3.png"));
            case 4:
                return new Texture(new Image("num4.png"));
            case 5:
                return new Texture(new Image("num5.png"));
            case 6:
                return new Texture(new Image("num6.png"));
            case 7:
                return new Texture(new Image("num7.png"));
            case 8:
                return new Texture(new Image("num8.png"));
            case 9:
                return new Texture(new Image("num9.png"));
        }
        return new Texture(new Image("num0.png"));
    }

    public static void click() {
        if(CatPerson.timemod){
            endtime = (int) (System.currentTimeMillis()/1000);
            showtime = 20 -(endtime-starttime);
            seconds = showtime % 10;
            minutes = showtime % 100 / 10;
            hours = showtime % 1000 / 100;
        }else {
            seconds = Map.getCount() % 10;
            minutes = Map.getCount() % 100 / 10;
            hours = Map.getCount() % 1000 / 100;
        }

        FXGL.getGameWorld().getEntitiesByType(EntityType.SE, EntityType.MI, EntityType.HO)
                .forEach(entity -> {
                    switch (entity.getString("type")) {
                        case "se":
                            entity.getViewComponent().clearChildren();
                            entity.getViewComponent().addChild(getTexture(seconds));
                            break;
                        case "mi":
                            entity.getViewComponent().clearChildren();
                            entity.getViewComponent().addChild(getTexture(minutes));
                            break;
                        case "ho":
                            entity.getViewComponent().clearChildren();
                            entity.getViewComponent().addChild(getTexture(hours));
                            break;
                    }
                });

        if (checkWinCondition()) {
            if(CatPerson.login){
                int[][] map = {
                        {5, 10, -10, 7},
                        {-5, -10, -10, -7},
                        {6, 9, -9, 8},
                        {-6, 2, 3, -8},
                        {1, 0, 0, 4}
                };
                Level.setLevel2(map);
                Restore.updateUserRecord(CatPerson.username);
            }
            showWinScreen();
        } else if (Map.count > 999 || (CatPerson.timemod && showtime <= 0)) {
            if(CatPerson.login){
                int[][] map = {
                        {5, 10, -10, 7},
                        {-5, -10, -10, -7},
                        {6, 9, -9, 8},
                        {-6, 2, 3, -8},
                        {1, 0, 0, 4}
                };
                Level.setLevel2(map);
                Map.history.clear();
                Restore.updateUserRecord(CatPerson.username);
            }
            FXGL.getGameController().pauseEngine();
            FXGL.getSceneService().pushSubScene(new FailSubScene());
        }
    }

    private static boolean checkWinCondition() {
        return Map.map[3][1] == 10;
    }

    private static void showWinScreen() {
        FXGL.getGameController().pauseEngine();
        FXGL.getSceneService().pushSubScene(new WinSubScene());
    }

}
