package codegen;

class HeapCell {

    HeapCell next; //contiene la prossima cella di memoria libera

    private int index; //l'indice di questa cella

    HeapCell(int i, HeapCell n) {
        index = i;
        next = n;
    }

    int ottieniIndice() {
        return index;
    }
}
