package com.poke.common.component

interface RecyclerAdapterComponent<T>{
    fun replaceItems(items: List<T>?)
}