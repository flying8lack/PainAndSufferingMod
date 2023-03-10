package com.flying_8lack.painmod;

import com.flying_8lack.painmod.items.ModTier;
import com.flying_8lack.painmod.items.hateFangItem;
import com.flying_8lack.painmod.items.loveFangItem;
import com.flying_8lack.painmod.items.tools.DarkAxeItem;
import com.flying_8lack.painmod.items.tools.DarkPickAxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, PainMod.MOD_ID);

    public static final RegistryObject<Item> DARK_APPLE = ITEMS.register("dark_apple",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.PAIN_TAB).food(ModFood.DARK_APPLE)));

    public static final RegistryObject<Item> DARK_INGOT = ITEMS.register("dark_ingot",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.PAIN_TAB).stacksTo(64)));


    public static final RegistryObject<Item> DARK_AXE = ITEMS.register("dark_axe",
            () -> new DarkAxeItem(ModTier.DARK,
                    3.0f, 1.8f,
                    new Item.Properties().tab(ModCreativeTab.PAIN_TAB)
                    ));

    public static final RegistryObject<Item> DARK_PICKAXE = ITEMS.register("dark_pickaxe",
            () -> new DarkPickAxeItem(
                    new Item.Properties().tab(ModCreativeTab.PAIN_TAB)));

    public static final RegistryObject<Item> DARK_SWORD = ITEMS.register("dark_sword",
            () -> new DarkPickAxeItem(
                    new Item.Properties().tab(ModCreativeTab.PAIN_TAB)));


    public static final RegistryObject<Item> LOVE_FANG = ITEMS.register("love_fang",
            () -> new loveFangItem(new Item.Properties().tab(ModCreativeTab.PAIN_TAB)));

    public static final RegistryObject<Item> HATE_FANG = ITEMS.register("hate_fang",
            () -> new hateFangItem(new Item.Properties().tab(ModCreativeTab.PAIN_TAB)));

    public static final RegistryObject<Item> THIEF_SPAWN_EGG = ITEMS.register("theif_spawn_egg",
            () -> new ForgeSpawnEggItem(ModEntity.THIEF,0xe3e3e3, 0x030303,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));

    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}
