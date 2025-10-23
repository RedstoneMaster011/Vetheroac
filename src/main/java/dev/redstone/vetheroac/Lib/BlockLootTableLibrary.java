package dev.redstone.vetheroac.Lib;

import dev.redstone.vetheroac.block.VetheroacBlocks;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;

public class BlockLootTableLibrary extends FabricBlockLootTableProvider {
    public BlockLootTableLibrary(FabricDataOutput dataOutput) {
        super(dataOutput);
    }

    @Override
    public void generate() {
        addDrop(VetheroacBlocks.Powerful_Tnt_Block);
        addDrop(VetheroacBlocks.Split_Tnt_Block);
        addDrop(VetheroacBlocks.Paint_Tnt_Block);
        addDrop(VetheroacBlocks.Rain_Tnt_Block);
        addDrop(VetheroacBlocks.Chunk_Tnt_Block);
        addDrop(VetheroacBlocks.Lucky_Tnt_Block);
        addDrop(VetheroacBlocks.Zombie_Tnt_Block);
        addDrop(VetheroacBlocks.Day_Tnt_Block);
        addDrop(VetheroacBlocks.Night_Tnt_Block);
        addDrop(VetheroacBlocks.Village_Tnt_Block);
        addDrop(VetheroacBlocks.twozero_Tnt_Block);
        addDrop(VetheroacBlocks.onezero_Tnt_Block);
        addDrop(VetheroacBlocks.five_Tnt_Block);
        addDrop(VetheroacBlocks.Xray_Tnt_Block);
        addDrop(VetheroacBlocks.WARRIAR_Tnt_Block);
        addDrop(VetheroacBlocks.Wither_Tnt_Block);
        addDrop(VetheroacBlocks.Hole_Tnt_Block);
        addDrop(VetheroacBlocks.Launch_Tnt_Block);
        addDrop(VetheroacBlocks.Timer_Tnt_Block);
        addDrop(VetheroacBlocks.Lucky_Timer_Tnt_Block);
        addDrop(VetheroacBlocks.Ore_Tnt_Block);
    }
}
