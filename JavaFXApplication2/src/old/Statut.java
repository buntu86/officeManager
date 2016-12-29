package old;

/**
 *
 * @author adrien.pillonel
 */
public class Statut {

    public static String getStatutById(int idStatut){
        String str="-";
        
        if(idStatut==0)
            str="en cours";
        
        if(idStatut==1)
            str="archivé";
        
        return str;
    }
}
