package info.u_team.u_team_core.intern.discord;

import com.mojang.blaze3d.matrix.MatrixStack;

import info.u_team.u_team_core.gui.elements.*;
import info.u_team.u_team_core.intern.config.ClientConfig;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.util.text.ITextComponent;
import net.minecraftforge.common.ForgeConfigSpec.BooleanValue;

public class DiscordConfigScreen extends Screen {
	
	private final Screen screenBefore;
	
	public DiscordConfigScreen(Screen screenBefore) {
		super(ITextComponent.getTextComponentOrEmpty("discord"));
		this.screenBefore = screenBefore;
	}
	
	@Override
	protected void init() {
		
		final BooleanValue discordRichPresence = ClientConfig.getInstance().discordRichPresence;
		
		final ITextComponent on = ITextComponent.getTextComponentOrEmpty("Discord connection is on");
		final ITextComponent off = ITextComponent.getTextComponentOrEmpty("Discord connection is off");
		
		final ActiveButton toggleDiscordRichPresenceButton = addButton(new ActiveButton(width / 2 - 100, 50, 200, 20, discordRichPresence.get() ? on : off, 0x006442FF));
		toggleDiscordRichPresenceButton.setActive(discordRichPresence.get());
		toggleDiscordRichPresenceButton.setPressable(() -> {
			discordRichPresence.set(!discordRichPresence.get());
			
			if (discordRichPresence.get() && !DiscordRichPresence.isEnabled()) {
				DiscordRichPresence.start();
				if (minecraft.world == null) {
					DiscordRichPresence.setIdling();
				} else {
					DiscordRichPresence.setDimension(minecraft.world);
				}
			} else if (!discordRichPresence.get() && DiscordRichPresence.isEnabled()) {
				DiscordRichPresence.stop();
			}
			
			toggleDiscordRichPresenceButton.setActive(discordRichPresence.get());
			toggleDiscordRichPresenceButton.setMessage(discordRichPresence.get() ? on : off);
		});
		
		addButton(new UButton(width / 2 - 100, 80, 200, 20, ITextComponent.getTextComponentOrEmpty("Done"), button -> closeScreen()));
	}
	
	@Override
	public void render(MatrixStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		renderBackground(matrixStack);
		drawCenteredString(matrixStack, font, title, width / 2, 15, 0xFFFFFF);
		super.render(matrixStack, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void closeScreen() {
		minecraft.displayGuiScreen(screenBefore);
	}
	
}
