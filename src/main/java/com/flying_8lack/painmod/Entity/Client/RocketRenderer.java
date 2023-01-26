package com.flying_8lack.painmod.Entity.Client;

import com.flying_8lack.painmod.Entity.Rocket;
import com.flying_8lack.painmod.PainMod;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;
import software.bernie.geckolib3.renderers.geo.GeoProjectilesRenderer;

public class RocketRenderer extends GeoProjectilesRenderer<Rocket> {
    public RocketRenderer(EntityRendererProvider.Context renderManager) {
        super(renderManager, new RocketModel());
        this.shadowRadius = 0.2f;

    }



    @Override
    public ResourceLocation getTextureLocation(Rocket animatable) {
        return new ResourceLocation(PainMod.MOD_ID, "textures/entity/rocket.png");
    }
}
