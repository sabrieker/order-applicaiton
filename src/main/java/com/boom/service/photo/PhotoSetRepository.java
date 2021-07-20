package com.boom.service.photo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.boom.entity.PhotoAlbum;

@Repository
interface PhotoSetRepository extends JpaRepository<PhotoAlbum, Long>,PagingAndSortingRepository<PhotoAlbum, Long>{

}
