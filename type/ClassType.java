package type;

import throwable.UndecIDException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ClassType implements Type {

    private String classID = "";
    private ClassType superClassType = null;

    private List<Field> fields = new ArrayList<>();
    private List<Method> methods = new ArrayList<>();

    public ClassType(String ID, ClassType st, List<Field> f, List<Method> m) {
        classID = ID;
        superClassType = st;
        fields = f;
        methods = m;
    }

    public void setSuperClassType(ClassType st) {
        superClassType = st;
    }

    public ClassType(String classID) {
        this.classID = classID;
    }

    public String getClassID() {
        return classID;
    }

    public ClassType getSuperClassType() {
        return superClassType;
    }

    public String getSuperclassID() {
        return superClassType.getClassID();
    }

    public List<Field> getFields() {
        return fields;
    }

    @Override
    public ID getID() {
        return ID.CLASS;
    }

    /*Una classe C1 e` sottotipo di una classe C2 se C1 estende C2 e se i campi e metodi che
    vengono sovrascritti sono sottotipi rispetto ai campi e metodi corrispondenti di C2.
    Inoltre, C1 e` sottotipo di C2 se esiste una classe C3 sottotipo di C2 di cui C1 e`
    sottotipo.*/

    //è gestita un'ipotetica ereditarietà infinita

    @Override
    public boolean isSubType(Type t2) { //A è sottotipo di B, A.isSubTypeOf(B)
        // Controllo se altro tipo è classe
        if (t2 instanceof ClassType) {
            ClassType ct2 = (ClassType) t2;
            if (this.getClassID().equals(ct2.getClassID())) {
                // è la stessa classe
                return true;
            }
            // Vado avanti solo se la classe corrente ha un supertipo
            if (superClassType != null) {
                ClassType tmp = superClassType;
                if (superClassType.isSubType(t2)) {
                    return true;
                } else if (ct2.getSuperClassType() != null) {
                    if (superClassType.getClassID().equals(ct2.getSuperclassID())) {
                        return true;
                    }
                }
                while (tmp.getSuperClassType() != null) {
                    tmp = tmp.getSuperClassType();
                    if (tmp.getClassID().equals(ct2.getClassID())) {
                        return true;
                    }
                    while (ct2.getSuperClassType() != null) {
                        ct2 = ct2.getSuperClassType();
                        if (tmp.getClassID().equals(ct2.getClassID())) {
                            return true;
                        }
                    }
                }
            }
        }
        return false;
    }

    @Override
    public String toPrint() {
        return "class " + classID;
    }

    //funzioni ausiliarie utilizzate nella checksemantics
    //ritorna un'HashMap di tutti i metodi di questa classe, con nome ed offset
    public HashMap<String, FunType> getMethodsMap() {
        HashMap<String, FunType> methodsMap = new HashMap<>();
        if (superClassType != null) {
            HashMap<String, FunType> superMethodsMap = superClassType.getMethodsMap();
            for (String m : superMethodsMap.keySet())
                methodsMap.put(m, superMethodsMap.get(m));
        }
        for (Method m : methods) {
            methodsMap.put(m.getId(), m.getType());
        }
        return methodsMap;
    }

    //ritorna l'offset del metodo situato nella dispatchTable
    public int getOffsetOfMethod(String methodID) throws UndecIDException {
        HashMap<String, Integer> methodsHashMap = methodsHashMapFromSuperClass();
        Integer offset = methodsHashMap.get(methodID);
        if (offset != null) {
            //ad offset 0 c'è la dispatch table, quindi vengono tutti aumentati di 1
            return offset + 1;
        } else {
            throw new UndecIDException(methodID);
        }
    }

    //ritorna un'HashMap dei metodi della superclasse, con nome ed offset
    public HashMap<String, Integer> methodsHashMapFromSuperClass() {
        if (superClassType == null) {
            HashMap<String, Integer> methodsHashMap = new HashMap<>();
            for (Method method : methods) {
                methodsHashMap.put(method.getId(), methodsHashMap.size());
            }
            return methodsHashMap;
        } else {
            HashMap<String, Integer> superMethodsMap = superClassType.methodsHashMapFromSuperClass();
            for (Method method : methods) {
                if (!superMethodsMap.containsKey(method.getId())) {
                    superMethodsMap.put(method.getId(), superMethodsMap.size());
                }
            }
            return superMethodsMap;
        }
    }

    //ritorna il tipo di un metodo dato l'ID
    public Type getTypeOfMethod(String id) {
        Method method = this.methods
                .stream()
                .filter(m -> m.getId().equals(id))
                .reduce(null, (prev, curr) -> curr);

        if (method != null) {
            return method.getType();
        } else {
            return superClassType.getTypeOfMethod(id);
        }
    }

}