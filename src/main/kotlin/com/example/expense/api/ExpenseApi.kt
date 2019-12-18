package com.example.expense.api

import com.example.expense.service.data.ExpenseData
import com.example.expense.service.ExpenseService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.ResponseEntity
import org.springframework.http.ResponseEntity.status
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.support.ServletUriComponentsBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class ExpenseApi {

    @Autowired
    lateinit var expenseService: ExpenseService

    @PostMapping(value = ["/expenses"], consumes = ["application/json"])
    fun create(@Valid @RequestBody expense: ExpenseData): ResponseEntity<Void> {

        val (newId, currentVersion) = expenseService.create(expense)

        return status(CREATED)
                .eTag(currentVersion.toString())
                .location(buildLocation("/$newId"))
                .build()
    }

    private fun buildLocation(path: String) = ServletUriComponentsBuilder
            .fromCurrentRequest()
            .path(path)
            .buildAndExpand()
            .toUri()
}