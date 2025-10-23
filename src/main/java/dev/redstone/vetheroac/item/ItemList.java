package dev.redstone.vetheroac.item;

import dev.redstone.vetheroac.Vetheroac;
import dev.redstone.vetheroac.block.VetheroacBlocks;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ItemList {
    public static final ItemGroup Vetheroac_GROUP = Registry.register(Registries.ITEM_GROUP,
            new Identifier(Vetheroac.MOD_ID, "vetheroac"),
            FabricItemGroup.builder().displayName(Text.of("Vetheroac"))
                    .icon(() -> new ItemStack(Blocks.TNT)).entries((displayContext, entries) -> {

                        entries.add(Items.FLINT_AND_STEEL);
                        entries.add(VetheroacItems.Clear_Physics_Entities);

                        entries.add(Items.TNT);

                        entries.add(VetheroacItems.Tnt_Launcher);

                        entries.add(VetheroacBlocks.Powerful_Tnt_Block);
                        entries.add(VetheroacBlocks.Split_Tnt_Block);
                        entries.add(VetheroacBlocks.Paint_Tnt_Block);
                        entries.add(VetheroacBlocks.Cube_Destroyer_Block);
                        entries.add(VetheroacBlocks.Rex8916_Tnt_Block);
                        entries.add(VetheroacBlocks.Rain_Tnt_Block);
                        entries.add(VetheroacBlocks.Chunk_Tnt_Block);
                        entries.add(VetheroacBlocks.Lucky_Tnt_Block);
                        entries.add(VetheroacBlocks.Zombie_Tnt_Block);
                        entries.add(VetheroacBlocks.Day_Tnt_Block);
                        entries.add(VetheroacBlocks.Night_Tnt_Block);
                        entries.add(VetheroacBlocks.Village_Tnt_Block);
                        entries.add(VetheroacBlocks.twozero_Tnt_Block);
                        entries.add(VetheroacBlocks.onezero_Tnt_Block);
                        entries.add(VetheroacBlocks.five_Tnt_Block);
                        entries.add(VetheroacBlocks.Xray_Tnt_Block);
                        entries.add(VetheroacBlocks.WARRIAR_Tnt_Block);
                        entries.add(VetheroacBlocks.Wither_Tnt_Block);
                        entries.add(VetheroacBlocks.Hole_Tnt_Block);
                        entries.add(VetheroacBlocks.Launch_Tnt_Block);
                        entries.add(VetheroacBlocks.Timer_Tnt_Block);
                        entries.add(VetheroacBlocks.Lucky_Timer_Tnt_Block);
                        entries.add(VetheroacBlocks.Ore_Tnt_Block);


                    }).build());

    public static void RegisterItemList() {

    }
}
