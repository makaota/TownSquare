package com.makaota.townsquare.di

import com.makaota.townsquare.domain.usecases.form_validation.ValidateEmail
import com.makaota.townsquare.domain.usecases.form_validation.ValidateName
import com.makaota.townsquare.domain.usecases.form_validation.ValidatePassword
import com.makaota.townsquare.domain.usecases.form_validation.ValidatePhoneNumber
import com.makaota.townsquare.domain.usecases.form_validation.ValidateSurname
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object ValidationModule {

    @Provides
    fun provideValidateName(): ValidateName = ValidateName()

    @Provides
    fun provideValidateSurname(): ValidateSurname = ValidateSurname()

    @Provides
    fun provideValidateEmail(): ValidateEmail = ValidateEmail()

    @Provides
    fun provideValidatePassword(): ValidatePassword = ValidatePassword()

    @Provides
    fun provideValidatePhoneNumber(): ValidatePhoneNumber = ValidatePhoneNumber()
}