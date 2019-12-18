package com.example.expense.api

import com.example.expense.ExpenseApplication
import com.example.expense.service.data.ExpenseData
import com.fasterxml.jackson.databind.ObjectMapper
import io.kotlintest.data.forall
import io.kotlintest.specs.StringSpec
import io.kotlintest.tables.row
import io.restassured.RestAssured
import io.restassured.RestAssured.baseURI
import io.restassured.RestAssured.given
import io.restassured.http.ContentType.JSON
import org.apache.http.HttpHeaders.ETAG
import org.apache.http.HttpHeaders.LOCATION
import org.hamcrest.Matchers
import org.hamcrest.Matchers.containsString
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT
import org.springframework.boot.web.server.LocalServerPort
import java.math.BigDecimal
import java.time.OffsetDateTime.now

@SpringBootTest(classes = [ExpenseApplication::class], webEnvironment = RANDOM_PORT)
class ExpenseApiPostIT (@LocalServerPort port: String,
						@Autowired mapper: ObjectMapper) : StringSpec() {

	init {
		RestAssured.port = port.toInt()
		RestAssured.baseURI = "http://localhost"

		"should create a new expenses" {
			val newExpense = ExpenseData(amount = BigDecimal(30.15), category = "Travel", date = now().plusSeconds(2))
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

		"should not create with invalid values" {
			forall(
					row(invalidExpenseAmount, 400, "must be greater than 0"),
					row(invalidExpenseDate, 400, "must be a date in the present or in the future")
			) { payload, expectedStatus, expectedMsg ->

				val jsonBody = mapper.writeValueAsString(payload)

				given()
						.basePath("/api")
						.contentType(JSON)
						.body(jsonBody)
						.`when`()
						.post("/expenses")
						.then()
						.statusCode(expectedStatus)
						.body(containsString(expectedMsg))
			}
		}
	}
}

val invalidExpenseAmount = ExpenseData(amount = BigDecimal(0), category = "Travel", date = now().plusSeconds(2))
val invalidExpenseDate = ExpenseData(amount = BigDecimal(1), category = "Travel", date = now().minusDays(1))

