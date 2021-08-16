package info.u_team.u_team_core.gui.elements;

import com.mojang.blaze3d.vertex.PoseStack;

import info.u_team.u_team_core.util.GuiUtil;
import info.u_team.u_team_core.util.RenderUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.resources.ResourceLocation;

public class CheckboxButton extends UButton {
	
	protected static final ResourceLocation TEXTURE = new ResourceLocation("textures/gui/checkbox.png");
	
	protected boolean checked;
	
	protected boolean drawText;
	protected boolean leftSideText;
	protected boolean dropShadow;
	
	public CheckboxButton(int x, int y, int width, int height, Component text, boolean checked, boolean drawText) {
		this(x, y, width, height, text, checked, drawText, EMTPY_PRESSABLE);
	}
	
	public CheckboxButton(int x, int y, int width, int height, Component text, boolean checked, boolean drawText, OnPress pessable) {
		this(x, y, width, height, text, checked, drawText, pessable, EMPTY_TOOLTIP);
	}
	
	public CheckboxButton(int x, int y, int width, int height, Component text, boolean checked, boolean drawText, OnTooltip tooltip) {
		this(x, y, width, height, text, checked, drawText, EMTPY_PRESSABLE, tooltip);
	}
	
	public CheckboxButton(int x, int y, int width, int height, Component text, boolean checked, boolean drawText, OnPress pessable, OnTooltip tooltip) {
		super(x, y, width, height, text, pessable, tooltip);
		this.checked = checked;
		this.drawText = drawText;
		leftSideText = false;
		dropShadow = false;
	}
	
	public boolean isChecked() {
		return checked;
	}
	
	public void setChecked(boolean checked) {
		this.checked = checked;
	}
	
	public boolean isDrawText() {
		return drawText;
	}
	
	public void setDrawText(boolean drawText) {
		this.drawText = drawText;
	}
	
	public boolean isLeftSideText() {
		return leftSideText;
	}
	
	public void setLeftSideText(boolean leftSideText) {
		this.leftSideText = leftSideText;
	}
	
	public void toggle() {
		checked = !checked;
	}
	
	@Override
	public void onPress() {
		toggle();
		super.onPress();
	}
	
	@Override
	public void renderButton(PoseStack matrixStack, int mouseX, int mouseY, float partialTicks) {
		final var minecraft = Minecraft.getInstance();
		
		minecraft.getTextureManager().bindForSetup(TEXTURE);
		
		RenderUtil.enableBlend();
		RenderUtil.defaultBlendFunc();
		GuiUtil.drawTexturedColoredQuad(matrixStack, x, y, width, height, 20, 20, isHovered() ? 20 : 0, checked ? 20 : 0, 64, 64, 0, getCurrentBackgroundColor(matrixStack, mouseX, mouseY, partialTicks));
		RenderUtil.disableBlend();
		
		renderBackground(matrixStack, minecraft, mouseX, mouseY, partialTicks);
		renderForeground(matrixStack, minecraft, mouseX, mouseY, partialTicks);
	}
	
	@Override
	public void renderForeground(PoseStack matrixStack, Minecraft minecraft, int mouseX, int mouseY, float partialTicks) {
		if (drawText) {
			final var fontRenderer = minecraft.font;
			
			final var message = getCurrentText();
			if (message != TextComponent.EMPTY) {
				final float xStart;
				final float yStart = y + (height - 8) / 2;
				
				if (leftSideText) {
					xStart = x - (fontRenderer.width(message) + 4);
				} else {
					xStart = x + width + 4;
				}
				
				final var color = getCurrentTextColor(matrixStack, mouseX, mouseY, partialTicks).getColorARGB();
				
				if (dropShadow) {
					fontRenderer.drawShadow(matrixStack, getCurrentText(), xStart, yStart, color);
				} else {
					fontRenderer.draw(matrixStack, getCurrentText(), xStart, yStart, color);
				}
			}
		}
	}
}
