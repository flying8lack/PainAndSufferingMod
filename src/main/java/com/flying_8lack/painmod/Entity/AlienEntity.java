package com.flying_8lack.painmod.Entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.RangedAttackMob;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.level.Level;

public class AlienEntity extends Monster implements RangedAttackMob {

    protected AlienEntity(EntityType<? extends Monster> pEntityType, Level pLevel) {
        super(pEntityType, pLevel);
    }

    @Override
    public void performRangedAttack(LivingEntity pTarget, float pVelocity) {

    }
}
