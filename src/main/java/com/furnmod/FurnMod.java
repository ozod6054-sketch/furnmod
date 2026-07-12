package com.furnmod;

import com.furnmod.init.ModBlocks;
import com.furnmod.init.ModItems;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(FurnMod.MOD_ID)
public class FurnMod {

    public static final String MOD_ID = "furnmod";

    public static final ItemGroup FURN_GROUP = new ItemGroup("furn_group") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModBlocks.WOOD_CHAIR.get());
        }
    };

    public FurnMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        ModBlocks.register(modEventBus);
        ModItems.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
    }
}
