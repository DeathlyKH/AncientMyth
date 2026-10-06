package com.gyc.ancientmyth.client.screen;

import com.gyc.ancientmyth.menu.PlantResearchTableMenu;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.player.Inventory;

public class PlantResearchTableScreen
        extends AbstractContainerScreen<PlantResearchTableMenu> {

    public PlantResearchTableScreen(
            PlantResearchTableMenu menu,
            Inventory inventory,
            Component title
    ) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(
            GuiGraphicsExtractor graphics,
            int mouseX,
            int mouseY,
            float delta
    ) {
        super.extractBackground(
                graphics,
                mouseX,
                mouseY,
                delta
        );

        // 临时研究台面板
        graphics.fill(
                this.leftPos,
                this.topPos,
                this.leftPos + this.imageWidth,
                this.topPos + this.imageHeight,
                0xFFE8E0D0
        );

        // 样本槽边框
        graphics.fill(
                this.leftPos + 79,
                this.topPos + 19,
                this.leftPos + 97,
                this.topPos + 37,
                0xFF6B5B45
        );

        graphics.fill(
                this.leftPos + 80,
                this.topPos + 20,
                this.leftPos + 96,
                this.topPos + 36,
                0xFFB8A98F
        );
    }
}
