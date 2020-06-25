package info.u_team.u_team_test.data.provider;

import info.u_team.u_team_core.data.*;
import info.u_team.u_team_test.init.*;

public class TestItemModelsProvider extends CommonItemModelsProvider {
	
	public TestItemModelsProvider(GenerationData data) {
		super(data);
	}
	
	@Override
	protected void registerModels() {
		// Items
		simpleGenerated(TestItems.BASIC);
		simpleGenerated(TestItems.BASIC_FOOD);
		simpleGenerated(TestItems.BETTER_ENDERPEARL);
		iterateItems(TestItems.BASIC_ARMOR, this::simpleGenerated);
		iterateItems(TestItems.BASIC_TOOL, this::simpleHandheld);
		// Blocks
		simpleBlock(TestBlocks.BASIC.get());
		simpleBlock(TestBlocks.BASIC_TILEENTITY.get());
		simpleBlock(TestBlocks.BASIC_ENERGY_CREATOR.get());
		simpleBlock(TestBlocks.BASIC_FLUID_INVENTORY.get());
	}
	
}
