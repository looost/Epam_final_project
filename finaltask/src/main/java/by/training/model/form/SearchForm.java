package by.training.model.form;

import java.util.ArrayList;
import java.util.List;

public class SearchForm {
    private String query;
    private List<Integer> genres;
    private List<Integer> country;

    public SearchForm(String query, String[] genres, String[] country) {
        this.query = query;
        this.genres = convert(genres);
        this.country = convert(country);
    }

    private List<Integer> convert(String[] arr) {
        if (arr == null) {
            return null;
        } else {
            List<Integer> res = new ArrayList<>();
            for (String arg : arr
            ) {
                res.add(Integer.parseInt(arg));
            }
            return res;
        }
    }

    public String getQuery() {
        return query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public List<Integer> getGenres() {
        return genres;
    }

    public void setGenres(List<Integer> genres) {
        this.genres = genres;
    }

    public List<Integer> getCountry() {
        return country;
    }

    public void setCountry(List<Integer> country) {
        this.country = country;
    }
}
