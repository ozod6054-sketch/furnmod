package com.furnmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Ko'rinmas entity - o'yinchini stul/divanga "o'tqizish" uchun ishlatiladi.
 * Blok o'chirilganda yoki o'yinchi turganda entity yo'qoladi.
 */
public class SeatEntity extends Entity {

    public SeatEntity(EntityType<?> type, World world) {
        super(type, world);
        this.noPhysics = true;
    }

    /** Berilgan pozitsiyada seat entity yaratib, o'yinchini o'tqizadi */
    public static void sit(World world, BlockPos pos, PlayerEntity player, double yOffset) {
        if (world.isClientSide) return;

        SeatEntity seat = ModEntities.SEAT.get().create(world);
        if (seat == null) return;

        seat.setPos(pos.getX() + 0.5, pos.getY() + yOffset, pos.getZ() + 0.5);
        world.addFreshEntity(seat);
        player.startRiding(seat);
    }

    @Override
    public void tick() {
        super.tick();
        if (level.isClientSide) return;

        // Altiga blok yo'q bo'lsa yoki o'yinchi turganda entity o'chirish
        if (!level.getBlockState(this.blockPosition()).is(
                level.getBlockState(this.blockPosition()).getBlock())) {
            this.remove();
            return;
        }

        if (this.getPassengers().isEmpty()) {
            this.remove();
        }
    }

    @Override
    protected void defineSynchedData() { }

    @Override
    protected void readAdditionalSaveData(CompoundNBT nbt) { }

    @Override
    protected void addAdditionalSaveData(CompoundNBT nbt) { }

    @Override
    public IPacket<?> getAddEntityPacket() {
        return NetworkHooks.getEntitySpawningPacket(this);
    }

    /** O'yinchi chiqib ketsa entity o'chadi */
    @Override
    public void ejectPassengers() {
        super.ejectPassengers();
        if (!level.isClientSide) {
            this.remove();
        }
    }
}
