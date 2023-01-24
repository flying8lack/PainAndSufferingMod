package com.flying_8lack.painmod.Entity;

import com.flying_8lack.painmod.ModEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.FloatGoal;
import net.minecraft.world.entity.ai.goal.LookAtPlayerGoal;
import net.minecraft.world.entity.ai.goal.RangedAttackGoal;
import net.minecraft.world.entity.ai.goal.WaterAvoidingRandomStrollGoal;
import net.minecraft.world.entity.ai.goal.target.NearestAttackableTargetGoal;
import net.minecraft.world.entity.animal.Cow;
import net.minecraft.world.entity.animal.IronGolem;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.NotNull;


public class AlienEntity extends Monster implements RangedAttackMob {



    public static AttributeSupplier setAttributes(){
        return Monster.createMonsterAttributes()
                .add(Attributes.MOVEMENT_SPEED, 0.3f)
                .add(Attributes.MAX_HEALTH, 25.0f)
                .add(Attributes.FOLLOW_RANGE, 30.0f)
                .add(Attributes.ATTACK_DAMAGE, 2.0f)
                .add(Attributes.ATTACK_SPEED, 1.0f)
                .build();
    }

    @Override
    protected void pickUpItem(ItemEntity pItemEntity) {
        pItemEntity.discard();
        //super.pickUpItem(pItemEntity);
    }

    @Override
    public void aiStep() {

        if(this.getTarget() == null && this.getHealth() < this.getMaxHealth()){
            this.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 40, 0));

        }

        super.aiStep();
    }

    protected AlienEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    protected void registerGoals() {
        //super.registerGoals();
        this.goalSelector.addGoal(1, new FloatGoal(this));
        this.goalSelector.addGoal(2, new RangedAttackGoal(this, 1.0D, 15, 32));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 20.0F));
        this.goalSelector.addGoal(4, new WaterAvoidingRandomStrollGoal(this, 1.0D));


        this.targetSelector.addGoal(1, new NearestAttackableTargetGoal<>(this, Player.class, true));
        this.targetSelector.addGoal(3, new NearestAttackableTargetGoal<>(this, Cow.class, true));
        this.targetSelector.addGoal(2, new NearestAttackableTargetGoal<>(this, IronGolem.class, true));
    }

    @Override
    public void performRangedAttack(@NotNull LivingEntity p, float pVelocity) {

        if(this.getTarget() != null && !this.getLevel().isClientSide()) {
            Rocket r = new Rocket(ModEntity.ROCKET.get(), p.getLevel());
            r.setTargetPos(new BlockPos(this.getTarget().getX(),
                    this.getTarget().getY(),
                    this.getTarget().getZ()));
            r.teleportTo(this.getLookAngle().x() + this.getX(),
                    this.getLookAngle().y() + this.getY(),
                    this.getLookAngle().z() + this.getZ());
            p.getLevel().addFreshEntity(r);
        }

    }
}
