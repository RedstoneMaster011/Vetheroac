package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;

public class ChunkTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public ChunkTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.CHUNK_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ChunkTntBlockEntity entity) {
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
        Chunk chunk = world.getChunk(pos);
        BlockPos origin = chunk.getPos().getStartPos();
        for (int x = 0; x < 16; x++) {
            for (int y = world.getBottomY(); y < world.getTopY(); y++) {
                for (int z = 0; z < 16; z++) {
                    world.removeBlock(origin.add(x, y, z), false);
                }
            }
        }
    }
}