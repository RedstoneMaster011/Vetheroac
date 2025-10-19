package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class PaintTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public PaintTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.PAINT_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, PaintTntBlockEntity entity) {
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
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        world.removeBlock(pos, false);

        Block[] concreteBlocks = new Block[] {
                Blocks.WHITE_CONCRETE, Blocks.ORANGE_CONCRETE, Blocks.MAGENTA_CONCRETE, Blocks.LIGHT_BLUE_CONCRETE,
                Blocks.YELLOW_CONCRETE, Blocks.LIME_CONCRETE, Blocks.PINK_CONCRETE, Blocks.GRAY_CONCRETE,
                Blocks.LIGHT_GRAY_CONCRETE, Blocks.CYAN_CONCRETE, Blocks.PURPLE_CONCRETE, Blocks.BLUE_CONCRETE,
                Blocks.BROWN_CONCRETE, Blocks.GREEN_CONCRETE, Blocks.RED_CONCRETE, Blocks.BLACK_CONCRETE
        };

        Random random = new Random();
        Block randomConcrete = concreteBlocks[random.nextInt(concreteBlocks.length)];

        for (int dx = -3; dx <= 2; dx++) {
            for (int dy = -3; dy <= 2; dy++) {
                for (int dz = -3; dz <= 2; dz++) {
                    BlockPos targetPos = pos.add(dx, dy, dz);
                    BlockState currentState = world.getBlockState(targetPos);
                    if (!currentState.isAir()) {
                        world.setBlockState(targetPos, randomConcrete.getDefaultState(), 3);
                    }
                }
            }
        }
    }
}