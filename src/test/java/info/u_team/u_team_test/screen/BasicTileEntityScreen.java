package info.u_team.u_team_test.screen;

import com.mojang.blaze3d.vertex.PoseStack;

import info.u_team.u_team_core.gui.elements.UButton;
import info.u_team.u_team_core.gui.elements.USlider;
import info.u_team.u_team_core.screen.UBasicContainerScreen;
import info.u_team.u_team_test.TestMod;
import info.u_team.u_team_test.container.BasicTileEntityContainer;
import io.netty.buffer.Unpooled;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class BasicTileEntityScreen extends UBasicContainerScreen<BasicTileEntityContainer> {

	private static final ResourceLocation BACKGROUND = new ResourceLocation(TestMod.MODID, "textures/gui/tileentity.png");

	private USlider slider;

	public BasicTileEntityScreen(BasicTileEntityContainer container, Inventory playerInventory, Component title) {
		super(container, playerInventory, title, BACKGROUND, 176, 173);
	}

	@Override
	protected void init() {
		super.init();
		addRenderableWidget(new UButton(leftPos + imageWidth / 2 - 25, topPos + 3, 50, 15, Component.nullToEmpty("Add 100"), button -> {
			menu.getValueMessage().triggerMessage();
		}));

		slider = addRenderableWidget(new USlider(leftPos + 7, topPos + 19, 162, 20, Component.nullToEmpty("Cooldown: "), Component.nullToEmpty(" Ticks"), 0, 100, menu.getTileEntity().cooldown, false, true, true, slider -> {
			menu.getCooldownMessage().triggerMessage(() -> new FriendlyByteBuf(Unpooled.copyShort(slider.getValueInt())));
		}));
	}

	@Override
	public void containerTick() {
		super.containerTick();
		slider.setValue(menu.getTileEntity().cooldown);
	}

	@Override
	protected void renderLabels(PoseStack matrixStack, int mouseX, int mouseY) {
		super.renderLabels(matrixStack, mouseX, mouseY);
		font.draw(matrixStack, Component.nullToEmpty("" + menu.getTileEntity().value), imageWidth / 2 + 32, 6, 0x404040);
	}

	@Override
	public boolean mouseReleased(double mouseX, double mouseY, int button) {
		slider.mouseReleased(mouseX, mouseY, button);
		return super.mouseReleased(mouseX, mouseY, button);
	}
}
