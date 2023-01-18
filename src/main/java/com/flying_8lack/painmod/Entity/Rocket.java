package com.flying_8lack.painmod.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.builder.AnimationBuilder;
import software.bernie.geckolib3.core.builder.ILoopType;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

import javax.annotation.Nullable;

public class Rocket extends Projectile implements IAnimatable {


    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private BlockPos pos;
    private final int max_time = 20*8;
    private int life_time = 0;
    private final float SPEED = 2.0f;
    private float current_speed = SPEED;

    public Rocket(EntityType<? extends Projectile> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);

    }

    public void setTargetPos(BlockPos position){
        this.pos = position;
    }



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

    public static float lerpR(float pCurrentRotation, float pTargetRotation) {
        while(pTargetRotation - pCurrentRotation < -180.0F) {
            pCurrentRotation -= 360.0F;
        }

        while(pTargetRotation - pCurrentRotation >= 180.0F) {
            pCurrentRotation += 360.0F;
        }

        return Mth.lerp(0.95F, pCurrentRotation, pTargetRotation);
    }


    @Override
    protected void defineSynchedData() {

    }

    @Override
    public void tick() {
        //super.tick();



        Vec3 direction = new Vec3(this.getX() - this.pos.getX(),
                this.getY() - this.pos.getY(),
                this.getZ() - this.pos.getZ()).normalize().reverse();
        double HorzDist = direction.horizontalDistance();
        float roty = (float)(Mth.atan2(direction.y, HorzDist) * 180.0/Mth.PI);
        float rotx = (float)(Mth.atan2(direction.x,
                direction.z) * 180.0/Mth.PI);


        //current_speed *= 0.995; //reduce the speed by 0.5% every tick


        this.setRot(roty, rotx);
        this.setDeltaMovement(this.getDeltaMovement().lerp(
                direction.scale(this.current_speed/20),0.95));

        /*if(!this.isNoGravity()){
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.2/20, 0));
        }*/

        this.tickDespawn();
        if(current_speed <= 0.81f){
            Explode();
        }



        BlockHitResult pResult = this.getLevel().clip(new ClipContext(
                this.position(), this.position().add(direction), ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,null));
        if(!this.level.isClientSide()){
            if(pResult.getType() == HitResult.Type.ENTITY ||
                    pResult.getType() == HitResult.Type.BLOCK) {

                Explode();
            }

        }

    }

    public <E extends IAnimatable> PlayState predicate(AnimationEvent event){
        event.getController().setAnimation(new AnimationBuilder().addAnimation("normal",
                ILoopType.EDefaultLoopTypes.LOOP));

        return PlayState.CONTINUE;
    }


    @Override
    public void registerControllers(AnimationData data) {
        data.addAnimationController(new AnimationController(this, "controller",
                0, this::predicate));

    }

    @Override
    public AnimationFactory getFactory() {
        return this.factory;
    }
}
