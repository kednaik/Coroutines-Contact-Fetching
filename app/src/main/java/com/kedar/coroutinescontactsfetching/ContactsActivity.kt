package com.kedar.coroutinescontactsfetching

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.activity_contacts.*


class ContactsActivity : AppCompatActivity() {
    private val contactsViewModel by viewModels<ContactsViewModel>()
    private val CONTACTS_READ_REQ_CODE = 100

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_contacts)
        init()
    }

    private fun init() {
        tvDefault.text = "Fetching contacts!!!"
        val adapter = ContactsAdapter(this)
        rvContacts.adapter = adapter
        contactsViewModel.contactsLiveData.observe(this, Observer {
            tvDefault.visibility = View.GONE
            adapter.contacts = it
        })
        if (hasPermission(Manifest.permission.READ_CONTACTS)) {
            contactsViewModel.fetchContacts()
        } else {
            requestPermissionWithRationale(Manifest.permission.READ_CONTACTS, CONTACTS_READ_REQ_CODE, getString(R.string.contact_permission_rationale))
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == CONTACTS_READ_REQ_CODE && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            contactsViewModel.fetchContacts()
        }
    }
}
