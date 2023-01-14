package com.flying_8lack.painmod.items.tools;

import com.flying_8lack.painmod.items.ModTier;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;

public class DarkSwordItem extends SwordItem {
    public DarkSwordItem(Properties pProperties) {
        super(ModTier.DARK, 7, 1.5f, pProperties);
        pProperties.stacksTo(1);
    }
}
