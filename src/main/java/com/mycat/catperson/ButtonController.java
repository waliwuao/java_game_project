package com.mycat.catperson;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import com.mycat.catperson.ui.TestMainMenu;
import javafx.geometry.Rectangle2D;
import javafx.scene.image.Image;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;


public class ButtonController extends Component {


    private EntityType entityType;


    private final Runnable onClickAction = new Runnable() {
        @Override
        public void run() {
            switch (entityType){
                case RE:
                    Map.setCount(0);
                    Map.setMap(Level.level1);
                    CatPerson.reinitcreat();
                    Map.clearhistory();
                    Timer.click();
                    AiHelp.bestgoal = 0;
                    break;
                case BA:
                    if (Map.getCount() > 0) {
                        int[] history = Map.getHistory().get(Map.getCount()-1);
                        Map.movecat(getEntityType(history[2]), reverse(history[0]), history[1], history[2]);
                        Map.remove();
                        CatPerson.reinitcreat();
                    }
                    Timer.click();
                    break;
                case AI:
                    int[] path = AiHelp.findshortmove(Map.map,AiHelp.goal);
                    if (path != null){
                        Map.movecat(Map.getEntityType(path[0]),path[1],path[2],path[0]);
                        Map.addhistory(new int[]{path[1],path[2],path[0]});
                        Map.setCount(Map.getCount() + 1);
                        CatPerson.reinitcreat();
                        Timer.click();
                    }
                    break;
                case LEFT:
                    if(Map.movecontroller(getEntityType(Map.getName()),Map.getName())[0] > 0){
                        Map.addhistory(new int[]{0,1,Map.getName()});
                        Map.setCount(Map.getCount() + 1);
                        Map.movecat(getEntityType(Map.getName()),0,1,Map.getName());
                        Timer.click();
                        CatPerson.reinitcreat();
                    }
                    break;
                case RIGHT:
                    if(Map.movecontroller(getEntityType(Map.getName()), Map.getName())[1] > 0){
                        Map.addhistory(new int[]{1,1, Map.getName()});
                        Map.setCount(Map.getCount() + 1);
                        Map.movecat(getEntityType(Map.getName()),1,1, Map.getName());
                        Timer.click();
                        CatPerson.reinitcreat();
                    }
                    break;
                case UP:
                    if(Map.movecontroller(getEntityType(Map.getName()),Map.getName())[2] > 0){
                        Map.addhistory(new int[]{2,1,Map.getName()});
                        Map.setCount(Map.getCount() + 1);
                        Map.movecat(getEntityType(Map.getName()),2,1,Map.getName());
                        Timer.click();
                        CatPerson.reinitcreat();
                    }
                    break;
                case DOWN:
                    if(Map.movecontroller(getEntityType(Map.getName()),Map.getName())[3] > 0){
                        Map.addhistory(new int[]{3,1, Map.getName()});
                        Map.setCount(Map.getCount() + 1);
                        Map.movecat(getEntityType(Map.getName()),3,1, Map.getName());
                        Timer.click();
                        CatPerson.reinitcreat();
                    }
                    break;
                case SA:
                    if (CatPerson.login){
                        Restore.updateUserRecord(CatPerson.username);
                    }
                    break;
                case EX:
                    if (CatPerson.login){
                        Restore.updateUserRecord(CatPerson.username);
                    }
                    FXGL.getSceneService().pushSubScene(new TestMainMenu());
                    Map.setCount(0);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + entityType);
            }
        }
    };

    public static EntityType getEntityType(int x){
        if (x>0 && x<=4){
            return EntityType.OOBlock;
        } else if (x>4&&x<=8) {
            return EntityType.OTBlock;
        } else if (x == 9) {
            return EntityType.TOBlock;
        } else if (x == 10) {
            return EntityType.TTBlock;
        }else {
            return EntityType.BACKGround;
        }
    }

    public int reverse(int x){
        if(x == 0){
            return 1;
        } else if (x == 1) {
            return 0;
        } else if (x == 2) {
            return 3;
        } else if (x == 3) {
            return 2;
        }else {
            return -1;
        }
    }

    public ButtonController(EntityType entityType, String normalTexture, String pressedTexture) {
        Image normal = new Image(normalTexture);
        Image pressed = new Image(pressedTexture);
        this.entityType = entityType;
    }

    @Override
    public void onAdded() {
        FXGL.getGameScene().getRoot().addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed);
    }

    private void onMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && entity != null) {
            BoundingBoxComponent bbox = entity.getBoundingBoxComponent();
            Rectangle2D bounds = new Rectangle2D(entity.getX() + bbox.getMinXLocal(), entity.getY() + bbox.getMinYLocal(), bbox.getWidth(), bbox.getHeight());
            if (bounds.contains(FXGL.getInput().getMouseXWorld(), FXGL.getInput().getMouseYWorld())) {
                onClickAction.run();
            }
        }
    }
}
