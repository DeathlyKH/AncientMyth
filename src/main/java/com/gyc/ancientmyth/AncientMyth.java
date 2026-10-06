package com.gyc.ancientmyth;

import com.gyc.ancientmyth.registry.ModBlocks;
import com.gyc.ancientmyth.registry.ModDataComponents;
import com.gyc.ancientmyth.registry.ModEntities;
import com.gyc.ancientmyth.registry.ModItems;
import net.fabricmc.api.ModInitializer;
import net.minecraft.resources.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.gyc.ancientmyth.registry.ModBlockEntities;
import com.gyc.ancientmyth.registry.ModMenus;

public class AncientMyth implements ModInitializer {

	public static final String MOD_ID = "ancient-myth";

	public static final Logger LOGGER =
			LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModDataComponents.initialize();

		ModBlocks.initialize();
		ModBlockEntities.initialize();

		ModItems.initialize();
		ModEntities.initialize();

		ModMenus.initialize();

		LOGGER.info("[ancient-myth] Initialized Successfully!");
	}

	public static Identifier id(String path) {
		return Identifier.fromNamespaceAndPath(MOD_ID, path);
	}
}
