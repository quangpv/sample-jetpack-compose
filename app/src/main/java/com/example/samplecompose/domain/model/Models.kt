package com.example.samplecompose.domain.model

enum class Screen {
    Login,
    Home;
}

enum class Scopes : IChars {
    Login,
    App,
    Home;

    override fun toString(): String {
        return name
    }
}

interface ILabel : CharSequence


interface IHome {
    val title: CharSequence
    val favorites: CharSequence
}

interface ILoginAgent {
    val name: CharSequence
}

interface ILogin {
    val loadingState: HasIsLoading
    val email: CharSequence
    val password: CharSequence
}

interface HasIsLoading {
    val isLoading: Boolean
}

interface IChars : CharSequence {
    override val length: Int
        get() = toString().length

    override fun get(index: Int): Char {
        return toString()[index]
    }

    override fun subSequence(startIndex: Int, endIndex: Int): CharSequence {
        return toString().subSequence(startIndex, endIndex)
    }
}

open class Chars(private val value: String = "") : IChars {
    override fun toString(): String {
        return value
    }
}
