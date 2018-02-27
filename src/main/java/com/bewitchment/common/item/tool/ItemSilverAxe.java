package com.bewitchment.common.item.tool;

import com.bewitchment.api.helper.IModelRegister;
import com.bewitchment.client.handler.ModelHandler;
import com.bewitchment.common.core.ModCreativeTabs;
import com.bewitchment.common.item.ModMaterials;
import com.bewitchment.common.lib.LibItemName;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

/**
 * This class was created by BerciTheBeast on 27.3.2017.
 * It's distributed as part of Bewitchment under
 * the MIT license.
 * Parts of the code were created by Vazkii, for Botania.
 */
public class ItemSilverAxe extends ItemAxe implements IModelRegister {

	public ItemSilverAxe() {
		super(ModMaterials.TOOL_SILVER, ModMaterials.TOOL_SILVER.getAttackDamage(), ModMaterials.TOOL_SILVER.getEfficiency());
		this.setMaxStackSize(1);
		setRegistryName(LibItemName.SILVER_AXE);
		setUnlocalizedName(LibItemName.SILVER_AXE);
		setCreativeTab(ModCreativeTabs.ITEMS_CREATIVE_TAB);
	}

	@Override
	public boolean hitEntity(ItemStack stack, EntityLivingBase target, @Nonnull EntityLivingBase attacker) {
		if (!target.world.isRemote) {
			if (target.getCreatureAttribute() == EnumCreatureAttribute.UNDEAD && attacker instanceof EntityPlayer) {
				target.attackEntityFrom(DamageSource.causePlayerDamage((EntityPlayer) attacker), 12);
				stack.damageItem(25, attacker);
			} else {
				stack.damageItem(1, attacker);
			}
		}

		return true;
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void registerModel() {
		ModelHandler.registerModel(this, 0);
	}
}
