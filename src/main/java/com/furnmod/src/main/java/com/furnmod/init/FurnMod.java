package com.furnmod;

import com.furnmod.entity.ModEntities;
import com.furnmod.init.ModBlocks;
import com.furnmod.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(FurnMod.MOD_ID)
public class FurnMod {

    public static final String MOD_ID = "furnmod";
    public static final Logger LOGGER = LogManager.getLogger();

    // Custom creative tab (mebel uchun alohida tab)
    public static final ItemGroup FURN_GROUP = new ItemGroup("furn_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.WOOD_CHAIR.get());
        }
    };

    public FurnMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // Blok va itemlarni ro'yxatdan o'tkazish
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);
    }
}
