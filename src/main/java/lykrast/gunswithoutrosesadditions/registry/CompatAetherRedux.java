package lykrast.gunswithoutrosesadditions.registry;

import lykrast.gunswithoutrosesadditions.CompatModids;
import lykrast.gunswithoutrosesadditions.item.aetherredux.VeridiumGunInfusedItem;
import lykrast.gunswithoutrosesadditions.item.aetherredux.VeridiumGunItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.registries.RegistryObject;

public class CompatAetherRedux {
	public static RegistryObject<VeridiumGunItem> veridiumRevolver;
	public static RegistryObject<VeridiumGunInfusedItem> veridiumRevolverInfused;
	@ObjectHolder(registryName = CompatModids.ITEM, value = CompatModids.AETHER_REDUX + ":veridium_ingot")
	public static Item VERIDIUM_INGOT = null;
	
	public static void registerItems() {
		//veridium tools have 750 durability, the sword got 1.5 attack speed and 4 damage so weaker than wood even
		//also what do you mean 0 enchatability?????
		veridiumRevolver = GWRAItems.initItem(() -> (VeridiumGunItem)new VeridiumGunItem(GWRAItems.defP().durability(1026), 0, 1, 18, 0.75, 10).repair(() -> Ingredient.of(VERIDIUM_INGOT)), "veridium_revolver");
		//infused sword gets 20% attack speed and +2 damage
		//but infused sword sucks ass (it's just +0.2 attack speed over iron, so outclassed by zanite) so I'm making that better
		veridiumRevolverInfused = GWRAItems.initItem(() -> (VeridiumGunInfusedItem)new VeridiumGunInfusedItem(GWRAItems.defP().durability(1026), 2, 1, 13, 0.75, 10).repair(() -> Ingredient.of(VERIDIUM_INGOT)), "veridium_revolver_infused");
		//doing some fugly casting here to get my functions available in the below
	}
	
	public static void makeTheVeridiumRevolversKiss() {
		veridiumRevolver.get().linkWith(veridiumRevolverInfused);
		veridiumRevolverInfused.get().linkWith(veridiumRevolver);
	}
}
