package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class SplitTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public SplitTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.SPLIT_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, SplitTntBlockEntity entity) {
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

        Vec3d[] directions = {
                new Vec3d(0, 0.8, -0.5),
                new Vec3d(0, 0.8, 0.5),
                new Vec3d(-0.5, 0.8, 0),
                new Vec3d(0.5, 0.8, 0)
        };

        for (Vec3d dir : directions) {
            TntEntity tnt = new TntEntity(world, pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5, null);
            tnt.setFuse(80);
            tnt.setVelocity(dir);
            world.spawnEntity(tnt);
        }
    }
}