package com.boom.service.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.boom.entity.Photo;

@Repository
interface PhotoRepository extends JpaRepository<Photo, Long>,PagingAndSortingRepository<Photo, Long>{

}
