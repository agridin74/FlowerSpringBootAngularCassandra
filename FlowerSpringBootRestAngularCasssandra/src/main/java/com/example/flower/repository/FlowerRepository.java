package com.example.flower.repository;

import java.util.List;
import java.util.UUID;
import org.springframework.data.cassandra.repository.CassandraRepository;
import com.example.flower.entity.Flower;

public interface FlowerRepository extends CassandraRepository<Flower, UUID> {
	List<Flower> findByPrice(double price);

}
