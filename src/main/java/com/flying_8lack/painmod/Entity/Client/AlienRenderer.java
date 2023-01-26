package com.flying_8lack.painmod.Entity.Client;

import com.flying_8lack.painmod.Entity.AlienEntity;
import com.flying_8lack.painmod.PainMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class AlienRenderer extends GeoEntityRenderer<AlienEntity> {
    public AlienRenderer(EntityRendererProvider.Context renderManager){//, AnimatedGeoModel<ThiefEntity> modelProvider) {
        super(renderManager, new AlienModel());
        this.shadowRadius = 0.8f;
    }

    @Override
    public ResourceLocation getTextureLocation(AlienEntity animatable) {
        return new ResourceLocation(PainMod.MOD_ID, "textures/entity/alien.png");

    }



    @Override
    public RenderType getRenderType(AlienEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        //poseStack.scale(1.0f, 1.0f, 1.0f);

        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
