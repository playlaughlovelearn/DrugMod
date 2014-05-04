package keeperofmee.drugmod.effects;

import keeperofmee.drugmod.DrugMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.ITextureObject;
import net.minecraft.client.renderer.texture.TextureMap;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;

public class PotionDrugMod extends Potion {
    
    public PotionDrugMod(int par1, boolean par2, int par3) {
    	super(par1, par2, par3);
    }

    public Potion setIconIndex(int par1, int par2) 
	{
		
	super.setIconIndex(0,0);
	return this;
	}
	
    @Override
    public int getStatusIconIndex() 
		{
    	ResourceLocation r = new ResourceLocation(DrugMod.modid + "textures/effects/potionEffects.png");

    	ITextureObject texture = Minecraft.getMinecraft().renderEngine.getTexture(r);
    	Minecraft.getMinecraft().renderEngine.bindTexture(r);
	
	
    	return super.getStatusIconIndex();
	
	}

    
}