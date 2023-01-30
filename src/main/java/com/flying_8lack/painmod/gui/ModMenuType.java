package com.flying_8lack.painmod.gui;

import com.flying_8lack.painmod.PainMod;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuType {

    public static final DeferredRegister<MenuType<?>> MENU = DeferredRegister.create(
            ForgeRegistries.CONTAINERS, PainMod.MOD_ID
    );

    public static final RegistryObject<MenuType<tradingBlockMenu>> TRADING_MENU =
            registerMenu(tradingBlockMenu::new, "trading_block_menu");

    private static <T extends AbstractContainerMenu>
    RegistryObject<MenuType<T>> registerMenu(IContainerFactory<T> factory,
                                                                  String name){
        return MENU.register(name, () -> IForgeMenuType.create(factory));
    }
    public static void register(IEventBus eventbus){
        MENU.register(eventbus);
    }
}
