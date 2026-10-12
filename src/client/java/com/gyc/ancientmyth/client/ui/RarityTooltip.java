
package com.gyc.ancientmyth.client.ui;

import com.gyc.ancientmyth.registry.ModRarities;
import net.fabricmc.fabric.api.client.item.v1.ItemTooltipCallback;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.world.item.Rarity;
import net.minecraft.network.chat.Style;

public final class RarityTooltip {

    private RarityTooltip() {
    }

    public static void initialize() {
        ItemTooltipCallback.EVENT.register(
                (stack, context, flag, lines) -> {

                    if (stack.isEmpty() || lines.isEmpty()) {
                        return;
                    }

                    Rarity rarity = stack.getRarity();

                    MutableComponent label =
                            getTranslatedName(rarity);

                    int color =
                            ModRarities.getCustomColor(rarity);

                    if (color >= 0) {
                        label.withStyle(
                                style -> style.withColor(color)
                        );
                    } else {
                        label.withStyle(rarity.color());
                    }

                    if (rarity == ModRarities.MYTH) {

                        // 动态处理物品名称
                        lines.set(
                                0,
                                MythGradient.animate(lines.get(0))
                        );

                        // 动态处理稀有度副标题
                        lines.add(
                                1,
                                markSubtitle(
                                        Component.literal(" ")
                                                .append(
                                                        MythGradient.animate(label)
                                                )
                                )
                        );

                    } else {
                        lines.add(
                                1,
                                markSubtitle(
                                        Component.literal(" ")
                                                .append(label)
                                )
                        );
                    }
                }
        );
    }

    private static MutableComponent getTranslatedName(
            Rarity rarity
    ) {
        String key;

        if (rarity == Rarity.COMMON) {
            key = "common";
        } else if (rarity == Rarity.UNCOMMON) {
            key = "uncommon";
        } else if (rarity == Rarity.RARE) {
            key = "rare";
        } else if (rarity == Rarity.EPIC) {
            key = "epic";
        } else if (rarity == ModRarities.ARCANE) {
            key = "arcane";
        } else if (rarity == ModRarities.LEGENDARY) {
            key = "legendary";
        } else if (rarity == ModRarities.FORBIDDEN) {
            key = "forbidden";
        } else if (rarity == ModRarities.MYTH) {
            key = "myth";
        } else {
            // 兼容其他模组扩展的未知稀有度
            return Component.literal(rarity.name());
        }

        return Component.translatable(
                "rarity.ancient-myth." + key
        );
    }

    private static Component markSubtitle(
            Component component
    ) {
        return component.copy().withStyle(
                style -> style.withItalic(true)
        );
    }

}
