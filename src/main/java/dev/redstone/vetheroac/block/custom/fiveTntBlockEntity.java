package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class fiveTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public fiveTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.FIVE_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, fiveTntBlockEntity entity) {
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
        for (int i = 0; i < 5; i++) {
            TntEntity tnt = new TntEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ(), null);
            tnt.setFuse(1);
            world.spawnEntity(tnt);
        }
    }
}