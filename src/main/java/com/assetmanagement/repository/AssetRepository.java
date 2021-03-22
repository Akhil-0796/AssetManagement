package com.assetmanagement.repository;

import com.assetmanagement.modal.Asset;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssetRepository extends JpaRepository<Asset,Long> {

    Optional<Asset> findByName(String name);


    void deleteByName(String assetName);
}
