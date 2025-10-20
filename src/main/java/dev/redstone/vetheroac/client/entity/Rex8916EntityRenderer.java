package dev.redstone.vetheroac.client.entity;

import dev.redstone.vetheroac.entity.custom.Rex8916Entity;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.util.Identifier;

public class Rex8916EntityRenderer extends MobEntityRenderer<Rex8916Entity, PlayerEntityModel<Rex8916Entity>> {
    public Rex8916EntityRenderer(EntityRendererFactory.Context context) {
        super(context, new PlayerEntityModel<>(context.getPart(EntityModelLayers.PLAYER), false), 0.5f);
    }

    @Override
    public Identifier getTexture(Rex8916Entity entity) {
        return new Identifier("vetheroac", "textures/entity/rex8916.png");
    }
}