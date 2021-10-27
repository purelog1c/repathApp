package com.company.repathapp.model

class UserRegistrationModel(private var name: String?, private var surname: String?, private var username: String?, private var password: String?, private var address: String?) {

    fun getName(): String? {
        return name
    }

    fun getSurname(): String?{
        return surname
    }

    fun getUsername():String? {
        return username
    }

    fun getPassword():String?{
        return password
    }

    fun getAddress():String?{
        return address
    }






}