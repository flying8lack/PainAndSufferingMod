package com.flying_8lack.boonmod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTab {
    public static final CreativeModeTab BOON_TAB = new CreativeModeTab("boontab") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItem.WUMPA.get());
        }


    };
}
