package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.command.CommandOutput;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class XrayTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public XrayTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.XRAY_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, XrayTntBlockEntity entity) {
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

        Block[] targets = {
                Blocks.STONE,
                Blocks.GRASS_BLOCK,
                Blocks.SAND,
                Blocks.SANDSTONE,
                Blocks.DEEPSLATE,
                Blocks.DIRT,
                Blocks.ANDESITE,
                Blocks.DIORITE
        };
        int radius = VetheroacConfigs.VetheroacConfig.XrayTntSection.XraySize.get();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    double distSq = x * x + y * y + z * z;
                    if (distSq <= radius * radius) {
                        mutable.set(pos.getX() + x, pos.getY() + y, pos.getZ() + z);
                        BlockState state = world.getBlockState(mutable);
                        for (Block target : targets) {
                            if (state.isOf(target)) {
                                world.setBlockState(mutable, Blocks.GLASS.getDefaultState(), 3);
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}