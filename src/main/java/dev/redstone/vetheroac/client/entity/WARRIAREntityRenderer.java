package dev.redstone.vetheroac.client.entity;

import dev.redstone.vetheroac.entity.custom.Rex8916Entity;
import dev.redstone.vetheroac.entity.custom.WARRIAREntity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.util.Identifier;

public class WARRIAREntityRenderer extends LivingEntityRenderer<WARRIAREntity, PlayerEntityModel<WARRIAREntity>> {
    public WARRIAREntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER), false), 0.5f);
    }

    @Override
    public Identifier getTexture(WARRIAREntity entity) {
        return new Identifier("vetheroac", "textures/entity/warriar.png");
    }
}