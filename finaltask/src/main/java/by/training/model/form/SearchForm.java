package by.training.model.form;

import java.util.ArrayList;
import java.util.List;

public class SearchForm {
    private String query;
    private List<Integer> genres;
    private List<Integer> country;
    private List<Integer> studio;

    public SearchForm(String query, String[] genres, String[] country, String[] studio) {
        this.query = query;
        this.genres = convert(genres);
        this.country = convert(country);
        this.studio = convert(studio);
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

    public List<Integer> getStudio() {
        return studio;
    }

    public void setStudio(List<Integer> studio) {
        this.studio = studio;
    }
}
