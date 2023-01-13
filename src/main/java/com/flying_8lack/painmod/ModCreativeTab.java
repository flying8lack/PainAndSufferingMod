package com.flying_8lack.painmod;

import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import org.jetbrains.annotations.NotNull;

public class ModCreativeTab {
    public static final CreativeModeTab PAIN_TAB = new CreativeModeTab("paintab") {
        @Override
        public @NotNull ItemStack makeIcon() {
            return new ItemStack(ModItem.DARK_INGOT.get());
        }


    };
}
