package flory.FloryServer.repository;

import flory.FloryServer.domain.Flower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FlowerRepository extends JpaRepository<Flower, Integer> {
    @Query("SELECT f FROM Flower f WHERE f.id BETWEEN 1 AND 18 AND f.flowerName = :flowerName")
    Optional<Flower> findByFlowerNameInFlowerRange(@Param("flowerName") String flowerName);

    @Query("SELECT f FROM Flower f WHERE f.id BETWEEN 21 AND 31 AND f.flowerName = :flowerName")
    Optional<Flower> findByBouquetNameInFlowerRange(@Param("flowerName") String flowerName);

}
