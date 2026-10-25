package com.farmersoplenty;

import com.farmersoplenty.datagen.ModBlocks;
import com.farmersoplenty.registry.ModCreativeTabs;
import com.farmersoplenty.registry.ModItems;
import com.farmersoplenty.registry.ModLootModifiers;
import com.mojang.logging.LogUtils;

//? if neoforge {
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
//?} else {
/*import com.farmersoplenty.registry.ExternalItems;
import net.minecraft.world.level.block.ComposterBlock;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
*///?}
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/neoforge.mods.toml file
@Mod(FarmersOPlenty.MODID)
public class FarmersOPlenty {
    public static final String MODID = "farmersoplenty";
    public static final Logger LOGGER = LogUtils.getLogger();

    //? if neoforge {
    public FarmersOPlenty(IEventBus modEventBus, ModContainer modContainer) {
    //?} else {
    /*public FarmersOPlenty() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
    *///?}
        ModItems.register(modEventBus);
        ModBlocks.register(modEventBus);
        ModCreativeTabs.register(modEventBus);
        ModLootModifiers.register(modEventBus);

        // Optional config. If you don't need config, delete this line, Config.java,
        // and the config-screen line in FarmersOPlentyClient.
        //? if neoforge {
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
        //?} else {
        /*ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);

        modEventBus.addListener(FarmersOPlenty::registerCompostables);
        *///?}
    }

    //? if forge {
    /*// 1.20.1 has no compostables data map (datagen/ModDataMaps is NeoForge-only), so the same
    // entries are put into the composter's table directly. Keep the two in sync.
    private static void registerCompostables(FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            ModItems.COMPOSTABLES.forEach((item, chance) -> ComposterBlock.COMPOSTABLES.put(item.get(), (float) chance));

            // Items we don't own (the 1.21.1 data map uses the LAVENDER tag; tags aren't loaded yet here)
            for (String id : new String[]{"lavender", "tall_lavender", "white_lavender", "tall_white_lavender", "barley"}) {
                ComposterBlock.COMPOSTABLES.put(ExternalItems.bop(id), ModItems.COMPOST_CROP);
            }
        });
    }
    *///?}
}
