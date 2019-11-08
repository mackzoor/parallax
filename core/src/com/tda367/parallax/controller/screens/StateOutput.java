package com.tda367.parallax.controller.screens;

import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.world.ICourseModule;
import com.tda367.parallax.model.core.world.World;
import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;

import java.net.*;
import java.io.*;

import javax.vecmath.Quat4f;

public class StateOutput {

    private static final int PORT = 5000;
    private PrintWriter pr;

    public StateOutput() throws IOException{
        ServerSocket ss = new ServerSocket(PORT);
        Socket s = ss.accept();
        System.out.println("Client connected");

        this.pr = new PrintWriter(s.getOutputStream());
    }

    public void update(Parallax parallax) {
        this.pr.println("UPDATE");
        this.pr.flush();
        this.pr.println("PLAYER" + parallax.getPlayer().getSpaceCraft().getPos().toString());
        this.pr.flush();
        for (ICourseModule module : parallax.getWorld().getModules()) {
            for (CourseObstacleBase obstacle : module.getCouseObstacles()) {
                float rel_pos = obstacle.getPos().y - parallax.getPlayer().getSpaceCraft().getPos().y;
                Quat4f rot = obstacle.getRot();
                // System.out.println(rel_pos + rot.toString());
                this.pr.println(obstacle.getObstacleType() + obstacle.getPos().toString() + obstacle.getRot().toString());
                this.pr.flush();
            }
        }
    }


}
