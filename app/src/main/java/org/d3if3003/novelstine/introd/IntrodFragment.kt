package org.d3if3003.novelstine.introd

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import org.d3if3003.novelstine.R
import org.d3if3003.novelstine.db.User
import org.d3if3003.novelstine.db.UserDb

class IntrodFragment : Fragment() {
    companion object {
        var NAMA = "nama"
    }
    private lateinit var binding: FragmentIntrodBinding
    private val viewModel: IntrodViewModel by lazy {
        val db = UserDb.getInstance(requireContext())
        val factory = IntrodViewModelFactory(db.dao)
        ViewModelProvider(this, factory)[IntrodViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentIntrodBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.button.setOnClickListener {
            var nama = binding.namaInput.text.toString()
            //binding.textView.text = "Namae wa, " + nama + " desu"
            viewModel.getNama(nama)
        }
        binding.next.setOnClickListener {
            findNavController().navigate(
                R.id.action_introdFragment_to_listFragment)
        }
        binding.shareButton.setOnClickListener { shareData() }


        viewModel.getHasilUser().observe(viewLifecycleOwner) { showResult(it) }

    }

    private fun showResult(result: User?) {
        if (result == null) return
        binding.textView.text = "Namae wa, " + result.nama + " desu"
        binding.buttonGroup.visibility = View.VISIBLE
    }

    private fun shareData() {
        var message = binding.namaInput.text.toString()
        binding.textView.text = "Namae wa, " + message + " desu"

        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain").putExtra(Intent.EXTRA_TEXT, message)
        if (shareIntent.resolveActivity(
                requireActivity().packageManager) != null) {
            startActivity(shareIntent)
        } }




}