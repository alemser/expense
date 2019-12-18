package com.example.expense.model

import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.persistence.*
import javax.persistence.Version

typealias ExpenseId = Long
typealias EntityVersion = Long

@Entity(name = "expense")
data class ExpenseEntity (
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: ExpenseId? = null,
        val amount: BigDecimal,
        val date: OffsetDateTime,
        val category: String,
        @Version
        val version: EntityVersion = 0
)