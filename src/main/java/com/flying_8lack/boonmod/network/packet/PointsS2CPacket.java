package com.flying_8lack.boonmod.network.packet;

import com.flying_8lack.boonmod.client.ClientPainData;
import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobSpawnType;
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
