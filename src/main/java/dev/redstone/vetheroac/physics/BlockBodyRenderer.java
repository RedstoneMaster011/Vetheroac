package dev.redstone.vetheroac.physics;

import com.github.stephengold.joltjni.Quat;
import com.github.stephengold.joltjni.RVec3;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.color.block.BlockColors;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.RenderLayers;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.block.BlockRenderManager;
import net.minecraft.client.render.block.entity.BlockEntityRenderDispatcher;
import net.minecraft.client.render.item.ItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.xmx.velthoric.physics.body.client.VxRenderState;
import net.xmx.velthoric.physics.body.client.body.renderer.VxRigidBodyRenderer;
import org.joml.Quaternionf;

/**
 * Renderer for the {@link BlockBody}.
 */
public class BlockBodyRenderer extends VxRigidBodyRenderer<BlockBody> {

    private BlockEntity cachedBlockEntity;
    private BlockState lastBlockState;

    @Override
    public void render(BlockBody body,
                       MatrixStack matrices,
                       VertexConsumerProvider.Immediate vertexConsumers,
                       float tickDelta,
                       int light,
                       VxRenderState renderState) {

        int blockStateId = body.getSyncData(BlockBody.DATA_BLOCK_STATE_ID);
        BlockState blockStateToRender = Block.getStateFromRawId(blockStateId);

        if (blockStateToRender.isAir() || blockStateToRender.getRenderType() == BlockRenderType.INVISIBLE) {
            return;
        }

        matrices.push();

        RVec3 renderPosition = renderState.transform.getTranslation();
        Quat renderRotation = renderState.transform.getRotation();
        matrices.translate(renderPosition.x(), renderPosition.y(), renderPosition.z());
        matrices.multiply(new Quaternionf(
                renderRotation.getX(),
                renderRotation.getY(),
                renderRotation.getZ(),
                renderRotation.getW()
        ));

        BlockRenderType shape = blockStateToRender.getRenderType();
        if (shape == BlockRenderType.MODEL || shape == BlockRenderType.ENTITYBLOCK_ANIMATED) {
            BlockRenderManager dispatcher = MinecraftClient.getInstance().getBlockRenderManager();

            BlockColors blockColors = MinecraftClient.getInstance().getBlockColors();
            World world = MinecraftClient.getInstance().world;
            BlockPos currentPos = BlockPos.ofFloored(renderPosition.x(), renderPosition.y(), renderPosition.z());

            int color = blockColors.getColor(blockStateToRender, world, currentPos, 0);

            float r = (color >> 16 & 255) / 255.0f;
            float g = (color >> 8 & 255) / 255.0f;
            float b = (color & 255) / 255.0f;

            matrices.push();
            matrices.translate(-0.5, -0.5, -0.5);

            dispatcher.getModelRenderer().render(
                    matrices.peek(),
                    vertexConsumers.getBuffer(RenderLayers.getBlockLayer(blockStateToRender)),
                    blockStateToRender,
                    dispatcher.getModel(blockStateToRender),
                    r, g, b,
                    light,
                    OverlayTexture.DEFAULT_UV
            );

            matrices.pop();
        }

        if (blockStateToRender.getBlock() instanceof BlockEntityProvider provider) {
            if (this.lastBlockState != blockStateToRender) {
                this.cachedBlockEntity = provider.createBlockEntity(BlockPos.ORIGIN, blockStateToRender);
                this.lastBlockState = blockStateToRender;

                if (this.cachedBlockEntity != null) {
                    this.cachedBlockEntity.setWorld(MinecraftClient.getInstance().world);
                }
            }
        } else {
            this.cachedBlockEntity = null;
            this.lastBlockState = null;
        }


        if (this.cachedBlockEntity != null) {
                BlockEntityRenderDispatcher beDispatcher = MinecraftClient.getInstance().getBlockEntityRenderDispatcher();
                var renderer = beDispatcher.get(this.cachedBlockEntity);
                if (renderer != null) {
                    matrices.push();
                    matrices.translate(-0.5, -0.5, -0.5);
                    renderer.render(this.cachedBlockEntity, tickDelta, matrices, vertexConsumers, light, OverlayTexture.DEFAULT_UV);
                    matrices.pop();
                }
        } else {
            this.cachedBlockEntity = null;
            this.lastBlockState = null;
        }

        matrices.pop();
    }
}
