package dev.redstone.vetheroac.block;

import dev.redstone.vetheroac.Vetheroac;
import dev.redstone.vetheroac.block.custom.CubeDestroyerTntBlock;
import dev.redstone.vetheroac.block.custom.OPTntBlock;
import dev.redstone.vetheroac.block.custom.PaintTntBlock;
import dev.redstone.vetheroac.block.custom.SplitTntBlock;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VetheroacBlocks {
    public static final Block OP_Tnt_Block = RegisterBlock("op_tnt", new OPTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Split_Tnt_Block = RegisterBlock("split_tnt", new SplitTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Paint_Tnt_Block = RegisterBlock("paint_tnt", new PaintTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Cube_Destroyer_Block = RegisterBlock("cube_destroyer", new CubeDestroyerTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));

    private static Block RegisterBlock(String name, Block block) {
        RegisterItem(name, block);
        return Registry.register(Registries.BLOCK, new Identifier(Vetheroac.MOD_ID, name), block);
    }

    private static Item RegisterItem(String name, Block block) {
        return Registry.register(Registries.ITEM, new Identifier(Vetheroac.MOD_ID, name), new BlockItem(block, new FabricItemSettings()));
    }

    public static void RegisterBlocks() {

    }
}
