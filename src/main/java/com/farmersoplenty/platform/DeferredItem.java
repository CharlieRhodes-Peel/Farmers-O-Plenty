package com.farmersoplenty.platform;

//? if forge {
/*import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.RegistryObject;

// Forge 1.20.1 stand-in for NeoForge's DeferredItem. Being ItemLike lets it go straight into recipe builders.
public class DeferredItem<T extends Item> extends DeferredHolder<Item, T> implements ItemLike {
    DeferredItem(RegistryObject<T> object) {
        super(object);
    }

    @Override
    public Item asItem() {
        return get();
    }
}
*///?}
