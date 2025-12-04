package dev.redstone.vetheroac.util;

import com.github.stephengold.joltjni.Quat;
import com.github.stephengold.joltjni.RVec3;
import com.github.stephengold.joltjni.enumerate.EActivation;
import dev.redstone.vetheroac.config.VetheroacConfigs;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.xmx.velthoric.builtin.VxRegisteredBodies;
import net.xmx.velthoric.builtin.block.BlockRigidBody;
import net.xmx.velthoric.math.VxTransform;
import net.xmx.velthoric.physics.body.manager.VxBodyManager;
import net.xmx.velthoric.physics.body.manager.VxRemovalReason;
import net.xmx.velthoric.physics.world.VxPhysicsWorld;

public class SpawnPhysicsBlock {
    public static void spawn(BlockState state, World world, BlockPos pos) {
        VxPhysicsWorld physicsWorld = VxPhysicsWorld.get(world.getRegistryKey());
        VxBodyManager bodyManager = physicsWorld.getBodyManager();

        RVec3 blockPOS = new RVec3(pos.getX() + 0.5, pos.getY() + 1, pos.getZ() + 0.5);
        Quat rot = Quat.sIdentity();
        VxTransform trans = new VxTransform(blockPOS, rot);

        BlockRigidBody blockBody = bodyManager.createRigidBody(
                VxRegisteredBodies.BLOCK,
                trans,
                EActivation.Activate,
                b -> b.setRepresentedBlockState(state != null ? state : Blocks.STONE.getDefaultState())
        );

        if (blockBody == null) return;
        WaitBeforeExecuting.execute(VetheroacConfigs.VetheroacConfig.MiscSection.TimeUntilRemove, () -> {

            bodyManager.removeBody(blockBody.getPhysicsId(), VxRemovalReason.DISCARD);
        });
    }
}

