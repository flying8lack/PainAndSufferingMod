package com.flying_8lack.boonmod.keys;

import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraftforge.client.settings.KeyConflictContext;

public class keyBind {



    public static final KeyMapping test_bind = new KeyMapping("Spawn Cow",KeyConflictContext.UNIVERSAL,
                   InputConstants.Type.KEYSYM,
            InputConstants.KEY_K, "key.categories.test");
}
