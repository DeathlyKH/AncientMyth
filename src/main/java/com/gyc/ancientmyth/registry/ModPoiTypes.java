package com.gyc.ancientmyth.registry;

import com.gyc.ancientmyth.AncientMyth;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.ai.village.poi.PoiType;

public final class ModPoiTypes {

    public static final ResourceKey<PoiType> PLANT_RESEARCH_TABLE_KEY =
            ResourceKey.create(
                    Registries.POINT_OF_INTEREST_TYPE,
                    AncientMyth.id("plant_research_table")
            );

    public static final PoiType PLANT_RESEARCH_TABLE =
            PoiHelper.register(
                    AncientMyth.id("plant_research_table"),
                    1,
                    1,
                    ModBlocks.PLANT_RESEARCH_TABLE
            );

    private ModPoiTypes() {
    }

    public static void initialize() {
    }
}
