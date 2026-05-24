package com.bindiya.ejyleassessment.data.remote

import com.bindiya.ejyleassessment.core.CustomerDetails
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST


interface CustomerService {

   @POST("customers")
  suspend fun registerCustomer(@Body data: CustomerDetails): Response<CustomerDetails>
}