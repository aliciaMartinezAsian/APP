package start;

/**
 * <h2>Esta clase sirve como punto de entrada para la aplicación, iniciando la interfaz principal.</h2>
 *
 * @author Alicia Martinez Asian
 * @version 1.0
 * @since 15/06/2024
 */
public class StartApp {

    /**
     * El método principal que sirve como punto de entrada de la aplicación.
     * Este método inicializa y muestra la ventana principal de la aplicación.
     *
     * @param args Argumentos de la línea de comandos (no utilizados en esta aplicación).
     */
    public static void main(String[] args) {
        // Inicializa la ventana principal de la aplicación.
        new view.FrmPrincipal();
    }

}
