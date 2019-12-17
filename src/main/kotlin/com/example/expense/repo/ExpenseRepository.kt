package com.example.expense.repo

import com.example.expense.model.ExpenseEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ExpenseRepository : JpaRepository<ExpenseEntity, Long>