package dev.redstone.vetheroac.block.custom;

import dev.redstone.vetheroac.block.VetheroacBlockEntities;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import net.minecraft.block.BlockState;
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

public class NightTntBlockEntity extends BlockEntity {
    private boolean triggered = false;
    private int ticks = 0;

    public NightTntBlockEntity(BlockPos pos, BlockState state) {
        super(VetheroacBlockEntities.NIGHT_TNT_BLOCK_ENTITY, pos, state);
    }

    public static void tick(World world, BlockPos pos, BlockState state, NightTntBlockEntity entity) {
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

        if (world instanceof ServerWorld serverWorld) {
            MinecraftServer server = serverWorld.getServer();

            ServerCommandSource source = new ServerCommandSource(
                    CommandOutput.DUMMY,
                    Vec3d.ofCenter(pos),
                    Vec2f.ZERO,
                    serverWorld,
                    2,
                    "explosion_trigger",
                    Text.literal("explosion_trigger"),
                    server,
                    null
            );

            server.getCommandManager().executeWithPrefix(source, "/time set " + VetheroacConfigs.VetheroacConfig.NightTntSection.Time);
        }
    }
}