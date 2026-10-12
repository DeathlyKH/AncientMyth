
package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import net.minecraft.core.registries.Registries;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;

public final class ModItemTags {

    public static final TagKey<Item> RESEARCHABLE =
            TagKey.create(
                    Registries.ITEM,
                    AncientMyth.id("researchable")
            );

    // Ancient Myth 稀有度覆盖标签

    public static final TagKey<Item> RARITY_ARCANE =
            TagKey.create(
                    Registries.ITEM,
                    AncientMyth.id("rarity/arcane")
            );

    public static final TagKey<Item> RARITY_LEGENDARY =
            TagKey.create(
                    Registries.ITEM,
                    AncientMyth.id("rarity/legendary")
            );

    public static final TagKey<Item> RARITY_FORBIDDEN =
            TagKey.create(
                    Registries.ITEM,
                    AncientMyth.id("rarity/forbidden")
            );

    public static final TagKey<Item> RARITY_MYTH =
            TagKey.create(
                    Registries.ITEM,
                    AncientMyth.id("rarity/myth")
            );

    private ModItemTags() {
    }
}

