package com.flying_8lack.boonmod.client;

import com.flying_8lack.boonmod.BoonMod;
import com.flying_8lack.boonmod.keys.keyBind;
import com.flying_8lack.boonmod.network.ModMessages;
import com.flying_8lack.boonmod.network.packet.PointsC2SPacket;
import com.mojang.blaze3d.platform.InputConstants;
import net.minecraft.client.KeyMapping;
import net.minecraft.client.gui.screens.controls.KeyBindsList;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.ClientRegistry;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.client.settings.KeyBindingMap;
import net.minecraftforge.client.settings.KeyConflictContext;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = BoonMod.MOD_ID, value = Dist.CLIENT)
public class ClientEvent {

    @SubscribeEvent
    public static void KeyBind(InputEvent.KeyInputEvent event){
        if(keyBind.test_bind.consumeClick()){
            ModMessages.sendToServer(new PointsC2SPacket());
        }
    }

}
