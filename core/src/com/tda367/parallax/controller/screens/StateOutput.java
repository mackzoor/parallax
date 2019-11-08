package com.tda367.parallax.controller.screens;

import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.model.core.world.ICourseModule;
import com.tda367.parallax.model.core.world.World;
import com.tda367.parallax.model.core.world.courseobstacles.CourseObstacleBase;

import org.json.JSONArray;
import org.json.JSONObject;

import java.net.*;
import java.io.*;
import java.util.LinkedHashMap;

import javax.vecmath.Quat4f;

public class StateOutput {

    private static final int PORT = 5000;
    private PrintWriter pr;
    private OutputStreamWriter out;

    public StateOutput() throws IOException{
        ServerSocket ss = new ServerSocket(PORT);
        Socket s = ss.accept();
        System.out.println("Client connected");
        this.pr = new PrintWriter(s.getOutputStream());
        this.out = new OutputStreamWriter(s.getOutputStream(), "UTF-8");
    }

    public void update(Parallax parallax) {
        JSONObject outputObject = new JSONObject();

        LinkedHashMap player = new LinkedHashMap(1);
        player.put("pos", parallax.getPlayer().getSpaceCraft().getPos().toString());

        outputObject.put("player", player);

        JSONArray obstacles = new JSONArray();
        int i = 0;
        for (ICourseModule module : parallax.getWorld().getModules()) {
            i++;
            System.out.println(i);
            for (CourseObstacleBase obstacle : module.getCouseObstacles()) {
                LinkedHashMap obstacle_json = new LinkedHashMap(3);
                obstacle_json.put("type", obstacle.getObstacleType());
                obstacle_json.put("pos", obstacle.getPos().toString());
                obstacle_json.put("rot", obstacle.getRot().toString());
                obstacles.put(obstacle_json);
            }
        }

        outputObject.put("obstacles", obstacles);

        this.pr.write(outputObject.toString());
        this.pr.flush();
    }


}
