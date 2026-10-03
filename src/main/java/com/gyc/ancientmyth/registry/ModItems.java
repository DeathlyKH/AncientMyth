package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.Item;

import java.util.function.Function;

public final class ModItems {

    private ModItems() {
    }

    private static ResourceKey<Item> key(String name) {
        return ResourceKey.create(Registries.ITEM, AncientMyth.id(name));
    }

    public static Item register(
            String name,
            Function<Item.Properties, Item> factory,
            Item.Properties properties
    ) {
        ResourceKey<Item> key = key(name);

        Item item = factory.apply(properties.setId(key));

        return Registry.register(
                BuiltInRegistries.ITEM,
                key,
                item
        );
    }

    public static Item register(String name) {
        return register(
                name,
                Item::new,
                new Item.Properties()
        );
    }

    public static void initialize() {
    }
}
