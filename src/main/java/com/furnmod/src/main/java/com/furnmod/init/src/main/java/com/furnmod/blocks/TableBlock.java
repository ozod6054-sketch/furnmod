package com.furnmod.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.world.IBlockReader;

public class TableBlock extends Block {

    // Stol shakli: yuza + to'rt oyoq
    private static final VoxelShape TABLE_TOP  = Block.box(0,  14, 0,  16, 16, 16);
    private static final VoxelShape LEG_NW     = Block.box(1,  0,  1,  3,  14, 3);
    private static final VoxelShape LEG_NE     = Block.box(13, 0,  1,  15, 14, 3);
    private static final VoxelShape LEG_SW     = Block.box(1,  0,  13, 3,  14, 15);
    private static final VoxelShape LEG_SE     = Block.box(13, 0,  13, 15, 14, 15);

    private static final VoxelShape SHAPE = VoxelShapes.or(
            TABLE_TOP, LEG_NW, LEG_NE, LEG_SW, LEG_SE);

    public TableBlock(Properties properties) {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, IBlockReader world,
                               BlockPos pos, ISelectionContext context) {
        return SHAPE;
    }
}
