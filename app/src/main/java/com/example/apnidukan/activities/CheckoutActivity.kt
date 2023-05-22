package com.example.apnidukan.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import com.example.apnidukan.R
import com.example.apnidukan.data.order.Order
import com.example.apnidukan.data.order.OrderStatus
import com.example.apnidukan.fragments.shopping.HomeFragment
import com.example.apnidukan.viewmodel.OrderViewModel
import com.razorpay.Checkout
import com.razorpay.PaymentResultListener
import org.json.JSONObject


class CheckoutActivity : AppCompatActivity() ,PaymentResultListener{
//    private val orderViewModel by viewModels<OrderViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_checkout)


        val price = intent.getFloatExtra("totalCost", 0F)

        val checkout = Checkout()
        checkout.setKeyID("rzp_test_LXH2RNCEkkYrB0")

        try {
            val options = JSONObject()
            options.put("name", "Razorpay Corp")
            options.put("description", "Demoing Charges")
            //You can omit the image option to fetch the image from the dashboard
            options.put("image", "https://s3.amazonaws.com/rzp-mobile/images/rzp.jpg")
            options.put("theme.color", "#3399cc")
            options.put("currency", "INR")
            options.put("amount", price *100) //pass amount in currency subunits
            options.put("email", "sonu9098839901@gmail.com")
            options.put("contact", "8085761394")
            checkout.open(this,options)
        } catch (e: Exception) {
            Toast.makeText(this, "Error in payment: " + e.message, Toast.LENGTH_LONG).show()
            e.printStackTrace()
        }

    }

    override fun onPaymentSuccess(p0: String?) {
//        val order = intent.getParcelableExtra("order")
        Toast.makeText(this, "Payment Success", Toast.LENGTH_SHORT).show()
//        orderViewModel.placeOrder(order)
        val intent = Intent(this, ShoppingActivity::class.java)
        startActivity(intent)
    }


    override fun onPaymentError(p0: Int, p1: String?) {
        Toast.makeText(this, "Payment Error", Toast.LENGTH_SHORT).show()
        val intent = Intent(this, ShoppingActivity::class.java)
        startActivity(intent)
    }
}