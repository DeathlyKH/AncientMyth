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

    private ModItemTags() {
    }
}
