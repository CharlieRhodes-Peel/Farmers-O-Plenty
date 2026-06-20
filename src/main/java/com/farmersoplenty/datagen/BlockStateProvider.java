package com.farmersoplenty.datagen;

import com.farmersoplenty.FarmersOPlenty;

import com.farmersoplenty.datagen.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import vectorwing.farmersdelight.common.block.CabinetBlock;

/**
 * Generates blockstate JSON + block models + (usually) their item models.
 */
public class BlockStateProvider extends net.neoforged.neoforge.client.model.generators.BlockStateProvider {

    public BlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, FarmersOPlenty.MODID, exFileHelper);
    }

    private void cabinetBlock(Block block) {
        String n = BuiltInRegistries.BLOCK.getKey(block).getPath();      // e.g. "fir_cabinet"
        ModelFile closed = models().orientable(n,
                modLoc("block/" + n + "_side"), modLoc("block/" + n + "_front"), modLoc("block/" + n + "_top"));
        ModelFile open = models().orientable(n + "_open",
                modLoc("block/" + n + "_side"), modLoc("block/" + n + "_front_open"), modLoc("block/" + n + "_top"));
        horizontalBlock(block, state -> state.getValue(CabinetBlock.OPEN) ? open : closed);
        simpleBlockItem(block, closed);
    }

    private void cropBlock(CropBlock crop) {
        String n = BuiltInRegistries.BLOCK.getKey(crop).getPath();
        getVariantBuilder(crop).forAllStates(state -> ConfiguredModel.builder()
                .modelFile(models().crop(n + "_stage" + state.getValue(CropBlock.AGE),
                                modLoc("block/" + n + "_stage" + state.getValue(CropBlock.AGE)))
                        .renderType("cutout"))
                .build());
    }

    @Override
    protected void registerStatesAndModels() {
        ModBlocks.CABINETS.forEach(c -> cabinetBlock(c.get()));
        ModBlocks.CROPS.forEach(c -> cropBlock(c.get()));
    }
}
