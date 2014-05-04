package keeperofmee.drugmod;

import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

import keeperofmee.drugmod.blocks.DmChemicalExtractor;
import keeperofmee.drugmod.blocks.DmCocaCrop;
import keeperofmee.drugmod.blocks.DmHotWater;
import keeperofmee.drugmod.effects.PotionDrugMod;
import keeperofmee.drugmod.handlers.BucketHandler;
import keeperofmee.drugmod.handlers.GuiHandler;
import keeperofmee.drugmod.hooks.DrugModEventHooks;
import keeperofmee.drugmod.items.DmCocaLeaf;
import keeperofmee.drugmod.items.DmHotWaterBucket;
import keeperofmee.drugmod.proxy.CommonProxy;
import keeperofmee.drugmod.tileentities.TileEntityChemicalExtractor;
import net.minecraft.block.Block;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialLiquid;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeeds;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidContainerRegistry;
import net.minecraftforge.fluids.FluidRegistry;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameRegistry;



@Mod(modid="drugmod", name="drugmod", version="1.0")
public class DrugMod {

	//modid = CostumeCraft
	public static final String modid = "drugmod";
	
        // The instance of your mod that Forge uses.
        @Instance("DrugMod")
        public static DrugMod instance;
       
      //Creative tab
        public static CreativeTabs drugTab = new CreativeTabs("drugTab"){
    		public Item getTabIconItem() {
    			return Items.apple;
    		}
    		
    	};
        public static final int hotwaterID = 4051;
        public static final int guiIdChemicalExtractor = 0;

         public static Item CocaSeeds;
         public static Item CocaLeaf;
         public static Item HotWaterBucket;

         public static Block CocaCrop;
         public static Block HotWaterBlock;
         public static Block ChemicalExtractorIdle;
         public static Block ChemicalExtractorActive;
         
         public static Fluid HotWaterFluid;
         
         public static Material HotWaterMaterial;     
         
         public static Potion waterHot;
         




        @SidedProxy(clientSide="keeperofmee.drugmod.proxy.ClientProxy", serverSide="keeperofmee.drugmod.proxy.CommonProxy")
        public static CommonProxy proxy;
 
        
        @EventHandler // used in 1.6.2
        public void preInit(FMLPreInitializationEvent event){ 
        	instance = this;

        	//Potion
        	Potion[] potionTypes = null;

        	for (Field f : Potion.class.getDeclaredFields()) {
        	f.setAccessible(true);
        	try {
        	if (f.getName().equals("potionTypes") || f.getName().equals("field_76425_a")) {
        	Field modfield = Field.class.getDeclaredField("modifiers");
        	modfield.setAccessible(true);
        	modfield.setInt(f, f.getModifiers() & ~Modifier.FINAL);

        	potionTypes = (Potion[])f.get(null);
        	final Potion[] newPotionTypes = new Potion[256];
        	System.arraycopy(potionTypes, 0, newPotionTypes, 0, potionTypes.length);
        	f.set(null, newPotionTypes);
        	}
        	}
        	catch (Exception e) {
        	System.err.println("Severe error, please report this to the mod author:");
        	System.err.println(e);
        	}
        	}

        	
        	
        	//Register Tileentities
    	    GameRegistry.registerTileEntity(TileEntityChemicalExtractor.class, "Chemical Extractor");
    		
        	//Registers1 <<
        	MinecraftForge.EVENT_BUS.register(new DrugModEventHooks());
    		NetworkRegistry.INSTANCE.registerGuiHandler(this, new GuiHandler());
    		
        	//Fluids
        	HotWaterFluid = new Fluid("hotwater").setTemperature(373).setViscosity(1200);
        	FluidRegistry.registerFluid(HotWaterFluid);
        	        	
        	//Blocks
        	CocaCrop = new DmCocaCrop().setBlockName("cocaCrop");
        	HotWaterBlock = new DmHotWater().setBlockName("hotWater");
        	ChemicalExtractorIdle = new DmChemicalExtractor(false).setBlockName("extractorChemicalIdle").setHardness(3.5F).setCreativeTab(drugTab); 
    		ChemicalExtractorActive = new DmChemicalExtractor(true).setBlockName("extractorChemicalActive").setHardness(3.5F).setLightLevel(0.9F);

        	
        	//Items
        	CocaSeeds = new ItemSeeds(CocaCrop, Blocks.farmland).setTextureName(modid + ":" + "seedCoca").setUnlocalizedName("seedCoca").setCreativeTab(drugTab);
        	CocaLeaf = new DmCocaLeaf("leafCoca");
        	HotWaterBucket = new DmHotWaterBucket(HotWaterBlock).setTextureName(DrugMod.modid + ":" + "bucketHotWater");
        	
        	//FluidContainer
        	FluidContainerRegistry.registerFluidContainer(FluidRegistry.getFluidStack("hotwater", FluidContainerRegistry.BUCKET_VOLUME), new ItemStack(HotWaterBucket), new ItemStack(Items.bucket));
        	BucketHandler.INSTANCE.buckets.put(HotWaterBlock, HotWaterBucket);
        	MinecraftForge.EVENT_BUS.register(BucketHandler.INSTANCE);

        	//Potion Effect
        	waterHot = (new PotionDrugMod(32, true, 0)).setIconIndex(1,1).setPotionName("potion.waterHot");
        	
        	//Register Blocks
        	GameRegistry.registerBlock(CocaCrop, "CocaCrop");
        	GameRegistry.registerBlock(HotWaterBlock, "HotWaterBlock");
    		GameRegistry.registerBlock(ChemicalExtractorIdle, "ChemicalExtractorIdle");
    		GameRegistry.registerBlock(ChemicalExtractorActive, "ChemicalExtractorActive");
    		
        	
        	//Register Items
        	GameRegistry.registerItem(CocaSeeds , "CocaSeeds");
        	GameRegistry.registerItem(CocaLeaf , "CocaLeaf");
        	GameRegistry.registerItem(HotWaterBucket , "HotWaterBucket");
    		



        	
        	MinecraftForge.addGrassSeed(new ItemStack(CocaSeeds), 10);

	
        	
        }
        

		@EventHandler
        public void load(FMLInitializationEvent event) {
                proxy.registerRenderers();
                

        }
        
        @EventHandler
        public void postInit(FMLPostInitializationEvent event) {
                // Stub Method
        }
        

        

}


