package com.mycat.catperson;

import com.almasb.fxgl.entity.component.Component;
import com.almasb.fxgl.texture.AnimatedTexture;
import com.almasb.fxgl.texture.AnimationChannel;
import javafx.geometry.Point2D;
import javafx.scene.image.Image;
import javafx.util.Duration;

public class AnimateComponent extends Component {
    private AnimatedTexture animatedTexture;
    private AnimationChannel animationChannel;
    private int width;
    private int height;

    public AnimateComponent(String image_name,int frame, int width, int height,int start, int end,int duration){
        this.width = width;
        this.height = height;
        Image image = new Image(image_name);
        animationChannel = new AnimationChannel(image,frame,width,height, Duration.seconds(duration), start, end);
        animatedTexture = new AnimatedTexture(animationChannel);
        animatedTexture.loop();
    }

    @Override
    public void onAdded(){
        entity.getViewComponent().addChild(animatedTexture);
    }
}
