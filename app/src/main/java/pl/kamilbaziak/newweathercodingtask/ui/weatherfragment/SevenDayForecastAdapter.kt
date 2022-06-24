package pl.kamilbaziak.newweathercodingtask.ui.weatherfragment

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import pl.kamilbaziak.newweathercodingtask.R
import pl.kamilbaziak.newweathercodingtask.databinding.RowSevenDaysForecastBinding
import pl.kamilbaziak.newweathercodingtask.sevendaysdata.Daily
import pl.kamilbaziak.newweathercodingtask.utils.Constants
import pl.kamilbaziak.newweathercodingtask.utils.Constants.getImageUrl
import java.util.*

class SevenDayForecastAdapter: ListAdapter<Daily, SevenDayForecastAdapter.MainFragmentViewHolder>(DiffCallback())
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainFragmentViewHolder
    {
        val binding = RowSevenDaysForecastBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainFragmentViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainFragmentViewHolder, position: Int)
    {
        val currentItem = getItem(position)
        holder.bind(currentItem)
    }

    inner class MainFragmentViewHolder(private val binding: RowSevenDaysForecastBinding): RecyclerView.ViewHolder(binding.root)
    {
        fun bind(daily: Daily)
        {
            binding.apply {
                root.context.apply {

                    textDate.text = formatUpdatedAtTime(daily.dt)

                    textWeatherState.text = daily.weather[0].main

                    Glide.with(this)
                        .load(getImageUrl(daily.weather[0].icon))
                        .into(imageWeatherState)

                    textMorningTemp.text = getString(
                        R.string.morning_temp,
                        daily.temp.morn.toString()
                    )

                    textDayTemp.text = getString(
                        R.string.day_temp,
                        daily.temp.day.toString()
                    )

                    textNightTemp.text = getString(
                        R.string.night_temp,
                        daily.temp.night.toString()
                    )

                    textHumidity.text = getString(
                        R.string.humidity,
                        daily.humidity.toString() + "%"
                    )
                }
            }
        }

        private fun formatUpdatedAtTime(value: Long) =
            Constants.dateFormat.format(Date(value*1000))
    }

    class DiffCallback: DiffUtil.ItemCallback<Daily>()
    {
        override fun areItemsTheSame(oldItem: Daily, newItem: Daily) =
            oldItem.sunrise == newItem.sunrise

        override fun areContentsTheSame(oldItem: Daily, newItem: Daily): Boolean =
            oldItem == newItem
    }
}