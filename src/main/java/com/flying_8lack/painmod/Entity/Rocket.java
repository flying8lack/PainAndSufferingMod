package com.flying_8lack.painmod.Entity;

import net.minecraft.core.BlockPos;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MoverType;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import net.minecraft.world.phys.Vec3;
import software.bernie.geckolib3.core.IAnimatable;
import software.bernie.geckolib3.core.PlayState;
import software.bernie.geckolib3.core.controller.AnimationController;
import software.bernie.geckolib3.core.event.predicate.AnimationEvent;
import software.bernie.geckolib3.core.manager.AnimationData;
import software.bernie.geckolib3.core.manager.AnimationFactory;
import software.bernie.geckolib3.util.GeckoLibUtil;

public class Rocket extends Projectile implements IAnimatable {


    public AnimationFactory factory = GeckoLibUtil.createFactory(this);
    private BlockPos pos = BlockPos.ZERO;
    private final int max_time = 20*8;
    private int life_time = 0;
    private final float SPEED = 5.0f;
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

    public static void rotateTowardsMovement(Entity pProjectile, float pRotationSpeed) {
        Vec3 vec3 = pProjectile.getDeltaMovement();
        if (vec3.lengthSqr() != 0.0D) {
            double d0 = vec3.horizontalDistance();
            pProjectile.setYRot((float)(Mth.atan2(vec3.z, vec3.x) * (double)(180F / (float)Math.PI)) + 90.0F);
            pProjectile.setXRot((float)(Mth.atan2(d0, vec3.y) * (double)(180F / (float)Math.PI)) - 90.0F);

            while(pProjectile.getXRot() - pProjectile.xRotO < -180.0F) {
                pProjectile.xRotO -= 360.0F;
            }

            while(pProjectile.getXRot() - pProjectile.xRotO >= 180.0F) {
                pProjectile.xRotO += 360.0F;
            }

            while(pProjectile.getYRot() - pProjectile.yRotO < -180.0F) {
                pProjectile.yRotO -= 360.0F;
            }

            while(pProjectile.getYRot() - pProjectile.yRotO >= 180.0F) {
                pProjectile.yRotO += 360.0F;
            }

            pProjectile.setXRot(Mth.lerp(pRotationSpeed, pProjectile.xRotO, pProjectile.getXRot()));
            pProjectile.setYRot(Mth.lerp(pRotationSpeed, pProjectile.yRotO, pProjectile.getYRot()));
        }
    }

    @Override
    public void tick() {
        //super.tick();




        Vec3 direction = new Vec3(this.getX() - this.pos.getX(),
                this.getY() - this.pos.getY(),
                this.getZ() - this.pos.getZ()).normalize().reverse();
        /*double HorzDist = direction.horizontalDistance();
        float roty = (float)(Mth.atan2(direction.y, HorzDist) * 180.0/Mth.PI);
        float rotx = (float)(Mth.atan2(direction.x,
                direction.z) * 180.0/Mth.PI);*/


        //current_speed *= 0.99; //reduce the speed by 1% every tick



        this.setDeltaMovement(
                direction.scale(this.current_speed/20));

        if(!this.isNoGravity()){
            this.setDeltaMovement(this.getDeltaMovement().add(0, -0.05, 0));
        }
        rotateTowardsMovement(this, 0.2f);
        this.move(MoverType.SELF, this.getDeltaMovement());


        //this.setRot(roty, rotx);

        this.tickDespawn();
        /*if(current_speed <= 0.81f){
            Explode();
        }*/



        BlockHitResult pResult = this.getLevel().clip(new ClipContext(
                this.position(), this.position().add(direction), ClipContext.Block.COLLIDER,
                ClipContext.Fluid.NONE,null));
        if (pResult.getType() == HitResult.Type.ENTITY ||
                pResult.getType() == HitResult.Type.BLOCK) {

            Explode();
        }

    }

    public <E extends IAnimatable> PlayState predicate(AnimationEvent event){
        /*event.getController().setAnimation(new AnimationBuilder().addAnimation(
                "animation.model.idle",
                ILoopType.EDefaultLoopTypes.LOOP));*/

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
