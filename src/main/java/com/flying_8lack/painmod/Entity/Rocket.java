package com.flying_8lack.painmod.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;

public class Rocket extends Arrow {

    private BlockPos pos;
    private final float SPEED = 5.0f;
    public Rocket(Level pLevel, double pX, double pY, double pZ) {
        super(pLevel, pX, pY, pZ);
        this.pos = new BlockPos(pX, pY, pZ);
    }

    @Override
    public void tick() {
        super.tick();
        Vec3 direction = new Vec3(this.getX() - this.pos.getX(),
                this.getY() - this.pos.getY(),
                this.getZ() - this.pos.getZ()).normalize().scale(this.SPEED);
        double HorzDist = this.getDeltaMovement().horizontalDistance();
        float roty = (float)(Mth.atan2(this.getY() - this.pos.getY(), HorzDist) * 180.0/Mth.PI);
        float rotx = (float)(Mth.atan2(this.getX() - this.pos.getX(),
                this.getZ() - this.pos.getZ()) * 180.0/Mth.PI);
        this.setRot(roty, rotx);
        this.setDeltaMovement(direction);
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(!this.level.isClientSide()){
            this.discard();
            this.level.explode(this, pResult.getBlockPos().getX(),
                    pResult.getBlockPos().getY(), pResult.getBlockPos().getZ(),
                    4.0f, Explosion.BlockInteraction.DESTROY);
        }
    }
}
