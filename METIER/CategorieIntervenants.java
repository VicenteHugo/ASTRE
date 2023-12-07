package METIER;

public class CategorieIntervenants
{

	/*-------------------------------------------------------------*/
	/*                           ATTRIBUTS                         */
	/*-------------------------------------------------------------*/

	/** Libellé de la catégorie de l'intervenants. */
	private String libCatInt;

	/** Coefficient de la catégorie de l'intervenants pour mettre ses heures en heures TD. */
	private float coefCatInt;

	/** Heur minimum demandé par la catégorie de l'intervenants. */
	private int heureMinCatInt;
	
	/** Heur minimum demandé par la catégorie de l'intervenants. */
	private int heureMaxCatInt;

	

	/*-------------------------------------------------------------*/
	/*                         CONSTRUCTEURS                       */
	/*-------------------------------------------------------------*/

	/**Constructeur prenant tous les paramètres.*/
	public CategorieIntervenants (String lib, float coef, int heurMax, int heurMin)
	{
		this.libCatInt = lib;
		this.coefCatInt = coef;
		this.heureMinCatInt = heurMin;
		this.heureMaxCatInt = heurMax;
	}

	

	/*-------------------------------------------------------------*/
	/*                           GET-TEURS                         */
	/*-------------------------------------------------------------*/

	public String getlibCatInt     () {return this.libCatInt;     }
	public float  getCoefCatInt    () {return this.coefCatInt;    }
	public int    getHeureMinCatInt() {return this.heureMinCatInt;}
	public int    getHeureMaxCatInt() {return this.heureMaxCatInt;}

	

	/*-------------------------------------------------------------*/
	/*                           SET-TEURS                         */
	/*-------------------------------------------------------------*/

	public void setlibCatInt     (String libCatInt     ) { this.libCatInt      = libCatInt     ;}
	public void setCoefCatInt    (float  CoefCatInt    ) { this.coefCatInt     = CoefCatInt    ;}
	public void setHeureMinCatInt(int    HeureMinCatInt) { this.heureMinCatInt = HeureMinCatInt;}
	public void setHeureMaxCatInt(int    HeureMaxCatInt) { this.heureMaxCatInt = HeureMaxCatInt;}

	

	/*-------------------------------------------------------------*/
	/*                             AUTRES                          */
	/*-------------------------------------------------------------*/

	public String toString ()
	{
		return String.format("\"%10s\" (%2.3f) [%4d - %-4d]", this.libCatInt, this.coefCatInt, this.heureMinCatInt, this.heureMaxCatInt);
	}
}