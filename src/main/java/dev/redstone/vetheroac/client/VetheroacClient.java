package dev.redstone.vetheroac.client;

import dev.redstone.vetheroac.client.entity.Rex8916EntityRenderer;
import dev.redstone.vetheroac.client.entity.WARRIAREntityRenderer;
import dev.redstone.vetheroac.entity.VetheroacEntities;
import dev.redstone.vetheroac.physics.VetheroacBodys;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;

public class VetheroacClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        EntityRendererRegistry.INSTANCE.register(VetheroacEntities.REX8916, Rex8916EntityRenderer::new);

        EntityRendererRegistry.INSTANCE.register(VetheroacEntities.WARRIAR, WARRIAREntityRenderer::new);

        VetheroacBodys.registerClient();
    }
}
