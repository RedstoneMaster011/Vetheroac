package dev.redstone.vetheroac.physics;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.xmx.velthoric.math.VxTransform;
import net.xmx.velthoric.physics.body.client.VxRenderState;
import net.xmx.velthoric.physics.body.client.body.renderer.VxBodyRenderer;
import net.xmx.velthoric.physics.body.type.VxBody;
import org.joml.Matrix3f;
import org.joml.Matrix4f;
import com.github.stephengold.joltjni.Quat;
import com.github.stephengold.joltjni.RVec3;

import java.util.UUID;

public class CapeRenderer extends VxBodyRenderer {
    @Override
    public void render(VxBody vxBody, MatrixStack matrixStack, VertexConsumerProvider.Immediate immediate, float tickDelta, int light, VxRenderState vxRenderState) {
        if (!(vxBody instanceof CapeSoftBody cape)) return;
        if (!cape.isInitialized()) {
            System.out.println("Cape not initialized: " + cape);
            return;
        }
        if (vxRenderState.vertexData == null) {
            System.out.println("Cape vertexData is null");
            return;
        }

        UUID ownerId = CapeRuntime.getCapePlayerId(cape);
        if (ownerId == null) {
            System.out.println("Cape owner UUID not found");
            return;
        }

        MinecraftClient client = MinecraftClient.getInstance();
        var basePlayer = client.world != null ? client.world.getPlayerByUuid(ownerId) : null;
        if (!(basePlayer instanceof AbstractClientPlayerEntity player)) {
            System.out.println("Cape owner is not a client player: " + basePlayer);
            return;
        }

        Identifier capeTexture = player.getCapeTexture();
        if (capeTexture == null) {
            System.out.println("Player has no cape texture: " + player.getName().getString());
            return;
        }

        System.out.println("Rendering cape for: " + player.getName().getString());

        RVec3 pos = new RVec3();
        Quat rot = new Quat();
        cape.calculateRenderState(tickDelta, vxRenderState, pos, rot);

        matrixStack.push();
        VxTransform transform = vxRenderState.transform;
        matrixStack.translate(transform.getTranslation().x(), transform.getTranslation().y(), transform.getTranslation().z());

        Matrix4f positionMatrix = matrixStack.peek().getPositionMatrix();
        Matrix3f normalMatrix = matrixStack.peek().getNormalMatrix();
        VertexConsumer vertexConsumer = immediate.getBuffer(RenderLayer.getEntityTranslucentCull(capeTexture));

        float[] data = vxRenderState.vertexData;
        int vertexCount = data.length / 8;
        System.out.println("Cape vertex count: " + vertexCount);

        for (int i = 0; i < data.length; i += 8) {
            float x = data[i];
            float y = data[i + 1];
            float z = data[i + 2];
            float nx = data[i + 3];
            float ny = data[i + 4];
            float nz = data[i + 5];
            float u = data[i + 6];
            float v = data[i + 7];

            if (i == 0) {
                System.out.println("Sample UV: " + u + ", " + v);
            }

            vertexConsumer.vertex(positionMatrix, x, y, z)
                    .color(255, 255, 255, 255)
                    .normal(normalMatrix, nx, ny, nz)
                    .texture(u, v)
                    .light(light)
                    .next();
        }

        matrixStack.pop();
    }
}