package dev.redstone.vetheroac.block;

import dev.redstone.vetheroac.block.custom.*;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class VetheroacBlockEntities {
    public static BlockEntityType<PowerfulTntBlockEntity> POWERFUL_TNT_BLOCK_ENTITY;
    public static BlockEntityType<SplitTntBlockEntity> SPLIT_TNT_BLOCK_ENTITY;
    public static BlockEntityType<PaintTntBlockEntity> PAINT_TNT_BLOCK_ENTITY;
    public static BlockEntityType<CubeDestroyerBlockEntity> CUBE_DESTROYER_BLOCK_ENTITY;
    public static BlockEntityType<Rex8916TntBlockEntity> REX8916_TNT_BLOCK_ENTITY;
    public static BlockEntityType<RainTntBlockEntity> RAIN_TNT_BLOCK_ENTITY;
    public static BlockEntityType<ChunkTntBlockEntity> CHUNK_TNT_BLOCK_ENTITY;
    public static BlockEntityType<LuckyTntBlockEntity> LUCKY_TNT_BLOCK_ENTITY;
    public static BlockEntityType<ZombieTntBlockEntity> ZOMBIE_TNT_BLOCK_ENTITY;
    public static BlockEntityType<DayTntBlockEntity>  DAY_TNT_BLOCK_ENTITY;
    public static BlockEntityType<NightTntBlockEntity>  NIGHT_TNT_BLOCK_ENTITY;
    public static BlockEntityType<VillageTntBlockEntity>  VILLAGE_TNT_BLOCK_ENTITY;
    public static BlockEntityType<twozeroTntBlockEntity>  TWOZERO_TNT_BLOCK_ENTITY;
    public static BlockEntityType<onezeroTntBlockEntity>  ONEZERO_TNT_BLOCK_ENTITY;
    public static BlockEntityType<fiveTntBlockEntity>  FIVE_TNT_BLOCK_ENTITY;
    public static BlockEntityType<XrayTntBlockEntity>  XRAY_TNT_BLOCK_ENTITY;
    public static BlockEntityType<WARRIARTntBlockEntity>  WARRIAR_TNT_BLOCK_ENTITY;
    public static BlockEntityType<WitherTntBlockEntity>  WITHER_TNT_BLOCK_ENTITY;
    public static BlockEntityType<HoleTntBlockEntity>  HOLE_TNT_BLOCK_ENTITY;
    public static BlockEntityType<LaunchTntBlockEntity>  LAUNCH_TNT_BLOCK_ENTITY;
    public static BlockEntityType<TimerTntBlockEntity>  TIMER_TNT_BLOCK_ENTITY;
    public static BlockEntityType<LuckyTimerTntBlockEntity>  LUCKY_TIMER_TNT_BLOCK_ENTITY;
    public static BlockEntityType<OreTntBlockEntity>  ORE_TNT_BLOCK_ENTITY;

    public static void register() {
        POWERFUL_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "powerful_tnt_block_entity"),
                BlockEntityType.Builder.create(PowerfulTntBlockEntity::new, VetheroacBlocks.Powerful_Tnt_Block).build(null)
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

        REX8916_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "rex8916_tnt_block_entity"),
                BlockEntityType.Builder.create(Rex8916TntBlockEntity::new, VetheroacBlocks.Rex8916_Tnt_Block).build(null)
        );

        RAIN_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "rain_tnt_block_entity"),
                BlockEntityType.Builder.create(RainTntBlockEntity::new, VetheroacBlocks.Rain_Tnt_Block).build(null)
        );

        CHUNK_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "chunk_tnt_block_entity"),
                BlockEntityType.Builder.create(ChunkTntBlockEntity::new, VetheroacBlocks.Chunk_Tnt_Block).build(null)
        );

        LUCKY_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "lucky_tnt_block_entity"),
                BlockEntityType.Builder.create(LuckyTntBlockEntity::new, VetheroacBlocks.Lucky_Tnt_Block).build(null)
        );

        ZOMBIE_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "zombie_tnt_block_entity"),
                BlockEntityType.Builder.create(ZombieTntBlockEntity::new, VetheroacBlocks.Zombie_Tnt_Block).build(null)
        );

        DAY_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "day_tnt_block_entity"),
                BlockEntityType.Builder.create(DayTntBlockEntity::new, VetheroacBlocks.Day_Tnt_Block).build(null)
        );

        NIGHT_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "night_tnt_block_entity"),
                BlockEntityType.Builder.create(NightTntBlockEntity::new, VetheroacBlocks.Night_Tnt_Block).build(null)
        );

        VILLAGE_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "village_tnt_block_entity"),
                BlockEntityType.Builder.create(VillageTntBlockEntity::new, VetheroacBlocks.Village_Tnt_Block).build(null)
        );

        TWOZERO_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "20x_tnt_block_entity"),
                BlockEntityType.Builder.create(twozeroTntBlockEntity::new, VetheroacBlocks.twozero_Tnt_Block).build(null)
        );

        ONEZERO_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "10x_tnt_block_entity"),
                BlockEntityType.Builder.create(onezeroTntBlockEntity::new, VetheroacBlocks.onezero_Tnt_Block).build(null)
        );

        FIVE_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "5x_tnt_block_entity"),
                BlockEntityType.Builder.create(fiveTntBlockEntity::new, VetheroacBlocks.five_Tnt_Block).build(null)
        );

        XRAY_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "xray_tnt_block_entity"),
                BlockEntityType.Builder.create(XrayTntBlockEntity::new, VetheroacBlocks.Xray_Tnt_Block).build(null)
        );

        WARRIAR_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "warriar_tnt_block_entity"),
                BlockEntityType.Builder.create(WARRIARTntBlockEntity::new, VetheroacBlocks.WARRIAR_Tnt_Block).build(null)
        );

        WITHER_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "wither_tnt_block_entity"),
                BlockEntityType.Builder.create(WitherTntBlockEntity::new, VetheroacBlocks.Wither_Tnt_Block).build(null)
        );

        HOLE_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "hole_tnt_block_entity"),
                BlockEntityType.Builder.create(HoleTntBlockEntity::new, VetheroacBlocks.Hole_Tnt_Block).build(null)
        );

        LAUNCH_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "launch_tnt_block_entity"),
                BlockEntityType.Builder.create(LaunchTntBlockEntity::new, VetheroacBlocks.Launch_Tnt_Block).build(null)
        );

        TIMER_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "timer_tnt_block_entity"),
                BlockEntityType.Builder.create(TimerTntBlockEntity::new, VetheroacBlocks.Timer_Tnt_Block).build(null)
        );

        LUCKY_TIMER_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "lucky_timer_tnt_block_entity"),
                BlockEntityType.Builder.create(LuckyTimerTntBlockEntity::new, VetheroacBlocks.Lucky_Timer_Tnt_Block).build(null)
        );

        ORE_TNT_BLOCK_ENTITY = Registry.register(
                Registries.BLOCK_ENTITY_TYPE,
                new Identifier("vetheroac", "ore_tnt_block_entity"),
                BlockEntityType.Builder.create(OreTntBlockEntity::new, VetheroacBlocks.Ore_Tnt_Block).build(null)
        );
    }
}