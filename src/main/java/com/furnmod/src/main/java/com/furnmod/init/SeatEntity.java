package com.furnmod.entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.network.IPacket;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.fml.network.NetworkHooks;

/**
 * Ko'rinmas entity — o'yinchini stul/divanga o'tqizish uchun.
 * Blok o'chirilganda yoki o'yinchi turganda yo'qoladi.
 */
public class SeatEntity extends Entity {

    // Entity yaratilgan blok pozitsiyasi
    private BlockPos seatPos;

    public SeatEntity(EntityType<?> type, World world) {
        super(type, world);
        this.noPhysics = true;
    }

    /** Berilgan pozitsiyada seat yaratib, o'yinchini o'tqizadi */
    public static void sit(World world, BlockPos pos, PlayerEntity player, double yOffset) {
        if (world.isClientSide) return;

        // Agar o'sha joyda seat allaqachon bo'lsa — yangi yaratma
        boolean alreadySitting = world.getEntitiesOfClass(
                SeatEntity.class,
                new net.minecraft.util.math.AxisAlignedBB(pos)
        ).stream().anyMatch(e -> !e.getPassengers().isEmpty());

        if (alreadySitting) return;

        SeatEntity seat = ModEntities.SEAT.get().create(world);
        if (seat == null) return;

        seat.seatPos = pos;
        seat.setPos(pos.getX() + 0.5, pos.getY() + yOffset, pos.getZ() + 0.5);
        world.addFreshEntity(seat);
        player.startRiding(seat);
    }

    @Override
    public void tick() {
        super.tick();
        if (level.isClientSide) return;

        // Altiga blok yo'q bo'lsa — o'chir
        if (seatPos != null && level.getBlockState(seatPos).getBlock() == Blocks.AIR) {
            this.ejectPassengers();
            this.remove();
            return;
        }

        // O'yinchi yo'q bo'lsa — o'chir
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

    @Override
    public void ejectPassengers() {
        super.ejectPassengers();
        if (!level.isClientSide) {
            this.remove();
        }
    }
}
