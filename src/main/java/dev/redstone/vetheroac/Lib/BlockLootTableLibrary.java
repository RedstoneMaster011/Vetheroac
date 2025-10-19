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
        addDrop(VetheroacBlocks.OP_Tnt_Block);
        addDrop(VetheroacBlocks.Split_Tnt_Block);
        addDrop(VetheroacBlocks.Paint_Tnt_Block);
    }
}
