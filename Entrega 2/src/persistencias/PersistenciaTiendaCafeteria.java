
package persistencias;
import club.Club;
import java.io.BufferedReader;
import java.io.FileReader;
import TiendaCafeteria.*;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.io.FileWriter;

import java.io.IOException;

import Usuarios.*;
import Excepciones.*;

public class PersistenciaTiendaCafeteria {

    private static final String ARCHIVO_ARTICULOS = "articulos.txt";
    private static final String ARCHIVO_BEBIDAS = "bebidas.txt";
    private static final String ARCHIVO_SNACKS = "snacks.txt";
    private static final String ARCHIVO_VENTAS = "ventas.txt";
    private static final String SEPARADOR = ";";

    public PersistenciaTiendaCafeteria() {
    }

    public void cargarTiendaCafeteria(Club seneca, String carpeta) throws IOException, PersistenciaException{
    }

    private void cargarBebidas(Cafeteria cafeteria, String carpeta) throws IOException, PersistenciaException{
        String ruta = carpeta +"/" + ARCHIVO_BEBIDAS;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea;
            while ((linea = reader.readLine()) != null){
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")){
                    continue;}
                String[] partes = linea.split(SEPARADOR);
                String nombre = partes[0];
                boolean caliente = Boolean.parseBoolean(partes[1]);
                double precio = Double.parseDouble(partes[2]);
                Bebida nuevaBebida = new Bebida(nombre, precio, caliente);
                cafeteria.agregarBebida(nuevaBebida);
                }
            }
        
        catch(IOException e){
            throw new PersistenciaException("No se pudo cargar el archivo. Error: "+ e);
        }
    }
    private void cargarArticulos(TiendaDeportiva tienda, String carpeta) throws IOException, PersistenciaException{
        String ruta = carpeta +"/" + ARCHIVO_ARTICULOS;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea;
            while ((linea = reader.readLine()) != null){
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")){
                    continue;}
                String[] partes = linea.split(SEPARADOR);  
                String nombre = partes[0];
                String categoria = partes[1];
                double precio = Double.parseDouble(partes[2]);
                int cantidad = Integer.parseInt(partes[3]);
                String ubicacion = partes[5];
                Articulo nuevoArticulo = new Articulo(nombre, categoria, precio, cantidad);
                tienda.agregarArticulo(nuevoArticulo);
            }
        }
        catch(IOException e){
            throw new PersistenciaException("No se pudo cargar el archivo. Error: "+ e);
        }
    }
    private void cargarSnacks(Cafeteria cafeteria, String carpeta) throws IOException, PersistenciaException{
        String ruta = carpeta +"/" + ARCHIVO_SNACKS;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea;
            while ((linea = reader.readLine()) != null){
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")){
                    continue;}
                String[] partes = linea.split(SEPARADOR); 
                String nombre = partes[0];
                double precio = Double.parseDouble(partes[1]);
                Snack nuevoSnack = new Snack(nombre, precio);
                String alergeno = partes[2];
                if (!alergeno.equals("NINGUNO")){
                    String[] alergenos = alergeno.split(",");
                    for (String a : alergenos){
                        a =  a.trim();
                        nuevoSnack.agregarAlergeno(a);
                    }
                }
                cafeteria.agregarSnack(nuevoSnack);
                }

            }
            catch(IOException e){
                throw new PersistenciaException("No se pudo cargar el archivo. Error: "+ e);
            }
        }
    private void cargarVentas(Club club, String carpeta) throws IOException, PersistenciaException{
        String ruta = carpeta +"/" + ARCHIVO_SNACKS;
        try (BufferedReader reader = new BufferedReader(new FileReader(ruta))){
            String linea;
            while ((linea = reader.readLine()) != null){
                linea = linea.trim();
                if (linea.isEmpty() || linea.startsWith("#")){
                    continue;}
                }
            }
        }
    }

