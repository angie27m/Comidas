package com.udec.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udec.view.ChefView;

@Repository
public interface ViewRepo extends JpaRepository<ChefView, Integer> {

}
