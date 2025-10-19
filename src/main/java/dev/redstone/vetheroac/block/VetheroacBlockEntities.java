package dev.redstone.vetheroac.block;

import dev.redstone.vetheroac.block.custom.CubeDestroyerBlockEntity;
import dev.redstone.vetheroac.block.custom.OPTntBlockEntity;
import dev.redstone.vetheroac.block.custom.PaintTntBlockEntity;
import dev.redstone.vetheroac.block.custom.SplitTntBlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VetheroacBlockEntities {
    public static BlockEntityType<OPTntBlockEntity> OP_TNT_BLOCK_ENTITY;
    public static BlockEntityType<SplitTntBlockEntity> SPLIT_TNT_BLOCK_ENTITY;
    public static BlockEntityType<PaintTntBlockEntity> PAINT_TNT_BLOCK_ENTITY;
    public static BlockEntityType<CubeDestroyerBlockEntity> CUBE_DESTROYER_BLOCK_ENTITY;

    public static void register() {
        OP_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "op_tnt_block_entity"),
                BlockEntityType.Builder.create(OPTntBlockEntity::new, VetheroacBlocks.OP_Tnt_Block).build(null)
        );

        SPLIT_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "split_tnt_block_entity"),
                BlockEntityType.Builder.create(SplitTntBlockEntity::new, VetheroacBlocks.Split_Tnt_Block).build(null)
        );

        PAINT_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "paint_tnt_block_entity"),
                BlockEntityType.Builder.create(PaintTntBlockEntity::new, VetheroacBlocks.Paint_Tnt_Block).build(null)
        );

        CUBE_DESTROYER_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "cube_destroyer_block_entity"),
                BlockEntityType.Builder.create(CubeDestroyerBlockEntity::new, VetheroacBlocks.Cube_Destroyer_Block).build(null)
        );
    }
}