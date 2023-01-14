package com.flying_8lack.painmod.Entity.Client;

import com.flying_8lack.painmod.Entity.ThiefEntity;
import com.flying_8lack.painmod.PainMod;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;

public class ThiefModel extends AnimatedGeoModel<ThiefEntity> {
    @Override
    public ResourceLocation getModelLocation(ThiefEntity object) {
        return new ResourceLocation(PainMod.MOD_ID, "geo/thief.geo.json");
    }

    @Override
    public ResourceLocation getTextureLocation(ThiefEntity object) {
        return new ResourceLocation(PainMod.MOD_ID, "textures/entity/thief.png");
    }

    @Override
    public ResourceLocation getAnimationFileLocation(ThiefEntity animatable) {
        return new ResourceLocation(PainMod.MOD_ID, "animations/thief.animation.json");
    }
}
