package com.gyc.ancientmyth.client;

import com.gyc.ancientmyth.client.screen.PlantResearchTableScreen;
import com.gyc.ancientmyth.registry.ModMenus;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.client.gui.screens.MenuScreens;
import com.gyc.ancientmyth.client.render.PlantResearchTableBlockEntityRenderer;
import com.gyc.ancientmyth.registry.ModBlockEntities;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;

public class AncientMythClient implements ClientModInitializer {

	@Override
	public void onInitializeClient() {
		MenuScreens.register(
				ModMenus.PLANT_RESEARCH_TABLE,
				PlantResearchTableScreen::new
		);
		BlockEntityRenderers.register(
				ModBlockEntities.PLANT_RESEARCH_TABLE,
				PlantResearchTableBlockEntityRenderer::new
		);
	}
}
