package com.flying_8lack.painmod.items.tools;

import com.flying_8lack.painmod.ModCreativeTab;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

public class DarkAxeItem extends AxeItem {
    public DarkAxeItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);





    }

    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {
        //Apply special damage to entity if its health is bigger than player's health
        //otherwise, apply normal action
        if(!player.getLevel().isClientSide()) {
            if (entity instanceof LivingEntity) {
                if(((LivingEntity) entity).getHealth() > player.getHealth()) {
                    entity.hurt(DamageSource.playerAttack(player),
                            ((LivingEntity) entity).getMaxHealth() * 0.2f);
                    //Apply damage to entity equal to its 20% of maximum health

                    player.addEffect(new MobEffectInstance(
                            MobEffects.MOVEMENT_SLOWDOWN,120, 16, true, false
                    )); //adds slowness to player for 6 seconds

                    return true; //do no further damage
                } else {
                    return super.onLeftClickEntity(stack, player, entity);
                }
            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }
}
