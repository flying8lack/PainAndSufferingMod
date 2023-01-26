package com.flying_8lack.painmod.items.tools;

import com.flying_8lack.painmod.util.capabilities.PainCapabilityProvider;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;

import java.util.concurrent.atomic.AtomicBoolean;

public class DarkAxeItem extends AxeItem {
    public DarkAxeItem(Tier pTier, float pAttackDamageModifier, float pAttackSpeedModifier, Properties pProperties) {
        super(pTier, pAttackDamageModifier, pAttackSpeedModifier, pProperties);





    }


    @Override
    public boolean onLeftClickEntity(ItemStack stack, Player player, Entity entity) {


        AtomicBoolean f = new AtomicBoolean(false);
        //Apply special damage to entity if 40% of its health is larger than one
        //otherwise, apply normal action
        if(!player.getLevel().isClientSide()) {
            if (entity instanceof LivingEntity) {
                player.getCapability(PainCapabilityProvider.PAIN).ifPresent(m -> {

                    if(m.canAfford(20) &&
                    ((LivingEntity) entity).getHealth() * 0.4f > 1.0f) {
                        f.set(true);

                        entity.hurt(DamageSource.playerAttack(player),
                                ((LivingEntity) entity).getHealth() * 0.4f);
                        //Apply damage to entity equal to its 40% of current health


                        player.addEffect(new MobEffectInstance(
                                MobEffects.MOVEMENT_SLOWDOWN, 20, 16, true, false
                        )); //adds slowness to player for 1 seconds

                        m.subPainPoint(20);

                    } else {
                        f.set(false);
                    }
                });

                    return (f.get() || super.onLeftClickEntity(stack, player, entity));

            }
        }

        return super.onLeftClickEntity(stack, player, entity);
    }
}
