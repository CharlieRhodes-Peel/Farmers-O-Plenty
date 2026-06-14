package com.farmersoplenty.registry;

import com.farmersoplenty.FarmersOPlenty;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Items;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

/**
 * A single creative tab that auto-fills with every item declared in ModItems,
 * in declaration order. Add an item in ModItems and it appears here for free.
 * The tab title comes from lang key "itemGroup.farmersoplenty" (emitted by the lang provider).
 */
public final class ModCreativeTabs {
    private ModCreativeTabs() {}

    public static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, FarmersOPlenty.MODID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> MAIN =
            TABS.register("main", () -> CreativeModeTab.builder()
                    .title(Component.translatable("itemGroup." + FarmersOPlenty.MODID))
                    // TODO: swap to a signature dish once it exists, e.g. ModItems.CATTAIL_RICE_SOUP.get()...
                    .icon(() -> Items.BOWL.getDefaultInstance())
                    .displayItems((params, output) ->
                            ModItems.ENTRIES.forEach(item -> output.accept(item.get())))
                    .build());

    public static void register(IEventBus bus) {
        TABS.register(bus);
    }
}
