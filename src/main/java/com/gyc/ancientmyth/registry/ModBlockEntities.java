package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import com.gyc.ancientmyth.block.entity.PlantResearchTableBlockEntity;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;

public final class ModBlockEntities {

    public static final BlockEntityType<PlantResearchTableBlockEntity> PLANT_RESEARCH_TABLE =
            register(
                    "plant_research_table",
                    PlantResearchTableBlockEntity::new,
                    ModBlocks.PLANT_RESEARCH_TABLE
            );

    private ModBlockEntities() {
    }

    private static <T extends BlockEntity> BlockEntityType<T> register(
            String name,
            FabricBlockEntityTypeBuilder.Factory<? extends T> factory,
            Block... blocks
    ) {
        return Registry.register(
                BuiltInRegistries.BLOCK_ENTITY_TYPE,
                AncientMyth.id(name),
                FabricBlockEntityTypeBuilder.<T>create(factory, blocks).build()
        );
    }

    public static void initialize() {
    }
}
