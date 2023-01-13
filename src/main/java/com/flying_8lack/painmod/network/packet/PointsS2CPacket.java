package com.flying_8lack.painmod.network.packet;

import com.flying_8lack.painmod.client.ClientPainData;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraftforge.network.NetworkEvent;

import java.util.function.Supplier;

public class PointsS2CPacket {

    private int Points;
    public PointsS2CPacket(int Points) {
        this.Points = Points;
    }

    public PointsS2CPacket(FriendlyByteBuf buf) {
        this.Points = buf.readInt();
    }

    public void toByte(FriendlyByteBuf buf){
        buf.writeInt(this.Points);
    }

    public boolean handle(Supplier<NetworkEvent.Context> supplier){
        NetworkEvent.Context context = supplier.get();
        context.enqueueWork(() -> {
            //HERE IS CLIENT
            ClientPainData.setPainPoint(this.Points);
        });
        return true;

    }

}
