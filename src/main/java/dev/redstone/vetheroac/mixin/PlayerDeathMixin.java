package dev.redstone.vetheroac.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.server.command.ServerCommandSource;

import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayerEntity.class)
public abstract class PlayerDeathMixin {
    @Inject(method = "onDeath", at = @At("TAIL"))
    private void onPlayerDeath(DamageSource source, CallbackInfo ci) {
        ServerPlayerEntity player = (ServerPlayerEntity)(Object)this;
        ServerWorld world = player.getServerWorld();

        BlockPos basePos = player.getBlockPos();
        Vec3d spawnPos = new Vec3d(
                basePos.getX() + 0.5,
                basePos.getY() + 1.2,
                basePos.getZ() + 0.5
        );
        BlockPos barrierPos = new BlockPos(
                (int)spawnPos.x,
                (int)spawnPos.y,
                (int)spawnPos.z
        );

        String username = player.getEntityName();
        String command = String.format("/vxtest spawnRagdoll %s %.2f %.2f %.2f",
                username, spawnPos.x, spawnPos.y, spawnPos.z);

        ServerCommandSource cmdSource = new ServerCommandSource(
                player,
                player.getPos(),
                new Vec2f(player.getYaw(), player.getPitch()),
                world,
                4,
                player.getName().getString(),
                player.getDisplayName(),
                player.getServer(),
                player
        ).withSilent();

        player.getServer().getCommandManager().executeWithPrefix(cmdSource, command);

        world.setBlockState(barrierPos, Blocks.BARRIER.getDefaultState());

        player.getServer().submit(() -> {
            world.setBlockState(barrierPos, Blocks.AIR.getDefaultState());
        });
    }
}