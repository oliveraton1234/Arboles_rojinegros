public class Main {
    public static void main(String[] args) {
        Arbol arbolito = new Arbol();
        arbolito.insertar(12);
        arbolito.insertar(18);
        arbolito.insertar(1);
        arbolito.insertar(3);
        arbolito.recorrerInOrder();
        arbolito.encontrar(2);
        arbolito.eliminar(12);
        arbolito.recorrerInOrder();
        arbolito.recorrerPostOrder();
        arbolito.recorrerPreOrder();
    }
}
