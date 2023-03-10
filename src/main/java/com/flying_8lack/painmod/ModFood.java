package com.flying_8lack.painmod;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFood {
    public static final FoodProperties DARK_APPLE = (new FoodProperties.Builder()).nutrition(2)
            .saturationMod(0.3F)

            .effect(() -> (new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 1)), 0.8f)
            .effect(() ->( new MobEffectInstance(MobEffects.BLINDNESS, 200, 5)), 1.0f)
            .fast().alwaysEat()
            .build();

}
