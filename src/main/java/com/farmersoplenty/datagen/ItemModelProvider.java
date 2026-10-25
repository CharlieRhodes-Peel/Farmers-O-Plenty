package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import com.farmersoplenty.registry.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemNameBlockItem;
//? if neoforge {
import net.neoforged.neoforge.common.data.ExistingFileHelper;
//?} else
//import net.minecraftforge.common.data.ExistingFileHelper;

/**
 * Generates item model JSON (assets/farmersoplenty/models/item/*.json).
 * For most new food/ingredient items you'll use basicItem(...) here later.
 */
//? if neoforge {
public class ItemModelProvider extends net.neoforged.neoforge.client.model.generators.ItemModelProvider {
//?} else
//public class ItemModelProvider extends net.minecraftforge.client.model.generators.ItemModelProvider {

    public ItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, FarmersOPlenty.MODID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        ModItems.ENTRIES.forEach(entry -> {
            Item i = entry.get();
            if (!(i instanceof BlockItem) || i instanceof ItemNameBlockItem) {
                basicItem(i);
            }
        });
    }
}
