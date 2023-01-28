package com.flying_8lack.painmod.items;

import com.flying_8lack.painmod.util.capabilities.PainCapabilityProvider;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntitySelector;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraftforge.common.extensions.IForgeItem;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class hateFangItem extends Item implements IForgeItem {
    public hateFangItem(Properties pProperties) {
        super(pProperties);


    }



    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag pIsAdvanced) {
        super.appendHoverText(pStack, pLevel, tooltip, pIsAdvanced);
        String mama = "A powerful fang that on use apply damage (20 Hp) to all entities" +
                " within 32 blocks radius.\nThe user recieve have half the damage dealt." +
                ChatFormatting.AQUA +
                "\n\nConsume 40 pain credits per use";



        tooltip.add(new TextComponent(mama));
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player User, InteractionHand pUsedHand) {

        User.getCapability(PainCapabilityProvider.PAIN).ifPresent(m ->{
            if(m.canAfford(40)) {
                m.subPainPoint(40);


                if (!pLevel.isClientSide()) {


                    List<Entity> M = pLevel.getEntitiesOfClass(Entity.class,
                            new AABB(User.getX() - 32, User.getY() - 10, User.getZ() - 32
                                    , User.getX() + 32, User.getY() + 10, User.getZ() + 32),
                            EntitySelector.LIVING_ENTITY_STILL_ALIVE);

                    for (Entity i : M) {
                        if (i instanceof LivingEntity && !(i instanceof Player)) {


                            i.hurt(DamageSource.playerAttack(User),
                                    20.0f);


                        }

                    }
                }
                User.hurt(DamageSource.MAGIC, 10.0f);

            }
        });
        return super.use(pLevel, User, pUsedHand);
    }
}
