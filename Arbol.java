public class Arbol {
    Nodo raiz = null;

    public void recorrerPreOrder(){
        preOrder(raiz);
    }

    public void preOrder(Nodo raiz) {
        if (raiz != null) {
            System.out.println(raiz.dato + " ");
            preOrder(raiz.izquierda);
            preOrder(raiz.derecha);
        }
    }

    public void recorrerInOrder(){
        inOrder(raiz);
    }

    public void inOrder(Nodo raiz){
        if (raiz != null){
            inOrder(raiz.izquierda);
            System.out.println(raiz.dato + " ");
            inOrder(raiz.derecha);
        }
    }

    public void recorrerPostOrder(){
        postOrder(raiz);
    }

    public void postOrder(Nodo raiz){
        if (raiz != null){
            postOrder(raiz.izquierda);
            postOrder(raiz.derecha);
            System.out.println(raiz.dato + " ");
        }
    }

    public void encontrar(int dato){
        Nodo x = new Nodo();
        x = buscar(raiz,dato);
        if (x != null){
            System.out.println("El dato " + x.dato + " Si se encuentra");
        }else{
            System.out.println("El dato " + dato + " no se encuentra");
        }

    }

    public Nodo buscar(Nodo raiz, int dato){
        if (raiz == null || dato == raiz.dato){
            return raiz;
        }
        if (dato < raiz.dato){
            return buscar(raiz.izquierda,dato);
        }
        return buscar(raiz.derecha,dato);
    }

    public void acomodarBorrar(Nodo aux){
        Nodo aux1;
        while (aux != raiz && aux.color == 0) {
            if (aux == aux.padre.izquierda) {
                aux1 = aux.padre.derecha;
                if (aux1.color == 1) {
                    aux1.color = 0;
                    aux.padre.color = 1;
                    rotarIzquierda(aux.padre);
                    aux1 = aux.padre.derecha;
                }

                if (aux1.izquierda.color == 0 && aux1.derecha.color == 0) {
                    aux1.color = 1;
                    aux = aux.padre;
                } else {
                    if (aux1.derecha.color == 0) {
                        aux1.izquierda.color = 0;
                        aux1.color = 1;
                        rotarDerecha(aux1);
                        aux1 = aux.padre.derecha;
                    }

                    aux1.color = aux.padre.color;
                    aux.padre.color = 0;
                    aux1.derecha.color = 0;
                    rotarIzquierda(aux.padre);
                    aux = raiz;
                }
            } else {
                aux1 = aux.padre.izquierda;
                if (aux1.color == 1) {
                    aux1.color = 0;
                    aux.padre.color = 1;
                    rotarDerecha(aux.padre);
                    aux1 = aux.padre.izquierda;
                }

                if (aux1.derecha.color == 0 && aux1.derecha.color == 0) {
                    aux1.color = 1;
                    aux = aux.padre;
                } else {
                    if (aux1.izquierda.color == 0) {
                        aux1.derecha.color = 0;
                        aux1.color = 1;
                        rotarIzquierda(aux1);
                        aux1 = aux.padre.izquierda;
                    }

                    aux1.color = aux.padre.color;
                    aux.padre.color = 0;
                    aux1.izquierda.color = 0;
                    rotarDerecha(aux.padre);
                    aux = raiz;
                }
            }
        }
        aux.color = 0;
    }

    public void rotarIzquierda(Nodo x) {
        Nodo y = x.derecha;
        x.derecha = y.izquierda;
        if (y.izquierda != null) {
            y.izquierda.padre = x;
        }
        y.padre = x.padre;
        if (x.padre == null) {
            this.raiz = y;
        } else if (x == x.padre.izquierda) {
            x.padre.izquierda = y;
        } else {
            x.padre.derecha = y;
        }
        y.izquierda = x;
        x.padre = y;
    }

    public void rotarDerecha(Nodo x) {
        Nodo y = x.izquierda;
        x.izquierda = y.derecha;
        if (y.derecha != null) {
            y.derecha.padre = x;
        }
        y.padre = x.padre;
        if (x.padre == null) {
            this.raiz = y;
        } else if (x == x.padre.derecha) {
            x.padre.derecha = y;
        } else {
            x.padre.izquierda = y;
        }
        y.derecha = x;
        x.padre = y;
    }

    public void acomodarInsertar(Nodo aux){
        Nodo aux1;
        while (aux.padre.color == 1) {
            if (aux.padre == aux.padre.padre.derecha) {
                aux1 = aux.padre.padre.izquierda;
                if (aux1.color == 1) {
                    aux1.color = 0;
                    aux.padre.color = 0;
                    aux.padre.padre.color = 1;
                    aux = aux.padre.padre;
                } else {
                    if (aux == aux.padre.izquierda) {
                        aux = aux.padre;
                        rotarDerecha(aux);
                    }
                    aux.padre.color = 0;
                    aux.padre.padre.color = 1;
                    rotarIzquierda(aux.padre.padre);
                }
            } else {
                aux1 = aux.padre.padre.derecha;

                if (aux1.color == 1) {
                    aux1.color = 0;
                    aux.padre.color = 0;
                    aux.padre.padre.color = 1;
                    aux = aux.padre.padre;
                } else {
                    if (aux == aux.padre.derecha) {
                        aux = aux.padre;
                        rotarIzquierda(aux);
                    }
                    aux.padre.color = 0;
                    aux.padre.padre.color = 1;
                    rotarDerecha(aux.padre.padre);
                }
            }
            if (aux == raiz) {
                break;
            }
        }
        raiz.color = 0;
    }

    public void insertar(int dato) {
        Nodo aux = new Nodo();
        aux.padre = null;
        aux.dato = dato;
        aux.izquierda = null;
        aux.derecha = null;
        aux.color = 1;

        Nodo anterior = null;
        Nodo aux1 = raiz;

        while (aux1 != null) {
            anterior = aux1;
            if (aux.dato < aux1.dato) {
                aux1 = aux1.izquierda;
            } else {
                aux1 = aux1.derecha;
            }
        }

        aux.padre = anterior;
        if (anterior == null) {
            raiz = aux;
        } else if (aux.dato < anterior.dato) {
            anterior.izquierda = aux;
        } else {
            anterior.derecha = aux;
        }

        if (aux.padre == null) {
            aux.color = 0;
            return;
        }

        if (aux.padre.padre == null) {
            return;
        }

        acomodarInsertar(aux);
    }

    public Nodo masChico(Nodo raiz) {
        while (raiz.izquierda != null) {
            raiz = raiz.izquierda;
        }
        return raiz;
    }



    public void intercambiar(Nodo aux, Nodo aux1) {
        if (aux.padre == null) {
            raiz = aux1;
        } else if (aux == aux.padre.izquierda) {
            aux.padre.izquierda = aux1;
        } else {
            aux.padre.derecha = aux1;
        }
        aux1.padre = aux.padre;
    }

    public void eliminar(int dato){
        Nodo aux = null;
        Nodo aux1, aux2;
        while (raiz != null) {
            if (raiz.dato == dato) {
                aux = raiz;
            }

            if (raiz.dato <= dato) {
                raiz = raiz.derecha;
            } else {
                raiz = raiz.izquierda;
            }
        }

        if (aux == null) {
            return;
        }

        aux2 = aux;
        int colorOriginal = aux2.color;
        if (aux.izquierda == null) {
            aux1 = aux.derecha;
            intercambiar(aux, aux.derecha);
        } else if (aux.derecha == null) {
            aux1 = aux.izquierda;
            intercambiar(aux, aux.izquierda);
        } else {
            aux2 = masChico(aux.derecha);
            colorOriginal = aux2.color;
            aux1 = aux2.derecha;
            if (aux2.padre == aux) {
                aux1.padre = aux2;
            } else {
                intercambiar(aux2, aux2.derecha);
                aux2.derecha = aux.derecha;
                aux2.derecha.padre = aux2;
            }

            intercambiar(aux, aux2);
            aux2.izquierda = aux.izquierda;
            aux2.izquierda.padre = aux2;
            aux2.color = aux.color;
        }
        if (colorOriginal == 0) {
            acomodarBorrar(aux1);
        }
    }


}
