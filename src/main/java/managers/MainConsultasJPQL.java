package managers;

import funciones.FuncionApp;
import org.example.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class MainConsultasJPQL {

    public static void main(String[] args) {
        //REPOSITORIO-> https://github.com/gerardomagni/jpqlquerys.git

        //buscarFacturas();
        //buscarFacturasActivas();
        //buscarFacturasXNroComprobante();
        //buscarFacturasXRangoFechas();
        //buscarFacturaXPtoVentaXNroComprobante();
        //buscarFacturasXCliente();
        //buscarFacturasXCuitCliente();
        //buscarFacturasXArticulo();
        //mostrarMaximoNroFactura();
        //buscarClientesXIds();
        //buscarClientesXRazonSocialParcial();

        // initializeData();

        // listarTodosLosClientes(); // Ejercicio 1 +
        // listarFacturasUltimoMes(); // Ejercicio 2 +
        // obtenerClienteConMasFacturas(); // Ejercicio 3 +
        // listarArticulosMasVendidos(); // Ejercicio 4 - (?)
        // consultarFacturasUltimosTresMesesDeCliente(); // Ejercicio 5 +
        // calcularMontoTotalFacturadoXCliente(); // Ejercicio 6 +
        // listarArticulosVendidosEnFactura(); // Ejercicio 7 +
        // obtenerArticuloMasCaroVendidoEnFactura(); // Ejercicio 8 +
        // contarCantidadTotalFacturas(); // Ejercicio 9 +
        // listarFacturasConTotalMayorA(); // Ejercicio 10 +
        // consultarFacturasXNombreArticulo(); // Ejercicio 11 +
        // listarArticulosXCodigoParcial(); // Ejercicio 12 +
        // listarArticulosConPrecioMayorAlPromedio(); // Ejercicio 13 +
        // explicarClausulaExists(); // Ejercicio 14 +

    }


    public static void buscarFacturas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturas();
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasActivas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasActivas();
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXNroComprobante(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXNroComprobante(796910l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXRangoFechas(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaInicio = FuncionApp.restarSeisMeses(fechaActual);
            List<Factura> facturas = mFactura.buscarFacturasXRangoFechas(fechaInicio, fechaActual);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturaXPtoVentaXNroComprobante(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Factura factura = mFactura.getFacturaXPtoVentaXNroComprobante(2024, 796910l);
            mostrarFactura(factura);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXCliente(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXCliente(7l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXCuitCliente(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXCuitCliente("27236068981");
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarFacturasXArticulo(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            List<Factura> facturas = mFactura.getFacturasXArticulo(6l);
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void mostrarMaximoNroFactura(){
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Long nroCompMax = mFactura.getMaxNroComprobanteFactura();
            System.out.println("N° " + nroCompMax);
        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mFactura.cerrarEntityManager();
        }
    }

    public static void buscarClientesXIds(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Long> idsClientes = new ArrayList<>();
            idsClientes.add(1l);
            idsClientes.add(2l);
            List<Cliente> clientes = mCliente.getClientesXIds(idsClientes);
            for(Cliente cli : clientes){
                System.out.println("Id: " + cli.getId());
                System.out.println("CUIT: " + cli.getCuit());
                System.out.println("Razon Social: " + cli.getRazonSocial());
                System.out.println("-----------------");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }

    public static void buscarClientesXRazonSocialParcial(){
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Long> idsClientes = new ArrayList<>();
            idsClientes.add(1l);
            idsClientes.add(2l);
            List<Cliente> clientes = mCliente.getClientesXRazonSocialParcialmente("Lui");
            for(Cliente cli : clientes){
                System.out.println("Id: " + cli.getId());
                System.out.println("CUIT: " + cli.getCuit());
                System.out.println("Razon Social: " + cli.getRazonSocial());
                System.out.println("-----------------");
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            mCliente.cerrarEntityManager();
        }
    }



    public static void mostrarFactura(Factura factura){
        List<Factura> facturas = new ArrayList<>();
        facturas.add(factura);
        mostrarFacturas(facturas);
    }

    public static void mostrarFacturas(List<Factura> facturas){
        for(Factura fact : facturas){
            System.out.println("N° Comp: " + fact.getStrProVentaNroComprobante());
            System.out.println("Fecha: " + FuncionApp.formatLocalDateToString(fact.getFechaComprobante()));
            System.out.println("CUIT Cliente: " + FuncionApp.formatCuitConGuiones(fact.getCliente().getCuit()));
            System.out.println("Cliente: " + fact.getCliente().getRazonSocial() + " ("+fact.getCliente().getId() + ")");
            System.out.println("------Articulos------");
            for(FacturaDetalle detalle : fact.getDetallesFactura()){
                System.out.println(detalle.getArticulo().getDenominacion() + ", " + detalle.getCantidad() + " unidades, $" + FuncionApp.getFormatMilDecimal(detalle.getSubTotal(), 2));
            }
            System.out.println("Total: $" + FuncionApp.getFormatMilDecimal(fact.getTotal(),2));
            System.out.println("*************************");
        }
    }

    // Ejercicio 1
    public static void listarTodosLosClientes() {
        ClienteManager mCliente = new ClienteManager(true);
        try {
            List<Cliente> clientes = mCliente.listarTodosLosClientes();
            System.out.println("--- Ejercicio 1: Listar todos los clientes ---");
            for (Cliente cli : clientes) {
                System.out.println("ID: " + cli.getId() + ", Razón Social: " + cli.getRazonSocial());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mCliente.cerrarEntityManager();
        }
    }

    // Ejercicio 2
    public static void listarFacturasUltimoMes() {
        FacturaManager mFactura = new FacturaManager(true);
        try {
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaInicio = fechaActual.minus(Period.ofMonths(1));
            List<Factura> facturas = mFactura.getFacturasUltimoMes(fechaInicio);
            System.out.println("--- Ejercicio 2: Facturas del último mes ---");
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mFactura.cerrarEntityManager();
        }
    }

    // Ejercicio 3
    public static void obtenerClienteConMasFacturas() {
        ClienteManager mCliente = new ClienteManager(true);
        try {
            Cliente cliente = mCliente.getClienteConMasFacturas();
            System.out.println("--- Ejercicio 3: Cliente con más facturas ---");
            System.out.println("ID: " + cliente.getId());
            System.out.println("Razón Social: " + cliente.getRazonSocial());
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mCliente.cerrarEntityManager();
        }
    }

    // Ejercicio 4
    public static void listarArticulosMasVendidos() {
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            List<Object[]> resultados = mArticulo.getArticulosMasVendidos();
            System.out.println("--- Ejercicio 4: Artículos más vendidos ---");
            for (Object[] resultado : resultados) {
                Articulo articulo = (Articulo) resultado[0];
                Long cantidadTotal = (Long) resultado[1];
                System.out.println("Artículo: " + articulo.getDenominacion() + ", Cantidad total vendida: " + cantidadTotal);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mArticulo.cerrarEntityManager();
        }
    }

    // Ejercicio 5
    public static void consultarFacturasUltimosTresMesesDeCliente() {
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Long idCliente = 1L; // Ejemplo: un cliente con ID 1
            LocalDate fechaActual = LocalDate.now();
            LocalDate fechaInicio = fechaActual.minus(Period.ofMonths(3));

            List<Factura> facturas = mFactura.getFacturasUltimosTresMesesXCliente(idCliente, fechaInicio);

            System.out.println("--- Ejercicio 5: Facturas de los últimos 3 meses para el cliente ID " + idCliente + " ---");
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mFactura.cerrarEntityManager();
        }
    }

    // Ejercicio 6
    public static void calcularMontoTotalFacturadoXCliente() {
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Long idCliente = 1L; // Ejemplo: un cliente con ID 1
            BigDecimal montoTotal = mFactura.getMontoTotalFacturadoXCliente(idCliente);
            System.out.println("--- Ejercicio 6: Monto total facturado por el cliente ID " + idCliente + " ---");
            System.out.println("Monto total: $" + (montoTotal != null ? montoTotal.toPlainString() : "0.00"));
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mFactura.cerrarEntityManager();
        }
    }

    // Ejercicio 7
    public static void listarArticulosVendidosEnFactura() {
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            Long idFactura = 1L; // Ejemplo: una factura con ID 1
            List<Articulo> articulos = mArticulo.getArticulosVendidosXFactura(idFactura);
            System.out.println("--- Ejercicio 7: Artículos vendidos en la factura ID " + idFactura + " ---");
            for (Articulo articulo : articulos) {
                System.out.println("Artículo: " + articulo.getDenominacion() + ", Precio: $" + articulo.getPrecioVenta());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mArticulo.cerrarEntityManager();
        }
    }

    // Ejercicio 8
    public static void obtenerArticuloMasCaroVendidoEnFactura() {
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            Long idFactura = 1L; // Ejemplo: una factura con ID 1
            Articulo articulo = mArticulo.getArticuloMasCaroVendidoEnFactura(idFactura);
            System.out.println("--- Ejercicio 8: Artículo más caro vendido en la factura ID " + idFactura + " ---");
            if (articulo != null) {
                System.out.println("Artículo: " + articulo.getDenominacion() + ", Precio: $" + articulo.getPrecioVenta());
            } else {
                System.out.println("No se encontró ningún artículo en la factura.");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mArticulo.cerrarEntityManager();
        }
    }

    // Ejercicio 9
    public static void contarCantidadTotalFacturas() {
        FacturaManager mFactura = new FacturaManager(true);
        try {
            Long cantidad = mFactura.getCantidadTotalFacturas();
            System.out.println("--- Ejercicio 9: Cantidad total de facturas ---");
            System.out.println("Cantidad total de facturas: " + cantidad);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mFactura.cerrarEntityManager();
        }
    }

    // Ejercicio 10
    public static void listarFacturasConTotalMayorA() {
        FacturaManager mFactura = new FacturaManager(true);
        try {
            double valor = 5000.00; // Ejemplo: un valor de 5000.00
            List<Factura> facturas = mFactura.getFacturasConTotalMayorA(valor);
            System.out.println("--- Ejercicio 10: Facturas con total mayor a $" + valor + " ---");
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mFactura.cerrarEntityManager();
        }
    }

    // Ejercicio 11
    public static void consultarFacturasXNombreArticulo() {
        FacturaManager mFactura = new FacturaManager(true);
        try {
            String nombreArticulo = "Notebook Gamer"; // Ejemplo: un nombre de artículo
            List<Factura> facturas = mFactura.getFacturasXNombreArticulo(nombreArticulo);
            System.out.println("--- Ejercicio 11: Facturas que contienen el artículo '" + nombreArticulo + "' ---");
            mostrarFacturas(facturas);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mFactura.cerrarEntityManager();
        }
    }

    // Ejercicio 12
    public static void listarArticulosXCodigoParcial() {
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            String codigo = "abc"; // Ejemplo: una parte del código
            List<Articulo> articulos = mArticulo.getArticulosXCodigoParcial(codigo);
            System.out.println("--- Ejercicio 12: Artículos con código parcial '" + codigo + "' ---");
            for (Articulo art : articulos) {
                System.out.println("ID: " + art.getId() + ", Código: " + art.getCodigo() + ", Denominación: " + art.getDenominacion());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mArticulo.cerrarEntityManager();
        }
    }

    // Ejercicio 13
    public static void listarArticulosConPrecioMayorAlPromedio() {
        ArticuloManager mArticulo = new ArticuloManager(true);
        try {
            List<Articulo> articulos = mArticulo.getArticulosConPrecioMayorAlPromedio();
            System.out.println("--- Ejercicio 13: Artículos con precio mayor al promedio ---");
            for (Articulo art : articulos) {
                System.out.println("ID: " + art.getId() + ", Denominación: " + art.getDenominacion() + ", Precio: $" + art.getPrecioVenta());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mArticulo.cerrarEntityManager();
        }
    }

    // Ejercicio 14
    public static void explicarClausulaExists() {
        ClienteManager mCliente = new ClienteManager(true);
        try {
            double totalMinimo = 1000.00; // Ejemplo: facturas con un total mayor a 1000
            List<Cliente> clientes = mCliente.getClientesConFacturaConTotalMayorA(totalMinimo);
            System.out.println("--- Ejercicio 14: Clientes con al menos una factura con total > $" + totalMinimo + " ---");
            for (Cliente cli : clientes) {
                System.out.println("ID: " + cli.getId() + ", Razón Social: " + cli.getRazonSocial());
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            mCliente.cerrarEntityManager();
        }
    }

    // Incializar Datos
    public static void initializeData() {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("example-unit");
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();

        // 1. Crear Unidades de Medida
        UnidadMedida unidad = UnidadMedida.builder().denominacion("unidad").build();
        em.persist(unidad);

        // 2. Crear Artículos
        Articulo articulo1 = Articulo.builder()
                .codigo("ABC-123")
                .denominacion("Notebook Gamer")
                .precioVenta(1500.00)
                .unidadMedida(unidad)
                .build();
        em.persist(articulo1);

        Articulo articulo2 = Articulo.builder()
                .codigo("XYZ-456")
                .denominacion("Mouse Inalámbrico")
                .precioVenta(25.50)
                .unidadMedida(unidad)
                .build();
        em.persist(articulo2);

        // 3. Crear Clientes
        Cliente cliente1 = Cliente.builder()
                .cuit("20345678901")
                .razonSocial("Cliente A")
                .build();
        em.persist(cliente1);

        Cliente cliente2 = Cliente.builder()
                .cuit("27987654321")
                .razonSocial("Cliente B")
                .build();
        em.persist(cliente2);

        // 4. Crear Facturas
        Factura factura1 = Factura.builder()
                .cliente(cliente1)
                .fechaComprobante(LocalDate.now())
                .puntoVenta(1)
                .nroComprobante(1001L)
                .build();
        em.persist(factura1);
        // Calcular el total de la factura
        FacturaDetalle detalle1 = new FacturaDetalle(1, articulo1);
        factura1.addDetalleFactura(detalle1);
        detalle1.calcularSubTotal(); // Asegúrate de que los subtotales se calculen

        Factura factura2 = Factura.builder()
                .cliente(cliente1)
                .fechaComprobante(LocalDate.now().minusMonths(1))
                .puntoVenta(1)
                .nroComprobante(1002L)
                .build();
        em.persist(factura2);

        // 5. Crear detalles para las facturas
        FacturaDetalle detalle2 = new FacturaDetalle(2, articulo2);
        factura2.addDetalleFactura(detalle2);
        detalle2.calcularSubTotal();

        // Actualizar el total de las facturas
        factura1.calcularTotal();
        factura2.calcularTotal();

        // 6. Persistir los detalles
        em.persist(detalle1);
        em.persist(detalle2);

        // Confirma la transacción
        em.getTransaction().commit();

        em.close();
        emf.close();
    }

}


