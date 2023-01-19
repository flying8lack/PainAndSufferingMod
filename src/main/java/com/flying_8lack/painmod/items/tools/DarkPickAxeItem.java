package com.flying_8lack.painmod.items.tools;

import com.flying_8lack.painmod.items.ModTier;
import net.minecraft.world.item.PickaxeItem;

public class DarkPickAxeItem extends PickaxeItem {
    public DarkPickAxeItem(Properties pProperties) {
        super(ModTier.DARK, 2, 0.2f, pProperties);


    }


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
    }*/
}
