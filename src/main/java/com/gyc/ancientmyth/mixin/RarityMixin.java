package com.gyc.ancientmyth.mixin;

import net.minecraft.ChatFormatting;
import net.minecraft.world.item.Rarity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.MixinIntrinsics;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Rarity.class)
enum RarityMixin {

    ANCIENT_MYTH_ARCANE(
            MixinIntrinsics.currentEnumOrdinal(),
            "ancient_myth_arcane",
            ChatFormatting.GRAY
    ),

    ANCIENT_MYTH_LEGENDARY(
            MixinIntrinsics.currentEnumOrdinal(),
            "ancient_myth_legendary",
            ChatFormatting.GREEN
    ),

    ANCIENT_MYTH_FORBIDDEN(
            MixinIntrinsics.currentEnumOrdinal(),
            "ancient_myth_forbidden",
            ChatFormatting.DARK_RED
    ),

    ANCIENT_MYTH_MYTH(
            MixinIntrinsics.currentEnumOrdinal(),
            "ancient_myth_myth",
            ChatFormatting.BLACK
    );

    @Shadow
    RarityMixin(
            int id,
            String name,
            ChatFormatting color
    ) {
    }
}
