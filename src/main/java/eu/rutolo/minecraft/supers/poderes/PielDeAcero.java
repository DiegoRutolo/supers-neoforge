package eu.rutolo.minecraft.supers.poderes;

import eu.rutolo.minecraft.supers.Supers;
import eu.rutolo.minecraft.supers.poderes.PoderesUtils.PoderesType;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;

public class PielDeAcero extends HumanoSinClase {
	
	private int armor;
	private String modifNameA;
	private int armorToughness;
	private String modifNameAT;
	
	public PielDeAcero() {
		super(PoderesType.PIEL_DE_ACERO);
		this.armor = 15;
		this.modifNameA = getCodigo().toLowerCase() + "_a";
		this.armorToughness = 10;
		this.modifNameAT = getCodigo().toLowerCase() + "_at";
	}

	@Override
	public boolean isPasiva() {
		return true;
	}
	
	@Override
	public void activar() {
		ResourceLocation idA = ResourceLocation.fromNamespaceAndPath(Supers.MODID, modifNameA);
		AttributeModifier modifA = new AttributeModifier(idA, armor, AttributeModifier.Operation.ADD_VALUE);
		player.getAttribute(Attributes.ARMOR).addOrReplacePermanentModifier(modifA);

		ResourceLocation idAT = ResourceLocation.fromNamespaceAndPath(Supers.MODID, modifNameAT);
		AttributeModifier modifAT = new AttributeModifier(idAT, armorToughness, AttributeModifier.Operation.ADD_VALUE);
		player.getAttribute(Attributes.ARMOR_TOUGHNESS).addOrReplacePermanentModifier(modifAT);
	}
	
}
