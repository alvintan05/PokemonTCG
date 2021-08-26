package com.aldev.pokemontcg.ui.cards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.aldev.pokemontcg.data.PokemonCardRepository
import com.aldev.pokemontcg.data.source.remote.response.Card
import com.aldev.pokemontcg.databinding.FragmentPokemonCardListBinding
import com.aldev.pokemontcg.utils.CardListViewModelFactory
import com.aldev.pokemontcg.utils.PokemonCardListViewState

class PokemonCardListFragment : Fragment() {

    private var _binding: FragmentPokemonCardListBinding? = null
    private val binding get() = _binding!!

    private lateinit var vm: PokemonListViewModel
    private lateinit var adapter: PokemonCardAdapter

    private val args: PokemonCardListFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentPokemonCardListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PokemonCardAdapter()
        binding.rvCard.adapter = adapter

        val factory = CardListViewModelFactory(PokemonCardRepository.instance)
        vm = ViewModelProvider(this, factory)[PokemonListViewModel::class.java]

        vm.apply {
            viewStateCard.observe(
                viewLifecycleOwner,
                Observer(this@PokemonCardListFragment::handleState)
            )

            if (viewStateCard.value?.data == null) getPokemons(args.setId)
            binding.swipeRefreshCard.setOnRefreshListener { getPokemons(args.setId) }
        }
    }

    private fun handleState(viewState: PokemonCardListViewState?) {
        viewState?.let {
            toggleLoading(it.loading)
            it.data?.let { data -> showData(data) }
            it.error?.let { error -> showError(error) }
        }
    }

    private fun showData(data: MutableList<Card>) {
        adapter.updateData(data)
        binding.tvCardError.visibility = View.GONE
        binding.rvCard.visibility = View.VISIBLE
    }

    private fun showError(error: Exception) {
        binding.tvCardError.text = error.message
        binding.tvCardError.visibility = View.VISIBLE
        binding.rvCard.visibility = View.GONE
    }

    private fun toggleLoading(loading: Boolean) {
        binding.swipeRefreshCard.isRefreshing = loading
    }

}