package com.flying_8lack.boonmod;

import com.flying_8lack.boonmod.items.hateFangItem;
import com.flying_8lack.boonmod.items.loveFangItem;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItem {

    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, BoonMod.MOD_ID);

    public static final RegistryObject<Item> WUMPA = ITEMS.register("wumpa",
            () -> new Item(new Item.Properties().tab(ModCreativeTab.BOON_TAB).food(ModFood.WUMPA)));

    public static final RegistryObject<Item> LOVE_FANG = ITEMS.register("love_fang",
            () -> new loveFangItem(new Item.Properties().tab(ModCreativeTab.BOON_TAB)));

    public static final RegistryObject<Item> HATE_FANG = ITEMS.register("hate_fang",
            () -> new hateFangItem(new Item.Properties().tab(ModCreativeTab.BOON_TAB)));

    public static void register(IEventBus eventbus){
        ITEMS.register(eventbus);
    }
}
