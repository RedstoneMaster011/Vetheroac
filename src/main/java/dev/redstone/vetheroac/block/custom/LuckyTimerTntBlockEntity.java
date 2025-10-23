package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class LuckyTimerTntBlockEntity extends BlockEntity {
    private int randomOffset = -1;
    private boolean triggered = false;
    private int ticks = 0;

    public LuckyTimerTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.LUCKY_TIMER_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, LuckyTimerTntBlockEntity entity) {
        if (!entity.triggered) return;

        entity.ticks++;
        if (entity.randomOffset == -1) {
            entity.randomOffset = world.random.nextInt(VetheroacConfigs.VetheroacConfig.LuckyTimerTntSection.RandomTime + 1);
        }

        if (entity.ticks >= entity.randomOffset) {
            entity.explode(world, pos);
            entity.triggered = false;
            entity.ticks = 0;
            entity.randomOffset = -1;
        }
    }

    public void trigger() {
        this.triggered = true;
        this.ticks = 0;
        this.randomOffset = -1;
    }

    private void explode(World world, BlockPos pos) {
        world.playSound(null, pos, SoundEvents.ENTITY_GENERIC_EXPLODE, SoundCategory.BLOCKS, 1.0f, 1.0f);
        world.removeBlock(pos, false);

        TntEntity tnt = new TntEntity(world, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, null);
        tnt.setFuse(1);
        world.spawnEntity(tnt);
    }
}