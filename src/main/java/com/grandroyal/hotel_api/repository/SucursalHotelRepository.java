package com.grandroyal.hotel_api.repository;

import com.grandroyal.hotel_api.model.SucursalHotel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SucursalHotelRepository extends JpaRepository<SucursalHotel, Long> {
}