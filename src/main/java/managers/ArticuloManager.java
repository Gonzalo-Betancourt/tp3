package managers;

import org.example.Articulo;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ArticuloManager {

    private EntityManagerFactory emf = null;
    private EntityManager em = null;

    public ArticuloManager(boolean anularShowSQL) {
        Map<String, Object> properties = new HashMap<>();
        if(anularShowSQL){
            properties.put("hibernate.show_sql", "false");
        }else{
            properties.put("hibernate.show_sql", "true");
        }
        emf = Persistence.createEntityManagerFactory("example-unit", properties);
        em = emf.createEntityManager();
    }

    // Ejercicio 4
    public List<Object[]> getArticulosMasVendidos() {
        String jpql = "SELECT fd.articulo, SUM(fd.cantidad) FROM FacturaDetalle fd GROUP BY fd.articulo ORDER BY SUM(fd.cantidad) DESC";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }

    public void cerrarEntityManager(){
        em.close();
        emf.close();
    }

    // Ejercicio 7
    public List<Articulo> getArticulosVendidosXFactura(Long idFactura) {
        String jpql = "SELECT fd.articulo FROM FacturaDetalle fd WHERE fd.factura.id = :idFactura";
        Query query = em.createQuery(jpql);
        query.setParameter("idFactura", idFactura);
        return query.getResultList();
    }

    // Ejercicio 8
    public Articulo getArticuloMasCaroVendidoEnFactura(Long idFactura) {
        String jpql = "SELECT fd.articulo FROM FacturaDetalle fd WHERE fd.factura.id = :idFactura ORDER BY fd.articulo.precioVenta DESC";
        Query query = em.createQuery(jpql);

        // **Corregido:** Se agrega esta línea para vincular el parámetro.
        query.setParameter("idFactura", idFactura);

        query.setMaxResults(1);

        return (Articulo) query.getSingleResult();
    }

    // Ejercicio 12
    public List<Articulo> getArticulosXCodigoParcial(String codigoParcial) {
        String jpql = "SELECT a FROM Articulo a WHERE a.codigo LIKE :codigo";
        Query query = em.createQuery(jpql);
        query.setParameter("codigo", "%" + codigoParcial.toLowerCase() + "%");
        return query.getResultList();
    }

    // Ejercicio 13
    public List<Articulo> getArticulosConPrecioMayorAlPromedio() {
        String jpql = "SELECT a FROM Articulo a WHERE a.precioVenta > (SELECT AVG(a2.precioVenta) FROM Articulo a2)";
        Query query = em.createQuery(jpql);
        return query.getResultList();
    }


}