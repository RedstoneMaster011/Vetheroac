package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.boss.WitherEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class HoleTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public HoleTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.HOLE_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, HoleTntBlockEntity entity) {
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

    private void explode(World world, BlockPos startPos) {
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        world.removeBlock(pos, false);

        BlockPos.Mutable currentPos = new BlockPos.Mutable(startPos.getX(), startPos.getY(), startPos.getZ());

        while (currentPos.getY() > 0) {
            Block block = world.getBlockState(currentPos).getBlock();
            if (block == Blocks.BEDROCK) break;

            if ((startPos.getY() - currentPos.getY()) % 3 == 0) {

                TntEntity tnt = new TntEntity(world, currentPos.getX() + 0.5, currentPos.getY(), currentPos.getZ() + 0.5, null);
                tnt.setFuse(1);
                world.spawnEntity(tnt);
            }

            currentPos.move(0, -1, 0);
        }
    }
}