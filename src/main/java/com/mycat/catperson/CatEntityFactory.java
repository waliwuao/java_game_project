package com.mycat.catperson;

import com.almasb.fxgl.dsl.FXGL;
import com.almasb.fxgl.entity.Entity;
import com.almasb.fxgl.entity.EntityFactory;
import com.almasb.fxgl.entity.SpawnData;
import com.almasb.fxgl.entity.Spawns;
import com.almasb.fxgl.entity.components.ViewComponent;
import com.almasb.fxgl.physics.BoundingShape;
import com.almasb.fxgl.physics.HitBox;
import com.almasb.fxgl.texture.Texture;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;

public class CatEntityFactory implements EntityFactory{
    //此处坐标需与主程序坐标相同
    private int ostartx = 256;
    private int ostarty = 128;

    @Spawns("background")
    public Entity newbackground(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        String animate = data.get("animate_name");
        int name = data.get("name");
        return FXGL.entityBuilder()
                .type(EntityType.BACKGround)
                .at(Xposition,Yposition)
                .with(new AnimateComponent(animate,1,1024,1024,0,0,1))
                .build();
    }

    @Spawns("fish")
    public Entity newfish(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.Fish)
                .at(Xposition,Yposition)
                .view(new Texture(new Image("FISH.png")))
                .build();
    }

    @Spawns("se")
    public Entity newse(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        String type = data.get("type");
        return FXGL.entityBuilder()
                .type(EntityType.SE)
                .at(Xposition,Yposition)
                .view(new Texture(new Image("num0.png")))
                .with("type",type)
                .build();
    }

    @Spawns("mi")
    public Entity newmi(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        String type = data.get("type");
        return FXGL.entityBuilder()
                .type(EntityType.SE)
                .at(Xposition,Yposition)
                .view(new Texture(new Image("num0.png")))
                .with("type",type)
                .build();
    }

    @Spawns("ho")
    public Entity newho(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        String type = data.get("type");
        return FXGL.entityBuilder()
                .type(EntityType.HO)
                .at(Xposition,Yposition)
                .with("type",type)
                .view(new Texture(new Image("num0.png")))
                .build();
    }

    @Spawns("sa")
    public Entity sa(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.SA)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(32,48)))
                .with(new ButtonController(EntityType.SA,"SAVE.png","SAVE.png"))
                .view(new Texture(new Image("SAVE.png")))
                .build();
    }

    @Spawns("up")
    public Entity newup(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.UP)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(32,48)))
                .with(new ButtonController(EntityType.UP,"UP.png","UP.png"))
                .view(new Texture(new Image("UP.png")))
                .build();
    }

    @Spawns("down")
    public Entity newdown(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.DOWN)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(32,48)))
                .with(new ButtonController(EntityType.DOWN,"DOWN.png","DOWN.png"))
                .view(new Texture(new Image("DOWN.png")))
                .build();
    }

    @Spawns("left")
    public Entity newleft(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.LEFT)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(48,32)))
                .with(new ButtonController(EntityType.LEFT,"LEFT.png","LEFT.png"))
                .view(new Texture(new Image("LEFT.png")))
                .build();
    }

    @Spawns("right")
    public Entity newright(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.RIGHT)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(48,32)))
                .with(new ButtonController(EntityType.RIGHT,"RIGHT.png","RIGHT.png"))
                .view(new Texture(new Image("RIGHT.png")))
                .build();
    }

    @Spawns("re")
    public Entity newre(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.RE)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(128,128)))
                .with(new ButtonController(EntityType.RE,"RE0.png","RE1.png"))
                .view(new Texture(new Image("RE0.png")))
                .build();
    }

    @Spawns("ba")
    public Entity newba(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.BA)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(128,128)))
                .with(new ButtonController(EntityType.BA,"BACK0.png","BACK1.png"))
                .view(new Texture(new Image("BACK0.png")))
                .build();
    }

    @Spawns("ai")
    public Entity newai(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.AI)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(128,128)))
                .with(new ButtonController(EntityType.AI,"AI0.png","AI1.png"))
                .view(new Texture(new Image("AI0.png")))
                .build();
    }

    @Spawns("mao")
    public Entity newmao(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.BACKGround)
                .at(Xposition,Yposition)
                .view(new Texture(new Image("MAO.png")))
                .build();
    }

    @Spawns("ex")
    public Entity newex(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        return FXGL.entityBuilder()
                .type(EntityType.EX)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(128,128)))
                .with(new ButtonController(EntityType.EX,"OUT0.png","OUT1.png"))
                .view(new Texture(new Image("OUT0.png")))
                .build();
    }

    @Spawns("otblock")
    public Entity newotblock(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        String animate = data.get("animate_name");
        int name = data.get("name");
        return FXGL.entityBuilder()
                .type(EntityType.OTBlock)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(128,256)))
                .with("name",name)
                .with(new AnimateComponent(animate,1,128,256,0,0,1))
                .with(new MouseController(EntityType.OTBlock,name,ostartx,ostarty))
                .build();
    }

    @Spawns("ooblock")
    public Entity newooblock(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        String animate = data.get("animate_name");
        int name = data.get("name");
        return FXGL.entityBuilder()
                .type(EntityType.OOBlock)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(128,128)))
                .with("name",name)
                .with(new AnimateComponent(animate,1,128,128,0,0,1))
                .with(new MouseController(EntityType.OOBlock,name,ostartx,ostarty))
                .build();
    }

    @Spawns("toblock")
    public Entity newtoblock(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        String animate = data.get("animate_name");
        int name = data.get("name");
        return FXGL.entityBuilder()
                .type(EntityType.TOBlock)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(256,128)))
                .with("name",name)
                .with(new AnimateComponent(animate,1,256,128,0,0,1))
                .with(new MouseController(EntityType.TOBlock,name,ostartx,ostarty))
                .build();
    }

    @Spawns("ttblock")
    public Entity newttblock(SpawnData data){
        double Xposition = data.get("X");
        double Yposition = data.get("Y");
        String animate = data.get("animate_name");
        int name = data.get("name");
        return FXGL.entityBuilder()
                .type(EntityType.TTBlock)
                .at(Xposition,Yposition)
                .bbox(new HitBox(BoundingShape.box(256,256)))
                .with("name",name)
                .with(new AnimateComponent(animate,1,256,256,0,0,1))
                .with(new MouseController(EntityType.TTBlock,name,ostartx,ostarty))
                .build();
    }
}
