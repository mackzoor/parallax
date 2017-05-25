package com.tda367.parallax.view.parallaxview;

/**
 * Interface for view classes in package.
 */
public interface View {

    /**
     * Renders the view object.
     */
    void render();

    /**
     * Checks if the Model object that the current View object has is dead.
     *
     * @return true if view is no longer needed.
     */
    boolean isObsolete();
}
