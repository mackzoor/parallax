package com.tda367.parallax.parallaxCore.course;

import com.tda367.parallax.model.coreabstraction.RenderManager;
import com.tda367.parallax.model.parallaxcore.course.Course;
import com.tda367.parallax.model.parallaxcore.spacecraft.Agelion;
import org.junit.Test;

import static org.junit.Assert.*;

public class CourseTest {
    private RenderManager renderManager = RenderManager.getInstance();
    private Course course = new Course();
    private Agelion agelion1 = new Agelion();
    private Agelion agelion2 = new Agelion();
    private Agelion agelion3 = new Agelion();

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