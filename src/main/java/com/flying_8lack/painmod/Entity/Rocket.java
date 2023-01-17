package com.flying_8lack.painmod.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

import javax.annotation.Nullable;

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
            Explode();
        }
        //super.tickDespawn();
    }

    public void Explode(){
        this.getLevel().explode(this,
                this.getX(), this.getY(), this.getZ(), 5.0f,
                true, Explosion.BlockInteraction.DESTROY);

        this.discard();
    }



    @Override
    public void tick() {
        //super.tick();
        this.tickDespawn();

        Vec3 direction = new Vec3(this.getX() - this.pos.getX(),
                this.getY() - this.pos.getY(),
                this.getZ() - this.pos.getZ()).normalize();
        double HorzDist = direction.horizontalDistance();
        float roty = (float)(Mth.atan2(direction.y, HorzDist) * 180.0/Mth.PI);
        float rotx = (float)(Mth.atan2(direction.x,
                direction.z) * 180.0/Mth.PI);

        if(direction == Vec3.ZERO){
            Explode();
            return;
        }
        this.setRot(roty, rotx);
        this.setDeltaMovement(direction.scale(this.SPEED));

        if(!this.isNoGravity()){
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.98, 0));
        }

    }



    @Override
    protected void onHitBlock(BlockHitResult pResult) {
        //super.onHitBlock(pResult);
        if(!this.level.isClientSide()){
            Explode();

        }
    }

    @Override
    protected void onHitEntity(EntityHitResult pResult) {
        if(!this.level.isClientSide()){
            Explode();

        }
        super.onHitEntity(pResult);
    }
}
