package com.farmersoplenty.datagen;

// NeoForge only: data maps don't exist on Forge 1.20.1, where FarmersOPlenty.registerCompostables does this instead.
//? if neoforge {
import com.farmersoplenty.registry.ExternalItems;
import com.farmersoplenty.registry.ModItems;
import com.farmersoplenty.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.DataMapProvider;
import net.neoforged.neoforge.registries.datamaps.builtin.Compostable;
import net.neoforged.neoforge.registries.datamaps.builtin.NeoForgeDataMaps;

import java.util.concurrent.CompletableFuture;

public class ModDataMaps extends DataMapProvider {

    public ModDataMaps(PackOutput output, CompletableFuture<HolderLookup.Provider> lookup) {
        super(output, lookup);
    }

    @Override
    protected void gather(HolderLookup.Provider provider) {
        var compost = builder(NeoForgeDataMaps.COMPOSTABLES);

        // everything declared with ModItems.compostable(...)
        ModItems.COMPOSTABLES.forEach((item, chance) ->
                compost.add(item, new Compostable(chance), false));

        // items and tags we don't own
        compost.add(ModTags.Items.LAVENDER, new Compostable(ModItems.COMPOST_CROP), false);
        compost.add(BuiltInRegistries.ITEM.getKey(ExternalItems.bop("barley")),
                new Compostable(ModItems.COMPOST_CROP), false);
    }
}
//?}
