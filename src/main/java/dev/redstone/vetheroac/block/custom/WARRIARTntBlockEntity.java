package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import dev.redstone.vetheroac.entity.VetheroacEntities;
import dev.redstone.vetheroac.entity.custom.Rex8916Entity;
import dev.redstone.vetheroac.entity.custom.WARRIAREntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class WARRIARTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public WARRIARTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.WARRIAR_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, WARRIARTntBlockEntity entity) {
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

        for (int i = 0; i < VetheroacConfigs.VetheroacConfig.WARRIARTntSection.Amount.get(); i++) {
            WARRIAREntity entity = new WARRIAREntity(VetheroacEntities.WARRIAR, world);

            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.0;
            double z = pos.getZ() + 0.5;

            entity.refreshPositionAndAngles(x, y, z, 0, 0);

            // Random launch velocity
            double vx = (world.random.nextDouble() - 0.5) * 0.8; // horizontal spread
            double vy = 0.6 + world.random.nextDouble() * 0.4;    // upward boost
            double vz = (world.random.nextDouble() - 0.5) * 0.8;

            entity.setVelocity(vx, vy, vz);
            world.spawnEntity(entity);

        }
    }
}