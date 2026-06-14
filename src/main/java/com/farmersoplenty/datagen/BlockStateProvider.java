package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.common.data.ExistingFileHelper;

/**
 * Generates blockstate JSON + block models + (usually) their item models.
 */
public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FarmersOPlenty.MODID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // Step 6 example (cabinet): horizontalBlock(...) / a custom cabinet model template.
    }
}
