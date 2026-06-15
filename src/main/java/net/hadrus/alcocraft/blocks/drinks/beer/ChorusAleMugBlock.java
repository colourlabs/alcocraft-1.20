package net.hadrus.alcocraft.blocks.drinks.beer;

import net.hadrus.alcocraft.blocks.drinks.MugBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ChorusAleMugBlock extends MugBlock {
    public ChorusAleMugBlock(Properties properties) { super(properties); }

    @Override
    public void animateTick(BlockState pState, Level pLevel, BlockPos pPos, RandomSource pRand) {
        if (pRand.nextFloat() < 0.4f) {
            for (int i = 0; i < 3; ++i) {
                int j = pRand.nextInt(2) * 2 - 1;
                int k = pRand.nextInt(2) * 2 - 1;
                double d0 = pPos.getX() + 0.5D + 0.25D * j;
                double d1 = pPos.getY() + pRand.nextFloat();
                double d2 = pPos.getZ() + 0.5D + 0.25D * k;
                double d3 = pRand.nextFloat() * (float) j;
                double d4 = (pRand.nextFloat() - 0.5D) * 0.125D;
                double d5 = pRand.nextFloat() * (float) k;
                pLevel.addParticle(ParticleTypes.PORTAL, d0, d1, d2, d3, d4, d5);
            }
        }
    }
}