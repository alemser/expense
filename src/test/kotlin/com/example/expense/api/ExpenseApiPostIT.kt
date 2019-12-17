package com.example.expense.api

import com.example.expense.ExpenseApplication
import com.example.expense.service.data.ExpenseData
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotlintest.specs.StringSpec
import io.restassured.RestAssured
import io.restassured.RestAssured.baseURI
import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import org.apache.http.HttpHeaders.ETAG
import org.apache.http.HttpHeaders.LOCATION
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import java.math.BigDecimal

@SpringBootTest(classes = [ExpenseApplication::class], webEnvironment = RANDOM_PORT)
class ExpenseApiPostIT (@LocalServerPort port: String,
						@Autowired mapper: ObjectMapper) : StringSpec() {

	init {
		RestAssured.port = port.toInt()
		RestAssured.baseURI = "http://localhost"

		"should create a new expenses" {
			val newExpense = ExpenseData(BigDecimal(50.25), "Travel")
			val jsonBody = mapper.writeValueAsString(newExpense)

			given()
					.basePath("/api")
					.contentType(JSON)
					.body(jsonBody)
			.`when`()
					.post("/expenses")
			.then()
					.statusCode(201)
					.header(ETAG, "\"0\"")
					.header(LOCATION, "$baseURI:$port/api/expenses/1")
		}
	}
}
