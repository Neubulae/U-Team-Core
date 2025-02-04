package info.u_team.u_team_test.init;

import info.u_team.u_team_core.api.registry.CommonRegister;
import info.u_team.u_team_core.api.registry.RegistryEntry;
import info.u_team.u_team_test.TestMod;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.material.Fluid;
import net.minecraftforge.fluids.ForgeFlowingFluid;

public class TestFluids {
	
	public static final CommonRegister<Fluid> FLUIDS = CommonRegister.create(Registries.FLUID, TestMod.MODID);
	
	private static ForgeFlowingFluid.Properties createTestProperties() {
		return new ForgeFlowingFluid.Properties(TestFluidTypes.TEST_FLUID, TEST_FLUID, TEST_FLUID_FLOWING).block(TestBlocks.TEST_FLUID).bucket(TestItems.TEST_FLUID_BUCKET);
	}
	
	public static final RegistryEntry<ForgeFlowingFluid> TEST_FLUID = FLUIDS.register("test_fluid", () -> new ForgeFlowingFluid.Source(createTestProperties()));
	public static final RegistryEntry<ForgeFlowingFluid> TEST_FLUID_FLOWING = FLUIDS.register("test_fluid_flowing", () -> new ForgeFlowingFluid.Flowing(createTestProperties()));
	
	static void register() {
		FLUIDS.register();
	}
	
}
