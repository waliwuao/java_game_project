package com.mycat.catperson;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.entity.components.BoundingBoxComponent;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.geometry.Rectangle2D;
import javafx.stage.Screen;

public class MouseController extends Component {
    private double startX;
    private double startY;
    private double startMouseX;
    private double startMouseY;
    private boolean isDragging = false;
    private EntityType entityType;
    private int vector = -1;
    private int grid = 128;
    private int move = 50;
    private int movable = 0;
    private int name;
    private int ostartx;
    private int ostarty;
    private int[] bound;



    public MouseController(EntityType entityType,int name,int ostartx,int ostarty){
        this.entityType = entityType;
        this.name = name;
        this.ostartx = ostartx;
        this.ostarty =ostarty;
        this.bound = Map.movecontroller(entityType,name);
    }

    public double boundcontrol(double move,int first,int second){
        int up = 0;
        int down = 0;
        if (first > second){
            up = first;
            down = second;
        }else {
            up = second;
            down = first;
        }
        if (move >= up){
            return up;
        } else if (move <= down) {
            return down;
        }else {
            return move;
        }
    }

    @Override
    public void onAdded() {
        FXGL.getGameScene().getRoot().addEventHandler(MouseEvent.MOUSE_PRESSED, this::onMousePressed);
        FXGL.getGameScene().getRoot().addEventHandler(MouseEvent.MOUSE_DRAGGED, this::onMouseDragged);
        FXGL.getGameScene().getRoot().addEventHandler(MouseEvent.MOUSE_RELEASED, this::onMouseReleased);
    }

    private void onMousePressed(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && entity != null) {
            bound = Map.movecontroller(entityType,name);
            BoundingBoxComponent bbox = entity.getBoundingBoxComponent();
            Rectangle2D bounds = new Rectangle2D(entity.getX() + bbox.getMinXLocal(), entity.getY() + bbox.getMinYLocal(), bbox.getWidth(), bbox.getHeight());

            if (bounds.contains(FXGL.getInput().getMouseXWorld(), FXGL.getInput().getMouseYWorld())) {
                Map.setName(name);
                CatPerson.setFish();
                startX = entity.getX();
                startY = entity.getY();
                startMouseX = FXGL.getInput().getMouseXWorld();
                startMouseY = FXGL.getInput().getMouseYWorld();
                isDragging = true;
            } else {
                isDragging = false;
            }
        }
    }

    private void onMouseDragged(MouseEvent event) {
        if (isDragging && event.getButton() == MouseButton.PRIMARY) {
            double deltaX = FXGL.getInput().getMouseXWorld() - startMouseX;
            double deltaY = FXGL.getInput().getMouseYWorld() - startMouseY;
            if ((deltaX*deltaX >= 100 ||deltaY*deltaY >= 100 ) && vector == -1){
                if (deltaX*deltaX >= deltaY*deltaY){
                    if (deltaX < 0 ){
                        vector = 0;
                    } else {
                        vector = 1;
                    }
                }else {
                    if (deltaY < 0){
                        vector = 2;
                    }else {
                        vector = 3;
                    }
                }
            }
            if (deltaX*deltaX < 600 && deltaY*deltaY <600 ){
                vector = -1;
                movable = 0;
            }
            switch (vector){
                case -1:
                    entity.setPosition(startX+deltaX,startY+deltaY);
                    break;
                case 0:
                    entity.setPosition(startX+boundcontrol(deltaX,-bound[0]*grid,10),startY+boundcontrol(deltaY,10,-10));
                    if(boundcontrol(deltaX,-bound[0]*grid,10) < -128-move){
                        movable = 2;
                    } else if (boundcontrol(deltaX,-bound[0]*grid,10) < -move) {
                        movable = 1;
                    }
                    break;
                case 1:
                    entity.setPosition(startX+boundcontrol(deltaX,bound[1]*grid,-10),startY+boundcontrol(deltaY,10,-10));
                    if(boundcontrol(deltaX,bound[1]*grid,-10) > move +128){
                        movable = 2;
                    } else if (boundcontrol(deltaX,bound[1]*grid,-10) > move) {
                        movable = 1;
                    }
                    break;
                case 2:
                    entity.setPosition(startX+boundcontrol(deltaX,10,-10),startY+boundcontrol(deltaY,-bound[2]*grid,10));
                    if (boundcontrol(deltaY,-bound[2]*grid,10) < -move-128 ){
                        movable = 2;
                    } else if (boundcontrol(deltaY,-bound[2]*grid,10) < -move) {
                        movable = 1;
                    }
                    break;
                case 3:
                    entity.setPosition(startX+boundcontrol(deltaX,10,-10),startY+boundcontrol(deltaY,bound[3]*grid,-10));
                    if (boundcontrol(deltaY,bound[3]*grid,-10) > move+128 ){
                        movable = 2;
                    } else if (boundcontrol(deltaY,bound[3]*grid,-10) > move) {
                        movable = 1;
                    }
                    break;
            }
            CatPerson.resetFish(entity.getPosition());
        }
    }


    private void onMouseReleased(MouseEvent event) {
        if (event.getButton() == MouseButton.PRIMARY && isDragging) {
            isDragging = false;
            if (vector != -1 && bound[vector] != 0 ){
                Map.addhistory(new int[]{vector,movable,name});
                Map.setCount(Map.getCount() + 1);
                Map.movecat(entityType,vector,movable,name);
            }
            entity.setPosition(Map.getposition(name)[0]*129+ostartx,Map.getposition(name)[1]*129+ostarty);
            if (movable > 0){
                Timer.click();
            }
            movable = 0;
        }
        CatPerson.setFish();
    }
}