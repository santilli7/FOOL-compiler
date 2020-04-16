package type;

import java.util.List;

public class FunType implements Type {

    private List<Type> params;
    private Type returnType;

    public FunType(List<Type> p, Type r) {
        params = p;
        returnType = r;
    }

    @Override
    public ID getID() {
        return ID.RETURN;
    }

    public List<Type> getParameters() {
        return params;
    }

    public Integer getNumParams() {
        return params.size();
    }

    public Type getReturnType() {
        return returnType;
    }

    /*Il tipo di una funzione f1 e` sottotipo del tipo di una funzione f2 se il tipo ritornato da f1
    e` sottotipo del tipo ritornato da f2, se hanno il medesimo numero di parametri,
    e se ogni tipo di paramentro di f1 e` sopratipo del corrisponde tipo di parametro di f2. */

    @Override
    public boolean isSubType(Type t) {
        //Controvarianza
        if (t instanceof FunType) {
            FunType inputType = (FunType) t;
            boolean controllo = true;
            if (params.size() == inputType.getParameters().size()) {
                //Controllo che tutti i parametri abbiano lo stesso tipo(supertype, come da cosegna)
                for (int i = 0; i < params.size(); i++) {
                    //controvarianza (input)
                    controllo = controllo & (inputType.getParameters().get(i).isSubType(params.get(i)));
                }
                //Controllo che anche il valore di ritorno della funzione
                controllo = controllo & returnType.isSubType(inputType.getReturnType()); //covarianza

            } else {
                controllo = false;
            }
            return controllo;
        } else {
            return false;
        }
    }

    @Override
    public String toPrint() {
        return "fun";
    }
}
