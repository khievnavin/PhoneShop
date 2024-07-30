package com.product.phoneshop.repository;

import com.product.phoneshop.model.SaleDetail;
import com.product.phoneshop.projections.SaleByDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface SaleDetailRepository extends JpaRepository<SaleDetail, Long>, JpaSpecificationExecutor<SaleDetail> {

    @Query(value = """
            select sold_date::date soldDate, product_id productId, p.name productName, sum(sd.unit) totalUnit, sum(p.sale_price * sd.unit) amount\r
            from  sales s inner join public.sale_details sd on s.id = sd.sale_id\r
            inner join products p on p.id = sd.product_id\r
            where sold_date::date = :soldDate\r
            group by sold_date::date , product_id, p.name
      """, nativeQuery = true)
    List<SaleByDate> findByProduct(@Param("soldDate") LocalDate soldDate);

    List<SaleDetail> findBySaleId(Long saleId);
}
