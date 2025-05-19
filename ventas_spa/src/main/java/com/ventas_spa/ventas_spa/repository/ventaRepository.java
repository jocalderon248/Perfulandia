package com.ventas_spa.ventas_spa.repository;

import com.ventas_spa.ventas_spa.model.venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ventaRepository extends JpaRepository<venta, Long> {
    /*
     *
     * @Query("SELECT v FROM venta v WHERE c.codigo= :codigo")
     * List<venta> findByCodigo(@Param("codigo")String codigo);
     */
}
