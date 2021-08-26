import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class ProductionCompanies(
    @SerializedName("id") val id: Int,
    @SerializedName("logo_path") val logo_path: String,
    @SerializedName("name") val name: String,
    @SerializedName("origin_country") val origin_country: String
) : Parcelable