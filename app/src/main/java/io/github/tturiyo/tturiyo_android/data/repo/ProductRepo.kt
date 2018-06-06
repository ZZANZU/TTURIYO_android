package io.github.tturiyo.tturiyo_android.data.repo

import com.google.firebase.database.*
import io.github.tturiyo.tturiyo_android.data.domain.Location
import io.github.tturiyo.tturiyo_android.data.domain.Product
import io.reactivex.Observable
import io.reactivex.subjects.BehaviorSubject
import java.util.*

object ProductRepo {
    const val PRODUCTS = "products"
    const val USERS = "users"

    val productsRef = FirebaseDatabase.getInstance().getReference(PRODUCTS)
    val usersRef = FirebaseDatabase.getInstance().getReference(USERS)

    fun insert(item: Product, onSuccess: () -> Unit = {}) {
        val productPush: DatabaseReference = productsRef.push()
        productsRef.child(productPush.key).setValue(item).addOnSuccessListener {
            val currUserProductsRef = usersRef.child(item.uid).child(PRODUCTS)
            val relationPush = currUserProductsRef.push()
            currUserProductsRef.child(relationPush.key).setValue(productPush.key).addOnSuccessListener {
                onSuccess()
            }
        }
    }

    fun getListAsObservable(): Observable<List<Product>> {
        val notifier: BehaviorSubject<List<Product>> = BehaviorSubject.create()
        val dummy = mutableListOf<Product>()
        dummy.apply {
            add(Product(
                    uid="id_1",
                    companyName = "양이 컴퍼니",
                    productName = "추어탕",
                    productPriceBefore = 12000,
                    productPriceAfter = 9000,
                    numberOfStock = 4,
                    location = Location(127.030442, 37.586441)
            ))

            add(Product(
                    uid="id_2",
                    companyName = "떡뽀이",
                    productName = "돈가스 떡볶이",
                    productPriceBefore = 6000,
                    productPriceAfter = 3000,
                    numberOfStock = 2,
                    location = Location(127.030442, 37.586441)
            ))
            add(Product(
                    uid="id_3",
                    companyName = "한솥 도시락",
                    productName = "참치마요",
                    productPriceBefore = 4500,
                    productPriceAfter = 2000,
                    numberOfStock = 4,
                    location = Location(127.030442, 37.586441)
            ))
        }
        notifier.onNext(dummy)

        val dummy2 = mutableListOf<Product>()
        dummy2.apply {
            add(Product(
                    uid="id_1",
                    companyName = "양이 컴퍼니",
                    productName = "추어탕",
                    productPriceBefore = 12000,
                    productPriceAfter = 9000,
                    numberOfStock = 4,
                    location = Location(127.030442, 37.586441)
            ))

            add(Product(
                    uid="id_2",
                    companyName = "떡뽀이",
                    productName = "돈가스 떡볶이",
                    productPriceBefore = 6000,
                    productPriceAfter = 3000,
                    numberOfStock = 2,
                    location = Location(127.030442, 37.586441)
            ))
        }
        notifier.onNext(dummy2)

        Observable.just(dummy, dummy2)

//        productsRef.addValueEventListener(
//                object : ValueEventListener {
//                    override fun onCancelled(p0: DatabaseError?) {
//
//                    }
//
//                    override fun onDataChange(snapshot: DataSnapshot) {
//                        val itemList = mutableListOf<Product>()
//
//                        for (child in snapshot.children) {
//                            val value: Product = child.getValue(Product::class.java)!!
//                            itemList.add(value)
//                        }
//
//                        notifier.onNext(itemList)
//                    }
//                }
//        )

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
