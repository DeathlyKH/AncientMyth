package com.gyc.ancientmyth.client.ui;

import com.gyc.ancientmyth.registry.ModRarities;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import org.jspecify.annotations.Nullable;

public final class RarityTooltipStyles {

    private RarityTooltipStyles() {
    }

    public static @Nullable Identifier resolve(
            ItemStack stack,
            @Nullable Identifier existing
    ) {
        // 不覆盖其他模组或数据组件指定的样式
        if (existing != null || stack.isEmpty()) {
            return existing;
        }

        Rarity rarity = stack.getRarity();

        String path;

        if (rarity == Rarity.COMMON) {
            return existing;
        } else if (rarity == Rarity.UNCOMMON) {
            path = "uncommon";
        } else if (rarity == Rarity.RARE) {
            path = "rare";
        } else if (rarity == Rarity.EPIC) {
            path = "epic";
        } else if (rarity == ModRarities.ARCANE) {
            path = "arcane";
        } else if (rarity == ModRarities.LEGENDARY) {
            path = "legendary";
        } else if (rarity == ModRarities.FORBIDDEN) {
            path = "forbidden";
        } else if (rarity == ModRarities.MYTH) {
            path = "myth";
        } else {
            return null;
        }

        return Identifier.fromNamespaceAndPath(
                "ancient-myth",
                "rarity/" + path
        );
    }
}
