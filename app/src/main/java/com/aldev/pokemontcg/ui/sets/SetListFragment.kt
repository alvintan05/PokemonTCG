package com.aldev.pokemontcg.ui.sets

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.aldev.pokemontcg.data.PokemonSetRepository
import com.aldev.pokemontcg.data.source.remote.response.Set
import com.aldev.pokemontcg.databinding.FragmentSetListBinding
import com.aldev.pokemontcg.utils.MainViewState
import com.aldev.pokemontcg.utils.SetListViewModelFactory

class SetListFragment : Fragment() {

    private lateinit var vm: SetListViewModel
    private lateinit var adapter: SetListAdapter

    private var _binding: FragmentSetListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSetListBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = SetListAdapter()
        binding.rvSet.adapter = adapter

        val factory = SetListViewModelFactory(PokemonSetRepository.instance)
        vm = ViewModelProvider(this, factory)[SetListViewModel::class.java]

        vm.viewState.observe(viewLifecycleOwner, Observer(this@SetListFragment::handleState))

        binding.swipeRefreshSet.setOnRefreshListener { vm.getSets() }

    }

    private fun handleState(viewState: MainViewState?) {
        viewState?.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<Set>) {
        adapter.updateData(data)
        binding.tvSetError.visibility = View.GONE
        binding.rvSet.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        binding.tvSetError.text = error.message
        binding.tvSetError.visibility = View.VISIBLE
        binding.rvSet.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        binding.swipeRefreshSet.isRefreshing = loading
    }

}