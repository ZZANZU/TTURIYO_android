package io.github.tturiyo.tturiyo_android.data.repo

import com.google.firebase.database.*
import io.github.tturiyo.base.debug.Log
import io.github.tturiyo.base.debug.assertDebug
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject

object ProductRepo {
    private const val PRODUCTS = "products"
    private const val USERS = "users"

    private val productsRef = FirebaseDatabase.getInstance().getReference(PRODUCTS)
    private val usersRef = FirebaseDatabase.getInstance().getReference(USERS)

    fun insert(item: Product, onSuccess: () -> Unit = {}) {
        Log.d()

        val productPush: DatabaseReference = productsRef.push()
        item.id = productPush.key
        productsRef.child(item.id).setValue(item).addOnSuccessListener {
            val currUserProductsRef = usersRef.child(item.uid).child(PRODUCTS)
            val relationPush = currUserProductsRef.push()
            currUserProductsRef.child(relationPush.key).setValue(productPush.key).addOnSuccessListener {
                onSuccess()
            }
        }
    }

    fun update(item: Product, onSuccess: () -> Unit = {}) {
        Log.d()

        productsRef.child(item.id).setValue(item).addOnSuccessListener {
            onSuccess()
        }
    }

    fun delete(item: Product, onSuccess: () -> Unit = {}) {
        val userProductsRef = usersRef.child(item.uid).child(PRODUCTS)
        userProductsRef.orderByValue().equalTo(item.id).addListenerForSingleValueEvent(object : ValueEventListener {
            override fun onCancelled(p0: DatabaseError?) {

            }

            override fun onDataChange(p0: DataSnapshot) {
                Log.d()

                assertDebug(p0.childrenCount > 0)

                val userProductItem = p0.children.iterator().next()
                userProductsRef.child(userProductItem.key).removeValue().addOnSuccessListener {
                    productsRef.child(item.id).removeValue().addOnSuccessListener {
                        onSuccess()
                    }
                }
            }
        })
    }

    fun getListAsObservable(): Observable<List<Product>> {
        val notifier: BehaviorSubject<List<Product>> = BehaviorSubject.create()

        productsRef.addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {

                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val itemList = mutableListOf<Product>()

                        for (child in snapshot.children) {
                            val value: Product = child.getValue(Product::class.java)!!
                            itemList.add(value)
                        }

                        notifier.onNext(itemList)
                    }
                }
        )

        return notifier
    }

    fun getListWithUidAsObservable(uid: String): Observable<List<Product>> {
        val notifier: BehaviorSubject<List<Product>> = BehaviorSubject.create()

        usersRef.child(uid).child(PRODUCTS).addValueEventListener(
                object : ValueEventListener {
                    override fun onCancelled(p0: DatabaseError?) {
                    }

                    override fun onDataChange(snapshot: DataSnapshot) {
                        val itemList = mutableListOf<Product>()

                        for (child in snapshot.children) {
                            val productId: String = child.getValue(String::class.java)!!
                            productsRef.child(productId).addListenerForSingleValueEvent(
                                    object : ValueEventListener {
                                        override fun onCancelled(p0: DatabaseError?) {
                                        }

                                        override fun onDataChange(p0: DataSnapshot) {
                                            itemList.add(p0.getValue(Product::class.java)!!)
                                            if (itemList.size >= snapshot.childrenCount) {
                                                notifier.onNext(itemList)
                                            }
                                        }
                                    }
                            )
                        }
                    }
                }
        )

        return notifier
    }
}
