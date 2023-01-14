package com.flying_8lack.painmod.Entity.Client;

import com.flying_8lack.painmod.Entity.ThiefEntity;
import com.flying_8lack.painmod.PainMod;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.resources.ResourceLocation;
import org.jetbrains.annotations.Nullable;
import software.bernie.geckolib3.model.AnimatedGeoModel;
import software.bernie.geckolib3.renderers.geo.GeoEntityRenderer;

public class ThiefRenderer extends GeoEntityRenderer<ThiefEntity> {
    public ThiefRenderer(EntityRendererProvider.Context renderManager){//, AnimatedGeoModel<ThiefEntity> modelProvider) {
        super(renderManager, new ThiefModel());
        this.shadowRadius = 0.8f;
    }

    @Override
    public ResourceLocation getTextureLocation(ThiefEntity animatable) {
        return new ResourceLocation(PainMod.MOD_ID, "textures/entity/thief.png");

    }

    @Override
    public RenderType getRenderType(ThiefEntity animatable, float partialTick, PoseStack poseStack, @Nullable MultiBufferSource bufferSource, @Nullable VertexConsumer buffer, int packedLight, ResourceLocation texture) {
        poseStack.scale(1.0f, 1.0f, 1.0f);
        return super.getRenderType(animatable, partialTick, poseStack, bufferSource, buffer, packedLight, texture);
    }
}
