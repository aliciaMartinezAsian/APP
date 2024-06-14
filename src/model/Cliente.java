package model;

public class Cliente {
    // Atributos del cliente
    private Integer id;
    private String DNI;
    private String nombre;
    private String apellidos;
    private String email;

    // Constructor con parámetros mínimos (id, nombre y apellidos)
    public Cliente(Integer id, String nombre, String apellidos) {
        super();
        this.id = id;
        this.nombre = nombre;
        this.apellidos = apellidos;
    }

    // Constructor con todos los parámetros
    public Cliente(Integer id, String dni, String nombre, String apellidos, String email) {
        super();
        this.id = id;
        this.DNI = dni;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.email = email;
    }

    // Constructor vacío
    public Cliente() {
        // Constructor por defecto sin parámetros
    }

    // Getter para el ID del cliente
    public Integer getId() {
        return id;
    }

    // Setter para el ID del cliente
    public void setId(Integer id) {
        this.id = id;
    }

    // Getter para el DNI del cliente
    public String getDni() {
        return DNI;
    }

    // Setter para el DNI del cliente
    public void setDni(String dni) {
        this.DNI = dni;
    }

    // Getter para el nombre del cliente
    public String getNombre() {
        return nombre;
    }

    // Setter para el nombre del cliente
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Getter para los apellidos del cliente
    public String getApellidos() {
        return apellidos;
    }

    // Setter para los apellidos del cliente
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    // Getter para el email del cliente
    public String getEmail() {
        return email;
    }

    // Setter para el email del cliente
    public void setEmail(String email) {
        this.email = email;
    }

    // Método toString para representar el cliente como una cadena de texto
    @Override
    public String toString() {
        // Devuelve una cadena de texto con los datos del cliente separados por comas
        return DNI + "," + nombre + "," + apellidos + "," + email + "," + id;
    }
}
