package com.bindiya.ejyleassessment.data

import com.bindiya.ejyleassessment.core.Resource
import com.bindiya.ejyleassessment.core.CustomerDetails
import com.bindiya.ejyleassessment.data.remote.CustomerService
import com.bindiya.ejyleassessment.domain.ICustomerRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CustomerRepository @Inject constructor(private val customerService: CustomerService) :
    ICustomerRepository {

    override suspend fun registerCustomer(details: CustomerDetails): Resource<CustomerDetails> {
        return withContext(Dispatchers.IO) {
            try {
                val result = customerService.registerCustomer(details)
                if (result.code()==201) {
                    result.body()?.let {
                        Resource.Success(it)
                    } ?: kotlin.run {
                        Resource.Error("No data found")
                    }
                } else {
                    result.errorBody()?.let {
                        Resource.Error(it.toString())
                    } ?: kotlin.run {
                        Resource.Error("Something went wrong")
                    }
                }
            } catch (e: Exception) {
                Resource.Error(e.localizedMessage ?: "An unexpected error occurred")
            }
        }
    }
}