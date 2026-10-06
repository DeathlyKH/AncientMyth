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

    public static final Item ANCIENT_ARMOR_TRIM_SMITHING_TEMPLATE =
            register(
                    "ancient_armor_trim_smithing_template",
                    Item::new,
                    new Item.Properties()
            );

    public static final Item ANCIENT_RUINS_COMPASS =
            register(
                    "ancient_ruins_compass",
                    Item::new,
                    new Item.Properties()
            );

    public static final Item TEST_ARCANE = register(
            "test_arcane",
            Item::new,
            new Item.Properties()
                    .rarity(ModRarities.ARCANE)
    );

    public static final Item TEST_LEGENDARY = register(
            "test_legendary",
            Item::new,
            new Item.Properties()
                    .rarity(ModRarities.LEGENDARY)
    );

    public static final Item TEST_FORBIDDEN = register(
            "test_forbidden",
            Item::new,
            new Item.Properties()
                    .rarity(ModRarities.FORBIDDEN)
    );

    public static final Item TEST_MYTH = register(
            "test_myth",
            Item::new,
            new Item.Properties()
                    .rarity(ModRarities.MYTH)
    );

    public static void initialize() {
    }
}
