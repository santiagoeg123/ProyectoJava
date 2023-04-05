package menu;

import java.util.Scanner;
import java.io.*;
import java.util.ArrayList;


public class Inventario {
    private static final String archivo= "inventario1.txt";
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Producto> inventario = cargarInventario();
        
        int opcion;
        do {
        	System.out.println("- Aplicacion Inventario - ");
            System.out.println("1. Registrar producto");
            System.out.println("2. Buscar producto");
            System.out.println("3. Eliminar producto");
            System.out.println("4. Actualizar producto");
            System.out.println("5. Salir");
            System.out.print("Ingrese una opcion: ");
            opcion = sc.nextInt();
            sc.nextLine();
            
            switch (opcion) {
                case 1:
                    registrarProducto(sc, inventario);
                    break;
                case 2:
                    buscarProducto(sc, inventario);
                    break;
                case 3:
                    eliminarProducto(sc, inventario);
                    break;
                case 4:
                    actualizarProducto(sc, inventario);
                    break;
                case 5:
                    guardarInventario(inventario);
                    System.out.println("finalizado");
                    break;
                default:
                    System.out.println("error");
            }
            
        } while (opcion != 5);
    }
    
    public static ArrayList<Producto> cargarInventario() {
        ArrayList<Producto> inventario = new ArrayList<>();
        try (ObjectInputStream entrada = new ObjectInputStream(new FileInputStream(archivo))) {
            ArrayList<Producto> readObject2 = (ArrayList<Producto>) entrada.readObject();
			ArrayList<Producto> readObject = readObject2;
			inventario = readObject;
            System.out.println("Inventario cargado exitosamente.");
        } catch (FileNotFoundException e) {
            System.out.println("El archivo de inventario no existe. Se crear un nuevo archivo.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar el inventario.");
        }
        return inventario;
    }
    
    public static void guardarInventario(ArrayList<Producto> inventario) {
        try (ObjectOutputStream salida = new ObjectOutputStream(new FileOutputStream(archivo))) {
            salida.writeObject(inventario);
            System.out.println("Inventario guardado exitosamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar el inventario");
        }
    }
   public static void registrarProducto(Scanner sc, ArrayList<Producto> inventario) {
        System.out.println("Ingrese los datos del nuevo producto:");
        System.out.print("Nombre: ");
        String nombre = sc.nextLine();
        System.out.print("Precio: ");
        double precio = sc.nextDouble();
        System.out.print("Cantidad: ");
        int cantidad = sc.nextInt();
        sc.nextLine();
        
        Producto nuevoProducto = new Producto(nombre, precio, cantidad);
        inventario.add(nuevoProducto);
        System.out.println("Producto registrado exitosamente.");
    }

    private static void buscarProducto(Scanner sc, ArrayList<Producto> inventario) {
        System.out.print("Ingrese el nombre del producto a buscar: ");
        String nombre = sc.nextLine();
        
        for (Producto producto : inventario) {
            if (producto.getNombre().equals(nombre)) {
                System.out.println(producto);
                return;
            }
        }
        
        System.out.println("Producto no encontrado.");
    }

    private static void eliminarProducto(Scanner sc, ArrayList<Producto> inventario) {
        System.out.print("Ingrese el nombre del producto a eliminar: ");
        String nombre = sc.nextLine();
        
        for (int i = 0; i < inventario.size(); i++) {
            if (inventario.get(i).getNombre().equals(nombre)) {
                inventario.remove(i);
                System.out.println("Producto eliminado exitosamente.");
                return;
            }
        }
        
        System.out.println("Producto no encontrado.");
    }

    private static void actualizarProducto(Scanner sc, ArrayList<Producto> inventario) {
        System.out.print("Ingrese el nombre del producto a actualizar: ");
        String nombre = sc.nextLine();
        
        for (Producto producto : inventario) {
            if (producto.getNombre().equals(nombre)) {
                System.out.println("Ingrese los nuevos datos del producto:");
                System.out.print("Nombre: ");
                producto.setNombre(sc.nextLine());
                System.out.print("Precio: ");
                producto.setPrecio(sc.nextDouble());
                System.out.print("Cantidad: ");
                producto.setCantidad(sc.nextInt());
                sc.nextLine();
                
                System.out.println("Producto actualizado");
                return;
            }
        }
        
        System.out.println("Producto no encontrado.");
    }
}

