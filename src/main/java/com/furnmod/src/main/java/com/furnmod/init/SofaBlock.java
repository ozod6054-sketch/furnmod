package com.furnmod.blocks;

import com.furnmod.entity.SeatEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockRayTraceResult;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.World;

public class SofaBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    private static final VoxelShape SEAT  = Block.box(0, 0,  2, 16, 8,  16);
    private static final VoxelShape BACK  = Block.box(0, 8,  0, 16, 16, 4);
    private static final VoxelShape ARM_L = Block.box(0, 0,  0, 3,  12, 16);
    private static final VoxelShape ARM_R = Block.box(13, 0, 0, 16, 12, 16);

    private static final VoxelShape SHAPE = VoxelShapes.or(SEAT, BACK, ARM_L, ARM_R);

    public SofaBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world,
                               BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.defaultBlockState()
                .setValue(FACING, context.getHorizontalDirection().getOpposite());
    }

    // O'yinchi o'ng tugma bosса — divanga o'tiradi
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos,
                                PlayerEntity player, Hand hand,
                                BlockRayTraceResult hit) {
        if (!world.isClientSide) {
            // Divan o'tiradigan balandlik: 0.4
            SeatEntity.sit(world, pos, player, 0.4);
        }
        return ActionResultType.sidedSuccess(world.isClientSide);
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rotation) {
        return state.setValue(FACING, rotation.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }
}
