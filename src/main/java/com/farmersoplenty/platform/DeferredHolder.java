package com.farmersoplenty.platform;

//? if forge {
/*import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

// Forge 1.20.1 stand-in for NeoForge's DeferredHolder: a lazily registered entry. See DeferredRegister.
public class DeferredHolder<R, T extends R> implements Supplier<T> {
    private final RegistryObject<T> object;

    DeferredHolder(RegistryObject<T> object) {
        this.object = object;
    }

    @Override
    public T get() {
        return object.get();
    }

    public ResourceLocation getId() {
        return object.getId();
    }
}
*///?}
