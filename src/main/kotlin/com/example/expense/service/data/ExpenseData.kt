package com.example.expense.service.data

import java.math.BigDecimal
import java.time.OffsetDateTime
import javax.validation.constraints.FutureOrPresent
import javax.validation.constraints.Positive

data class ExpenseData(
        @get:Positive
        val amount: BigDecimal,

        @get:FutureOrPresent
        val date: OffsetDateTime,

        val category: String)
