package com.tda367.parallax.view.parallaxview;

import com.badlogic.gdx.Gdx;
import com.tda367.parallax.model.core.Parallax;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews.CollisionEffect;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.extraviews.HudView;
import com.tda367.parallax.view.parallaxview.parallaxviewcomponents.worldview.WorldView;
import com.tda367.parallax.view.rendering.Renderer3D;
import com.tda367.parallax.view.sound.Sound;
import lombok.Getter;
import lombok.Setter;

import java.util.Random;

/**
 * View class for {@link Parallax}.
 */
public class ParallaxView {

    private static final Random RAND = new Random();
    private static final String MUSIC_DIRECTORY = "sounds/music/";
    private static final int SPANISH_FLEA_ODDS = 100;
    private static final int SECRET_TRACK_ODDS = 100;
    private static final float SPANISH_VOLUME = 0.7f;
    private static final float SPACE_VOLUME = 0.7f;
    private static final float BALLS_VOLUME = 0.7f;
    private static final float SOUNDS_GREAT_VOLUME = 0.5f;

    private final Parallax parallax;
    private final WorldView worldView;
    private final HudView playerHudView;
    private final CollisionEffect collisionEffect;
    @Getter
    @Setter
    private boolean hudViewActive;

    private Boolean paused;

    private String backgroundMusic;
    private String pauseMusic;

    /**
     * Creates a ParallaxView from a {@link Parallax}.
     *
     * @param parallax to be used to create the ParallaxView.
     */
    public ParallaxView(Parallax parallax, boolean isVr, boolean particlesEnabled) {
        Renderer3D.initialize(
                parallax.getCamera().getFov(),
                Gdx.graphics.getWidth(),
                Gdx.graphics.getHeight(),
                isVr,
                particlesEnabled
        );
        this.paused = parallax.isPaused();
        this.parallax = parallax;
        this.playerHudView = new HudView(parallax.getPlayer());
        this.worldView = new WorldView(parallax.getWorld());
        this.hudViewActive = false;
        this.collisionEffect = new CollisionEffect();

        startBackgroundMusic();
    }

    public void render() {
        checkPaused();
        Renderer3D.getInstance().setCameraPosition(
                this.parallax.getCamera().getPos().getX(),
                this.parallax.getCamera().getPos().getY(),
                this.parallax.getCamera().getPos().getZ()
        );
        this.worldView.render();
        if (this.hudViewActive) {
            this.playerHudView.render();
        }

        this.collisionEffect.render();
        Renderer3D.getInstance().renderFrame();
    }

    private void checkPaused() {
        if (this.parallax.isPaused() != this.paused) {
            this.paused = this.parallax.isPaused();
            changeMainMusic();
        }
    }

    private void changeMainMusic() {
        if (this.parallax.isPaused()) {
            Sound.getInstance().pauseActiveMusic(this.backgroundMusic);

            final int randomSong = RAND.nextInt(SPANISH_FLEA_ODDS) + 1;

            if (randomSong == 1) {
                this.pauseMusic = "sounds/music/spanishFlea.mp3";
                Sound.getInstance().playMusic(MUSIC_DIRECTORY + "spanishFlea.mp3", SPANISH_VOLUME);
            } else {
                this.pauseMusic = "sounds/music/spaceInTime.mp3";
                Sound.getInstance().playMusic(MUSIC_DIRECTORY + "spaceInTime.mp3", SPACE_VOLUME);
            }
        } else {
            Sound.getInstance().unPauseActiveMusic(this.backgroundMusic);
            Sound.getInstance().stopActiveMusic(this.pauseMusic);
        }
    }

    private void startBackgroundMusic() {

        final int randomSong = RAND.nextInt(SECRET_TRACK_ODDS) + 1;

        if (randomSong == 1) {
            this.backgroundMusic = "sounds/music/secretTrack.mp3";
            Sound.getInstance().playMusic(this.backgroundMusic);
        } else if (randomSong == 2) {
            this.backgroundMusic = "sounds/music/trippingBalls.mp3";
            Sound.getInstance().playMusic(this.backgroundMusic, BALLS_VOLUME);
        } else {
            /*
            Dj Smack's Youtube: http://www.youtube.com/Djsmack100
            Dj Smack's Soundcloud: http://soundcloud.com/dj-smack-1
            Dj Smack's Facebook: http://www.facebook.com/pages/Dj-Smac...
             */
            this.backgroundMusic = "sounds/music/soundsGreat.mp3";
            Sound.getInstance().playMusic(this.backgroundMusic, SOUNDS_GREAT_VOLUME);
        }
    }

    public void setWidth(int width) {
        Renderer3D.getInstance().setWidth(width);
    }

    public void setHeight(int height) {
        Renderer3D.getInstance().setHeight(height);
    }
}
