package com.gyc.ancientmyth.mixin.client;

import com.gyc.ancientmyth.client.ui.RarityTooltipStyles;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.Slot;
import org.jspecify.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;

@Mixin(AbstractContainerScreen.class)
public abstract class ContainerTooltipStyleMixin {

    @Shadow
    protected @Nullable Slot hoveredSlot;

    @ModifyArg(
            method = "extractTooltip(Lnet/minecraft/client/gui/GuiGraphicsExtractor;II)V",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/client/gui/GuiGraphicsExtractor;setTooltipForNextFrame(Lnet/minecraft/client/gui/Font;Ljava/util/List;Ljava/util/Optional;IILnet/minecraft/resources/Identifier;Z)V"
            ),
            index = 5
    )
    private @Nullable Identifier ancientmyth$rarityStyle(
            @Nullable Identifier original
    ) {
        if (this.hoveredSlot == null
                || !this.hoveredSlot.hasItem()) {
            return original;
        }

        return RarityTooltipStyles.resolve(
                this.hoveredSlot.getItem(),
                original
        );
    }
}
