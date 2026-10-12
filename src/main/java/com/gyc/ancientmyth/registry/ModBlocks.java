package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import com.gyc.ancientmyth.block.AncientFarmlandBlock;
import com.gyc.ancientmyth.block.PlantResearchTableBlock;
import net.fabricmc.fabric.api.item.v1.BlockTransformerHelper;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import com.gyc.ancientmyth.block.CompleteTorchflowerBlock;
import net.minecraft.world.level.material.MapColor;

import java.util.function.Function;

public final class ModBlocks {

    private ModBlocks() {
    }

    private static ResourceKey<Block> blockKey(String name) {
        return ResourceKey.create(
                Registries.BLOCK,
                AncientMyth.id(name)
        );
    }

    private static ResourceKey<Item> itemKey(String name) {
        return ResourceKey.create(
                Registries.ITEM,
                AncientMyth.id(name)
        );
    }

    public static Block register(
            String name,
            Function<BlockBehaviour.Properties, Block> factory,
            BlockBehaviour.Properties properties,
            boolean registerItem
    ) {
        ResourceKey<Block> blockKey = blockKey(name);

        Block block = factory.apply(
                properties.setId(blockKey)
        );

        Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                block
        );

        if (registerItem) {
            ResourceKey<Item> itemKey = itemKey(name);

            BlockItem blockItem = new BlockItem(
                    block,
                    new Item.Properties()
                            .setId(itemKey)
                            .useBlockDescriptionPrefix()
            );

            Registry.register(
                    BuiltInRegistries.ITEM,
                    itemKey,
                    blockItem
            );
        }

        return block;
    }

    public static Block register(
            String name,
            Function<BlockBehaviour.Properties, Block> factory,
            BlockBehaviour.Properties properties
    ) {
        return register(
                name,
                factory,
                properties,
                true
        );
    }

    public static Block registerWithoutItem(
            String name,
            Function<BlockBehaviour.Properties, Block> factory,
            BlockBehaviour.Properties properties
    ) {
        ResourceKey<Block> blockKey = blockKey(name);

        Block block = factory.apply(
                properties.setId(blockKey)
        );

        Registry.register(
                BuiltInRegistries.BLOCK,
                blockKey,
                block
        );

        return block;
    }

    public static Block registerHiddenItem(
            String name,
            Function<BlockBehaviour.Properties, Block> factory,
            BlockBehaviour.Properties properties
    ) {
        return register(
                name,
                factory,
                properties,
                true
        );
    }

    public static final Block PLANT_RESEARCH_TABLE = register(
            "plant_research_table",
            PlantResearchTableBlock::new,
            BlockBehaviour.Properties.of()
                    .strength(1.5F)
                    .requiresCorrectToolForDrops()
                    .noOcclusion()
    );

    public static final Block ANCIENT_DIRT = register(
            "ancient_dirt",
            Block::new,
            BlockBehaviour.Properties.of()
                    .strength(0.5F)
                    .sound(SoundType.GRAVEL)
    );

    public static final Block ANCIENT_FARMLAND =
            registerHiddenItem(
                    "ancient_farmland",
                    properties -> new AncientFarmlandBlock(
                            ANCIENT_DIRT,
                            properties
                    ),
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.DIRT)
                            .strength(0.6F)
                            .sound(SoundType.GRASS)
                            .randomTicks()
            );

    public static final Block COMPLETE_TORCHFLOWER = register(
            "complete_torchflower",
            CompleteTorchflowerBlock::new,
            BlockBehaviour.Properties.of()
                    .noCollision()
                    .noOcclusion()
                    .instabreak()
                    .sound(SoundType.GRASS)
    );



    public static void initialize() {
        BlockTransformerHelper.registerTilling(
                ModBlocks.ANCIENT_DIRT,
                ModBlocks.ANCIENT_FARMLAND
        );

    }
}
