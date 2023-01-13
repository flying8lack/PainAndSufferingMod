package com.flying_8lack.painmod.client;

import com.flying_8lack.painmod.PainMod;
import com.flying_8lack.painmod.keys.keyBind;
import com.flying_8lack.painmod.network.ModMessages;
import com.flying_8lack.painmod.network.packet.PointsC2SPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.InputEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = PainMod.MOD_ID, value = Dist.CLIENT)
public class ClientEvent {

    @SubscribeEvent
    public static void KeyBind(InputEvent.KeyInputEvent event){
        if(keyBind.test_bind.consumeClick()){
            ModMessages.sendToServer(new PointsC2SPacket());
        }
    }

}
