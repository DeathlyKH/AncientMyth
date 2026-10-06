package com.gyc.ancientmyth.registry;

import com.google.common.collect.ImmutableSet;
import com.gyc.ancientmyth.AncientMyth;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;

public final class ModVillagerProfessions {

    public static final ResourceKey<VillagerProfession> NATURALIST_KEY =
            ResourceKey.create(
                    Registries.VILLAGER_PROFESSION,
                    AncientMyth.id("naturalist")
            );

    private static ResourceKey<TradeSet> tradeSet(String path) {
        return ResourceKey.create(
                Registries.TRADE_SET,
                AncientMyth.id(path)
        );
    }

    private static Int2ObjectMap<ResourceKey<TradeSet>> naturalistTrades() {
        Int2ObjectOpenHashMap<ResourceKey<TradeSet>> trades =
                new Int2ObjectOpenHashMap<>();

        trades.put(1, tradeSet("naturalist/level_1"));
        trades.put(2, tradeSet("naturalist/level_2"));
        trades.put(3, tradeSet("naturalist/level_3"));
        trades.put(4, tradeSet("naturalist/level_4"));
        trades.put(5, tradeSet("naturalist/level_5"));

        return trades;
    }

    public static final VillagerProfession NATURALIST =
            Registry.register(
                    BuiltInRegistries.VILLAGER_PROFESSION,
                    NATURALIST_KEY,
                    new VillagerProfession(
                            Component.translatable(
                                    "entity.minecraft.villager.ancient-myth.naturalist"
                            ),

                            // 已经持有的工作站是否仍属于这个职业
                            poi -> poi.is(ModPoiTypes.PLANT_RESEARCH_TABLE_KEY),

                            // 是否能够认领这个工作站
                            poi -> poi.is(ModPoiTypes.PLANT_RESEARCH_TABLE_KEY),

                            // 村民主动捡取/请求的物品，暂时没有
                            ImmutableSet.of(),

                            // secondary POI，暂时没有
                            ImmutableSet.of(),

                            // 暂用图书管理员工作音效
                            SoundEvents.VILLAGER_WORK_LIBRARIAN,

                            naturalistTrades()
                    )
            );

    private ModVillagerProfessions() {
    }

    public static void initialize() {
    }
}
