package com.tda367.parallax.parallaxCore.course.courseTest;

import com.tda367.parallax.parallaxCore.RenderManager;
import com.tda367.parallax.parallaxCore.course.Course;
import com.tda367.parallax.parallaxCore.spaceCraft.Agelion;
import com.tda367.parallax.parallaxCore.spaceCraft.ISpaceCraft;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by Rasmus on 2017-05-01.
 */
public class CourseTest {
    RenderManager renderManager = RenderManager.getInstance();
    Course course = new Course();
    Agelion agelion1 = new Agelion();
    Agelion agelion2 = new Agelion();
    Agelion agelion3 = new Agelion();

    @Test
    public void addSpaceCraft() throws Exception {
        int sizeOne = renderManager.getRenderables().size();
        course.addSpaceCraft(new Agelion());
        course.addSpaceCraft(new Agelion());
        int sizeTwo = renderManager.getRenderables().size();
        assertTrue(course.getSpaceCrafts().size() == 2);
        assertTrue(sizeOne + 2 == sizeTwo);
    }

    @Test
    public void removeSpaceCraft() throws Exception {
        course.addSpaceCraft(agelion1);
        course.addSpaceCraft(agelion2);
        course.addSpaceCraft(agelion3);
        int sizeOne = renderManager.getRenderables().size();
        course.removeSpaceCraft(agelion3);
        course.removeSpaceCraft(agelion2);
        System.out.println(course.getSpaceCrafts().size());
        int sizeTwo = renderManager.getRenderables().size();
        assertTrue(course.getSpaceCrafts().size() == 1);
        assertTrue(sizeOne - 2 == sizeTwo);
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void setCollisionCalculator() throws Exception {
    }

    @Test
    public void powerUPUsed() throws Exception {
    }

}