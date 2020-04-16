package codegen;

import throwable.HeapOverflowError;

class Heap {

    private HeapCell head;
    private int heapSize;

    Heap(int size) {
        HeapCell[] listaDiCelle = new HeapCell[size];
        heapSize = size;

        listaDiCelle[size - 1] = new HeapCell(ExecuteVM.START_ADDRESS + size - 1, null); //l'ultimo elemento punta a null

        // Gli altri campi puntano a quello dopo
        for (int i = size - 2; i >= 0; i--) {
            listaDiCelle[i] = new HeapCell(ExecuteVM.START_ADDRESS + i, listaDiCelle[i + 1]);
        }

        // Il primo elemento e' la testa della lista
        head = listaDiCelle[0];
    }

    HeapCell alloca(int size) throws HeapOverflowError {

        if (heapSize < size) throw new HeapOverflowError();

        HeapCell res = head; //deve essere restituita la testa della lista


        HeapCell lastItem = head;
        for (int i = 1; i < size; i++) {
            lastItem = lastItem.next;
        }
        head = lastItem.next; // La testa della lista diventa il primo elemento dopo l'ultimo restituito


        lastItem.next = null; // L'ultimo elemento restituito deve puntare a null
        heapSize = heapSize - size;
        return res; //Si ritorna la testa della lista (collegata a tutte le altre)
    }


    void dealloca(HeapCell firstCell) {
        int spazioRecuperato = 1;
        HeapCell curr = firstCell;

        while (curr.next != null) {
            spazioRecuperato++;
            curr = curr.next;
        }
        curr.next = head;  // L'ultimo elemento della lista restituita va fatto puntare a head


        head = firstCell;  // La testa della listaDiCelle sara' il primo elemento della lista deallocata
        heapSize = heapSize + spazioRecuperato;
    }

    int ottienIndirizzoLibero() {
        if (head != null) {
            return head.ottieniIndice();
        } else {
            return -1;
        }
    }

}
