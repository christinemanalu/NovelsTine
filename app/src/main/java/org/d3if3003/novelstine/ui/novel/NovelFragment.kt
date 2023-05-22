package org.d3if3003.novelstine.ui.novel

import android.os.Bundle
import android.view.*
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch
import org.d3if3003.novelstine.R
import org.d3if3003.novelstine.data.SettingDataStore
import org.d3if3003.novelstine.data.dataStore
import org.d3if3003.novelstine.databinding.FragmentNovelBinding

class NovelFragment : Fragment() {
    private val isLinearLayoutManager = true
    private val layoutDataStore: SettingDataStore by lazy {
        SettingDataStore(requireContext().dataStore)
    }

    private val viewModel: NovelViewModel by lazy {
        ViewModelProvider(this)[NovelViewModel::class.java]
    }
    private lateinit var myAdapter: NovelAdapter
    private lateinit var binding: FragmentNovelBinding
    private var isLinearLayout = true
    companion object {
        const val EXTRA_MESSAGE = "com.example.myapp.MESSAGE"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentNovelBinding.inflate(layoutInflater, container, false)
        myAdapter = NovelAdapter()
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            adapter = myAdapter
            setHasFixedSize(true)
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getData().observe(viewLifecycleOwner) {
            myAdapter.updateData(it)
        }
        //val messageTextView = findViewById<TextView>(R.id.messageTextView)
        //val message = context.getStringExtra(EXTRA_MESSAGE)
        //messageTextView.text = message
        with(binding.recyclerView) {
            addItemDecoration(DividerItemDecoration(context, RecyclerView.VERTICAL))
            //adapter = MainAdapter(getData())
            setHasFixedSize(true)
        }
        layoutDataStore.preferenceFlow.asLiveData().observe(viewLifecycleOwner) {
            isLinearLayout = it
            setLayout()
            activity?.invalidateOptionsMenu()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.options_menu, menu)
        inflater.inflate(R.menu.menu_main, menu)
        val menuItem = menu.findItem(R.id.action_switch_layout)
        setIcon(menuItem)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_switch_layout) {
            lifecycleScope.launch {
                layoutDataStore.saveLayout(!isLinearLayout, requireContext())
            }
            return true
        }
        when(item.itemId) {
            R.id.menu_histori -> {
                findNavController().navigate(
                    R.id.action_novelFragment_to_historiFragment
                )
                return true
            }
            R.id.menu_about -> {
                findNavController().navigate(
                    R.id.action_novelFragment_to_aboutFragment
                )
                return true
            }
        }
        return super.onOptionsItemSelected(item) }

    private fun setLayout() {
        (if (isLinearLayout)
            LinearLayoutManager(context)
        else
            GridLayoutManager(context, 2)).also { binding.recyclerView.layoutManager = it }
    }

    private fun setIcon(menuItem: MenuItem) {
        val iconId = if (isLinearLayout)
            R.drawable.baseline_grid_view_24
        else
            R.drawable.baseline_view_list_24
        menuItem.icon = ContextCompat.getDrawable(requireContext(), iconId)
    }
}