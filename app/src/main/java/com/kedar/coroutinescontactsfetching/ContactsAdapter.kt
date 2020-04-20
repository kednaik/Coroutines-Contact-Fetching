package com.kedar.coroutinescontactsfetching

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.row_contact.view.*
import kotlinx.android.synthetic.main.row_contact_data.view.*

class ContactsAdapter(context: Context) : RecyclerView.Adapter<ContactsAdapter.MyViewHolder>() {
    val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    var contacts = ArrayList<Contact>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(layoutInflater.inflate(R.layout.row_contact, parent, false))
    }

    override fun getItemCount() = contacts.size

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val contact = contacts[position]
        with(holder.itemView) {
            tvContactName.text = contact.name
            llContactDetails.removeAllViews()
            contact.numbers.forEach {
                val detail = layoutInflater.inflate(R.layout.row_contact_data,llContactDetails,false)
                detail.imgIcon.setImageResource(R.drawable.ic_local_phone_black_24dp)
                detail.tvContactData.text = it
                llContactDetails.addView(detail)
            }
            contact.emails.forEach {
                val detail = layoutInflater.inflate(R.layout.row_contact_data,llContactDetails,false)
                detail.imgIcon.setImageResource(R.drawable.ic_mail_black_24dp)
                detail.tvContactData.text = it
                llContactDetails.addView(detail)
            }
        }
    }

    inner class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}