package com.gyc.ancientmyth.registry;

import net.minecraft.world.item.Rarity;

public final class ModRarities {

    public static final Rarity ARCANE =
            Rarity.valueOf("ANCIENT_MYTH_ARCANE");

    public static final Rarity LEGENDARY =
            Rarity.valueOf("ANCIENT_MYTH_LEGENDARY");

    public static final Rarity FORBIDDEN =
            Rarity.valueOf("ANCIENT_MYTH_FORBIDDEN");

    public static final Rarity MYTH =
            Rarity.valueOf("ANCIENT_MYTH_MYTH");

    public static final int ARCANE_COLOR = 0xB8C2CC;
    public static final int LEGENDARY_COLOR = 0x32C878;
    public static final int FORBIDDEN_COLOR = 0x7A1F2B;
    public static final int MYTH_COLOR = 0x000000;

    private ModRarities() {
    }

    public static int getCustomColor(Rarity rarity) {
        if (rarity == ARCANE) {
            return ARCANE_COLOR;
        }

        if (rarity == LEGENDARY) {
            return LEGENDARY_COLOR;
        }

        if (rarity == FORBIDDEN) {
            return FORBIDDEN_COLOR;
        }

        if (rarity == MYTH) {
            return MYTH_COLOR;
        }

        return -1;
    }

    public static void initialize() {
    }
}