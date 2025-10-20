package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.TntEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class PowerfulTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public PowerfulTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.POWERFUL_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, PowerfulTntBlockEntity entity) {
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
        for (int x = 0; x < VetheroacConfigs.VetheroacConfig.PowerfulTntSection.PowerfulTntPower.get(); x++) {
            for (int y = 0; y < VetheroacConfigs.VetheroacConfig.PowerfulTntSection.PowerfulTntPower.get(); y++) {
                for (int z = 0; z < VetheroacConfigs.VetheroacConfig.PowerfulTntSection.PowerfulTntPower.get(); z++) {
                    TntEntity tnt = new TntEntity(world, pos.getX() + 0.5 + x, pos.getY() + y - 5, pos.getZ() + 0.5 + z, null);
                    tnt.setFuse(1);
                    world.spawnEntity(tnt);
                }
            }
        }
    }
}