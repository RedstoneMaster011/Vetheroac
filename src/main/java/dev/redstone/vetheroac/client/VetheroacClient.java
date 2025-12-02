package dev.redstone.vetheroac.client;

import dev.redstone.vetheroac.block.VetheroacBlocks;
import dev.redstone.vetheroac.client.entity.Rex8916EntityRenderer;
import dev.redstone.vetheroac.client.entity.WARRIAREntityRenderer;
import dev.redstone.vetheroac.entity.VetheroacEntities;
import dev.redstone.vetheroac.physics.VetheroacBodys;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;

public class VetheroacClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(VetheroacEntities.REX8916, Rex8916EntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(VetheroacEntities.WARRIAR, WARRIAREntityRenderer::new);

        VetheroacBodys.registerClient();

        BlockRenderLayerMap.INSTANCE.putBlock(VetheroacBlocks.Xray_Tnt_Block, RenderLayer.getCutout());
    }
}
