package com.flying_8lack.painmod.items.tools;

import com.flying_8lack.painmod.items.ModTier;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.level.Level;

public class DarkPickAxeItem extends PickaxeItem {
    public DarkPickAxeItem(Properties pProperties) {
        super(ModTier.DARK, 2, 0.2f, pProperties);


    }


    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
