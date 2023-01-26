package com.flying_8lack.painmod.Entity.Client;

import com.flying_8lack.painmod.Entity.AlienEntity;
import com.flying_8lack.painmod.Entity.Rocket;
import com.flying_8lack.painmod.PainMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class AlienModel extends AnimatedGeoModel<AlienEntity> {

    @Override
    public ResourceLocation getModelLocation(AlienEntity object) {
        return new ResourceLocation(PainMod.MOD_ID, "geo/alien.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(AlienEntity object) {
        return new ResourceLocation(PainMod.MOD_ID, "textures/entity/alien.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(AlienEntity animatable) {
        return new ResourceLocation(PainMod.MOD_ID, "animations/rocket.animation.json");
    }
}
