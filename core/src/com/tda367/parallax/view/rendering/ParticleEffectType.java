package com.tda367.parallax.view.rendering;

import lombok.Getter;

/**
 * List of different particle effects that can be used.
 */
public enum ParticleEffectType {

    EXPLOSION("particles/explosion.p"),
    BOOST_TRAIL("particles/boostone.p"),
    BOOST_TRAIL_2("particles/boosttwo.p"),
    ROCKET_TRAIL("particles/rockettrail.p"),
    ASCEND("particles/ascend.p"),
    FIRE("particles/fire.p");

    @Getter
    private final String filePath;

    ParticleEffectType(String filePath) {
        this.filePath = filePath;
    }
}
