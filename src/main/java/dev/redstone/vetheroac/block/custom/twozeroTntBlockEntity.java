package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class twozeroTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public twozeroTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.TWOZERO_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, twozeroTntBlockEntity entity) {
        if (!entity.triggered) return;

        entity.ticks++;
        if (entity.ticks >= 60) {
            entity.explode(world, pos);
            entity.triggered = false;
            entity.ticks = 0;
        }
    }

    public void trigger() {
        this.triggered = true;
        this.ticks = 0;
    }

    private void explode(World world, BlockPos pos) {
        world.removeBlock(pos, false);

        MinecraftServer server = world.getServer();
        assert server != null;
        ServerCommandSource source = server.getCommandSource();
        String command = "/tellraw @a \"[Vetheroac] §l§cWARNING: The TNT '20x TNT' has been lit, the server may lag quite a bit until it has finished and low fps after\"";
        server.getCommandManager().executeWithPrefix(source, command);

        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 10; y++) {
                for (int z = 0; z < 10; z++) {
                    TntEntity tnt = new TntEntity(world, pos.getX() + 0.5 + x, pos.getY() + y - 5, pos.getZ() + 0.5 + z, null);
                    tnt.setFuse(1);
                    world.spawnEntity(tnt);
                }
            }
        }
    }
}