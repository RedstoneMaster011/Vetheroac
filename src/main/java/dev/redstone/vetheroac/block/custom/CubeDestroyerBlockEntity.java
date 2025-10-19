package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class CubeDestroyerBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public CubeDestroyerBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.CUBE_DESTROYER_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, CubeDestroyerBlockEntity entity) {
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
        int radius = 150;
        int radiusSq = radius * radius;
        int px = pos.getX();
        int py = pos.getY();
        int pz = pos.getZ();

        MinecraftServer server = world.getServer();
        assert server != null;
        ServerCommandSource source = server.getCommandSource();
        String command = "/tellraw @a \"[Vetheroac] §l§cWARNING: The TNT 'Cube Destroyer' has been lit, the server may lag quite a bit until it has finished\"";
        server.getCommandManager().executeWithPrefix(source, command);
        TntEntity tnt = new TntEntity(world, pos.getX(), pos.getY(), pos.getZ(), null);
        tnt.setFuse(1);
        world.spawnEntity(tnt);

            for (int x = -radius; x <= radius; x++) {
                for (int y = -radius; y <= radius; y++) {
                    for (int z = -radius; z <= radius; z++) {
                        if (x * x + y * y + z * z <= radiusSq) {
                            BlockPos target = new BlockPos(px + x, py + y, pz + z);
                            BlockState state = world.getBlockState(target);
                            if (!state.isAir()) {
                                world.setBlockState(target, Blocks.AIR.getDefaultState(), 3);
                            }
                        }
                    }
                }
            }
    }
}