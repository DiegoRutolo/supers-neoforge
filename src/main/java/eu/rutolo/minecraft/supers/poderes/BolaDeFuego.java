package eu.rutolo.minecraft.supers.poderes;

import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.world.entity.projectile.LargeFireball;
import net.minecraft.world.phys.Vec3;

public class BolaDeFuego extends HumanoSinClase implements ISuperpoder {
	
	private static final Logger LOGGER = LogUtils.getLogger();
	
	private int explosion;
	private Vec3 posOffset;
	
	public BolaDeFuego() {
		super(PoderesUtils.Poderes.BOLA_DE_FUEGO);
		this.explosion = 1;
		this.posOffset = new Vec3(4.0, 1.0, 4.0);
	}
	
	@Override
	public void activar() {
		LOGGER.info("Lanza una {}", getPoder().toString());
		
		if (!player.level().isClientSide) {
			Vec3 pViewVec = player.getViewVector(1f);
			Vec3 posicion = new Vec3(player.getX() + pViewVec.x * posOffset.x, 
					player.getY() + pViewVec.y * posOffset.y,
					player.getZ() + pViewVec.z * posOffset.z);
			
			LargeFireball proyectil = new LargeFireball(player.level(), player, pViewVec.normalize(), explosion);
			proyectil.setPos(posicion);
			player.level().addFreshEntity(proyectil);
		}
	}
	
	@Override
	public boolean isPasiva() {
		return false;
	}

}
