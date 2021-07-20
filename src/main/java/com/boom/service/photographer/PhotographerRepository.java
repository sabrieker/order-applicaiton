package com.boom.service.photographer;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.boom.entity.Photographer;

@Repository
interface PhotographerRepository extends JpaRepository<Photographer, Long>,PagingAndSortingRepository<Photographer, Long>{

}
