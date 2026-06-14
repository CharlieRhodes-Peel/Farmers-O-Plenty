package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import com.farmersoplenty.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * Generates item model JSON (assets/farmersoplenty/models/item/*.json).
 * For most new food/ingredient items you'll use basicItem(...) here later.
 */
public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {

    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FarmersOPlenty.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.ENTRIES.forEach(item -> basicItem(item.get()));
    }
}
