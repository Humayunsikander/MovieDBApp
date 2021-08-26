import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCountries(

    @SerializedName("iso_3166_1") val iso_3166_1: String,
    @SerializedName("name") val name: String
) : Parcelable