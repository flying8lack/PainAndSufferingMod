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
    private final int max_time = 20*8;
    private int life_time = 0;
    private final float SPEED = 2.0f;
    public Rocket(Level pLevel, double pX, double pY, double pZ, BlockPos Target) {
        super(pLevel, pX, pY, pZ);
        this.pos = Target;

    }

    @Override
    protected void tickDespawn() {
        this.life_time++;
        if(this.life_time > this.max_time){
            this.discard();
        }
        //super.tickDespawn();
    }

    @Override
    public void tick() {
        //super.tick();
        this.tickDespawn();

        Vec3 direction = new Vec3(this.getX() - this.pos.getX(),
                this.getY() - this.pos.getY(),
                this.getZ() - this.pos.getZ()).normalize();
        double HorzDist = this.getDeltaMovement().horizontalDistance();
        float roty = (float)(Mth.atan2(direction.y, HorzDist) * 180.0/Mth.PI);
        float rotx = (float)(Mth.atan2(direction.x,
                direction.z) * 180.0/Mth.PI);
        this.setRot(roty, rotx);
        this.setDeltaMovement(direction.scale(this.SPEED));
    }

    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        super.onHitBlock(pResult);
        if(!this.level.isClientSide()){
            this.discard();
            this.level.explode(this, pResult.getBlockPos().getX(),
                    pResult.getBlockPos().getY(), pResult.getBlockPos().getZ(),
                    5.0f, Explosion.BlockInteraction.DESTROY);
        }
    }
}
