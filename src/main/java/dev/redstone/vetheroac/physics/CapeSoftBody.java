package dev.redstone.vetheroac.physics;

import com.github.stephengold.joltjni.Quat;
import com.github.stephengold.joltjni.RVec3;
import net.minecraft.util.math.Vec3d;
import net.xmx.velthoric.physics.body.client.VxRenderState;
import net.xmx.velthoric.physics.body.registry.VxBodyType;
import net.xmx.velthoric.physics.body.sync.VxDataAccessor;
import net.xmx.velthoric.physics.body.sync.VxDataSerializer;
import net.xmx.velthoric.physics.body.sync.VxSynchronizedData;
import net.xmx.velthoric.physics.body.type.VxBody;
import net.xmx.velthoric.physics.world.VxPhysicsWorld;

import java.util.UUID;

public class CapeSoftBody extends VxBody {

    private static final VxDataSerializer<Integer> INTEGER_SERIALIZER = new VxDataSerializer<>() {
        @Override
        public void write(net.xmx.velthoric.network.VxByteBuf buf, Integer value) {
            buf.writeInt(value);
        }

        @Override
        public Integer read(net.xmx.velthoric.network.VxByteBuf buf) {
            return buf.readInt();
        }

        @Override
        public Integer copy(Integer value) {
            return value;
        }
    };
    public static final VxDataAccessor<Integer> WIDTH_SEGMENTS =
            VxDataAccessor.create(CapeSoftBody.class, INTEGER_SERIALIZER);

    public static final VxDataAccessor<Integer> HEIGHT_SEGMENTS =
            VxDataAccessor.create(CapeSoftBody.class, INTEGER_SERIALIZER);

    public CapeSoftBody(VxBodyType<?> type, VxPhysicsWorld world, UUID id) {
        super(type, world, id);
    }

    public CapeSoftBody(VxBodyType<?> type, UUID id) {
        super(type, id);
    }

    @Override
    protected void defineSyncData(VxSynchronizedData.Builder builder) {
        builder.define(WIDTH_SEGMENTS, 8);
        builder.define(HEIGHT_SEGMENTS, 12);
    }

    @Override
    public void calculateRenderState(float tickDelta, VxRenderState renderState, RVec3 pos, Quat rot) {
        int width = getWidthSegments();
        int height = getHeightSegments();
        float segmentSize = 0.0625f; // 1/16 block

        float[] data = new float[width * height * 8]; // 8 floats per vertex
        int index = 0;

        for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                float px = x * segmentSize;
                float py = -y * segmentSize;
                float pz = 0;

                float nx = 0;
                float ny = 0;
                float nz = 1;

                float u = (float) x / (width - 1);
                float v = (float) y / (height - 1);

                data[index++] = px;
                data[index++] = py;
                data[index++] = pz;
                data[index++] = nx;
                data[index++] = ny;
                data[index++] = nz;
                data[index++] = u;
                data[index++] = v;
            }
        }

        renderState.vertexData = data;
    }

    public int getWidthSegments() {
        Integer value = getSyncData(WIDTH_SEGMENTS);
        return value != null ? value : 8;
    }

    public int getHeightSegments() {
        Integer value = getSyncData(HEIGHT_SEGMENTS);
        return value != null ? value : 12;
    }

    public Vec3d getOriginOffset() {
        return new Vec3d(0, -0.5, 0.125);
    }

}