package com.furnmod.init;

import com.furnmod.FurnMod;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModItems {

    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, FurnMod.MOD_ID);

    // Har bir blok uchun item (inventory'da ko'rinishi uchun)
    public static final RegistryObject<Item> WOOD_CHAIR_ITEM = ITEMS.register("wood_chair",
            () -> new BlockItem(ModBlocks.WOOD_CHAIR.get(),
                    new Item.Properties().tab(FurnMod.FURN_GROUP)));

    public static final RegistryObject<Item> OAK_CHAIR_ITEM = ITEMS.register("oak_chair",
            () -> new BlockItem(ModBlocks.OAK_CHAIR.get(),
                    new Item.Properties().tab(FurnMod.FURN_GROUP)));

    public static final RegistryObject<Item> WOOD_TABLE_ITEM = ITEMS.register("wood_table",
            () -> new BlockItem(ModBlocks.WOOD_TABLE.get(),
                    new Item.Properties().tab(FurnMod.FURN_GROUP)));

    public static final RegistryObject<Item> OAK_TABLE_ITEM = ITEMS.register("oak_table",
            () -> new BlockItem(ModBlocks.OAK_TABLE.get(),
                    new Item.Properties().tab(FurnMod.FURN_GROUP)));

    public static final RegistryObject<Item> RED_SOFA_ITEM = ITEMS.register("red_sofa",
            () -> new BlockItem(ModBlocks.RED_SOFA.get(),
                    new Item.Properties().tab(FurnMod.FURN_GROUP)));

    public static final RegistryObject<Item> BLUE_SOFA_ITEM = ITEMS.register("blue_sofa",
            () -> new BlockItem(ModBlocks.BLUE_SOFA.get(),
                    new Item.Properties().tab(FurnMod.FURN_GROUP)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
