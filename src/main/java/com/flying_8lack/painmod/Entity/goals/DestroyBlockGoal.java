package com.flying_8lack.painmod.Entity.goals;

import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.goal.Goal;

public class DestroyBlockGoal extends Goal {

    private LivingEntity entity;
    private BlockPos pos;
    @Override
    public boolean canUse() {
        return false;
    }

    @Override
    public void start() {
        super.start();
    }
}
