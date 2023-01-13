package com.flying_8lack.painmod.items.tools;

import com.flying_8lack.painmod.items.ModTier;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.OreBlock;
import net.minecraft.world.level.block.state.BlockState;

public class DarkPickAxeItem extends PickaxeItem {
    public DarkPickAxeItem(Properties pProperties) {
        super(ModTier.DARK, 2, 0.2f, pProperties);
        pProperties.stacksTo(1);

    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {


        for(int x = 0; x < 8; x++){

            BlockPos pos = new BlockPos(pPlayer.getLookAngle().scale((double)x));
            BlockState state = pLevel.getBlockState(pos);


            if(state.getBlock() == Blocks.STONE){
                pLevel.destroyBlock(pos,true);
                pLevel.destroyBlock(pos.below(),true);
            }
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
