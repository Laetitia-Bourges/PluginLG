package fr.wintersnow.monplugin.enums;

import java.lang.reflect.Constructor;

import fr.wintersnow.monplugin.functions.Role;
import fr.wintersnow.monplugin.roles.villageois.SimpleVillageois;
import fr.wintersnow.monplugin.roles.villageois.Sorciere;
import fr.wintersnow.monplugin.roles.villageois.Voyante;
import fr.wintersnow.monplugin.roles.villageois.PetiteFille;
import fr.wintersnow.monplugin.roles.villageois.Salvateur;
import fr.wintersnow.monplugin.roles.villageois.Assassin;
import fr.wintersnow.monplugin.roles.villageois.Renard;
import fr.wintersnow.monplugin.roles.villageois.Cupidon;
import fr.wintersnow.monplugin.roles.loupgarous.LoupBasic;
import fr.wintersnow.monplugin.roles.loupgarous.LoupBlanc;
import fr.wintersnow.monplugin.roles.loupgarous.VilainPetitLoup;

public enum RoleType {

	
	SimpleVillageois(0, SimpleVillageois.class),
	Sorciere(1, Sorciere.class),
	Voyante(2, Voyante.class),
	PetiteFille(3, PetiteFille.class),
	Salvateur(4, Salvateur.class),
	Assassin(5, Assassin.class),
	Renard(6, Renard.class),
	Cupidon(7, Cupidon.class),
	LoupBasic(8,LoupBasic.class),
	LoupBlanc(9, LoupBlanc.class),
	VilainPetitLoup(10, VilainPetitLoup.class),
	;
	
	private Class<? extends Role> clazz = null;
	private Constructor<? extends Role> constructor = null;
	private Role role;
	private int id;
	
	private RoleType(int id, Class<? extends Role> clazz) {
		this.id = id;
		this.clazz = clazz;
		try{
			@SuppressWarnings("unchecked")
			Constructor<? extends Role>[] ctors = (Constructor<? extends Role>[]) clazz.getDeclaredConstructors();
			Constructor<? extends Role> ctor = null;
			for (int i = 
					0; i < ctors.length; i++) {
			    ctor = ctors[i];
			    if (ctor.getGenericParameterTypes().length == 0)
				break; //TODO: Supprimer ?
			}
			ctor.setAccessible(true);
			constructor = ctor;
		}catch(Exception e){
			System.out.println("[Role] Could not get Constructor of " + name());
		}
		this.role = create();
	}
	
	public int getID() {
		return id;
	}
	
	public Role getInstance() {
		return role;
	}
	
	public Class<? extends Role> getRoleClass() {
		return clazz;
	}

	public Constructor<? extends Role> getConstructor() {
		return constructor;
	}
	
	public Role create() {
		try {
			return getConstructor().newInstance();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
}
