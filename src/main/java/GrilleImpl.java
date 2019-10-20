public class GrilleImpl implements Grille {

    private char[][] grille;

    public GrilleImpl(int taille) {
        this.grille = new char[taille][taille];
    }

    @Override
    public int getDimension() {
        return grille.length;
    }

    @Override
    public void setValue(int x, int y, char value) throws IllegalArgumentException {
        isHorsBorne(x,y);
        if (esInterdit(value)) {
            throw new IllegalArgumentException("la valeur est interdite aux vues des autres valeurs de la grille");
        }
        if (possible(x,y,value)){
            grille[x][y] = value;
        }
    }

    @Override
    public char getValue(int x, int y) throws IllegalArgumentException {
        isHorsBorne(x,y);
        return grille[x][y];
    }

    @Override
    public boolean complete() {
        for (int i = 0 ; i < grille.length; i++) {
            for (int j = 0; j < grille.length; j++) {
                if (grille[i][j] == Grille.EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public boolean possible(int x, int y, char value) throws IllegalArgumentException {
        isHorsBorne(x,y);
        if (value!= Grille.EMPTY && !isPossible(value)) {
            throw new IllegalArgumentException("value n'est pas un caractere autorisé");
        }
        return true;
    }

    /**
     * verifier si hors borne
     * @param x position x dans la grille
     * @param y position y dans la grille
     * @throws IllegalArgumentException si x ou y sont hors bornes (0-8)
     */
    private void isHorsBorne(int x, int y) throws IllegalArgumentException{
        if ((x < 0|| x > grille.length) && (y < 0|| y> grille.length)) {
            throw new IllegalArgumentException("x ou y sont hors bornes (0-8)");
        }
    }

    /**
     * verifier si caractere autorisé
     * @param value valeur dans la case x,y
     * @return true si caractere autorisé
     */
    private boolean isPossible(char value) {
        for (int i = 0; i < possible.length; i++) {
            if (possible[i] == value){
                return true;
            }
        }
        return false;
    }

    /**
     * verifier si valeur est interdite
     * @param value valeur dans la case x,y
     * @return true si caractere est interdite
     */
    private boolean esInterdit(char value) {
        for (int i = 0; i < grille.length; i++) {
            for (int j = 0; j < grille.length; j++) {
                if (grille[i][j] == value) {
                   return true;
                }
            }
        }
        return false;
    }
}
