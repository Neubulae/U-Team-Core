package info.u_team.u_team_test.test_multiloader.data;

import info.u_team.u_team_core.data.GenerationData;
import info.u_team.u_team_test.test_multiloader.TestMultiLoaderMod;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderBlockStateProvider;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderBlockTagsProvider;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderDatapackBuiltinEntriesProvider;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderGlobalLootModifierProvider;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderItemModelProvider;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderLanguagesProvider;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderLootTableProvider;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderRecipeProvider;
import info.u_team.u_team_test.test_multiloader.data.provider.TestMultiLoaderSoundProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@EventBusSubscriber(modid = TestMultiLoaderMod.MODID, bus = Bus.MOD)
public class TestMultiLoaderDataGenerator {
	
	@SubscribeEvent
	public static void data(GatherDataEvent event) {
		final GenerationData data = new GenerationData(TestMultiLoaderMod.MODID, event);
		
		data.addProvider(event.includeServer(), TestMultiLoaderBlockTagsProvider::new);
		data.addProvider(event.includeServer(), TestMultiLoaderLootTableProvider::new);
		data.addProvider(event.includeServer(), TestMultiLoaderDatapackBuiltinEntriesProvider::new);
		data.addProvider(event.includeServer(), TestMultiLoaderRecipeProvider::new);
		data.addProvider(event.includeServer(), TestMultiLoaderSoundProvider::new);
		data.addProvider(event.includeServer(), TestMultiLoaderGlobalLootModifierProvider::new);
		
		data.addProvider(event.includeClient(), TestMultiLoaderBlockStateProvider::new);
		data.addProvider(event.includeClient(), TestMultiLoaderItemModelProvider::new);
		data.addProvider(event.includeClient(), TestMultiLoaderLanguagesProvider::new);
	}
}
