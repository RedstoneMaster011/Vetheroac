package dev.redstone.vetheroac.entity;

import dev.redstone.vetheroac.entity.custom.Rex8916Entity;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VetheroacEntities {
    public static final EntityType<Rex8916Entity> REX8916 = Registry.register(
            Registries.ENTITY_TYPE,
            new Identifier("vetheroac", "rex8916"),
            EntityType.Builder.create(Rex8916Entity::new, SpawnGroup.CREATURE)
                    .setDimensions(0.6f, 1.8f)
                    .build("rex8916")
    );

    public static void register() {

        FabricDefaultAttributeRegistry.register(REX8916, Rex8916Entity.createRex8916Attributes());

    }
}