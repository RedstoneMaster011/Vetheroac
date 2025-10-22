package dev.redstone.vetheroac.block;

import dev.redstone.vetheroac.Vetheroac;
import dev.redstone.vetheroac.block.custom.*;
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
    public static final Block Powerful_Tnt_Block = RegisterBlock("powerful_tnt", new PowerfulTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Split_Tnt_Block = RegisterBlock("split_tnt", new SplitTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Paint_Tnt_Block = RegisterBlock("paint_tnt", new PaintTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Cube_Destroyer_Block = RegisterBlock("cube_destroyer", new CubeDestroyerTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Rex8916_Tnt_Block = RegisterBlock("rex8916_tnt", new Rex8916TntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Rain_Tnt_Block = RegisterBlock("rain_tnt", new RainTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Chunk_Tnt_Block = RegisterBlock("chunk_tnt", new ChunkTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Lucky_Tnt_Block = RegisterBlock("lucky_tnt", new LuckyTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Zombie_Tnt_Block = RegisterBlock("zombie_tnt", new ZombieTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Day_Tnt_Block = RegisterBlock("day_tnt", new DayTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Night_Tnt_Block = RegisterBlock("night_tnt", new NightTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Village_Tnt_Block = RegisterBlock("village_tnt", new VillageTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block twozero_Tnt_Block = RegisterBlock("20x_tnt", new twozeroTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block onezero_Tnt_Block = RegisterBlock("10x_tnt", new onezeroTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block five_Tnt_Block = RegisterBlock("5x_tnt", new fiveTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block Xray_Tnt_Block = RegisterBlock("xray_tnt", new XrayTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));
    public static final Block WARRIAR_Tnt_Block = RegisterBlock("warriar_tnt", new WARRIARTntBlock(FabricBlockSettings.copyOf(Blocks.TNT)));

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
