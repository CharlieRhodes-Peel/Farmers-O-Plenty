package com.farmersoplenty.platform;

//? if forge {
/*import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.registries.RegistryObject;

// Forge 1.20.1 stand-in for NeoForge's DeferredBlock.
public class DeferredBlock<T extends Block> extends DeferredHolder<Block, T> implements ItemLike {
    DeferredBlock(RegistryObject<T> object) {
        super(object);
    }

    @Override
    public Item asItem() {
        return get().asItem();
    }
}
*///?}
