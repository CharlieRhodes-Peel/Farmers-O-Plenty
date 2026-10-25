package com.farmersoplenty.platform;

//? if forge {
/*import net.minecraft.core.Registry;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

// Forge 1.20.1 stand-in for NeoForge's DeferredRegister (plus its Items/Blocks helpers), so the
// registration code in registry/ and ModBlocks is written once, NeoForge style, and only its imports
// differ per loader. Everything here just forwards to Forge's own DeferredRegister.
// Only the parts this mod uses are mirrored; add more as needed.
public class DeferredRegister<T> {
    private final net.minecraftforge.registries.DeferredRegister<T> registry;

    protected DeferredRegister(ResourceKey<? extends Registry<T>> key, String modId) {
        this.registry = net.minecraftforge.registries.DeferredRegister.create(key, modId);
    }

    public static <T> DeferredRegister<T> create(ResourceKey<? extends Registry<T>> key, String modId) {
        return new DeferredRegister<>(key, modId);
    }

    public static Items createItems(String modId) {
        return new Items(modId);
    }

    public static Blocks createBlocks(String modId) {
        return new Blocks(modId);
    }

    public <I extends T> DeferredHolder<T, I> register(String name, Supplier<? extends I> sup) {
        return new DeferredHolder<>(registerObject(name, sup));
    }

    protected <I extends T> RegistryObject<I> registerObject(String name, Supplier<? extends I> sup) {
        return registry.register(name, sup);
    }

    public void register(IEventBus bus) {
        registry.register(bus);
    }

    public static class Items extends DeferredRegister<Item> {
        protected Items(String modId) {
            super(Registries.ITEM, modId);
        }

        @Override
        public <I extends Item> DeferredItem<I> register(String name, Supplier<? extends I> sup) {
            return new DeferredItem<>(registerObject(name, sup));
        }

        public DeferredItem<BlockItem> registerSimpleBlockItem(DeferredBlock<?> block) {
            return register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
        }
    }

    public static class Blocks extends DeferredRegister<Block> {
        protected Blocks(String modId) {
            super(Registries.BLOCK, modId);
        }

        @Override
        public <B extends Block> DeferredBlock<B> register(String name, Supplier<? extends B> sup) {
            return new DeferredBlock<>(registerObject(name, sup));
        }

        public <B extends Block> DeferredBlock<B> registerBlock(String name,
                                                               Function<BlockBehaviour.Properties, ? extends B> func,
                                                               BlockBehaviour.Properties props) {
            return register(name, () -> func.apply(props));
        }
    }
}
*///?}
