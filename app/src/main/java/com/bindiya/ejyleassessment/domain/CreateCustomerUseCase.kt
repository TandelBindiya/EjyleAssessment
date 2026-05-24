package com.bindiya.ejyleassessment.domain

import com.bindiya.ejyleassessment.core.CustomerDetails
import com.bindiya.ejyleassessment.core.Resource
import javax.inject.Inject

class CreateCustomerUseCase @Inject constructor(private val ICustomerRepository: ICustomerRepository) {

    suspend operator fun invoke(data: CustomerDetails): Resource<CustomerDetails> {
        return ICustomerRepository.registerCustomer(data)
    }

}