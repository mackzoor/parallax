package com.tda367.parallax.view.cardboardmenu;

import com.tda367.parallax.model.cardboardmenu.CardboardWorld;
import com.tda367.parallax.view.Renderer3D;
import com.tda367.parallax.view.parallaxview.View;
import com.tda367.parallax.view.util.Renderable3dObject;
import com.tda367.parallax.view.util.ResourceLoader;


public class CardboardMenuWorldView implements View {


    private String internalPath;
    private Renderable3dObject renderable3dObject;
    private CardboardWorld world;

    CardboardMenuWorldView(CardboardWorld world){
        internalPath = "/3dModels/defaultCourse/course.g3db";
        this.world = world;
        renderable3dObject = new Renderable3dObject(
                world.getPos(),
                world.getRot(),
                ResourceLoader.getInstance().getModel("3dModels/defaultCourse/course.g3db")
                ,1f);
    }

    @Override
    public void render(){
        Renderer3D.getInstance().addObjectToFrame(renderable3dObject);
    }

    @Override
    public boolean isObsolete() {
        return false;
    }

}
