package dev.redstone.vetheroac.physics;

import com.github.stephengold.joltjni.BodyCreationSettings;
import com.github.stephengold.joltjni.ShapeSettings;
import com.github.stephengold.joltjni.enumerate.EMotionType;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.xmx.velthoric.natives.VxLayers;
import net.xmx.velthoric.network.VxByteBuf;
import net.xmx.velthoric.physics.body.registry.VxBodyType;
import net.xmx.velthoric.physics.body.sync.VxDataAccessor;
import net.xmx.velthoric.physics.body.sync.VxDataSerializers;
import net.xmx.velthoric.physics.body.sync.VxSynchronizedData;
import net.xmx.velthoric.physics.body.type.VxRigidBody;
import net.xmx.velthoric.physics.body.type.factory.VxRigidBodyFactory;
import net.xmx.velthoric.physics.body.util.VxVoxelShapeUtil;
import net.xmx.velthoric.physics.world.VxPhysicsWorld;

import java.util.UUID;

/**
 * A physics body that represents a single, dynamic block.
 */
public class BlockBody extends VxRigidBody {

    public static final VxDataAccessor<Integer> DATA_BLOCK_STATE_ID =
            VxDataAccessor.create(BlockBody.class, VxDataSerializers.INTEGER);

    /** Server-side constructor. */
    public BlockBody(VxBodyType<BlockBody> type, VxPhysicsWorld world, UUID id) {
        super(type, world, id);
    }

    /** Client-side constructor. */
    @Environment(EnvType.CLIENT)
    public BlockBody(VxBodyType<BlockBody> type, UUID id) {
        super(type, id);
    }

    public void setRepresentedBlockState(BlockState blockState) {
        BlockState state = (blockState != null && !blockState.isAir())
                ? blockState
                : Blocks.STONE.getDefaultState();
        this.setSyncData(DATA_BLOCK_STATE_ID, Block.getRawIdFromState(state));
    }

    public BlockState getRepresentedBlockState() {
        BlockState state = Block.getStateFromRawId(this.getSyncData(DATA_BLOCK_STATE_ID));
        return !state.isAir() ? state : Blocks.STONE.getDefaultState();
    }

    @Override
    public int createJoltBody(VxRigidBodyFactory factory) {
        BlockState stateForShape = getRepresentedBlockState();
        VoxelShape voxelShape = stateForShape.getCollisionShape(this.physicsWorld.getLevel(), BlockPos.ORIGIN);

        try (ShapeSettings shapeSettings = VxVoxelShapeUtil.toMutableCompoundShape(voxelShape)) {
            if (shapeSettings == null) {
                try (var boxSettings = VxVoxelShapeUtil.toMutableCompoundShape(
                        Blocks.STONE.getDefaultState().getCollisionShape(this.physicsWorld.getLevel(), BlockPos.ORIGIN));
                     BodyCreationSettings bcs = new BodyCreationSettings()) {
                    bcs.setMotionType(EMotionType.Dynamic);
                    bcs.setObjectLayer(VxLayers.DYNAMIC);
                    return factory.create(boxSettings, bcs);
                }
            }
            try (BodyCreationSettings bcs = new BodyCreationSettings()) {
                bcs.setMotionType(EMotionType.Dynamic);
                bcs.setObjectLayer(VxLayers.DYNAMIC);
                return factory.create(shapeSettings, bcs);
            }
        }
    }

    @Override
    protected void defineSyncData() {

    }

    @Override
    public void writePersistenceData(VxByteBuf buf) {
        buf.writeVarInt(this.getSyncData(DATA_BLOCK_STATE_ID));
    }

    @Override
    public void readPersistenceData(VxByteBuf buf) {
        int blockStateId = buf.readVarInt();
        BlockState state = Block.getStateFromRawId(blockStateId);
        if (state.isAir()) {
            this.setSyncData(DATA_BLOCK_STATE_ID, Block.getRawIdFromState(Blocks.STONE.getDefaultState()));
        } else {
            this.setSyncData(DATA_BLOCK_STATE_ID, blockStateId);
        }
    }
}