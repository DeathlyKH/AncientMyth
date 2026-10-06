package com.gyc.ancientmyth.mixin;

import com.gyc.ancientmyth.AncientMyth;
import com.gyc.ancientmyth.registry.ModItems;
import com.gyc.ancientmyth.registry.ModVillagerProfessions;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.npc.villager.Villager;
import net.minecraft.world.entity.npc.villager.VillagerData;
import net.minecraft.world.item.trading.MerchantOffers;
import net.minecraft.world.item.trading.TradeSet;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Villager.class)
public abstract class VillagerMixin {

    private static final ResourceKey<TradeSet> NATURALIST_MASTER_GUARANTEED =
            ResourceKey.create(
                    Registries.TRADE_SET,
                    AncientMyth.id("naturalist/level_5_guaranteed")
            );

    @Inject(
            method = "updateTrades",
            at = @At("TAIL")
    )
    private void ancientMyth$addNaturalistMasterGuaranteedTrade(
            ServerLevel level,
            CallbackInfo ci
    ) {
        Villager villager = (Villager) (Object) this;
        VillagerData data = villager.getVillagerData();

        if (data.level() != 5) {
            return;
        }

        if (!data.profession().is(
                ModVillagerProfessions.NATURALIST_KEY
        )) {
            return;
        }

        MerchantOffers offers = villager.getOffers();

        // 防止万一 updateTrades 再次执行时重复添加指南针
        boolean alreadyHasCompass = offers.stream().anyMatch(
                offer -> offer.getResult().getItem()
                        == ModItems.ANCIENT_RUINS_COMPASS
        );

        if (alreadyHasCompass) {
            return;
        }

        ((AbstractVillagerInvoker) villager)
                .ancientMyth$addOffersFromTradeSet(
                        level,
                        offers,
                        NATURALIST_MASTER_GUARANTEED
                );
    }
}
