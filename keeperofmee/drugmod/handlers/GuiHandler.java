package keeperofmee.drugmod.handlers;

import keeperofmee.drugmod.DrugMod;
import keeperofmee.drugmod.containers.ContainerChemicalExtractor;
import keeperofmee.drugmod.gui.GuiChemicalExtractor;
import keeperofmee.drugmod.tileentities.TileEntityChemicalExtractor;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.world.World;
import cpw.mods.fml.common.network.IGuiHandler;

public class GuiHandler implements IGuiHandler {

	@Override
	public Object getServerGuiElement(int ID, EntityPlayer player, World world, int x, int y, int z) {
	TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null){
			switch(ID){
				case DrugMod.guiIdChemicalExtractor:
					if(entity instanceof TileEntityChemicalExtractor){
						return new ContainerChemicalExtractor(player.inventory, (TileEntityChemicalExtractor) entity);
					}
			}
		}
		return null;
	}

	@Override
	public Object getClientGuiElement(int ID, EntityPlayer player, World world,	int x, int y, int z) {
		TileEntity entity = world.getTileEntity(x, y, z);
		
		if(entity != null){
			switch(ID){
				case DrugMod.guiIdChemicalExtractor:
					if(entity instanceof TileEntityChemicalExtractor){
						return new GuiChemicalExtractor(player.inventory, (TileEntityChemicalExtractor) entity);
					}
			}
		}
		return null;
	}

}
