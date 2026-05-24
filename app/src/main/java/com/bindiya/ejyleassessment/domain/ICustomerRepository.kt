package com.bindiya.ejyleassessment.domain

import com.bindiya.ejyleassessment.core.CustomerDetails
import com.bindiya.ejyleassessment.core.Resource

interface ICustomerRepository {
    suspend fun registerCustomer(details: CustomerDetails): Resource<CustomerDetails>
}