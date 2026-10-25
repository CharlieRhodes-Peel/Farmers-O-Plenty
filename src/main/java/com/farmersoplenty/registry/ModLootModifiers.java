package com.farmersoplenty.registry;

import com.farmersoplenty.FarmersOPlenty;
import com.farmersoplenty.datagen.loot.AddItemModifier;
//? if neoforge {
import com.mojang.serialization.MapCodec;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.common.loot.IGlobalLootModifier;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;
//?} else {
/*import com.farmersoplenty.platform.DeferredRegister;
import com.mojang.serialization.Codec;
import net.minecraftforge.common.loot.IGlobalLootModifier;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.ForgeRegistries;
*///?}

import java.util.function.Supplier;

/** Registers our global-loot-modifier codecs. Call register(modEventBus) from the main class. */
public final class ModLootModifiers {
    private ModLootModifiers() {}

    // Loot modifier codecs are MapCodecs on NeoForge 1.21.1 and plain Codecs on Forge 1.20.1.
    //? if neoforge {
    public static final DeferredRegister<MapCodec<? extends IGlobalLootModifier>> SERIALIZERS =
            DeferredRegister.create(NeoForgeRegistries.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FarmersOPlenty.MODID);

    public static final Supplier<MapCodec<AddItemModifier>> ADD_ITEM =
            SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);
    //?} else {
    /*public static final DeferredRegister<Codec<? extends IGlobalLootModifier>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.Keys.GLOBAL_LOOT_MODIFIER_SERIALIZERS, FarmersOPlenty.MODID);

    public static final Supplier<Codec<AddItemModifier>> ADD_ITEM =
            SERIALIZERS.register("add_item", () -> AddItemModifier.CODEC);
    *///?}

    public static void register(IEventBus modEventBus) {
        SERIALIZERS.register(modEventBus);
    }
}
