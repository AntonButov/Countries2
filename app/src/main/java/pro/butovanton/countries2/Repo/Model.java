package pro.butovanton.countries2.Repo;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

@Entity(tableName = "ctable")
public class Model {
    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "name")
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("capital")
    @Expose
    public String capital;
    @SerializedName("flag")
    @Expose
    public String flag;

    public String flagPatch;

    @SerializedName("currencies")
    @Expose
    @TypeConverters(CurrencieConvertor.class)
    public List<Currencie> currencies;

    public static class Currencie {
    @SerializedName("name")
    @Expose
    public String name;
    }

    public Model(@NonNull String name, String capital, List<Currencie> currencies, String flag, String flagPatch) {
        this.name = name;
        this.capital = capital;
        this.currencies = currencies;
        this.flag = flag;
        this.flagPatch = flagPatch;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getcapital() {
        return capital;
    }

    public void setcapital(String capital) { this.capital = capital; }

    public String getflag() {
        return flag;
    }

    public void setFlagPatch(String flagPatch) {
        this.flagPatch = flagPatch;
    }

    public void setflag(String flag) {
        this.flag = flag;
    }

    public List<String> getcurriencies () {
        List<String> liststring = new ArrayList<>();
        for (Currencie currencie :  currencies)
            liststring.add(currencie.name);
    return liststring;
    }

    public static class CurrencieConvertor {
        @TypeConverter
        public String fromCurrencie(List<Model.Currencie> currencie) {
            return currencieToStr(currencie.get(0));
        }

        String currencieToStr(Currencie currencie){
            return currencie.name;
        }

        @TypeConverter
        public List<Currencie> toCurrencie(String data) {
            List<Model.Currencie> currencies = new ArrayList<>();
            currencies.add(strToCurrencie(data));
            return currencies;
        }

        Currencie strToCurrencie(String data) {
            Model.Currencie currencie = new Model.Currencie();
            currencie.name = data;
        return currencie;
        }
    }

}


