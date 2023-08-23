package com.ml.hotel

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.autoconfigure.domain.EntityScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories

@SpringBootApplication
@EntityScan(basePackages = ["com.ml.hotel.model"])
@EnableJpaRepositories(basePackages = ["com.ml.hotel.repository"])
class HotelApplication

fun main(args: Array<String>) {
	runApplication<HotelApplication>(*args)
}
