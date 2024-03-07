package com.brainycorp.tourism.seller.adapter.out.jdbc

import com.brainycorp.tourism.seller.application.port.out.UpdateSellerRepository
import com.brainycorp.tourism.seller.domain.Seller
import com.brainycorp.tourism.shared.FileReader.Companion.getSql
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.stereotype.Component

@Component
class UpdateSellerMySqlAdapter(
    val jdbcTemplate: JdbcTemplate
): UpdateSellerRepository {
    val UPDATE_SELLER: String = getSql("updateSeller")
    override fun execute(seller: Seller, sellerId: String) {

        jdbcTemplate.update(
            UPDATE_SELLER,
            seller.name,
            seller.lastname,
            seller.dni,
            seller.birthday,
            seller.nationality,
            seller.cellPhone,
            seller.email,
            seller.salary,
            seller.charge,
            sellerId
        )
    }


}