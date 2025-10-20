package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import dev.redstone.vetheroac.entity.VetheroacEntities;
import dev.redstone.vetheroac.entity.custom.Rex8916Entity;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.mob.ZombieEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class ZombieTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public ZombieTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.ZOMBIE_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, ZombieTntBlockEntity entity) {
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

        for (int i = 0; i < 15; i++) {
            ZombieEntity zombie = new ZombieEntity(EntityType.ZOMBIE, world);

            double x = pos.getX() + 0.5;
            double y = pos.getY() + 1.0;
            double z = pos.getZ() + 0.5;

            zombie.refreshPositionAndAngles(x, y, z, 0, 0);

            double vx = (world.random.nextDouble() - 0.5) * 0.8;
            double vy = 0.6 + world.random.nextDouble() * 0.4;
            double vz = (world.random.nextDouble() - 0.5) * 0.8;

            zombie.setVelocity(vx, vy, vz);
            world.spawnEntity(zombie);
        }
    }
}