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

public class ChairBlock extends Block {

    public static final DirectionProperty FACING = HorizontalBlock.FACING;

    // Stul shakli: o'tirish joyi + orqa tayanchiq + 4 oyoq
    private static final VoxelShape SEAT     = Block.box(2,  6,  2,  14, 9,  14);
    private static final VoxelShape BACK     = Block.box(2,  9,  12, 14, 18, 14);
    private static final VoxelShape LEG_NW   = Block.box(2,  0,  2,  4,  6,  4);
    private static final VoxelShape LEG_NE   = Block.box(12, 0,  2,  14, 6,  4);
    private static final VoxelShape LEG_SW   = Block.box(2,  0,  12, 4,  6,  14);
    private static final VoxelShape LEG_SE   = Block.box(12, 0,  12, 14, 6,  14);

    private static final VoxelShape SHAPE =
            VoxelShapes.or(SEAT, BACK, LEG_NW, LEG_NE, LEG_SW, LEG_SE);

    public ChairBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any()
                .setValue(FACING, Direction.NORTH));
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world,
                               BlockPos pos, ISelectionContext ctx) {
        return SHAPE;
    }

    /** O'ng tugma - o'yinchini stulga o'tqizadi */
    @Override
    public ActionResultType use(BlockState state, World world, BlockPos pos,
                                PlayerEntity player, Hand hand,
                                BlockRayTraceResult hit) {
        if (!world.isClientSide) {
            // Allaqachon birov o'tiribdi - o'tqizma
            if (!world.getEntitiesOfClass(
                    net.minecraft.entity.Entity.class,
                    new net.minecraft.util.math.AxisAlignedBB(pos)).isEmpty()) {
                return ActionResultType.CONSUME;
            }
            // 0.3 - o'yinchi stul o'tirish yuzasida ko'rinadi
            SeatEntity.sit(world, pos, player, 0.3);
        }
        return ActionResultType.sidedSuccess(world.isClientSide);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext ctx) {
        return this.defaultBlockState()
                .setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
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
