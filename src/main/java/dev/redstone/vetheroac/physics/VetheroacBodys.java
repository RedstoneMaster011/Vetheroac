package dev.redstone.vetheroac.physics;

import dev.redstone.vetheroac.Vetheroac;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.util.Identifier;
import net.xmx.velthoric.physics.body.registry.VxBodyRegistry;
import net.xmx.velthoric.physics.body.registry.VxBodyType;

public class VetheroacBodys {

    public static final VxBodyType<BlockBody> Block = VxBodyType.Builder
            .<BlockBody>create(BlockBody::new)
            .build(new Identifier(Vetheroac.MOD_ID, "block"));

    public static void register() {

        VxBodyRegistry.getInstance().register(Block);
    }

    @Environment(EnvType.CLIENT)
    public static void registerClient() {
        var registry = VxBodyRegistry.getInstance();

        registry.registerClientFactory(Block.getTypeId(),
                (type, id) -> new BlockBody((VxBodyType<BlockBody>) type, id));

        registry.registerClientRenderer(Block.getTypeId(), new BlockBodyRenderer());
    }
}