package com.farmersoplenty;

import net.neoforged.api.distmarker.Dist;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.gui.ConfigurationScreen;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

// Client-only. Safe to access client code here. Does not load on dedicated servers.
@Mod(value = FarmersOPlenty.MODID, dist = Dist.CLIENT)
public class FarmersOPlentyClient {
    public FarmersOPlentyClient(ModContainer container) {
        // In-game config screen: Mods > Farmer's O' Plenty > Config.
        // Delete this line (and the whole class) if you remove Config.java.
        container.registerExtensionPoint(IConfigScreenFactory.class, ConfigurationScreen::new);
    }
}