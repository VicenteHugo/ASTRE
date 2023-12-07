package METIER;

public class CategorieHeures
{

	/*-------------------------------------------------------------*/
	/*                           ATTRIBUTS                         */
	/*-------------------------------------------------------------*/

	/** Libellé de la catégorie de l'intervenants. */
	private String libCatHeur;

	/** Coefficient de la catégorie de l'intervenants pour mettre ses heures en heures TD. */
	private float coefCatHeur;

	

	/*-------------------------------------------------------------*/
	/*                         CONSTRUCTEURS                       */
	/*-------------------------------------------------------------*/

	/**Constructeur prenant tous les paramètres.*/
	public CategorieHeures (String lib, float coef)
	{
		this.libCatHeur = lib;
		this.coefCatHeur = coef;
	}

	

	/*-------------------------------------------------------------*/
	/*                           GET-TEURS                         */
	/*-------------------------------------------------------------*/

	public String getlibCatHeur     () {return this.libCatHeur;     }
	public float  getcoefCatHeur    () {return this.coefCatHeur;    }

	

	/*-------------------------------------------------------------*/
	/*                           SET-TEURS                         */
	/*-------------------------------------------------------------*/

	public void setlibCatHeur     (String libCatHeur     ) { this.libCatHeur      = libCatHeur     ;}
	public void setcoefCatHeur    (float  coefCatHeur    ) { this.coefCatHeur     = coefCatHeur    ;}

	

	/*-------------------------------------------------------------*/
	/*                             AUTRES                          */
	/*-------------------------------------------------------------*/

	public String toString ()
	{
		return String.format("\"%10s\" (%2.3f)", this.libCatHeur, this.coefCatHeur);
	}
}