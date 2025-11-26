package dev.redstone.vetheroac.physics;

import com.github.stephengold.joltjni.enumerate.EActivation;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.xmx.velthoric.math.VxTransform;
import net.xmx.velthoric.network.VxByteBuf;
import net.xmx.velthoric.physics.body.registry.VxBodyType;
import net.xmx.velthoric.physics.body.sync.VxDataSerializer;
import net.xmx.velthoric.physics.world.VxPhysicsWorld;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class CapeRuntime {
    public static final VxDataSerializer<Integer> INTEGER_SERIALIZER = new VxDataSerializer<>() {
        @Override
        public void write(VxByteBuf buf, Integer value) {
            buf.writeInt(value);
        }

        @Override
        public Integer read(VxByteBuf buf) {
            return buf.readInt();
        }

        @Override
        public Integer copy(Integer value) {
            return value;
        }
    };

    public static final VxBodyType<CapeSoftBody> CapeType = VxBodyType.Builder
            .<CapeSoftBody>create(CapeSoftBody::new)
            .build(new Identifier("vetheroac", "cape"));

    private static final Map<UUID, CapeSoftBody> attachedCapes = new HashMap<>();

    public static void attachCape(PlayerEntity player) {
        UUID playerId = player.getUuid();
        if (attachedCapes.containsKey(playerId)) return;

        VxPhysicsWorld vxWorld = VxPhysicsWorld.get(player.getWorld().getRegistryKey());
        if (vxWorld == null) return;

        vxWorld.execute(() -> {
            CapeSoftBody newCape = new CapeSoftBody(CapeType, vxWorld, playerId);
            newCape.setSyncData(CapeSoftBody.WIDTH_SEGMENTS, 8);
            newCape.setSyncData(CapeSoftBody.HEIGHT_SEGMENTS, 12);
            vxWorld.getBodyManager().addConstructedBody(newCape, EActivation.Activate, new VxTransform());
            attachedCapes.put(playerId, newCape);
        });
    }

    @Environment(EnvType.CLIENT)
    public static void attachClientCape(AbstractClientPlayerEntity player) {
        UUID playerId = player.getUuid();
        if (attachedCapes.containsKey(playerId)) return;

        CapeSoftBody cape = new CapeSoftBody(CapeType, playerId);
        cape.setSyncData(CapeSoftBody.WIDTH_SEGMENTS, 8);
        cape.setSyncData(CapeSoftBody.HEIGHT_SEGMENTS, 12);
        attachedCapes.put(playerId, cape);
    }

    public static boolean hasCape(UUID uuid) {
        return attachedCapes.containsKey(uuid);
    }

    @Environment(EnvType.CLIENT)
    public static UUID getCapePlayerId(CapeSoftBody cape) {
        for (Map.Entry<UUID, CapeSoftBody> entry : attachedCapes.entrySet()) {
            if (entry.getValue() == cape) {
                MinecraftClient client = MinecraftClient.getInstance();
                if (client.world != null) {
                    return entry.getKey();
                }
            }
        }
        return null;
    }
}