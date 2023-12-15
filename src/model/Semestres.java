package model;

public class Semestres
{

	/*-------------------------------------------------------------*/
	/*                           ATTRIBUTS                         */
	/*-------------------------------------------------------------*/

	/** Numéro du semestre. */
	private int numSem;

	/** Nombre de groupe TD dans le semestres. */
	private int nbGpTdSem;

	/** Nombre de groupe TP dans le semestres. */
	private int nbGpTpSem;

	/** Nombre d'étudiants dans le semestres. */
	private int nbEtdSem;
	
	/** Nombre de semaine dans le semestres. */
	private int nbSemSem ;
	

	/*-------------------------------------------------------------*/
	/*                         CONSTRUCTEURS                       */
	/*-------------------------------------------------------------*/

	/**Constructeur prenant tous les paramètres.*/
	public Semestres (int num, int nbTd, int nbTp, int nbEtd, int sem)
	{
		this.numSem    = num;
		this.nbGpTdSem = nbTd;
		this.nbGpTpSem = nbTp;
		this.nbEtdSem  = nbEtd;
		this.nbSemSem  = sem;
	}

	

	/*-------------------------------------------------------------*/
	/*                           GET-TEURS                         */
	/*-------------------------------------------------------------*/

	public int getNumSem    () {return this.numSem    ;}
	public int getNbGpTdSem () {return this.nbGpTdSem ;}
	public int getNbGpTpSem () {return this.nbGpTpSem ;}
	public int getNbEtdSem  () {return this.nbEtdSem  ;}
	public int getNbSemSem  () {return this.nbSemSem  ;}

	

	/*-------------------------------------------------------------*/
	/*                           SET-TEURS                         */
	/*-------------------------------------------------------------*/

	public void setNumSem    (int numSem   ) { this.numSem    = numSem    ;}
	public void setNbGpTdSem (int nbGpTdSem) { this.nbGpTdSem = nbGpTdSem ;}
	public void setNbGpTpSem (int nbGpTpSem) { this.nbGpTpSem = nbGpTpSem ;}
	public void setNbEtdSem  (int nbEtdSem ) { this.nbEtdSem  = nbEtdSem  ;}
	public void setNbSemSem  (int nbSemSem ) { this.nbSemSem  = nbSemSem  ;}

	

	/*-------------------------------------------------------------*/
	/*                             AUTRES                          */
	/*-------------------------------------------------------------*/

	public String toString ()
	{
		return String.format("Sem %1d (%3d etds) : %2d TP et %2d TP sur %3d semaines", this.numSem, this.nbEtdSem, this.nbGpTdSem, this.nbGpTpSem, this.nbSemSem);
	}
}