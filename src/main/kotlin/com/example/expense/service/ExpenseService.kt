package com.example.expense.service

import com.example.expense.service.data.ExpenseData
import com.example.expense.model.ExpenseEntity
import com.example.expense.model.ExpenseId
import com.example.expense.model.EntityVersion
import com.example.expense.repo.ExpenseRepository
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class ExpenseService {

    val logger = LoggerFactory.getLogger(ExpenseService::class.java)

    @Autowired
    lateinit var expenseRepository: ExpenseRepository

    fun create(expenseData: ExpenseData): Pair<ExpenseId, EntityVersion> {
        logger.debug("About to create a new expense {}", expenseData)

        val entity = ExpenseEntity(category = expenseData.category, amount = expenseData.amount, date = expenseData.date)

        val createdEntity = expenseRepository.save(entity)

        logger.debug("New expense created with id {} and version {}.", createdEntity.id, createdEntity.version)

        return createdEntity.id!! to createdEntity.version
    }
}