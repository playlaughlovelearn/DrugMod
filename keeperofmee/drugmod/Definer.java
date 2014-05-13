package keeperofmee.drugmod;

import keeperofmee.drugmod.blocks.DmChemicalExtractor;
import keeperofmee.drugmod.blocks.DmCocaCrop;
import keeperofmee.drugmod.blocks.DmHotWater;
import keeperofmee.drugmod.effects.PotionDrugMod;
import keeperofmee.drugmod.items.DmCocaLeaf;
import keeperofmee.drugmod.items.DmHotWaterBucket;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemSeeds;
import net.minecraftforge.fluids.Fluid;

public class Definer {

	public static void addFluidDefinitions(){
		DrugMod m = new DrugMod();
		
    	m.HotWaterFluid = new Fluid("hotwater").setTemperature(373).setViscosity(1200);

	}
	
	public static void addBlockDefinitions(){
		DrugMod m = new DrugMod();
		
    	m.CocaCrop = new DmCocaCrop().setBlockName("cocaCrop");
    	m.HotWaterBlock = new DmHotWater().setBlockName("hotWater");
    	m.ChemicalExtractorIdle = new DmChemicalExtractor(false).setBlockName("extractorChemicalIdle").setHardness(3.5F).setCreativeTab(m.drugTab); 
		m.ChemicalExtractorActive = new DmChemicalExtractor(true).setBlockName("extractorChemicalActive").setHardness(3.5F).setLightLevel(0.9F);
		
	}
	
	public static void addItemDefinitions(){
		DrugMod m = new DrugMod();

    	m.CocaSeeds = new ItemSeeds(m.CocaCrop, Blocks.farmland).setTextureName(DrugMod.modid + ":" + "seedCoca").setUnlocalizedName("seedCoca").setCreativeTab(m.drugTab);
    	m.CocaLeaf = new DmCocaLeaf("leafCoca");
    	m.HotWaterBucket = new DmHotWaterBucket(m.HotWaterBlock).setTextureName(DrugMod.modid + ":" + "bucketHotWater");
    	
	}
	
	public static void addPotionDefinitions(){
		DrugMod m = new DrugMod();
		
    	m.waterHot = (new PotionDrugMod(32, true, 0)).setIconIndex(1,1).setPotionName("potion.waterHot");

	}
}
