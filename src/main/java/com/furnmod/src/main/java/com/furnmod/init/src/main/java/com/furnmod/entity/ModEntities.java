package com.furnmod.entity;

import com.furnmod.FurnMod;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntities {

    public static final DeferredRegister<EntityType<?>> ENTITIES =
            DeferredRegister.create(ForgeRegistries.ENTITIES, FurnMod.MOD_ID);

    public static final RegistryObject<EntityType<SeatEntity>> SEAT =
            ENTITIES.register("seat",
                    () -> EntityType.Builder.<SeatEntity>of(SeatEntity::new, EntityClassification.MISC)
                            .sized(0.001f, 0.001f)   // ko'rinmas, juda kichik
                            .noSave()
                            .noSummon()
                            .build("seat"));

    public static void register(IEventBus eventBus) {
        ENTITIES.register(eventBus);
    }
}
