package model.modules;

import model.Semestres;

public class ModuleSae extends Module {

	public ModuleSae(String libelleLongModule, String libelleCourtModule, Semestres semestres, int nbEtudiant,
			int nbGroupeTD, int nbGroupeTP, boolean valide) {
		super(libelleLongModule, libelleCourtModule, semestres, nbEtudiant, nbGroupeTD, nbGroupeTP, valide);

	}

}