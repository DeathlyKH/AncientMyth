package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;

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

    public static void initialize() {
    }
}
