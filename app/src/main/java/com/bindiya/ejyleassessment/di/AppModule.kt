package com.bindiya.ejyleassessment.di

import com.bindiya.ejyleassessment.BuildConfig
import com.bindiya.ejyleassessment.data.remote.CustomerService
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton
import com.bindiya.ejyleassessment.data.CustomerRepository
import com.bindiya.ejyleassessment.domain.CreateCustomerUseCase
import com.bindiya.ejyleassessment.domain.ICustomerRepository

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideCustomerService(): CustomerService {
        return Retrofit.Builder().baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create()).build()
            .create(CustomerService::class.java)
    }

    @Provides
    @Singleton
    fun provideCustomerRepository(customerService: CustomerService): ICustomerRepository {
        return CustomerRepository(customerService)
    }

    @Provides
    fun provideCreateCustomerUseCase(ICustomerRepository: ICustomerRepository): CreateCustomerUseCase {
        return CreateCustomerUseCase(ICustomerRepository)
    }

}