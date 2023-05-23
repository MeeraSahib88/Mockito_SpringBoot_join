package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.entity.Userdetails;

public interface userDetailsRepos extends JpaRepository<Userdetails, String> {

}
