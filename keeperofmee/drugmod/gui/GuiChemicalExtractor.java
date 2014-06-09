package keeperofmee.drugmod.gui;

import keeperofmee.drugmod.containers.ContainerChemicalExtractor;
import keeperofmee.drugmod.main.DrugMod;
import keeperofmee.drugmod.tileentities.TileEntityChemicalExtractor;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.util.ResourceLocation;

public class GuiChemicalExtractor extends GuiContainer{

	public static ResourceLocation texture = new ResourceLocation( DrugMod.modid + ":" + "textures/gui/ChemicalExtractor.png");
	public TileEntityChemicalExtractor chemicalextractor;
	
	public GuiChemicalExtractor(InventoryPlayer inventoryPlayer, TileEntityChemicalExtractor entity) {
		super(new ContainerChemicalExtractor(inventoryPlayer,entity));
		
		this.chemicalextractor = entity;
		
		this.xSize = 176;
		this.ySize = 166;
	}

	public void drawGuiContainerForegroundLayer(int par1, int par2){
		String name = this.chemicalextractor.hasCustomInventoryName() ? this.chemicalextractor.getInventoryName() : I18n.format("Chemical Extractor", new Object[0]);
	
	this.fontRendererObj.drawString(name, this.xSize / 2 - this.fontRendererObj.getStringWidth(name) / 2, 6, 4210752);
	this.fontRendererObj.drawString(I18n.format("container.inventory"), 8, this.ySize - 96 + 2, 4210752);
	}
	
	protected void drawGuiContainerBackgroundLayer(float f, int i, int j) {
GL11.glColor4f(1F, 1F, 1F, 1F);
		
		Minecraft.getMinecraft().getTextureManager().bindTexture(texture);
		drawTexturedModalRect(guiLeft, guiTop, 0, 0, xSize, ySize);
		
		if(this.chemicalextractor.isBurning()){
			int k = this.chemicalextractor.getBurnTimeRemainingScaled(12);
			drawTexturedModalRect(guiLeft + 56, guiTop + 36 + 12 - k, 176, 12-k, 14, k + 2); 
		}
		
		int k = this.chemicalextractor.getCookProgressScaled(24);
		drawTexturedModalRect(guiLeft + 79, guiTop + 34, 176, 14, k + 1, 16);
		
	}

}
