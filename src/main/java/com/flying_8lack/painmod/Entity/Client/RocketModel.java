package com.flying_8lack.painmod.Entity.Client;

import com.flying_8lack.painmod.Entity.Rocket;
import com.flying_8lack.painmod.PainMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class RocketModel extends AnimatedGeoModel<Rocket> {
    @Override
    public ResourceLocation getModelLocation(Rocket object) {
        return new ResourceLocation(PainMod.MOD_ID, "geo/rocket.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(Rocket object) {
        return new ResourceLocation(PainMod.MOD_ID, "textures/entity/rocket.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(Rocket animatable) {
        return new ResourceLocation(PainMod.MOD_ID, "animations/rocket.animation.json");
    }
}
