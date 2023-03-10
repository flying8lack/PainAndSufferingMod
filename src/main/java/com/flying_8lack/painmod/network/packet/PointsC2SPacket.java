package com.flying_8lack.painmod.network.packet;

import com.flying_8lack.painmod.network.ModMessages;
import com.flying_8lack.painmod.util.PainCapabilityProvider;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PointsC2SPacket {

    public PointsC2SPacket() {

    }

    public PointsC2SPacket(FriendlyByteBuf buf) {

    }

    public void toByte(FriendlyByteBuf buf){

    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE IS SERVER
            ServerPlayer player = context.getSender();

            player.getCapability(PainCapabilityProvider.PAIN).ifPresent(pain -> {
                ModMessages.sendToPlayer(new PointsS2CPacket(pain.getPainPoint()), player);
            });



        });
        return true;

    }


}
