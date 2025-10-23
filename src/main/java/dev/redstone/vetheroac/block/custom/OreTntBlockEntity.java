package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class OreTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public OreTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.ORE_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, OreTntBlockEntity entity) {
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

        for (int dx = -VetheroacConfigs.VetheroacConfig.OreTntSection.OreSize.get(); dx <= VetheroacConfigs.VetheroacConfig.OreTntSection.OreSize.get() / 2; dx++) {
            for (int dy = -VetheroacConfigs.VetheroacConfig.OreTntSection.OreSize.get(); dy <= VetheroacConfigs.VetheroacConfig.OreTntSection.OreSize.get() / 2; dy++) {
                for (int dz = -VetheroacConfigs.VetheroacConfig.OreTntSection.OreSize.get(); dz <= VetheroacConfigs.VetheroacConfig.OreTntSection.OreSize.get() / 2; dz++) {

                    Random random = new Random();

                    Block[] concreteBlocks = new Block[] {
                            Blocks.COPPER_ORE, Blocks.REDSTONE_ORE, Blocks.IRON_ORE, Blocks.NETHER_QUARTZ_ORE,
                            Blocks.DEEPSLATE_COPPER_ORE, Blocks.GRAY_CONCRETE, Blocks.DIAMOND_ORE,
                            Blocks.DEEPSLATE_DIAMOND_ORE, Blocks.DEEPSLATE_GOLD_ORE, Blocks.GOLD_ORE,
                            Blocks.DEEPSLATE_REDSTONE_ORE, Blocks.DEEPSLATE_IRON_ORE, Blocks.NETHER_GOLD_ORE
                    };
                    Block randomConcrete = concreteBlocks[random.nextInt(concreteBlocks.length)];


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