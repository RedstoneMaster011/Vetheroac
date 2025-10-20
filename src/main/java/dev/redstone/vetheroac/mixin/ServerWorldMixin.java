package dev.redstone.vetheroac.mixin;

import com.github.stephengold.joltjni.Quat;
import com.github.stephengold.joltjni.RVec3;
import com.github.stephengold.joltjni.enumerate.EActivation;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.TntEntity;
import net.minecraft.entity.decoration.EndCrystalEntity;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.xmx.velthoric.builtin.VxRegisteredBodies;
import net.xmx.velthoric.builtin.block.BlockRigidBody;
import net.xmx.velthoric.math.VxTransform;
import net.xmx.velthoric.physics.body.manager.VxBodyManager;
import net.xmx.velthoric.physics.world.VxPhysicsWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import dev.redstone.vetheroac.config.VetheroacConfigs;

@Mixin(TntEntity.class)
public class ServerWorldMixin {

    @Inject(method = "explode", at = @At("HEAD"))
    private void beforeExplode(CallbackInfo ci) {
        TntEntity self = (TntEntity)(Object)this;
        ServerWorld world = (ServerWorld) self.getWorld();
        MinecraftServer server = world.getServer();
        BlockPos origin = self.getBlockPos();


        if (!VetheroacConfigs.VetheroacConfig.BaseTntSection.Physic_Based_TNT) return;

        for (BlockPos pos : BlockPos.iterateOutwards(origin, 2, 2, 2)) {
            BlockState state = world.getBlockState(pos);
            if (state.isAir()) continue;
            if (state.isOf(Blocks.OBSIDIAN)) continue;
            if (state.isOf(Blocks.BEDROCK)) continue;
            if (state.isOf(Blocks.WATER)) continue;
            if (state.isOf(Blocks.LAVA)) continue;
            if (state.isOf(Blocks.REINFORCED_DEEPSLATE)) continue;
            if (state.isOf(Blocks.TNT)) continue;
            VxPhysicsWorld physicsWorld = VxPhysicsWorld.get(world.getRegistryKey());
            VxBodyManager bodyManager = physicsWorld.getBodyManager();


            BlockState originalState = state;

            RVec3 position = new RVec3(pos.getX(), pos.getY(), pos.getZ());
            Quat rotation = Quat.sIdentity();
            VxTransform transform = new VxTransform(position, rotation);

            BlockRigidBody body = bodyManager.createRigidBody(
                    VxRegisteredBodies.BLOCK,
                    transform,
                    EActivation.Activate,
                    b -> b.setRepresentedBlockState(originalState != null ? originalState : Blocks.STONE.getDefaultState())
            );



        }

        server.submit(() -> {
            server.submit(() -> {
                server.submit(() -> {
                    server.submit(() -> {
                        for (int i = 0; i < VetheroacConfigs.VetheroacConfig.BaseTntSection.PowerTNT.get(); i++) {
                            Vec3d spawnPos = Vec3d.ofCenter(origin).add(0, -2, 0);

                            ServerWorld serverWorld = world;
                            EndCrystalEntity crystal = new EndCrystalEntity(EntityType.END_CRYSTAL, serverWorld);
                            crystal.refreshPositionAndAngles(spawnPos.x, spawnPos.y, spawnPos.z, 0.0F, 0.0F);
                            crystal.setShowBottom(false);

                            if (serverWorld.spawnEntity(crystal)) {
                                serverWorld.createExplosion(crystal, spawnPos.x, spawnPos.y, spawnPos.z, 3.0F, World.ExplosionSourceType.BLOCK);
                                crystal.discard();
                            }
                        }
                    });
                });
            });
        });
    }
}