package keeperofmee.drugmod.items;

import keeperofmee.drugmod.DrugMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemBucket;

public class DmHotWaterBucket extends ItemBucket {

	public DmHotWaterBucket(Block p_i45331_1_) {
		super(p_i45331_1_);
		this.setUnlocalizedName("bucketHotWater");
		this.setCreativeTab(DrugMod.drugTab);
		
	}

}
