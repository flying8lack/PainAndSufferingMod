package com.flying_8lack.painmod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab PAIN_TAB = new CreativeModeTab("paintab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItem.DARK_INGOT.get());
        }


    };
}
