package io.github.tturiyo.tturiyo_android.data.repo

import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.reactivex.Observable

object ProductRepo {
    const val PRODUCTS = "products"

    val ref = FirebaseDatabase.getInstance().getReference(PRODUCTS)

    fun insert(item: Product, onSuccess: () -> Unit = {}) {
        val push: DatabaseReference = ref.push()
        ref.child(push.key).setValue(item).addOnSuccessListener {
            onSuccess()
        }
    }

    fun getListAsObservable(): Observable<List<Product>> {
        return Observable.empty()
    }
}
