package info.u_team.u_team_core.data;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

import net.minecraft.data.CachedOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;

public abstract class CommonDatapackBuiltinEntriesProvider implements CommonDataProvider<Consumer<DatapackBuiltinEntriesProvider>> {
	
	private final GenerationData generationData;
	
	public CommonDatapackBuiltinEntriesProvider(GenerationData generationData) {
		this.generationData = generationData;
	}
	
	@Override
	public GenerationData getGenerationData() {
		return generationData;
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public CompletableFuture<?> run(CachedOutput cachedOutput) {
		final List<CompletableFuture<?>> futures = new ArrayList<>();
		register(provider -> {
			futures.add(provider.run(cachedOutput));
		});
		return CompletableFuture.allOf(futures.toArray(CompletableFuture[]::new));
	}
	
	@Override
	public String getName() {
		return "Datapack-Builtin-Entries-Provider";
	}
}
