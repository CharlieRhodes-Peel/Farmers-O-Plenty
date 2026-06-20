package com.farmersoplenty.registry;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.datagen.loot.AddItemModifier;
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

/** Registers our global-loot-modifier codecs. Call register(modEventBus) from the main class. */
public final class ModLootModifiers {
    private ModLootModifiers() {}

    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FarmersOPlenty.MODID);

    public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM =
            SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);

    public static void register(IEventBus modEventBus) {
        SERIALIZERS.register(modEventBus);
    }
}
