package com.flying_8lack.painmod.items;

import com.flying_8lack.painmod.ModItem;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

public class ModTier {

    public static final ForgeTier DARK = new ForgeTier(2,900,1.8f,
            3.0f, 16, BlockTags.NEEDS_DIAMOND_TOOL,
            () -> Ingredient.of(ModItem.DARK_INGOT.get()));
}
