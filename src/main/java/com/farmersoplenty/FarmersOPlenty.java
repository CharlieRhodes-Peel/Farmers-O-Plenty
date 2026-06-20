package com.farmersoplenty;

import com.farmersoplenty.datagen.ModBlocks;
import com.farmersoplenty.registry.ModCreativeTabs;
import com.farmersoplenty.registry.ModItems;
import com.farmersoplenty.registry.ModLootModifiers;
import com.mojang.logging.LogUtils;

import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FarmersOPlenty.MODID)
public class FarmersOPlenty {
    public static final String MODID = "farmersoplenty";
    public static final Logger LOGGER = LogUtils.getLogger();

    public FarmersOPlenty(IEventBus modEventBus, ModContainer modContainer) {
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        // Optional config. If you don't need config, delete this line, Config.java,
        // and the config-screen line in FarmersOPlentyClient.
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }
}