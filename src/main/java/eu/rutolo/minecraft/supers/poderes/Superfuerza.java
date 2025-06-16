package eu.rutolo.minecraft.supers.poderes;

import eu.rutolo.minecraft.supers.Supers;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class Superfuerza extends HumanoSinClase {
	
	private int attackDamage;
	private String modifNameAD;
	private int knockback;
	private String modifNameK;
	private int blockBreakSpeed;
	private String modifNameBBS;
	
	public Superfuerza() {
		super(PoderesUtils.PoderesType.SUPERFUERZA);
		this.attackDamage = 16;
		this.modifNameAD = getNombre().toLowerCase() + "_ad";
		this.knockback = 2;
		this.modifNameK = getNombre().toLowerCase() + "_k";
		this.blockBreakSpeed = 2;
		this.modifNameBBS = getNombre().toLowerCase() + "_bbs";
	}

	@Override
	public void activar() {
		// Ataque
		ResourceLocation idAD = ResourceLocation.fromNamespaceAndPath(Supers.MODID, modifNameAD);
		AttributeModifier modifAD = new AttributeModifier(idAD, attackDamage, AttributeModifier.Operation.ADD_VALUE);
		player.getAttribute(Attributes.ATTACK_DAMAGE).addOrReplacePermanentModifier(modifAD);
		
		// Knockback
		ResourceLocation idK = ResourceLocation.fromNamespaceAndPath(Supers.MODID, modifNameK);
		AttributeModifier modifK = new AttributeModifier(idK, knockback, AttributeModifier.Operation.ADD_VALUE);
		player.getAttribute(Attributes.ATTACK_KNOCKBACK).addOrReplacePermanentModifier(modifK);
		
		// BlockBreakSpeed
		ResourceLocation idBBS = ResourceLocation.fromNamespaceAndPath(Supers.MODID, modifNameBBS);
		AttributeModifier modifBBS = new AttributeModifier(idBBS, blockBreakSpeed, AttributeModifier.Operation.ADD_VALUE);
		player.getAttribute(Attributes.BLOCK_BREAK_SPEED).addOrReplacePermanentModifier(modifBBS);
		
	}
	
	@Override
	public boolean isPasiva() {
		return true;
	}

}
