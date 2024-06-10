/*Objetivo: Introducción a bases de datos y conexión a través de JDBC.
Tareas: Desarrollar una aplicación que gestione productos en inventario 
(agregar, eliminar, actualizar, listar). Utiliza una base de datos como MySQL o SQLite para almacenar los datos */



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class GestionInventario {
    static final String URL = "jdbc:mysql://localhost:3306/inventario";
    static final String USER = "root";
    static final String PASSWORD = "Dre2023!";

    public static void main(String[] args) {
        try {
            // Registrar el driver de MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");

            try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
                System.out.println("Conexión exitosa a la base de datos.");

                Scanner scanner = new Scanner(System.in);
                boolean salir = false;

                while (!salir) {
                    System.out.println("Seleccione una opción:");
                    System.out.println("1. Agregar producto");
                    System.out.println("2. Listar productos");
                    System.out.println("3. Actualizar producto");
                    System.out.println("4. Salir");

                    int opcion = scanner.nextInt();
                    scanner.nextLine(); // Limpiar el buffer

                    switch (opcion) {
                        case 1:
                            agregarProducto(connection, scanner);
                            break;
                        case 2:
                            listarProductos(connection);
                            break;
                        case 3:
                            actualizarProducto(connection, scanner);
                            break;
                        case 4:
                            salir = true;
                            break;
                        default:
                            System.out.println("Opción no válida.");
                    }
                }

                scanner.close();
            } catch (SQLException e) {
                System.out.println("Error al conectar a la base de datos: " + e.getMessage());
                e.printStackTrace();
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Driver JDBC no encontrado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public static void agregarProducto(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese la cantidad del producto: ");
        int cantidad = scanner.nextInt();

        String sql = "INSERT INTO productos (nombre, precio, cantidad) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, cantidad);
            statement.executeUpdate();
            System.out.println("Producto agregado correctamente.");
        }
    }

    public static void listarProductos(Connection connection) throws SQLException {
        String sql = "SELECT * FROM productos";

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery(sql)) {

            System.out.println("ID\tNombre\t\tPrecio\tCantidad");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nombre = resultSet.getString("nombre");
                double precio = resultSet.getDouble("precio");
                int cantidad = resultSet.getInt("cantidad");
                System.out.println(id + "\t" + nombre + "\t\t" + precio + "\t" + cantidad);
            }
        }
    }

    public static void actualizarProducto(Connection connection, Scanner scanner) throws SQLException {
        System.out.print("Ingrese el ID del producto a actualizar: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer
        System.out.print("Ingrese el nuevo nombre del producto: ");
        String nombre = scanner.nextLine();
        System.out.print("Ingrese el nuevo precio del producto: ");
        double precio = scanner.nextDouble();
        System.out.print("Ingrese la nueva cantidad del producto: ");
        int cantidad = scanner.nextInt();

        String sql = "UPDATE productos SET nombre = ?, precio = ?, cantidad = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, nombre);
            statement.setDouble(2, precio);
            statement.setInt(3, cantidad);
            statement.setInt(4, id);
            int rowsUpdated = statement.executeUpdate();
            if (rowsUpdated > 0) {
                System.out.println("Producto actualizado correctamente.");
            } else {
                System.out.println("No se encontró el producto con el ID especificado.");
            }
        }
    }
}
