package keeperofmee.drugmod.items;

import keeperofmee.drugmod.DrugMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public class DmCocaLeaf extends Item{
	DrugMod m = new DrugMod();

	
	public DmCocaLeaf(String str){
		super();
		this.setMaxStackSize(64);
		this.setCreativeTab(m.drugTab);
		this.setUnlocalizedName(str);
		this.setTextureName(m.modid + ":" + str);

	}
	

}
